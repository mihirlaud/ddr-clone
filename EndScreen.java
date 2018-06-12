import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.*;
import javafx.scene.paint.Color;
import javafx.scene.text.*;

public class EndScreen extends Screen {

	private Song song;
	private GraphicsContext gc;

	public EndScreen(Song song, GraphicsContext gc) {

		this.song = song;
		this.gc = gc;

	}

	public void animationHandle(long now) {
		drawOptions(gc);
		gc.setFill(Color.PURPLE);
		Font font1 = Font.font("Roboto", FontWeight.NORMAL, 70);
		Font font2 = Font.font("Roboto", FontWeight.NORMAL, 40);
		gc.setFont(font1);
		gc.fillText("SONG COMPLETE!", 250, 150);
		gc.setFont(font2);
		gc.setFill(Color.BLACK);
		gc.fillText("Final Score: " + song.getScore() + "\nGreat Job!", 275, 250);
	}

	public boolean inputHandle(final InputEvent event) {
		if(event instanceof MouseEvent) {
			double mx = ((MouseEvent)event).getX();
			double my = ((MouseEvent)event).getY();
			for(Option o : getOptions()) {
				if(o.isSelected(mx, my)) {
					proceedTo(o.click(gc));
				}
			}
		}

		return false;
	}

}