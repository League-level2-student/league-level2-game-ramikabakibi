import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Rocket extends GameObject{
	int xspeed;
	BufferedImage rocketship=null;
	
	Rocket(int x, int y, int width, int height) {
		super(x, y, width, height);
		try {
			rocketship= ImageIO.read(this.getClass().getResourceAsStream("rocket.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(""+width + ", " + height);
	}

	public void right() {
		xspeed = speed;
	}

	public void left() {
		xspeed = -speed;
	}
	
	void update() {
		super.update();
		if (x + xspeed <= Game.WIDTH - width && x + xspeed >= 0) {
			
			x += xspeed;
		}
	}

	
	
	void draw(Graphics g) {
		g.drawImage(rocketship, x, y, width, height, null);
		System.out.println(""+width + ", " + height);
		//g.drawRect(collisionBox.x, collisionBox.y, collisionBox.width, collisionBox.height);
	}
}
