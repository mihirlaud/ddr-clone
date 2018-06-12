import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.*;
import javafx.scene.media.AudioClip;
import java.net.URL;
import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.event.*;
import javafx.scene.input.*;
import javafx.scene.text.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main extends Application implements EventHandler<InputEvent> {

	final int SCREEN_WIDTH = 1000;
	final int SCREEN_HEIGHT = 750;
	int timer = 0;

	Group root;
	Canvas canvas;
	GraphicsContext gc;
	AnimateObjects animate;

	Song song = new Song();

	StartScreen startScreen;
	ExitScreen exit;
	Instructions instructions;
	SongSelect songSelect;
	PlayScreen playScreen;
	EndScreen endScreen;

	ArrayList<Screen> screens;
	Stage s;

	Scanner sc;

	boolean done = false;

	public static void main(String[]args) {

		launch();

	}

	public void start(Stage stage) {

		stage.setTitle("Schmance Schmance Schmevolution");
		s = stage;

		root = new Group();
		canvas = new Canvas(SCREEN_WIDTH, SCREEN_HEIGHT);
		root.getChildren().add(canvas);

		Scene scene = new Scene(root);
		stage.setScene(scene);

		gc = canvas.getGraphicsContext2D();

		animate = new AnimateObjects();
		animate.start();

		scene.addEventHandler(KeyEvent.KEY_PRESSED,this);
		scene.addEventHandler(KeyEvent.KEY_RELEASED,this);
		scene.addEventHandler(MouseEvent.MOUSE_PRESSED, this);

		stage.show();

		startScreen = new StartScreen(gc);
		exit = new ExitScreen();
		instructions = new Instructions(gc);
		songSelect = new SongSelect(gc, song);
		endScreen = new EndScreen(song, gc);
		playScreen = new PlayScreen(song, timer, gc, endScreen);

		ArrayList<Option> startOptions = new ArrayList<>();
		startOptions.add(new Option(SCREEN_WIDTH / 2 - 100, SCREEN_HEIGHT / 2 - 25, 200, 50, Color.PURPLE, instructions, "PLAY GAME"));
		startOptions.add(new Option(SCREEN_WIDTH / 2 - 100, SCREEN_HEIGHT / 2 + 50, 200, 50, Color.RED, exit, "EXIT"));
		startScreen.setOptions(startOptions);

		ArrayList<Option> instructOptions = new ArrayList<>();
		instructOptions.add(new Option(SCREEN_WIDTH / 2 - 100, SCREEN_HEIGHT * 13 / 16, 200, 50, Color.PURPLE, songSelect, "GOT IT!"));
		instructions.setOptions(instructOptions);

		Path file = Paths.get("directory.txt");

		try {
			sc = new Scanner(file);
		} catch(Exception e) {
			System.out.println("Could not find file.");
		}

		ArrayList<Option> selectOptions = new ArrayList<>();

		int count = 0;
		while(sc.hasNext()) {
			selectOptions.add(new Option(SCREEN_WIDTH / 2 - 100, SCREEN_HEIGHT / 2 + 25 + 75 * count, 200, 50, Color.PURPLE, playScreen, sc.nextLine()));
			count++;
		}

		songSelect.setOptions(selectOptions);

		ArrayList<Option> playOptions = new ArrayList<>();
		playOptions.add(new Option(SCREEN_WIDTH / 2 + 200, SCREEN_HEIGHT / 2 - 25, 200, 50, Color.RED, playScreen, "RESTART"));
		playOptions.add(new Option(SCREEN_WIDTH / 2 + 200, SCREEN_HEIGHT / 2 + 50, 200, 50, Color.RED, exit, "EXIT"));
		playScreen.setOptions(playOptions);

		ArrayList<Option> endOptions = new ArrayList<>();
		endOptions.add(new Option(SCREEN_WIDTH / 2 - 100, SCREEN_HEIGHT / 2 - 25, 200, 50, Color.PURPLE, songSelect, "PLAY AGAIN?"));
		endOptions.add(new Option(SCREEN_WIDTH / 2 - 100, SCREEN_HEIGHT / 2 + 50, 200, 50, Color.RED, exit, "EXIT"));
		endScreen.setOptions(endOptions);

		startScreen.setActive(true);

		screens = new ArrayList<Screen>();

		screens.add(startScreen);
		screens.add(instructions);
		screens.add(songSelect);
		screens.add(playScreen);
		screens.add(endScreen);
		screens.add(exit);

	}

	public void handle(final InputEvent event) {
		for(Screen s : screens) {
			if(s.isActive())
				done = s.inputHandle(event);
		}

		if(done) {
			s.close();
		}

	}

	public class AnimateObjects extends AnimationTimer {

		public void handle(long now) {
			for(Screen s : screens) {
				if(s.isActive())
					s.animationHandle(now);
			}
		}

	}




}