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
    	//frame name
    	super("Alien Invaders");  
    	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
    	setLayout(new BorderLayout());
    	gamePanel = new GamePanel(WINDOW_WIDTH,WINDOW_HEIGHT,2);
    	add(gamePanel);
    	center(this);
    	setVisible(true);
    	gamePanel.setFocusable(true);

    	boolean gameOver = false;
    	
    	//main game loop, close to halt
    	while(!gameOver)
    	{
    		pause();
    		gameOver = gamePanel.move(gamePanel);
    	}
    }
	
	/**
     * Pause command used to control the speed of animation.
     * Currently pauses for 10 ms. Use smaller values for faster animation and
     * vice versa.
     */
    public static void pause()
    {
    	try
	    {
    		Thread.sleep(10); //pause for 10 ms
	    }
	    catch (Exception e)
	    {
	    	System.out.println(e);
	        e.printStackTrace();
	    }
    }
    
    /**
     * Helper routine to center a frame on the screen (causes problems if
     * frame is bigger than the screen)
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
