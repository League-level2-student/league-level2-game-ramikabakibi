import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class GamePanel extends JPanel implements KeyListener {
	BufferedImage background = null;

	GamePanel() {
		try {
			background = ImageIO.read(this.getClass().getResourceAsStream("space.jpg"));
		} catch (Exception e) {
			System.out.println("Could not display background");
		}

		repaint();
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (background != null) {
			g.drawImage(background, 0, 0, Game.WIDTH, Game.HEIGHT, null);
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

}
