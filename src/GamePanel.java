import java.awt.*;
import javax.swing.*;
import java.util.ArrayList;

public class GamePanel extends JPanel
{
	Scorebar scorebar = new Scorebar();
	
	ArrayList<Spaceship> enemies = new ArrayList<>();
	ArrayList<Projectile> projectiles = new ArrayList<>();
	ArrayList<Integer> outOfRangeProjectiles = new ArrayList<>();
	
	Player player;
	
	public GamePanel()
	{
		super();
		
		//player is always hxw 30x50
		player = new Player(235,650);
		
		enemies.add(new Spaceship(50,50));
		enemies.add(new Spaceship(100,200));
	}
	
	public void move(GamePanel gamePanel)
	  {
		if(!projectiles.isEmpty())
		{
			for(Projectile p : projectiles)
			{
				//move projectiles, also flags projectiles for being out of range
				p.move(gamePanel);
				
				//make note of outOfRange projectiles
				if(p.getIsOutOfRange())
					outOfRangeProjectiles.add(projectiles.indexOf(p));
			}
		}
		
		//remove flagged projectiles
		for(int i : outOfRangeProjectiles)
		{
			if(i >= 0)
				projectiles.remove(i);
		}
		//clear all flags
		outOfRangeProjectiles.clear();
		
		
	    for(Spaceship s : enemies)
	    {
	   		s.move(gamePanel);
	   		
	   		if(!s.getHasProjectile())
	   		{
	   			projectiles.add(s.fire());
	   			s.setHasProjectile(true);
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
        g.drawString("String", 30, 600);
        
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
