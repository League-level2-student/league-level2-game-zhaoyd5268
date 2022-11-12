import java.awt.Dimension;

import javax.swing.JFrame;

public class AvoidTheBullets {

// RUN THIS CLASS ONLY PLEASE

	// Game variables

	JFrame frame;
	GamePanel gamepanel;
	public static final int WIDTH = 800;
	public static final int HEIGHT = 800;

	// JFrame inititalizer constructor

	AvoidTheBullets() {
		frame = new JFrame();
		gamepanel = new GamePanel();
	}

	// Main method
	public static void main(String[] args) {
		AvoidTheBullets avoidthebullets = new AvoidTheBullets();
		avoidthebullets.setup();
	}

	// Setup method

	void setup() {
		Dimension d = new Dimension(WIDTH, HEIGHT);
		frame.add(gamepanel);
		frame.setSize(d);
		frame.addKeyListener(gamepanel);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	void update() {

	}
}
