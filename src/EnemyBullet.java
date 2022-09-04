import java.awt.Color;
import java.awt.Graphics;

public class EnemyBullet extends GameObject {

	// member variables
	
	
	
	EnemyBullet(int x, int y, int width, int height, int speed) {
		super(x, y, width, height, speed);
	}
	
	// draw and update methods
	
	
	void draw(Graphics g) {
		 g.setColor(Color.YELLOW);
	     g.fillRect(x, y, width, height);
	     
	}
	
	
	void update()  {
		
	}
}
