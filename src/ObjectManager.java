import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

public class ObjectManager implements ActionListener{
Rocket falcon;
ArrayList<Asteroid> asteroids=new ArrayList<Asteroid>();
Random random=new Random();
ObjectManager(Rocket rocket){
	falcon=rocket;
	addAsteroid();
	
}
void addAsteroid(){
	asteroids.add(new Asteroid(random.nextInt(Game.WIDTH),0, 50, 50));
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
	addAsteroid();
}
}
