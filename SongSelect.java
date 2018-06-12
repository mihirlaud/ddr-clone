import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.*;
import javafx.scene.paint.Color;
import javafx.scene.text.*;

public class SongSelect extends Screen {

	private GraphicsContext gc;
	private Song song;

	public SongSelect(GraphicsContext gc, Song song) {

		this.gc = gc;
		this.song = song;

	}

	public void animationHandle(long now) {
		gc.setFill(Color.PURPLE);
		Font font = Font.font("Roboto", FontWeight.NORMAL, 70);
		gc.setFont(font);
		gc.fillText("  SONG  ", 360, 180);
		gc.fillText(" SELECT ", 360, 280);
		drawOptions(gc);
	}

	public boolean inputHandle(final InputEvent event) {
		if(event instanceof MouseEvent) {
			double mx = ((MouseEvent)event).getX();
			double my = ((MouseEvent)event).getY();
			for(Option o : getOptions()) {
				if(o.isSelected(mx, my)) {
					switch(o.getText()) {
						case "Mii Channel": song.init("MII_CHANNEL");
											break;
						case "Deja Vu": song.init("DEJA_VU");
											break;
						case "Mortal Kombat": song.init("MORTAL_KOMBAT");
											break;
					}
					proceedTo(o.click(gc));
				}
			}
		}

		return false;
	}

}