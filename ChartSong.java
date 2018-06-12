import java.util.Scanner;
import java.util.ArrayList;
import javafx.scene.media.AudioClip;
import java.io.PrintWriter;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ChartSong {

	private Scanner sc;
	private AudioClip music;
	private int bpm;

	public ChartSong(String songName) {

		URL resource = getClass().getResource("songs\\" + songName + "\\" + songName + ".mp3");
		music = new AudioClip(resource.toString());

		Path file = Paths.get("songs\\" + songName + "\\" + songName + ".txt");
		try {
			sc = new Scanner(file);
		} catch(Exception e) {
			System.out.println("Could not find file.");
		}

		sc.nextLine();
		sc.nextLine();
		bpm = Integer.parseInt(sc.nextLine());

	}

	public AudioClip getMusic() {
		return this.music;
	}

	public void printBeat(int timer) {
		System.out.println(((double)(timer - 55) * bpm / 3600.0 - 1));
	}

}