/** 
     * Cogan Shimizu
     * CS-1180L-90
     * Kurtis Glendenning
     * Michael Ondrasek
     * 
     * PURPOSE:
     * This class describes a ball: how it moves, its size, and its color.
     * 
     * It's movement routines detect for collisions against other balls
     * and outer boundaries. Further, if the flag isCollided is true,
     * the ball is ignored until it is removed.
     * 
     * @author MOndrasek wrote this class 10 Aug 2012
     * modified by Cogan Shimizu 11/30/2013
     */

import java.awt.*;

import javax.swing.*;

public class Game extends JFrame
{
	private static final int WINDOW_WIDTH = 500;
	private static final int WINDOW_HEIGHT = 750;
	
	private GamePanel gamePanel;
	private boolean startNewGame = false; //needs to be false to start FIRST new game
	private int gameState = 0;
	/**
     * Creates a new instance of Game
     */
    public Game()
    {
    	super("Alien Invaders");
    	
    	//frame name
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setLayout(new BorderLayout());
        
	    gamePanel = new GamePanel(WINDOW_WIDTH,WINDOW_HEIGHT);
	    add(gamePanel);
	    center(this);
	    setVisible(true);
	    gamePanel.setFocusable(true);
		
	   	System.out.println("New Game Starting");
	  	//reset startNewGame
	   	startNewGame = false;
	    gamePanel.setGameState(0);
	   	
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
	    			break;
	    		}
	    		case 99:
	    		{
	    			startNewGame = true;
	    			setVisible(false);
	    			break;
	    		}
	    		default:
	    		{
	    			System.out.println(gameState);
	    			System.out.println("gameState errorasdf");
	    			System.exit(1);
	    		}
	    	}
    	}
   	}
	
    public boolean getStartNewGame()
    {
    	return startNewGame;
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
