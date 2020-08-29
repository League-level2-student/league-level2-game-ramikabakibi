import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

public class Asteroid extends GameObject{
	public static BufferedImage image;
	public static boolean needImage = true;
	public static boolean gotImage = false;	
Asteroid(int x, int y, int width, int height){
	super(x,y,width,height);
	isActive=true;
	if(needImage) {
	loadImage("asteroid.jpg");	
	}
	
}
void update() {
	y+=1;
	super.update();
}

void draw(Graphics g) {
g.drawImage(image, x,y,width,height, null);	
g.drawRect(collisionBox.x, collisionBox.y, collisionBox.width, collisionBox.height);
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
