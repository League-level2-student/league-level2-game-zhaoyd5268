import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

public class GamePanel extends JPanel implements KeyListener, ActionListener {

	// Image member variables

	public static BufferedImage image;
	public static boolean needImage = true;
	public static boolean gotImage = false;

	// GamePanel variables

	Timer framedraw;
	public static Timer bulletspawn;
	public static Timer otherpoweruptimer;
	Font titlefont;
	public static Timer survival;
	public static Timer survivalpowerupstop;
	Font instructions;
	public static Timer poweruptimer;
	public static Timer spinnerspawn;
	Font endgamefont;
	final int MENU = 0;
	final int GAME = 1;
	final int END = 2;
	int currentState = MENU;
	ObjectManager manager = new ObjectManager();
	// gamePanel constructor(Font method)

	GamePanel() {
		titlefont = new Font("Arial", Font.BOLD, 50);
		instructions = new Font("Arial", Font.BOLD, 30);
		endgamefont = new Font("Arial", Font.BOLD, 40);
		framedraw = new Timer(1000 / 60, this);
		framedraw.start();
		bulletspawn();
		scorecounter();
		poweruptimer();
		spinnerspawn();
		if (needImage) {
			loadImage("sky1.png");
		}
	}

	// Paint method

	@Override
	public void paintComponent(Graphics g) {
		if (currentState == MENU) {
			drawMenuState(g);
		} else if (currentState == GAME) {
			drawGameState(g);
		} else if (currentState == END) {
			drawEndState(g);
		}
	}

	// Game state methods

	void updateMenuState() {
	  	bulletspawn.stop();
		spinnerspawn.stop();
		poweruptimer.stop();
		survival.stop();
		spinnerspawn.stop();
	}

	void updateGameState() {
		bulletspawn.start();
		spinnerspawn.start();
		poweruptimer.start();
		survival.start();
		manager.update();
		if (manager.ship.active == false) {
			currentState = END;
		}

	}

	void updateEndState() {
		if (currentState == END) {
			bulletspawn.stop();
			spinnerspawn.stop();
			poweruptimer.stop();
			survival.stop();
			spinnerspawn.stop();
		}
	}

	// draw methods
	void drawMenuState(Graphics g) {
		g.setColor(Color.GREEN);
		g.fillRect(0, 0, AvoidTheBullets.WIDTH, AvoidTheBullets.HEIGHT);
		g.setFont(titlefont);
		g.setColor(Color.black);
		g.drawString("AVOID THE BULLETS ", 150, 200);
		g.setFont(instructions);
		g.setColor(Color.black);
		g.drawString("press t to continue or press i for instructions", 100, 300);

	}

	void drawGameState(Graphics g) {
		if (gotImage) {
			g.drawImage(image, 0, 0, AvoidTheBullets.WIDTH, AvoidTheBullets.HEIGHT, null);
		} else {
			g.setColor(Color.ORANGE);
			g.fillRect(0, 0, AvoidTheBullets.WIDTH, AvoidTheBullets.HEIGHT);
		}
		manager.draw(g);

	}

	void drawEndState(Graphics g) {
		g.setColor(Color.GREEN);
		g.fillRect(0, 0, AvoidTheBullets.WIDTH, AvoidTheBullets.HEIGHT);
		g.setFont(endgamefont);
		g.setColor(Color.white);
		g.drawString("You survived for " + manager.score + " seconds", 150, 200);
		g.drawString("Press Space to restart", 150, 400);
		g.drawString("Or, press t to go back to the menu", 150, 600);
	}

	// Key movement methods
	@Override
	public void keyPressed(KeyEvent b) {
		if (b.getKeyCode() == KeyEvent.VK_T) {
			if (currentState == END) {
				currentState = MENU;
			} else if (currentState == MENU) {
				currentState = GAME;
			}
			if (currentState == MENU) {
				manager.ship.active = true;
				manager.bullets.clear();
				manager.score = 0;
			}
			if (currentState == GAME) {
				survival.start();
			}

		}
		if (b.getKeyCode() == KeyEvent.VK_I && currentState == MENU) {
			JOptionPane.showMessageDialog(null,
					"Welcome to Avoid The Bullets! This is a survival game. Use your arrow keys to move, and if you are on mobile, sucks for you."
					+ "\n Missiles will come at you and mad angry suns will also come at you. Powerups spawn every couple of seconds. The turtle \n"
					+ "shell one is invulnerability for 5 seconds. The lightning bolt is increased speed for 5 seconds. Have fun!");
		}
		if (b.getKeyCode() == KeyEvent.VK_SPACE && currentState == END) {
			manager.ship.x = 400;
			manager.ship.y = 400;
			manager.score = 0;
			manager.ship.active = true;
			manager.bullets.clear();
			poweruptimer.restart();
			survival.start();
			currentState = GAME;
			manager.powerups.clear();
		}
		if (b.getKeyCode() == KeyEvent.VK_UP) {
			manager.ship.UP(true);
		}
		if (b.getKeyCode() == KeyEvent.VK_DOWN) {
			manager.ship.DOWN(true);
		}
		if (b.getKeyCode() == KeyEvent.VK_LEFT) {
			manager.ship.LEFT(true);
		}
		if (b.getKeyCode() == KeyEvent.VK_RIGHT) {
			manager.ship.RIGHT(true);
		}

	}

	@Override
	public void keyReleased(KeyEvent b) {
		if (b.getKeyCode() == KeyEvent.VK_UP) {
			manager.ship.UP(false);

		}
		if (b.getKeyCode() == KeyEvent.VK_DOWN) {
			manager.ship.DOWN(false);
		}
		if (b.getKeyCode() == KeyEvent.VK_LEFT) {
			manager.ship.LEFT(false);
		}
		if (b.getKeyCode() == KeyEvent.VK_RIGHT) {
			manager.ship.RIGHT(false);
		}
	}

	@Override
	public void actionPerformed(ActionEvent b) {
		if (currentState == MENU) {
			updateMenuState();
		} else if (currentState == GAME) {
			updateGameState();
		} else if (currentState == END) {
			updateEndState();
		}
		repaint();
	}

	public void bulletspawn() {
		bulletspawn = new Timer(1000, manager);
		bulletspawn.start();

	}

	// Image loader method

	void loadImage(String imageFile) {
		if (needImage) {
			try {
				image = ImageIO.read(this.getClass().getResourceAsStream(imageFile));
				gotImage = true;
			} catch (Exception e) {
				e.printStackTrace();
			}
			needImage = false;
		}
	}

	// score counter

	public void scorecounter() {
		survival = new Timer(1000, manager);
		survival.start();

	}

	// score counter

	public void spinnerspawn() {
		spinnerspawn = new Timer(5000, manager);
		spinnerspawn.start();

	}

	// powerup timer
	public void poweruptimer() {
		poweruptimer = new Timer(10000, manager);
		poweruptimer.start();

	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}
}
