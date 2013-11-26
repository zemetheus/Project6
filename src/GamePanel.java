import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.*;
import java.util.ArrayList;
import java.util.HashMap;

public class GamePanel extends JPanel
{
	Scorebar scorebar = new Scorebar();
	
	HashMap<Integer,Spaceship> enemies = new HashMap<>();
	HashMap<Integer,Projectile> projectiles = new HashMap<>();
	ArrayList<Integer> outOfRangeProjectiles = new ArrayList<>();
	
	Player player;
	
	final int w, h;
	
	public GamePanel(final int w, final int h)
	{
		super();
		
		this.w = w;
		this.h = h;
		
		
		
		//player is always hxw 30x50
		player = new Player(235,650);
		
		Spaceship s = new Spaceship(50,50);
		
		enemies.put(s.getShipID(),s);
		s = new Spaceship(100,200);
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
	
	public void move(GamePanel gamePanel)
	  {
		Projectile temp;
		
		//player movement is handled by the KeyListener/Handler
		
		//move projectiles
		if(!projectiles.isEmpty())
		{
			for(Integer i : projectiles.keySet())
			{
				temp = projectiles.get(i);
				
				//move projectiles, also flags projectiles for being out of range
				temp.move(gamePanel);
				
				temp.checkCollision(new ArrayList<Spaceship>(enemies.values()),player);
				
				//make note of outOfRange projectiles
				if(temp.getIsOutOfRange())
					outOfRangeProjectiles.add(i);
				
			}
		}
		
		
		//remove flagged projectiles
		for(int i : outOfRangeProjectiles)
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
		outOfRangeProjectiles.clear();
		  
		//move enemies
	    for(Spaceship s : enemies.values())
	    {
	   		s.move(w,h);
	   		
	   		if(!s.getHasProjectile())
	   		{
	   			Projectile p = s.fire();
	   			
	   			projectiles.put(s.getShipID(),p);
	   		}
	    }
	    
	 
	  		
	    
	    repaint();
	  }
	
	public void paintComponent(Graphics g)
  	{
  		super.paintComponent(g);
  		//set color black
        g.setColor(Color.black);
        //paint background
        g.fillRect(0, 0, getWidth(), getHeight());
        
        //draw Scorebar
        
        scorebar.drawScorebar(g);
        
        
        //draw projectiles
        if(!projectiles.isEmpty())
           	for(Projectile p : projectiles.values())
		        p.draw(g);
        
        //Draw Enemies
        for(Spaceship ship : enemies.values())
        {
        	g.setColor(ship.getColor());
            g.fillRect(ship.getXCoord(), ship.getYCoord(),
                ship.getSSWidth(), ship.getSSHeight());
        }
        
        //Draw Player
        g.setColor(player.getColor());
        g.fillRect(player.getXCoord(),player.getYCoord(),
        		   player.getSSWidth(),player.getSSHeight());
        
     }
}
