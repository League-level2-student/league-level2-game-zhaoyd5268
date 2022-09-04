import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;

public class ObjectManager {

	// member variables

	SpaceShip SpaceShip;
	ArrayList<EnemyBullet> bullets = new ArrayList<EnemyBullet>();
	Random generator = new Random();
	SpaceShip ship = new SpaceShip(400, 550, 50, 50, 5);	
	// constructor

	ObjectManager() {
		
	}

	// spawner method
	void spawn(Graphics g) {
		int spawnlocation = generator.nextInt(3);
		int spawnareaX =  generator.nextInt(750);
		int spawnareaY = generator.nextInt(750);
		if (spawnlocation == 0) {
			EnemyBullet bullet = new EnemyBullet(0, spawnareaY, 20, 20, 15);
			bullets.add(bullet);
		} else if (spawnlocation == 1) {
			EnemyBullet bullet = new EnemyBullet(spawnareaX, 0, 20, 20, 15);
			bullets.add(bullet);
		} else if (spawnlocation == 2) {
			EnemyBullet bullet = new EnemyBullet(AvoidTheBullets.WIDTH, spawnareaY, 20, 20, 15);
			bullets.add(bullet);
		} else if (spawnlocation == 3) {
			EnemyBullet bullet = new EnemyBullet(spawnareaX, AvoidTheBullets.HEIGHT, 20, 20, 15);
			bullets.add(bullet);
		}
	}

	public void draw(Graphics g) {

		
	}

	public void update() {
		
	}
}
