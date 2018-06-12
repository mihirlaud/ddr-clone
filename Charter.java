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

public class Charter extends Application implements EventHandler<InputEvent> {

	final int SCREEN_WIDTH = 1000;
	final int SCREEN_HEIGHT = 750;
	int timer = 0;

	Group root;
	Canvas canvas;
	GraphicsContext gc;
	AnimateObjects animate;

	ChartSong song;

	public static void main(String[]args) {

		launch();

	}

	public void start(Stage stage) {

		stage.setTitle("Guitar Hero");

		root = new Group();
		canvas = new Canvas(SCREEN_WIDTH, SCREEN_HEIGHT);
		root.getChildren().add(canvas);

		Scene scene = new Scene(root);
		stage.setScene(scene);

		gc = canvas.getGraphicsContext2D();

		animate = new AnimateObjects();
		animate.start();

		scene.addEventHandler(KeyEvent.KEY_PRESSED,this);
		song = new ChartSong("MORTAL_KOMBAT");

		stage.show();
	}

	public void handle(final InputEvent event) {
		if(event instanceof KeyEvent) {
			KeyCode kc = ((KeyEvent)event).getCode();
			song.printBeat(timer);
		}
	}

	public class AnimateObjects extends AnimationTimer {

		public void handle(long now) {
			if(timer == 55) {
				AudioClip clip = song.getMusic();
				clip.play();
			}

			gc.clearRect(0, 0, SCREEN_WIDTH / 2 + 100, SCREEN_HEIGHT);
			Rectangle2D fret = new Rectangle2D(0, SCREEN_HEIGHT * 7 / 8 - 40, SCREEN_WIDTH / 2 + 100, 40);
			gc.fillRect(0, SCREEN_HEIGHT * 7 / 8 - 40, SCREEN_WIDTH / 2 + 100, 40);
			gc.setFill(Color.PURPLE);
			gc.fillRect(SCREEN_WIDTH / 2 + 100, 0, SCREEN_WIDTH / 2, SCREEN_HEIGHT);
			gc.setFill(Color.BLACK);

			timer++;
		}

	}




}