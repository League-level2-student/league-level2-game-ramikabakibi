import java.awt.Color;
import java.awt.Font;
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
	Font titleFont=new Font("Arial", Font.PLAIN, 48);
	Font enterFont=new Font("Arial", Font.PLAIN, 30);
	Timer frameDraw;
	Timer fuelMeter;
	public static int fuelLeft=200;
    static int gameTimeSec=0;
    long gameCounter=0;
    ObjectManager manager;
    int backgroundY=-400;
	GamePanel() {
		try {
			background = ImageIO.read(this.getClass().getResourceAsStream("space.jpg"));

		} catch (Exception e) {
			System.out.println("Could not display background");
		}
		createRocket();
		frameDraw=new Timer(1000/60, this);
		frameDraw.start();
	}
	
	void createRocket() {
		rocket = new Rocket(300, 615, 80, 150);
		manager=new ObjectManager(rocket);
	}
	
	void updateMenuState() {
		
	}
	
	void updateGameState() {
		if(rocket.isActive==false) {
			currentState=END;
		}
		manager.update();
	}
	
	void updateEndState() {
		
	}
	void drawMenuState(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, Game.WIDTH, Game.HEIGHT);
		g.setFont(titleFont);
		g.setColor(Color.WHITE);
		g.drawString("ROCKET RUN", 150, 200);
		g.setFont(enterFont);
		g.setColor(Color.WHITE);
		g.drawString("Press ENTER to start", 150, 600);
	}
	
	void drawGameState(Graphics g) {
		if (background != null) {
			g.drawImage(background, 0, backgroundY, Game.WIDTH, Game.HEIGHT*2, null);
		}
		backgroundY++;
		if(backgroundY>0) {
			backgroundY=-400;
		}
		drawFuel(g);
		manager.draw(g);
		
	}
	void drawFuel(Graphics g) {
		g.setColor(Color.GREEN);
		g.fillRect(60, 60, fuelLeft, 30);
		g.setColor(Color.WHITE);
		g.setFont(enterFont);
		g.drawString("Fuel: "+ fuelLeft,60,60);
		
	}
	
	void drawEndState(Graphics g) {
		g.setColor(Color.BLUE);
		g.fillRect(0, 0, Game.WIDTH, Game.HEIGHT);
		g.setFont(titleFont);
		g.setColor(Color.BLACK);
		g.drawString("GAME OVER", 150, 200);
		g.setFont(enterFont);
		g.setColor(Color.BLACK);
		g.drawString("Press ENTER to restart", 150, 400);
	}
	void startGame() {
		
		
		fuelMeter=new Timer(1000, this);
		fuelMeter.start();
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
		
		
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
		if (e.getKeyCode()==KeyEvent.VK_ENTER) {
		    if (currentState == END){
		    	currentState = MENU;
		    	createRocket();
		    	fuelLeft=200;
		    } 
		    else {
		        currentState++;
		        if(currentState==GAME) {
		        	startGame();
		        }
		        if(currentState==END) {
		        	
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
		
		if(arg0.getSource()==fuelMeter) {
			
			if(fuelLeft==0) {
				currentState=END;
				fuelLeft=200;
			}
			if(currentState==GAME&& fuelLeft>=8) {
				fuelLeft-=8;
			}
		}
		
		else {
		gameCounter++;
		if(gameCounter!=0 && gameCounter%60==0) {
			gameTimeSec++;
		}
		if(currentState==MENU) {
			updateMenuState();
		}
		if(currentState==GAME) {
			updateGameState();
		}
		if(currentState==END) {
			updateEndState();
		}
		repaint();
		}
	}

}
