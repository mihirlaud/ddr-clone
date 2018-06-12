import javafx.scene.image.Image;
import javafx.scene.canvas.GraphicsContext;

public class Hold extends Note {

	private double length;
	private boolean held;
	private boolean targetable = true;
	private Image tail;

	public Hold(int fret, double beat, double length) {
		super(fret, beat);
		this.length = length;
		switch(fret) {
			case 0: setImage(new Image("art\\green_circle.png"));
					tail = new Image("art\\green_square.png", 10, this.length, false, false);
					break;
			case 1: setImage(new Image("art\\red_circle.png"));
					tail = new Image("art\\red_square.png", 10, this.length, false, false);
					break;
			case 2: setImage(new Image("art\\blue_circle.png"));
					tail = new Image("art\\blue_square.png", 10, this.length, false, false);
					break;
			case 3: setImage(new Image("art\\orange_circle.png"));
					tail = new Image("art\\orange_square.png", 10, this.length, false, false);
					break;
			case 4: setImage(new Image("art\\yellow_circle.png"));
					tail = new Image("art\\yellow_square.png", 10, this.length, false, false);
					break;
		}
	}

	public void draw(GraphicsContext gc) {
		super.draw(gc);
		gc.drawImage(tail, getX() -  tail.getWidth() / 2, getY() - tail.getHeight() + 40);
	}

	public void step() {
		if(!held)
			super.step();
		else {
			int newHeight = (int)tail.getHeight() - 8;
			switch(getFret()) {
				case 0: tail = new Image("art\\green_square.png", 10, newHeight, false, false);
						break;
				case 1: tail = new Image("art\\red_square.png", 10, newHeight, false, false);
						break;
				case 2: tail = new Image("art\\blue_square.png", 10, newHeight, false, false);
						break;
				case 3: tail = new Image("art\\orange_square.png", 10, newHeight, false, false);
						break;
				case 4: tail = new Image("art\\yellow_square.png", 10, newHeight, false, false);
			}
			if(newHeight <= 0) {
				tail = new Image("art\\gray_square.png", 1, 1, false, false);
				release();
			}
		}
	}

	public void press(GraphicsContext gc) {
		setScored(true);
		if(targetable) {
			held = true;
			switch(getFret()) {
				case 0: setImage(new Image("art\\held_green.png"));
						break;
				case 1: setImage(new Image("art\\held_red.png"));
						break;
				case 2: setImage(new Image("art\\held_blue.png"));
						break;
				case 3: setImage(new Image("art\\held_orange.png"));
						break;
				case 4: setImage(new Image("art\\held_yellow.png"));
						break;
			}
			draw(gc);
		}
	}

	public void release() {
		held = false;
		targetable = false;
		setImage(new Image("art\\gray_circle.png"));
	}

	public boolean isHeld() {
		return this.held;
	}

}