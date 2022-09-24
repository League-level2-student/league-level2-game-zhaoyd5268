import java.awt.Color;
import java.awt.Graphics;

public class PowerUp extends GameObject {

	// member variables

	boolean whichpower;

	// powerup constructor

	PowerUp(double x, double y, int width, int height, int speed, double size, boolean active, boolean whichpower) {
		super(x, y, width, height, speed, size, active);
		this.whichpower = whichpower;
	}
	// update method
	
	public void update() {
		
	}
	// draw method

	void draw(Graphics g) {
		g.setColor(Color.black);
		g.fillRect((int) x, (int) y, 30, 30);
	}
}
