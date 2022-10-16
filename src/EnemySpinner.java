import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class EnemySpinner extends GameObject {

	// member variables
	double angle = 0;
	int mode;

	// constructor
	
	EnemySpinner(double x, double y, int width, int height, int speed, double size, boolean active) {
		super(x, y, width, height, 10, 10, active);
		x = Math.sin(angle);
		y = Math.cos(angle);
	
	}

	// draw method 
	
	void draw(Graphics g) {
		g.setColor(Color.BLUE);
		g.fillRect((int) x, (int) y, (int) width, (int) height);
	}
	
	
}
