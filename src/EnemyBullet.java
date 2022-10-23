
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class EnemyBullet extends GameObject {

	// Image member variables

	public static Image image;
	public static boolean needImage = true;
	public static boolean gotImage = false;

	// member variables

	double angle = 0;
	double vx;
	double vy;

	// constructor method

	EnemyBullet(double x, double y, int width, int height, int speed) {
		super(x, y, width, height, speed, 20, true);
		double dx = x - ObjectManager.ship.x;
		double dy = y - ObjectManager.ship.y;
		vx = dx/35;
		vy = dy/35;
		
		angle = Math.toDegrees(Math.atan(vy/vx)+ Math.toRadians(-90));
		if (vx<0) {
			angle = angle + 180;
		}
		if (needImage) {
			loadImage("bullet.png");
		}
	}

	// draw and update methods

	void draw(Graphics g) {
		Graphics2D g2d = (Graphics2D) 
		g.create(0, 0, AvoidTheBullets.WIDTH, AvoidTheBullets.HEIGHT);
		g2d.translate(x, y);
		g2d.rotate(Math.toRadians(angle));
		if (gotImage) {
			g2d.drawImage(image,(int)(-width / 2), (int)(-height / 2), width, height, null);
		} else {
			g2d.setColor(Color.WHITE);
			g2d.fillRect((int)(-width / 2),(int)(-height / 2), width, height);
		}
		g2d.rotate(-Math.toRadians(angle));
	}

	void update(double shipX, double shipY) {
		x -= vx;
		y -= vy;

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
