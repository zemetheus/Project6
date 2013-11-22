import java.awt.*;

import javax.swing.*;

public class Game extends JFrame
{
	private static final int WINDOW_WIDTH = 500;
	private static final int WINDOW_HEIGHT = 750;
	
	private GamePanel gamePanel;
	
	
	/**
     * Creates a new instance of Game
     */
    public Game() {
    	super("Alien Invaders");  //frame name
    	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
    	setLayout(new BorderLayout());
    	gamePanel = new GamePanel();
    	add(gamePanel);
    	center(this);
    	setVisible(true);

    	//main game loop, close to halt
    	while(true)
    	{
    		pause();
    		gamePanel.move(gamePanel);
    	}
    }
	
	/**
     * Pause command used to control the speed of the bouncing ball animation.
     * Currently pauses for 20 ms. Use smaller values for faster animation and
     * vice versa.
     */
    public static void pause()
    {
    	try
	    {
    		Thread.sleep(20); //pause for 20 ms
	    }
	    catch (Exception e)
	    {
	    	System.out.println(e);
	        e.printStackTrace();
	    }
    }
    
    /**
     * Helper routine to center a frame on the screen (will cause problems if
     * frame is bigger than the screen!)
     */
    public static void center(JFrame frame)
    {
    	GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
    	Point center = ge.getCenterPoint();

    	int w = frame.getWidth();
    	int h = frame.getHeight();

    	int x = center.x - w / 2, y = center.y - h / 2;
    	frame.setBounds(x, y, w, h);
    	frame.validate();
    }
}
