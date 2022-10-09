import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

import javax.imageio.ImageIO;

public class PowerUp extends GameObject {

	// Image member variables

		public static Image image;
		public static boolean needImage = true;
		public static boolean gotImage = false;

	// member variables

	int type;

	
	// powerup constructor

	PowerUp(double x, double y, int width, int height, int speed, double size, boolean active, int type) {
		super(x, y, width, height, speed, size, active);
		this.type = type;
		if (needImage) {
			if (type == 0) {
			loadImage("1425568833219.png");
			} else if (type == 1) {
			loadImage("1425569023892.png");
			}
		}
	}
	// update method

	public void update() {
	}
	// draw method

	void draw(Graphics g) {
		if (type == 0) {
			if (gotImage) {
				g.drawImage(image, (int) x, (int) y, width, height, null);
			} else {
				g.setColor(Color.BLACK);
				g.fillRect((int) x, (int) y, width, height);
			}
		}
		if (type == 1) {
			if (gotImage) {
				g.drawImage(image, (int) x, (int) y, width, height, null);
			} else {
				g.setColor(Color.GREEN);
				g.fillRect((int) x, (int) y, width, height);
			}
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
