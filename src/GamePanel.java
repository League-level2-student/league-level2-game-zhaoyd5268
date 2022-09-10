import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


import javax.swing.JPanel;
import javax.swing.Timer;

public class GamePanel extends JPanel implements KeyListener, ActionListener {

// GamePanel variables

	Timer framedraw;
	Timer bulletspawn;
	Font titlefont;
	Font instructions;
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
		framedraw = new Timer(1000/60, this);
		framedraw.start();
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

	}

	void updateGameState() {
		manager.update();
	}

	void updateEndState() {

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
		g.drawString("press t to continue", 150, 300);
	}

	void drawGameState(Graphics g) {
		g.setColor(Color.PINK);
		g.fillRect(0, 0, AvoidTheBullets.WIDTH, AvoidTheBullets.HEIGHT);
		manager.ship.draw(g);
		manager.spawn(g);
		
	}

	void drawEndState(Graphics g) {
		g.setColor(Color.GREEN);
		g.fillRect(0, 0, AvoidTheBullets.WIDTH, AvoidTheBullets.HEIGHT);
		g.setFont(endgamefont);
		g.setColor(Color.white);
		g.drawString("You survived for seconds ", 150, 200);
	}

	// Key movement methods
	@Override
	public void keyPressed(KeyEvent b) {
		if (b.getKeyCode() == KeyEvent.VK_T) {
			if (currentState == END) {
				currentState = MENU;
			} else {
				currentState++;
			}
		}
	
		if (b.getKeyCode() == KeyEvent.VK_UP) {
			System.out.println("UP");
			manager.ship.UP(true);
		}
		if (b.getKeyCode() == KeyEvent.VK_DOWN) {
			System.out.println("DOWN");
			manager.ship.DOWN(true);
		}
		if (b.getKeyCode() == KeyEvent.VK_LEFT) {
			System.out.println("LEFT");
			manager.ship.LEFT(true);
		}
		if (b.getKeyCode() == KeyEvent.VK_RIGHT) {
			System.out.println("RIGHT");
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

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
