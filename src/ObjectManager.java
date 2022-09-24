import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

public class ObjectManager implements ActionListener {

	// member variables

	ArrayList<EnemyBullet> bullets = new ArrayList<EnemyBullet>();
	ArrayList<PowerUp> powerups = new ArrayList<PowerUp>();
	Random generator = new Random();
	int score = 0;
	public static SpaceShip ship = new SpaceShip(400, 550, 50, 50, 5, true);

	// constructor

	ObjectManager() {

	}

	// spawner method
	public void spawn() {
		int spawnlocation = generator.nextInt(3);
		int spawnareaX = generator.nextInt(750);
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
			EnemyBullet bullet = new EnemyBullet(spawnareaX, AvoidTheBullets.HEIGHT - 15, 20, 20, 15);
			bullets.add(bullet);
		}
	}

	public void spawnpowerups() {
		int spawnareaX = generator.nextInt(750);
		int spawnareaY = generator.nextInt(750);
		int whichpowerup = generator.nextInt(2);
		boolean userpowerup = false;
		if (whichpowerup == 0) {
			userpowerup = false;
		}
		if (whichpowerup == 1) {
			userpowerup = true;
		}
		PowerUp powerup = new PowerUp(spawnareaX, spawnareaY, 30, 30, 0, 10, true, userpowerup);
		powerups.add(powerup);
	}

	public void draw(Graphics g) {
		for (int i = 0; i < bullets.size(); i++) {
			bullets.get(i).draw(g);
		}
		ship.draw(g);
		for (int i = 0; i < powerups.size(); i++) {
			powerups.get(i).draw(g);
		}
	}

	public void update() {
		for (int i = 0; i < bullets.size(); i++) {
			bullets.get(i).update(ship.x, ship.y);
		}
		ship.update();
		checkCollision();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == GamePanel.bulletspawn) {
			spawn();
		}
		if (e.getSource() == GamePanel.survival) {
			survivetime();
		}
		if (e.getSource() == GamePanel.poweruptimer) {
			powerup();
		}

	}

	public void checkCollision() {
		for (int i = 0; i < bullets.size(); i++) {
			if (bullets.get(i).hasCollided(ship)) {
				death();
			}
		}
		for (int i = 0; i < powerups.size(); i++) {
			if (powerups.get(i).hasCollided(ship)) {
				ship.speed+=5;
				powerups.clear();
			}
		}
	}

	public void survivetime() {
		score += 1;
	}

	public void powerup() {
		spawnpowerups();
	}

	public void death() {
		ship.active = false;
	}
}
