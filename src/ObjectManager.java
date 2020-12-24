import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.Timer;

public class ObjectManager implements ActionListener{
Rocket falcon;
Timer fuelTankSpawn;
Timer asteroidSpawn;
ArrayList<Asteroid> asteroids=new ArrayList<Asteroid>();
ArrayList<GasTank> tanks=new ArrayList<GasTank>();
Random random=new Random();
ObjectManager(Rocket rocket){
	falcon=rocket;
	asteroidSpawn=new Timer(2000, this);
	asteroidSpawn.start();
	fuelTankSpawn=new Timer(17000, this);
	fuelTankSpawn.start();
}
void addAsteroid(){
	asteroids.add(new Asteroid(random.nextInt(Game.WIDTH),0, 80, 80));
}

void addTanks() {
	tanks.add(new GasTank(random.nextInt(Game.WIDTH), 0, 50, 50));
}

void checkCollision() {
	if(!falcon.shielded) {
	for(int i=0; i<asteroids.size();i++) {
		if(falcon.collisionBox.intersects(asteroids.get(i).collisionBox)){
			falcon.isActive=false;
			asteroids.get(i).isActive=false;
		}
	}
	}
	for(int i=0; i<tanks.size(); i++) {
		if(falcon.collisionBox.intersects(tanks.get(i).collisionBox)) {
			GamePanel.fuelLeft=200;
			tanks.get(i).isActive=false;
		}
	}
	
}



void update(){
	
	falcon.update();
	
	for(int i=0; i<asteroids.size();i++) {
		asteroids.get(i).update();
		if(asteroids.get(i).y>Game.HEIGHT) {
			asteroids.get(i).isActive=false;
		}
	}
	for(int i=0; i<tanks.size();i++) {
		tanks.get(i).update();
		if(tanks.get(i).y>Game.HEIGHT) {
			tanks.get(i).isActive=false;
		}
	}
	checkCollision();
	purgeObjects();
	if(!falcon.isActive) {
		asteroidSpawn.stop();
		fuelTankSpawn.stop();
	}
}

void draw(Graphics g) {
	falcon.draw(g);
	for(int i=0; i<asteroids.size();i++) {
		asteroids.get(i).draw(g);
	}
	for(int i=0; i<tanks.size();i++) {
		tanks.get(i).draw(g);
	}
}
void purgeObjects() {
		for (int i = 0; i < asteroids.size(); i++) {
			if (asteroids.get(i).isActive == false) {
				asteroids.remove(asteroids.get(i));
			}
		}
		for(int i=0; i< tanks.size(); i++) {
			if(tanks.get(i).isActive==false) {
				tanks.remove(tanks.get(i));
			}
		}
}
@Override
public void actionPerformed(ActionEvent arg0) {
	// TODO Auto-generated method stub
if(arg0.getSource()==fuelTankSpawn) {
	System.out.println("fuel");
	addTanks();
 
}
else {
	addAsteroid();
}
}
}
