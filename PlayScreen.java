import java.util.ArrayList;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.media.AudioClip;
import javafx.geometry.Rectangle2D;
import javafx.scene.input.*;

public class PlayScreen extends Screen {

	private Song song;
	private int timer;
	private GraphicsContext gc;
	private EndScreen endScreen;
	private final int SCREEN_WIDTH = 1000;
	private final int SCREEN_HEIGHT = 750;

	public PlayScreen(Song song, int timer, GraphicsContext gc, EndScreen endScreen) {
		super();
		this.song = song;
		this.timer = timer;
		this.gc = gc;
		this.endScreen = endScreen;
	}

	public void animationHandle(long now) {
		if(song.isMK()) {
			if(timer == 70) {
				AudioClip clip = song.getMusic();
				clip.play();
			}
		} else {
			if(timer == 50) {
				AudioClip clip = song.getMusic();
				clip.play();
			}
		}

		gc.clearRect(0, 0, SCREEN_WIDTH / 2 + 100, SCREEN_HEIGHT);
		Rectangle2D fret = new Rectangle2D(0, SCREEN_HEIGHT * 7 / 8 - 40, SCREEN_WIDTH / 2 + 100, 80);
		gc.fillRect(0, SCREEN_HEIGHT * 7 / 8 - 40, SCREEN_WIDTH / 2 + 100, 80);
		gc.setFill(Color.PURPLE);
		gc.fillRect(SCREEN_WIDTH / 2 + 100, 0, SCREEN_WIDTH / 2, SCREEN_HEIGHT);
		gc.setFill(Color.BLACK);
		song.displaySongInfo(gc, timer);
		drawOptions(gc);

		for(Note note : song) {
			if(Math.abs(note.getBeat() - timer) <= 1)
				note.draw(gc);
			if(note.isOnScreen()) {
				note.step();
				note.draw(gc);
			}
			if(note.getHitbox().intersects(fret) && note.isOnScreen()) {
				note.setPressable(true);
			} else
				note.setPressable(false);
			if(note.getY() >= SCREEN_WIDTH * 7 / 8 + 1 && !note.getScored() && !note.getMissed()) {
				song.resetCombo();
				note.setMissed(true);
			}
		}

		timer++;

		if(timer - 300 > song.getNotes().get(song.getNotes().size() - 1).getBeat()) {
			timer = 0;
			song.getSongName();
			gc.clearRect(0, 0, 1000, 750);
			proceedTo(endScreen);
		}

	}

	public boolean inputHandle(final InputEvent event) {
		if(event instanceof KeyEvent) {
			KeyCode kc = ((KeyEvent)event).getCode();
			int fret = -1;
			switch(kc) {
				case DIGIT1: fret = 0;
						 	 break;
				case DIGIT2: fret = 1;
						 	 break;
				case DIGIT3: fret = 2;
						 	 break;
				case DIGIT4: fret = 3;
						 	 break;
				case DIGIT5: fret = 4;
							 break;
				case SPACE:  fret = 4;
							 break;
			}

			for(Note note : song) {
				if(note.getPressable() && note.getFret() == fret) {
					if(event.getEventType().toString().equals("KEY_PRESSED")) {
						if(!note.getScored())
							song.incrementCombo();
						note.press(gc);
						song.incrementScore();
					}
					else if(note instanceof Hold && event.getEventType().toString().equals("KEY_RELEASED"))
						((Hold)note).release();
					break;
				}
			}
		}

		if(event instanceof MouseEvent) {
			double mx = ((MouseEvent)event).getX();
			double my = ((MouseEvent)event).getY();
			for(Option o : getOptions()) {
				if(o.isSelected(mx, my)) {
					switch(o.getText()) {
						case "RESTART": song.init(song.getSongName());
										timer = 0;
										break;
					}
					proceedTo(o.click(gc));
				}
			}
		}

		return false;
	}

}