/** 
     * Cogan Shimizu
     * CS-1180L-90
     * Kurtis Glendenning
     * Michael Ondrasek
     * 
     * PURPOSE:
     * This class is the workhorse of the game.
     * 
     * Herein are updated the positions, speeds, existence, removal,
     * drawing, and gamestates of all objects, score, and the game itself.
     * 
     * KeyListeners handle player movement, game exit and replay.
     * 
     * 0: start menu
     * 1: game action!
     * 2: victory screen!
     * 3: loss screen D:
     * 99: newGame catcher/flagger
     */

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.*;
import java.util.ArrayList;
import java.util.HashMap;

public class GamePanel extends JPanel
{
	private Scorebar scorebar = new Scorebar();
	
	private HashMap<Integer,Spaceship> enemies = new HashMap<>();
	private HashMap<Integer,Projectile> projectiles = new HashMap<>();
	private ArrayList<Integer> invalidProjectiles = new ArrayList<>();
	
	private Player player;
	
	private Upgrade upgrade = null;
	
	private boolean allEnemiesDestroyed = false;
	
	private final int w, h;
	
	private int level = 1,
				gameState,
				highlight;
	
	public GamePanel(final int w, final int h)
	{
		super();
		
		//new game reset
		SpaceObject.newGameResetID();
		gameState = 0;
		
		this.w = w;
		this.h = h;		
		
		scorebar.setLevelValue(level);
		
		//player is always hxw 30x50
		player = new Player(235,610);
		Spaceship s;
		
		s = new Spaceship(40,40);
		enemies.put(s.getEntityID(),s);
		
		/*
		 * left moves the player left
		 * right moves the player right
		 * space fires a projectile
		 * escape will exit the game.
		 */
		addKeyListener(new KeyAdapter()
		{
			public void keyPressed(KeyEvent e)
			{
				switch(e.getKeyCode())
				{
					case KeyEvent.VK_LEFT:
					{
						if(gameState == 2 || gameState == 3)
							highlight = 0;
						
						player.setXVel(-6);
						player.move(w,h);
						break;
					}
					case KeyEvent.VK_RIGHT:
					{
						if(gameState == 2 || gameState == 3)
							highlight = 1;
		
						player.setXVel(6);
						player.move(w,h);
						break;
					}
					case KeyEvent.VK_SPACE:
					{
						if(!player.getHasProjectile())
							projectiles.put(player.getEntityID(),player.fire(true));
						
						break;
					}
					case KeyEvent.VK_ESCAPE:
					{
						System.exit(0);
					}
					default:
					{
						break;
					}
				}
			}
		}
		);
	}
	
