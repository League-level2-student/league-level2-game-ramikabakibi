import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

public class SpeedBoost extends GameObject{
	public static BufferedImage image;
	public static boolean needImage = true;
	public static boolean gotImage = false;
	boolean boosted=false;
	SpeedBoost(int x, int y, int width, int height, int speed) {
		super(x, y, width, height, speed);
		if(needImage) {
			loadImage("speedboost.png");
		}
	}

	
	void update() {
		
		y+=speed;
		super.update();
		
	}
	
	void draw(Graphics g) {
		g.drawImage(image, x, y, width, height, null);
		
	}
	void speedIncrease() {
		y+=8;
		super.update();
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
