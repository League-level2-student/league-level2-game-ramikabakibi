import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

public class GasTank extends GameObject {
	public static BufferedImage image;
	public static boolean needImage = true;
	public static boolean gotImage = false;
GasTank(int x, int y, int width, int height, int speed){
	super(x,y,width,height, speed);
	if(needImage) {
		loadImage("gastank.png");
	}
}

void update() {
	
	y+=speed;
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
