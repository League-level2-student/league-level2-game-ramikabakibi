import java.awt.Rectangle;

public class GameObject {
	int x;
	int y;
	int width;
	int height;
	int speed;
	boolean isActive = true;
	Rectangle collisionBox;

	GameObject(int x, int y, int width, int height, int speed) {
		collisionBox = new Rectangle(x, y, width, height);
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.speed=speed;

	}

	void update() {
		collisionBox.setBounds(x, y, width, height);
	}

}
