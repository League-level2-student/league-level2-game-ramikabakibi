import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.Timer;

public class GamePanel extends JPanel implements KeyListener, ActionListener {
	final int MENU = 0;
	final int GAME = 1;
	final int END = 2;
	int currentState = MENU;
	BufferedImage background = null;
	Rocket rocket;
	Timer frameDraw;
	Timer asteroidSpawn;
    static int gameTimeSec=0;
    long gameCounter=0;
    ObjectManager manager;
	GamePanel() {
		try {
			background = ImageIO.read(this.getClass().getResourceAsStream("space.jpg"));

		} catch (Exception e) {
			System.out.println("Could not display background");
		}
		rocket = new Rocket(300, 568, 100, 200);
		manager=new ObjectManager(rocket);
		frameDraw=new Timer(1000/60, this);
		frameDraw.start();
	}
	
	void updateMenuState() {
		
	}
	
	void updateGameState() {
		if(rocket.isActive==false) {
			currentState=END;
		}
	}
	
	void updateEndState() {
		
	}
	void drawMenuState(Graphics g) {
		
	}
	
	void drawGameState(Graphics g) {
		
	}
	
	void drawEndState(Graphics g) {
		
	}
	void startGame() {
		asteroidSpawn=new Timer(1000, manager);
		asteroidSpawn.start();
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		if(currentState==MENU) {
			drawMenuState(g);
		}
		if(currentState==GAME) {
			drawGameState(g);
		}
		if(currentState==END) {
			drawEndState(g);
		}
		if (background != null) {
			g.drawImage(background, 0, 0, Game.WIDTH, Game.HEIGHT, null);
		}
		
		manager.update();
		manager.draw(g);
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if (e.getKeyCode()==KeyEvent.VK_ENTER) {
		    if (currentState == END){
		    	currentState = MENU;
		    	rocket=new Rocket(250,700,50,50);
		    	manager=new ObjectManager(rocket);
		    } 
		    else {
		        currentState++;
		        if(currentState==GAME) {
		        	startGame();
		        }
		        if(currentState==END) {
		        	asteroidSpawn.stop();
		        }
		        
		    }
		}
		if (currentState == GAME) {
			if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
				rocket.right();
			}
			if (e.getKeyCode() == KeyEvent.VK_LEFT) {
				rocket.left();
			}
		}
		
//		repaint();
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		if(e.getKeyCode()==KeyEvent.VK_RIGHT ||e.getKeyCode()==KeyEvent.VK_LEFT ) {
			rocket.xspeed=0;
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		repaint();
		gameCounter++;
		if(gameCounter!=0 && gameCounter%60==0) {
			gameTimeSec++;
		}
	}

}
