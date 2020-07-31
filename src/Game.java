import javax.swing.JFrame;

public class Game {
	JFrame frame=new JFrame();
	GamePanel panel;
    public static final int WIDTH= 600;
    public static final int HEIGHT=800;
	
    Game(){
    	frame=new JFrame();
    	panel=new GamePanel();
    }
	void run() {
		frame.setVisible(true);
		frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
		frame.add(panel);
		frame.setSize(WIDTH, HEIGHT);
		frame.addKeyListener(panel);
	}
}