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
Random random=new Random();
ObjectManager(Rocket rocket){
	falcon=rocket;
	asteroidSpawn=new Timer(2000, this);
	asteroidSpawn.start();
	fuelTankSpawn=new Timer(15000, this);
	fuelTankSpawn.start();
}
void addAsteroid(){
	asteroids.add(new Asteroid(random.nextInt(Game.WIDTH),0, 80, 80));
}

void checkCollision() {
	for(int i=0; i<asteroids.size();i++) {
		if(falcon.collisionBox.intersects(asteroids.get(i).collisionBox)){
			falcon.isActive=false;
			asteroids.get(i).isActive=false;
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
	checkCollision();
	purgeObjects();
	if(!falcon.isActive) {
		asteroidSpawn.stop();
	}
}

void draw(Graphics g) {
	falcon.draw(g);
	for(int i=0; i<asteroids.size();i++) {
		asteroids.get(i).draw(g);
	}
}
void purgeObjects() {
		for (int i = 0; i < asteroids.size(); i++) {
			if (asteroids.get(i).isActive == false) {
				asteroids.remove(asteroids.get(i));
			}
		}
}
@Override
public void actionPerformed(ActionEvent arg0) {
	// TODO Auto-generated method stub
if(arg0.getSource()==fuelTankSpawn) {
	System.out.println("Fuel");
 
}
else {
	addAsteroid();
}
}
}
