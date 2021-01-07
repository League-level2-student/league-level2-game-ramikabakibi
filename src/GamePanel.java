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
	Font smallFont=new Font("Arial", Font.PLAIN, 24);
	Timer frameDraw;
	Timer fuelMeter;
	Timer shieldTimer;
	public static int fuelLeft=200;
    static int gameTimeSec=0;
    long gameCounter=0;
    ObjectManager manager;
    int backgroundY=-400;
    int shield=0;
    int score=0;
    int boostCounter=0;
    boolean shieldAvailable=false;
    int level=1;
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
		shieldAvailable=false;
	}
	
	void updateMenuState() {
		
	}
	
	void updateGameState() {
		if(!rocket.isActive) {
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
		if(shieldAvailable) {
			g.setColor(Color.WHITE);
			g.setFont(smallFont);
			g.drawString("Press Enter to Activate Shield", 230, 50);
		}
		drawFuel(g);
		drawBoost(g);
		drawScore(g);
		manager.draw(g);
		
	}
	void drawFuel(Graphics g) {
		g.setColor(Color.GREEN);
		g.fillRect(20, 30, fuelLeft, 30);
		g.setColor(Color.WHITE);
		g.setFont(enterFont);
		g.drawString("Fuel: "+ fuelLeft,20,27);
		
	}
	
	void drawBoost(Graphics g) {
		g.setColor(Color.RED);
		g.fillRect(20, 83, shield, 30);
		g.setColor(Color.WHITE);
		g.setFont(enterFont);
		g.drawString("Shield: "+ shield, 20, 83);
	}
	void drawScore(Graphics g) {
		g.setColor(Color.WHITE);
		g.setFont(enterFont);
		g.drawString("Score: "+ score , 450, 30);
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
		
		shieldTimer=new Timer(1000, this);
		shieldTimer.start();
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
		    	shield=0;
		    } 
		    else if(currentState==GAME) {
		    	if(shieldAvailable) {
		    		rocket.shielded=true;
		    		shieldAvailable=false;
		    	}
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
		
		if(arg0.getSource()==shieldTimer&& currentState==GAME) {
			if(shield<=95) {
				shield+=5;
				if(shield==100) {
					shieldAvailable=true;
					
				}
			}
			if(rocket.shielded) {
				boostCounter++;
				if(boostCounter==7) {
					boostCounter=0;
					rocket.shielded=false;
					shieldAvailable=false;
					shield=0;
				}
			}
			
			
			if(currentState==END) {
				shield=0;
				shieldAvailable=false;
			}
		}
		else if(arg0.getSource()==fuelMeter) {
			
			if(fuelLeft==0) {
				currentState=END;
				fuelLeft=200;
			}
			if(currentState==GAME&& fuelLeft>=8) {
				fuelLeft-=8;
			}
		}
		//THIS IS THE TIMER BELOW I AM GOING TO USE TO INCREMENT THE LEVELS,(i have a level variable at the top)
		//I think the way I'll use the timer is to say if a certain amount of milliseconds or time or whatever
		//has passed, then up the level. Like every time the timer goes up by a certain amount of seconds,
		//increase the level. Everything will have to be sorted into categories, like if the level is 1
		//then do this and more ifs and stuf....
		else if(arg0.getSource()==frameDraw){
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
