import java.awt.Rectangle;

public class GameObject {
	int x;
	int y;
	int width;
	int height;
	int speed=5;
	boolean isActive = true;
	Rectangle collisionBox;

	GameObject(int x, int y, int width, int height) {
		collisionBox = new Rectangle(x, y, width, height);
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;

	}

	void update() {
		collisionBox.setBounds(x, y, width, height);
	}

}