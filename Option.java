import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.*;

public class Option {

	private int x;
	private int y;
	private int w;
	private int h;
	private Color color;
	private Screen nextScreen;
	private String text;

	public Option(int x, int y, int w, int h, Color color, Screen nextScreen, String text) {
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
		this.color = color;
		this.nextScreen = nextScreen;
		this.text = text;
	}

	public void draw(GraphicsContext gc) {
		gc.setFill(color);
		gc.fillRect(x, y, w, h);
		gc.setFill(Color.WHITE);
		Font font = Font.font("Roboto", FontWeight.NORMAL, 24);
		gc.setFont(font);
		gc.fillText(text, x + w / 2 - 14 * text.length() / 2, y + h / 2 + 10);
		gc.setFill(Color.BLACK);
	}

	public boolean isSelected(double mouseX, double mouseY) {
		if(mouseX >= x && mouseX <= x + w && mouseY >= y && mouseY <= y + h)
			return true;
		return false;
	}

	public Screen click(GraphicsContext gc) {
		gc.clearRect(0, 0, 1000, 750);
		return nextScreen;
	}

	public String getText() {
		return this.text;
	}

}