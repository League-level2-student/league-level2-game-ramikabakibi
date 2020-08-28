import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;

public class ObjectManager {
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

void update(){
	for(int i=0; i<asteroids.size();i++) {
		asteroids.get(i).update();
		if(asteroids.get(i).y>Game.HEIGHT) {
			asteroids.get(i).isActive=false;
		}
	}
}

void draw(Graphics g) {
	for(int i=0; i<asteroids.size();i++) {
		asteroids.get(i).draw(g);
	}
}
void purgeObjects() {
	
}
}
