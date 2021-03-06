import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Random;

import javax.imageio.ImageIO;

public class Asteroid extends GameObject {
	public static BufferedImage image;
	public static boolean needImage = true;
	public static boolean gotImage = false;
double exactX;
Random rand=new Random();
double speedX=(rand.nextDouble()-1.2);
	Asteroid(int x, int y, int width, int height, int speed) {
		super(x, y, width, height, speed);
		isActive = true;
		exactX=x;
		if (needImage) {
			loadImage("asteroid.png");
		}

	}

	void update() {
		
		y += speed;
		exactX+=speedX;
		x=(int)exactX;
		super.update();
	}

	void draw(Graphics g) {
		g.drawImage(image, x, y, width, height, null);
	//g.drawRect(collisionBox.x, collisionBox.y, collisionBox.width, collisionBox.height);
	}

	void loadImage(String imageFile) {
		if (needImage) {
			try {
				image = ImageIO.read(this.getClass().getResourceAsStream(imageFile));
				gotImage = true;
			} catch (Exception e) {

			}
			needImage = false;
		}
	}
}
