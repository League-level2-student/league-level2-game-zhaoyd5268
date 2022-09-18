
public class GameObject {

	// Object variables

	double x;
	double y;
	int width;
	int height;
	int speed;
	double size;
	boolean active;
	// Initializer

	GameObject(double x, double y, int width, int height, int speed, double size, boolean active) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.speed = speed;
		this.size = size;
		this.active = active;
	}

	// update
	void update() {

	}

	// getdistance method
	double getDistanceFrom(double x2, double y2) {
		return Math.sqrt(Math.pow(x2 - x, 2) + Math.pow(y2 - y, 2));

	}
	// has collided method

	boolean hasCollided(GameObject other) {
		double distance = getDistanceFrom(other.x, other.y);
		boolean hasCollided = distance < other.size + size;
		return hasCollided;

	}
}
