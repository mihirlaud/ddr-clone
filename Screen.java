import java.util.ArrayList;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.*;

public abstract class Screen{

	private ArrayList<Option> options;
	private boolean active;

	public Screen() {}

	public void setOptions(ArrayList<Option> options) {
		this.options = options;
	}

	public ArrayList<Option> getOptions() {
		return options;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public void drawOptions(GraphicsContext gc) {
		for(Option o : options)
			o.draw(gc);
	}

	public void proceedTo(Screen nextScreen) {
		active = false;
		nextScreen.setActive(true);
	}

	public abstract void animationHandle(long now);

	public abstract boolean inputHandle(final InputEvent event);

}