	/**
	 * move method handles the position, speed, existence, and removal
	 * of all objects on the screen during gamestate 1.
	 * 
	 * That is, objects flagged for removal are removed, positions  and speed
	 * are updated according to the boundaries.
	 * 
	 * move method returns gameState 1 is !allEnemiesDestroyed, 2 if allEnemiesDestroyed
	 * or 3 is player.getIsDestroyed() == true;
	 * @param gamePanel
	 * @return
	 */
	public int move(GamePanel gamePanel)
	  {
		Projectile p;
		Spaceship ss;
		//player movement is handled by the KeyListener/Handler
		
		//update scorebar
		scorebar.updateScorebar(player);
		
		
		//move projectiles
		if(!projectiles.isEmpty())
		{
			for(Integer i : projectiles.keySet())
			{
				p = projectiles.get(i);
				
				//move projectiles, also flags projectiles for being out of range
				p.move(new ArrayList<Spaceship>(enemies.values()),player);
				
				//make note of outOfRange projectiles
				if(p.getIsInvalid())
					invalidProjectiles.add(i);
						
			}
		}
		if(upgrade != null)
		{
			upgrade.move(w,h,player);
		
			if(upgrade.getIsClaimed() || upgrade.getIsInvalid())
				upgrade = null;
			
		}
		
		//remove flagged projectiles
		for(int i : invalidProjectiles)
		{
			if(i > 0 && projectiles.containsKey(i))
			{
				enemies.get(i).setHasProjectile(false);
				projectiles.remove(i);
			}
			
			if(i == 0 && player.getHasProjectile())
			{
				player.setHasProjectile(false);
				projectiles.remove(i);
			}
			
		}
		
		//clear all flags
		invalidProjectiles.clear();		
		
		
		for(Spaceship s : enemies.values())
		{
			//assume all ships are destroyed
			allEnemiesDestroyed = s.getIsDestroyed();
			
			if(allEnemiesDestroyed)
				continue;
			else
				break;
		}
		
		if(player.getIsDestroyed())
		{
			gameState = 3; //game loss menu
			return gameState;
		}
		
		if(allEnemiesDestroyed)
		{
			if(level == 5)
			{
				gameState = 2; //game Victory menu
				return gameState;
			}
			else
			{
				//reset data for next level
				enemies.clear();
				Spaceship.resetID();
				
				upgrade = new Upgrade();
				
				player.setXCoord(235);
				player.setYCoord(610);
				
				level++;
				scorebar.setLevelValue(level);
				
				for(int i=0;i<level;i++)
				{
					ss = new Spaceship(40,40*(i+1));
					enemies.put(ss.getEntityID(),ss);
				}
			}
		}
		
		//move enemies
	    for(Spaceship s : enemies.values())
	    {
	   		if(!s.getIsDestroyed())
	   		{
		    	s.move(w,h);
		   		
		   		if(!s.getHasProjectile())
		   		{
		   			p = s.fire(false);
		   			
		   			projectiles.put(s.getEntityID(),p);
		   		}
		   	}
	    }
	    	  	
	    repaint();
	    
	    return gameState = 1; //continue game.move();
	      
	  }
	/**
	 * startMenu method extends the KeyAdapter functionality so that
	 * enter will start the game, and left and right, during gameStates 
	 * 2 and 3 will move the highlight box.
	 * 
	 * @param gamePanel
	 * @return
	 */
	public int startMenu(final GamePanel gamePanel)
	{
		
		addKeyListener(new KeyAdapter()
		{
			public void keyPressed(KeyEvent e)
			{
				switch(e.getKeyCode())
				{
					case KeyEvent.VK_ENTER:
					{
						if(gamePanel.getGameState() == 0)
							gamePanel.setGameState(1);
						if(gamePanel.getGameState() == 2 ||
						   gamePanel.getGameState() == 3)
						{
							switch(highlight)
							{
								case 0:
								{
									gamePanel.setGameState(99);
									break;
								}
								case 1:
								{
									System.exit(0);
									break;
								}
								default:
								{
									System.out.println("Highlight Enter Error");
									System.exit(1);
								}
							}
						}
						break;
					}
					case KeyEvent.VK_ESCAPE:
					{
						System.exit(0);
					}
					default:
					{
						break;
					}
				}
			}
		}
		);
		
		repaint();
		
		return gameState;
		
	}
	/**
	 * victoryScreen is a dummy method for handling the victory screen during
	 * gamestate 2
	 * @param gamePanel
	 * @return
	 */
	public int victoryScreen(GamePanel gamePanel)
	{
		repaint();
		
		return gameState;
	}
	/**
	 * lossScreen method is a dummy method for handling the loss screen during gameState 3
	 * @param gamePanel
	 * @return
	 */
	public int lossScreen(GamePanel gamePanel)
	{
		repaint();
		
		return gameState;
	}
	/**
	 * paintComponent method updates the drawings of all the objects on the screen
	 * with respect to the gameState. Menuscreens are handled by the Scorebar class.
	 */
	public void paintComponent(Graphics g)
  	{
		super.paintComponent(g);
  		
		//set color black
        g.setColor(Color.black);
        //paint background
        g.fillRect(0, 0, getWidth(), getHeight());
        
        switch(gameState)
        {
	        case 0:
	        {
	        	scorebar.drawStartMenu(g);
	        	break;
	        }
	        case 1:
	        {
	        	//draw scorebar
		        scorebar.drawScorebar(g);
		        
		        
			    //draw projectiles
			    if(!projectiles.isEmpty())
			    	for(Projectile p : projectiles.values())
			    	    p.draw(g);
			    
			    //draw upgrade
			    if(upgrade != null && !upgrade.getIsClaimed())
			    	upgrade.draw(g);
			    
			    //Draw Enemies
			    for(Spaceship ship : enemies.values())
			    	if(!ship.getIsDestroyed())
				        ship.draw(g);
			       
			    //Draw Player
			    player.draw(g);
		        
		        break;
	        }
	        case 2:
	        {
	        	scorebar.drawVictoryScreen(g,highlight);
	    		break;
	        }
	        case 3:
	        {
	        	scorebar.drawGameOverScreen(g,highlight);
	        	break;
	        }
	        case 99:
	        {
	        	//restart game code
	        	break;
	        }
	        default:
	        {
	        	System.out.println("gameState paint error");
	        	System.exit(1);
	        }
        }
    }
	/**
	 * setGameState method sets gameState (see Game documentation for details)
	 * @param gameState
	 */
	public void setGameState(int gameState)
	{
		this.gameState = gameState;
	}
	/**
	 * getGameState method returns the gameState
	 * @return
	 */
	public int getGameState()
	{
		return gameState;
	}
}
