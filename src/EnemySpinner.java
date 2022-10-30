import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;

public class EnemySpinner extends GameObject {

	// Image member variables

	public static Image image;
	public static boolean needImage = true;
	public static boolean gotImage = false;

	// member variables
	double angle = 0;
	double width;
	double height;
	int mode;
	double vx;
	double vy;
	int age = 0;

	// constructor

	EnemySpinner(double x, double y, int width, int height, int speed, double size, boolean active) {
		super(x, y, width, height, 10, 10, active);
		this.width = width;
		this.height = height;
		mode = 0;
		double dx = x - AvoidTheBullets.WIDTH / 2;
		double dy = y - AvoidTheBullets.HEIGHT / 2;
		vx = dx / 60;
		vy = dy / 60;
		if (needImage) {
			loadImage("madsun.png");
		}
	}

	// draw method

	void draw(Graphics g) {
		if (gotImage) {
			g.drawImage(image, (int) x, (int) y, (int) width, (int) height, null);
		} else {
			g.setColor(Color.BLUE);
			g.fillRect((int) x, (int) y, (int) width, (int) height);
		}

	}
	// update method

	void update() {
		age += 1;
		if (mode == 0) {
			if (age >= 20) {
				mode = 1;
				double dx = x - AvoidTheBullets.WIDTH / 2;
				double dy = y - AvoidTheBullets.HEIGHT / 2;
				double d = Math.sqrt(Math.pow(dx, 2) + Math.pow(dy, 2));
				;
				double l = Math.asin(dx / d);
				double h = 180 - l;
				double b = 360 - h;
				angle = Math.toRadians(b);
			}
			x -= vx;
			y -= vy;
		}
		if (mode == 1) {
			x = AvoidTheBullets.WIDTH / 2 + 320 * (Math.sin(angle));
			y = AvoidTheBullets.HEIGHT / 2 + 320 * (Math.cos(angle));
			angle += 0.05;
			if (age >= 270) {
				mode = 2;
				double dx = x - AvoidTheBullets.WIDTH / 2;
				double dy = y - AvoidTheBullets.HEIGHT / 2;
				vx = dx / 60;
				vy = dy / 60;
			}
		}
		if (mode == 2) {
			x -= vx;
			y -= vy;
			width += 0.8;
			height += 0.8;
			if (age >= 600) {
				active = false;
			}
		}
	}

	void loadImage(String imageFile) {
		if (needImage) {
			try {
				image = new ImageIcon("src/" + imageFile).getImage();
				gotImage = true;
			} catch (Exception e) {
				e.printStackTrace();
			}
			needImage = false;
		}
	}

}
