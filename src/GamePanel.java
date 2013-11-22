import java.awt.*;
import javax.swing.*;
import java.util.ArrayList;

public class GamePanel extends JPanel
{
	ArrayList<Spaceship> enemies = new ArrayList<>();
	ArrayList<Projectile> projectiles = new ArrayList<>();
	Player player = new Player();
	
	public GamePanel()
	{
		super();
		enemies.add(new Spaceship(50,50));
	}
	
	public void move(GamePanel gamePanel)
	  {
	    for(Spaceship s : enemies)
	   		s.move(gamePanel);
	    repaint();
	  }
	
	public void paintComponent(Graphics g)
  	{
  		super.paintComponent(g);
  		//set color black
        g.setColor(Color.black);
        //paint background
        g.fillRect(0, 0, getWidth(), getHeight());
        
        /*
        for(Projectile p : projectiles)
        {
        	g.setColor(p.getColor());
        }
        */
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
