import java.awt.Color;
import java.awt.Graphics;

public class PowerUp extends GameObject {

	// member variables

	int type;

	
	// powerup constructor

	PowerUp(double x, double y, int width, int height, int speed, double size, boolean active, int type) {
		super(x, y, width, height, speed, size, active);
		this.type = type;
	}
	// update method

	public void update() {
	}
	// draw method

	void draw(Graphics g) {
		if (type == 0) {
			g.setColor(Color.BLACK);
			g.fillRect((int) x, (int) y, 30, 30);
		}
		if (type == 1) {
			g.setColor(Color.GREEN);
			g.fillRect((int) x, (int) y, 30, 30);
		}
	}
}
