import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

public class SpaceShip extends GameObject {

	// Image member variables

	public static BufferedImage image;
	public static boolean needImage = true;
	public static boolean gotImage = false;

	// game variables
	boolean up;
	boolean down;
	boolean left;
	boolean right;
	int speed = 5;
	// ship constructor

	SpaceShip(int x, int y, int width, int height, int speed, boolean active) {
		super(x, y, width, height, speed, width/2, true);
		if (needImage) {
			loadImage("glowCircle.png");
		}
	}

	// draw method

	void draw(Graphics g) {
		if (gotImage) {
			g.drawImage(image, (int) (x - width/2), (int) (y - height/2), width, height, null);
		} else {
			g.setColor(Color.WHITE);
			g.fillRect((int) x, (int) y, width, height);
		}
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

	void loadImage(String imageFile) {
		if (needImage) {
			try {
				image = ImageIO.read(this.getClass().getResourceAsStream(imageFile));
				gotImage = true;
			} catch (Exception e) {

			}
			needImage = false;
		}
	}
}
