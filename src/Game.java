import java.awt.*;

import javax.swing.*;

public class Game extends JFrame
{
	private static final int WINDOW_WIDTH = 500;
	private static final int WINDOW_HEIGHT = 750;
	
	private GamePanel gamePanel;
	private boolean startNewGame = false; //needs to be false to start FIRST new game
	
	/**
     * Creates a new instance of Game
     */
    public Game()
    {
    	super("Alien Invaders");
    	
    	while(true)
    	{
    		//frame name
        	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        	setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        	setLayout(new BorderLayout());
        	
	    	gamePanel = new GamePanel(WINDOW_WIDTH,WINDOW_HEIGHT);
	    	add(gamePanel);
	    	center(this);
	    	setVisible(true);
	    	gamePanel.setFocusable(true);
	
	    	int gameState = 0;
	    	gamePanel.setGameState(0);
	    	
	    	System.out.println("New Game Starting");
	    	//reset startNewGame
	    	startNewGame = false;
	    	
	    	//main game loop, close to halt
	    	while(!startNewGame)
	    	{
	    		pause();
	    		switch(gameState)
	    		{
		    		case 0:
		    		{
		    			gameState = gamePanel.startMenu(gamePanel);
		    			break;
		    		}
		    		case 1:
		    		{
		    			gameState = gamePanel.move(gamePanel);
		    			break;
		    		}
		    		case 2:
		    		{
		    			gameState = gamePanel.victoryScreen(gamePanel);
		    			break;
		    		}
		    		case 3:
		    		{
		    			gameState = gamePanel.lossScreen(gamePanel);
		    			if(gameState == 99)
		    				startNewGame = true;
		    			break;
		    		}
		    		default:
		    		{
		    			System.out.println("gameState error");
		    			System.exit(1);
		    		}
	    		}
	    		
	    		
	    	}
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
