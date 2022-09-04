import java.awt.Color;
import java.awt.Graphics;

public class SpaceShip extends GameObject {
	// 
// game variables
	boolean up;
	boolean down;
	boolean left;
	boolean right;
	int speed = 5;
	// ship constructor

	SpaceShip(int x, int y, int width, int height, int speed) {
		super(x, y, width, height, speed);
		
	}

	// draw method

	void draw(Graphics g) {
		g.setColor(Color.ORANGE);
		g.fillRect(x, y, width, height);
	}

	// movement methods
	public void UP(boolean up) {
		this.up = up;
	}

	public void DOWN(boolean down) {
		this.down = down;
	}

	public void LEFT(boolean left) {
		this.left = left;
	}

	public void RIGHT(boolean right) {
		this.right = right;
	}

	void update() {
		super.update();
		if (up == true) {
			y -= speed;
		}
		if (down == true) {
			y += speed;
		}
		if (left == true) {
			x -= speed;
		}
		if (right == true) {
			x += speed;
		}
	}
}