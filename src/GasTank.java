import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

public class GasTank extends GameObject {
	public static BufferedImage image;
	public static boolean needImage = true;
	public static boolean gotImage = false;
GasTank(int x, int y, int width, int height){
	super(x,y,width,height);
	if(needImage) {
		loadImage("gastank.png");
	}
}

void update() {
	
}

void draw() {
	
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