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
	
	private int level;
	
	public GamePanel(final int w, final int h, int level)
	{
		super();
		
		this.w = w;
		this.h = h;		
		
		this.level = level;
		
		//player is always hxw 30x50
		player = new Player(235,650);
		Spaceship s;
		
		for(int i=0;i<level;i++)
		{
			s = new Spaceship(40,40*(i+1));
			enemies.put(s.getShipID(),s);
		}
	
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
	
	public boolean move(GamePanel gamePanel)
	  {
		Projectile p;
		
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
			return allEnemiesDestroyed;
		
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
	    
	    return allEnemiesDestroyed;
	  }
	
	public void paintComponent(Graphics g)
  	{
  		super.paintComponent(g);
  		//set color black
        g.setColor(Color.black);
        //paint background
        g.fillRect(0, 0, getWidth(), getHeight());
        
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
     }
}
