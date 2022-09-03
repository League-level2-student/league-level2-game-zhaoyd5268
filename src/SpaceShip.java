import java.awt.Color;
import java.awt.Graphics;

public class SpaceShip extends GameObject {
	// movement variables
	boolean up;
	boolean down;
	boolean left;
	boolean right;
	// ship constructor
	
	SpaceShip(int x, int y, int width, int height) {
		super(x, y, width, height);
		
	}

	// draw method
	
	void draw(Graphics g) {
		   g.setColor(Color.ORANGE);
	       g.fillRect(x, y, width, height);
	}
	
	//	movement methods 
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
}
