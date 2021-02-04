import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Rocket extends GameObject{
	int xspeed;
	boolean shielded=false;
	BufferedImage rocketship=null;
	boolean boosted=false;
	int boostCounter=10;
	Rocket(int x, int y, int width, int height, int speed) {
		super(x, y, width, height, speed);
		shielded=false;
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
public void boost() {
	speed=17;
	boosted=true;
	boostCounter=10;
}
public void resetBoost() {
	speed=6;
	boosted=false;
	boostCounter=0;
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
		g.drawImage(rocketship, x, y, 80, 150, null);
		
		//g.drawRect(collisionBox.x, collisionBox.y, collisionBox.width, collisionBox.height);
	}
}
