import javafx.scene.input.*;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.text.*;
import javafx.scene.paint.Color;

public class StartScreen extends Screen {

	private GraphicsContext gc;

	public StartScreen(GraphicsContext gc) {
		this.gc = gc;
	}

	public void animationHandle(long now) {
		gc.setFill(Color.PURPLE);
		Font font = Font.font("Roboto", FontWeight.NORMAL, 70);
		gc.setFont(font);
		gc.fillText("SCHMANCE", 325, 150);
		gc.fillText("SCHMANCE", 325, 210);
		gc.fillText("SCHMEVOLUTION!", 225, 270);
		drawOptions(gc);
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