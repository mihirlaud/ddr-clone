import java.util.Scanner;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.ArrayList;
import java.net.URL;
import javafx.scene.media.AudioClip;
import java.util.Iterator;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.text.*;
import javafx.scene.paint.Color;
import javafx.scene.image.Image;

public class Song implements Iterable<Note> {

	private Scanner sc;
	private ArrayList<Note> notes;
	private AudioClip music;
	private int bpm;
	private String name;
	private String artist;

	private int score;
	private double multiplier;
	private int combo;
	private int notesHit;

	private String songName;

	public Song() {	}

	public void init(String songName) {
		this.songName = songName;

		notes = new ArrayList<Note>();

		URL resource = getClass().getResource("songs\\" + songName + "\\" + songName + ".mp3");
		music = new AudioClip(resource.toString());

		Path file = Paths.get("songs\\" + songName + "\\" + songName + ".txt");
		try {
			sc = new Scanner(file);
		} catch(Exception e) {
			System.out.println("Could not find file.");
		}

		name = sc.nextLine();
		artist = sc.nextLine();
		bpm = Integer.parseInt(sc.nextLine());

		while(sc.hasNext()) {
			String noteData = sc.nextLine();
			if(noteData.equals("/"))
				break;
			if(noteData.charAt(0) == 'H')
				notes.add(new Hold(Integer.parseInt(noteData.substring(1, 2)),
								   60 * 60.0 / bpm * Double.parseDouble(noteData.substring(3, noteData.indexOf(";"))),
								   240 * 60.0 / bpm * Double.parseDouble(noteData.substring(noteData.indexOf(";") + 1))));
			else
				notes.add(new Note(Integer.parseInt(noteData.substring(0, 1)), 60.0 * 60.0 / bpm * Double.parseDouble(noteData.substring(2))));
		}

		score = 0;
		multiplier = 1.0;
		combo = 0;
		notesHit = 0;
	}

	public String getSongName() {
		music.stop();
		return this.songName;
	}

	public boolean isMK() {
		if(songName.equals("MORTAL_KOMBAT")) {
			return true;
		}
		return false;
	}

	public void displaySongInfo(GraphicsContext gc, int timer) {
		Font font = Font.font("Roboto", FontWeight.NORMAL, 18);
		gc.setFont(font);
		gc.setFill(Color.WHITE);
		gc.fillText("Song: " + name, 610, 20);
		gc.fillText("Artist: " + artist, 610, 50);
		gc.fillText("Score: " + score, 610, 100);
		gc.fillText("Multiplier: " + multiplier, 610, 130);
		gc.fillText("Combo: " + combo, 610, 160);
		gc.fillText("Notes Hit: " + notesHit + " / " + notes.size(), 610, 190);
		gc.setFill(Color.BLACK);
	}

	public Iterator<Note> iterator() {
		return notes.iterator();
	}

	public ArrayList<Note> getNotes() {
		return this.notes;
	}

	public AudioClip getMusic() {
		return music;
	}

	public void incrementScore() {
		score += 10 * multiplier;
	}

	public void incrementCombo() {
		combo++;
		notesHit++;
		scoreMultiplier();
	}

	public void resetCombo() {
		combo = 0;
		scoreMultiplier();
	}

	public void scoreMultiplier() {
		multiplier = (combo + 10) / 10;
		if(multiplier >= 5.0)
			multiplier = 5.0;
	}

	public int getScore() {
		return this.score;
	}

}