import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class EnemyBullet extends GameObject {

	// member variables

	double angle = 0;
	double vx;
	double vy;

	// constructor method

	EnemyBullet(double x, double y, int width, int height, int speed) {
		super(x, y, width, height, speed, 12, true);
		double dx = x - ObjectManager.ship.x;
		double dy = y - ObjectManager.ship.y;
		vx = dx;
		vy = dy;
	}

	// draw and update methods

	void draw(Graphics g) {
		Graphics2D g2d = (Graphics2D) g.create(0, 0, AvoidTheBullets.WIDTH, AvoidTheBullets.HEIGHT);
		g2d.translate(x, y);
		g2d.rotate(Math.toRadians(angle));
		g2d.setColor(Color.YELLOW);
		g2d.fillRect(-width / 2, -height / 2, width, height);
		g2d.rotate(-Math.toRadians(angle));
	}

	void update(double shipX, double shipY) {
		x -= vx / 45;
		y -= vy / 45;

	}
}
