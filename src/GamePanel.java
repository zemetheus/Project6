import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.*;
import java.util.ArrayList;
import java.util.HashMap;

public class GamePanel extends JPanel
{
	Scorebar scorebar = new Scorebar();
	
	ArrayList<Spaceship> enemies = new ArrayList<>();
	ArrayList<Projectile> projectiles = new ArrayList<>();
	ArrayList<Integer> outOfRangeProjectiles = new ArrayList<>();
	
	Player player;
	
	final int w, h;
	
	public GamePanel(final int w, final int h)
	{
		super();
		
		this.w = w;
		this.h = h;
		
		Spaceship s;
		
		//player is always hxw 30x50
		player = new Player(235,650);
		
		enemies.add(new Spaceship(50,50));
		enemies.add(new Spaceship(100,200));
	
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
							projectiles.add(player.fire());
						
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
		//player movement is handled by the KeyListener
		
		//move projectiles
		if(!projectiles.isEmpty())
		{
			for(Projectile p : projectiles)
			{
				//move projectiles, also flags projectiles for being out of range
				p.move(gamePanel);
				
				//make note of outOfRange projectiles
				if(p.getIsOutOfRange())
				{
					if(p.getIsPlayerProjectile())
						player.setHasProjectile(false);
					
					outOfRangeProjectiles.add(projectiles.indexOf(p));
				}
			}
		}
		
		//remove flagged projectiles
		
		//move enemies
	    for(Spaceship s : enemies)
	    {
	   		s.move(w,h);
	   		
	   		//remove flagged projectiles and check against current ships 
	   		//projectile ID. setHasProjectile to false if PID = s.getPID
	   		
	   		for(int i : outOfRangeProjectiles)
			{
				if(i >= 0)
				{
					if(s.getProjectileID() == projectiles.get(i).getProjectileID())
						s.setHasProjectile(false);
					
					projectiles.remove(i);
				}
					
			}
	   		
	   		if(!s.getHasProjectile())
	   		{
	   			Projectile p = s.fire();
	   			
	   			projectiles.add(p);
	   		}
	    }
	    
	  //clear all flags
	  outOfRangeProjectiles.clear();
	  		
	    
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
        {
	        for(Projectile p : projectiles)
	        {
	        	g.setColor(p.getColor());
	        	g.fillOval(p.getXCoord(),p.getYCoord(),p.getSSWidth(),p.getSSHeight());
	        }
        }
        //Draw Enemies
        for(Spaceship ship : enemies)
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
