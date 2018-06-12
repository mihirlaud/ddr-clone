import javafx.scene.input.*;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.text.*;
import javafx.scene.paint.Color;

public class Instructions extends Screen {

	private GraphicsContext gc;

	public Instructions(GraphicsContext gc) {

		this.gc  = gc;

	}

	public void animationHandle(long now) {

		drawOptions(gc);
		gc.setFill(Color.PURPLE);
		Font font1 = Font.font("Roboto", FontWeight.NORMAL, 70);
		Font font2 = Font.font("Roboto", FontWeight.NORMAL, 40);
		gc.setFont(font1);
		gc.fillText("INSTRUCTIONS", 275, 150);
		gc.setFont(font2);
		gc.setFill(Color.BLACK);
		gc.fillText("Use the 1 - 5 keys to hit the notes \nas they appear onscreen.\n\t1 -> Green\n\t2 -> Red\n\t3 -> Blue\n\t4 -> Orange\n\t5 -> Yellow", 200, 250);

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