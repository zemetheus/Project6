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
	
	private boolean allEnemiesDestroyed = false;
	
	private final int w, h;
	
	private int level = 1,
				gameState;
	
	public GamePanel(final int w, final int h)
	{
		super();
		
		this.w = w;
		this.h = h;		
		
		scorebar.setLevelValue(level);
		
		//player is always hxw 30x50
		player = new Player(235,650);
		Spaceship s;
		
		s = new Spaceship(40,40);
		enemies.put(s.getShipID(),s);
		
	
		addKeyListener(new KeyAdapter()
		{
			public void keyPressed(KeyEvent e)
			{
				switch(e.getKeyCode())
				{
					case KeyEvent.VK_LEFT:
					{
						player.setXVel(-6);
						player.move(w,h);
						break;
					}
					case KeyEvent.VK_RIGHT:
					{
						player.setXVel(6);
						player.move(w,h);
						break;
					}
					case KeyEvent.VK_SPACE:
					{
						if(!player.getHasProjectile())
							projectiles.put(player.getShipID(),player.fire());
						
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
	
	public int move(GamePanel gamePanel)
	  {
		Projectile p;
		Spaceship ss;
		//player movement is handled by the KeyListener/Handler
		
		//move projectiles
		if(!projectiles.isEmpty())
		{
			for(Integer i : projectiles.keySet())
			{
				p = projectiles.get(i);
				
				//move projectiles, also flags projectiles for being out of range
				p.move(gamePanel,new ArrayList<Spaceship>(enemies.values()),player);
				
				//make note of outOfRange projectiles
				if(p.getIsInvalid())
					invalidProjectiles.add(i);
						
			}
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
		
		if(allEnemiesDestroyed)
		{
			if(level == 5)
			{
				gameState = 2; //game over menu
				return gameState;
			}
			else
			{
				enemies.clear();
				Spaceship.resetID();
				
				player.setXCoord(235);
				player.setYCoord(650);
				
				level++;
				scorebar.setLevelValue(level);
				
				for(int i=0;i<level;i++)
				{
					ss = new Spaceship(40,40*(i+1));
					enemies.put(ss.getShipID(),ss);
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
		   			p = s.fire();
		   			
		   			projectiles.put(s.getShipID(),p);
		   		}
		   	}
	    }
	    	  	
	    repaint();
	    
	    return gameState = 1; //continue game.move();
	      
	  }
	
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
	
	public int victoryScreen(GamePanel gamePanel)
	{
		repaint();
		
		return gameState;
	}
	
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
	        	printStartMenu(g);
	        	
	        	break;
	        }
	        case 1:
	        {
	        	//update score
		        scorebar.addScore(player.getScore());
		        //reset player score
		        player.setScore(0);
		        //draw scorebar
		        scorebar.drawScorebar(g);
		        
		        if(!player.getIsDestroyed())
		        {
			        //draw projectiles
			        if(!projectiles.isEmpty())
			           	for(Projectile p : projectiles.values())
					        p.draw(g);
			        
			        //Draw Enemies
			        for(Spaceship ship : enemies.values())
			        {
			        	if(!ship.getIsDestroyed())
			        	{
			        		g.setColor(ship.getColor());
			        		g.fillRect(ship.getXCoord(), ship.getYCoord(),
			        				   ship.getSSWidth(), ship.getSSHeight());
			        	}
			        }
			        
			        //Draw Player
			        g.setColor(player.getColor());
			        g.fillRect(player.getXCoord(),player.getYCoord(),
			        		   player.getSSWidth(),player.getSSHeight());
		        }
		        else
		        	scorebar.gameOver(g);
		        
		        break;
	        }
	        case 2:
	        {
	        	g.setColor(Color.white);
	        	centerString("YOU HAVE DEFEATED THE ALIENS",g,10);
	    		centerString("ALL HAIL THE HERO OF THE GALAXY",g,15);
	    		break;
	        }
	        default:
	        {
	        	System.out.println("gameState paint error");
	        	System.exit(1);
	        }
        }
    }
	public void printStartMenu(Graphics g)
	{
		g.setColor(Color.white);
    	
    	centerString("Welcome to",g,0);
    	centerString("ALIEN INVADERS",g,1);
    	
    	g.drawLine(201,125,295,125);
    	
    	centerString("ENTER - start the game",g,4);
    	centerString("ESCAPE - exit the game",g,5);
    	
    	centerString("LEFT ARROW - move left",g,6);
    	centerString("RIGHT ARROW - move right",g,7);
    	centerString("SPACEBAR - FIRE ZE MISSILE!",g,8);
	}
	public void centerString(String msg, Graphics g, int nLine)
	{
		int center, length;
		FontMetrics fm = g.getFontMetrics();
		
		length = fm.stringWidth(msg);
    	center = (w - length) / 2;
    	g.drawString(msg, center, 100+(nLine * 20));
	}
	public void setGameState(int gameState)
	{
		this.gameState = gameState;
	}
	public int getGameState()
	{
		return gameState;
	}
}
