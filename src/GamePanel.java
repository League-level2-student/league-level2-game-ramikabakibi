import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

public class GamePanel extends JPanel implements KeyListener, ActionListener {
	final int MENU = 0;
	final int GAME = 1;
	final int END = 2;
	final int DIRECTIONS=3;
	int currentState = MENU;
	BufferedImage background = null;
	Rocket rocket;
	Font titleFont=new Font("Arial", Font.PLAIN, 48);
	Font enterFont=new Font("Arial", Font.PLAIN, 30);
	Font smallFont=new Font("Arial", Font.PLAIN, 24);
	Timer frameDraw;
	Timer fuelMeter;
	Timer shieldTimer;
	Timer scoreTimer;
	public static int fuelLeft=200;
    static int gameTimeSec=0;
    long gameCounter=0;
    ObjectManager manager;
    int backgroundY=-400;
    int shield=0;
    int score=0;
    int shieldCounter=10;
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
		rocket = new Rocket(300, 615, 70, 100, 6);
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
		g.drawString("ROCKET RUN", 150, 150);
		g.setFont(enterFont);
		g.setColor(Color.WHITE);
		g.drawString("Press ENTER to start", 150, 300);
		g.drawString("Press D for directions",150, 450 );
	}
	void drawDirections(Graphics g) {
		g.setColor(Color.green);
		g.fillRect(0, 0, Game.WIDTH, Game.HEIGHT);
		g.setColor(Color.BLACK);
		g.setFont(titleFont);
		g.drawString("DIRECTIONS", 150, 70);
		g.setFont(smallFont);;
		g.drawString("Welcome to Rocket Run! ", 150, 120);
		g.drawString("This is a very simple game. Use your left and right arrow keys to move the rocket around", 4, 135);
		
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
		drawShield(g);
		drawScore(g);
		drawBoost(g);
		manager.draw(g);
		
	}
	void drawFuel(Graphics g) {
		g.setColor(Color.GREEN);
		g.fillRect(20, 30, fuelLeft, 30);
		g.setColor(Color.WHITE);
		g.setFont(enterFont);
		g.drawString("Fuel: "+ fuelLeft,20,27);
		
	}
	
	void drawShield(Graphics g) {
		g.setColor(Color.RED);
		if(rocket.shielded) {
			g.fillRect(20, 83, shieldCounter*10, 30);
			g.setColor(Color.WHITE);
			g.setFont(enterFont);
			g.drawString("Shield: "+ shieldCounter*10, 20, 83);
		}
		else {
		g.fillRect(20, 83, shield, 30);
		
		g.setColor(Color.WHITE);
		g.setFont(enterFont);
		g.drawString("Shield: "+ shield, 20, 83);
		}
	}
	void drawScore(Graphics g) {
		g.setColor(Color.WHITE);
		g.setFont(enterFont);
		g.drawString("Score: "+ score , 450, 30);
	}
	
	void drawBoost(Graphics g) {
		if(rocket.boosted) {
			g.setColor(Color.ORANGE);
			g.fillRect(65, 720, rocket.boostCounter*10, 30);
			g.setColor(Color.WHITE);
			g.setFont(enterFont);
			g.drawString("Boost: "+rocket.boostCounter*10, 65, 710);
		}
	}
	
	
	
	void drawEndState(Graphics g) {
		g.setColor(Color.BLUE);
		g.fillRect(0, 0, Game.WIDTH, Game.HEIGHT);
		g.setFont(titleFont);
		g.setColor(Color.BLACK);
		g.drawString("GAME OVER", 150, 200);
		g.setFont(titleFont);
		g.setColor(Color.BLACK);
		g.drawString("Press ENTER to restart", 70, 500);
		scoreTimer.stop();
		g.drawString("Your Score: "+ score, 150, 380);
		
	}
	void startGame() {
		
		shieldTimer=new Timer(1000, this);
		shieldTimer.start();
		fuelMeter=new Timer(1000, this);
		fuelMeter.start();
		scoreTimer=new Timer(1000, this);
		scoreTimer.start();
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
		if(currentState==DIRECTIONS) {
			drawDirections(g);
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
		    	score=0;
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
		    if(currentState==MENU&&e.getKeyCode()==KeyEvent.VK_D) {
		    System.out.println("directions");
		    	JOptionPane.showMessageDialog(null, "Welcome to Rocket Run! This is a very simple game. The objective: simply get the highest score you can! Use the left and right arrow keys to move your rocket around. Asteroids will be falling down from the top of the screen, so make sure you avoid those, or you'll die!" +System.lineSeparator() +"Your rocket also has a green fuel gauge in the upper left hand corner, and your fuel will be decreasing every second, so make sure you collect the red fuel tanks! You also have a shield that will fill up over time, so press the enter key when it is full, and you will be invincible to the asteroids!"+System.lineSeparator()+" Lastly, shield boost tokens will descend every once in a while, so this will give you heightened speed for a short amount of time, indicated by a gauge that will appear in the lower left hand corner. Those are all the directions, so have fun playing!", "Directions for Rocket Run!", JOptionPane.INFORMATION_MESSAGE);
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
		
		if(arg0.getSource()==scoreTimer) {
			score++;
		}
		if(arg0.getSource()==shieldTimer&& currentState==GAME) {
			if(shield<=95) {
				shield+=5;
				if(shield==100) {
					shieldAvailable=true;
					
				}
			}
			if(rocket.shielded&&shieldCounter>=0) {
				shieldCounter--;
				
				
				if(shieldCounter==0) {
					shieldCounter=10;
					System.out.println("No shield");
					rocket.shielded=false;
					shieldAvailable=false;
					shield=0;
					
				}
			}
			if(rocket.shielded) {
				
			}
			if(rocket.boosted) {
				rocket.boostCounter--;
			System.out.println(rocket.boostCounter);
			if(rocket.boostCounter==0) {
				rocket.resetBoost();
				System.out.println("no boost");
				
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
