import javafx.scene.image.Image;
import javafx.scene.canvas.GraphicsContext;
import javafx.geometry.Rectangle2D;
import javafx.scene.paint.Color;

public class Note {

	private int fret;
	private double beat;
	private Image image;
	private boolean onScreen = false;
	private int x, y;
	private boolean pressable;
	private boolean scored = false;
	private boolean missed = false;

	public Note(int fret, double beat) {
		this.fret = fret;
		switch(this.fret) {
			case 0: image = new Image("art\\green_square.png");
					x = 100;
					break;
			case 1: image = new Image("art\\red_square.png");
					x = 200;
					break;
			case 2: image = new Image("art\\blue_square.png");
					x = 300;
					break;
			case 3: image = new Image("art\\orange_square.png");
					x = 400;
					break;
			case 4: image = new Image("art\\yellow_square.png");
					x = 500;
					break;
		}
		y = (int)-image.getHeight();
		this.beat = beat;
	}

	public void draw(GraphicsContext gc) {
		gc.drawImage(image, x -  image.getWidth() / 2, y);
		onScreen = true;
	}

	public void press(GraphicsContext gc) {
		scored = true;
		gc.setFill(Color.WHITE);
		gc.fillRect(x - image.getWidth() / 2, y, image.getWidth(), image.getHeight());
		gc.setFill(Color.BLACK);
		image = new Image("art\\gray_square.png");
		gc.drawImage(image, x - image.getWidth() / 2, y);

	}

	public void step() {
		y += 10;
	}

	public double getBeat() {
		return this.beat;
	}

	public boolean isOnScreen() {
		return this.onScreen;
	}

	public Rectangle2D getHitbox() {
		return new Rectangle2D(x - image.getWidth() / 2, y, image.getWidth(), image.getHeight());
	}

	public void setPressable(boolean pressable) {
		this.pressable = pressable;
	}

	public boolean getPressable() {
		return this.pressable;
	}

	public int getFret() {
		return this.fret;
	}

	public void setImage(Image image) {
		this.image = image;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public boolean getScored() {
		return scored;
	}

	public void setScored(boolean scored) {
		this.scored = scored;
	}

	public void setMissed(boolean missed) {
		this.missed = missed;
	}

	public boolean getMissed() {
		return missed;
	}

}