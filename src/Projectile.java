import java.awt.*;
import java.util.ArrayList;

public class Projectile extends Spaceship
{
	private boolean isOutOfRange = false,
					isPlayerProjectile;
	
	private int xCenter, yCenter;
	
	public Projectile(){}
	
	public Projectile(int xCoord, int yCoord, 
					  boolean isPlayerProjectile)
	{
		//instantiate a new projectile
		super(xCoord,yCoord);
		
		//Projectiles are always circles with radius 10; 
		super.setSSWidth(10);
		super.setSSHeight(10);
		
		//projectiles only move vertically.
		super.setXVel(0);
		
		if(isPlayerProjectile)
			super.setYVel(-6);
		else
			super.setYVel(4);
		
		//projectiles are always white
		super.setColor(new Color(255,255,255));
		
		this.isPlayerProjectile = isPlayerProjectile;
		
		this.xCenter = xCoord+5;
		this.yCenter = yCoord+5;
	}
	
	public void draw(Graphics g)
	{
		g.setColor(super.getColor());
    	g.fillOval(super.getXCoord()-5,super.getYCoord(),
    			   super.getSSWidth(),super.getSSHeight());
	}
	
	public void move(GamePanel gp)
	{
		int w = gp.getHeight(),
			yCoord = super.getYCoord();
		
		super.setYCoord(yCoord + super.getYVel());
		
		if(yCoord > w || yCoord < 0)
			isOutOfRange = true;
		else
			isOutOfRange = false;
	}
	
	public void checkCollision(ArrayList<Spaceship> ships, Player player)
	{
		ships.add(player);
		
		//boundaries
		int xBoundLow, xBoundHigh, yBoundLow, yBoundHigh;
		
		for(Spaceship s : ships)
		{
			xBoundLow = s.getXCoord();
			xBoundHigh = xBoundLow + s.getSSWidth();
			yBoundLow = s.getYCoord();
			yBoundHigh = yBoundLow+s.getSSHeight();
			
			if(xCenter > xBoundLow && xCenter < xBoundHigh &&
			   yCenter > yBoundLow && yCenter < yBoundHigh)
			{
				s.setIsDestroyed(true);
				this.isOutOfRange = true;
			}
		}
	}
	
	public int getXCenter()
	{
		return xCenter;
	}
	public int getYCenter()
	{
		return yCenter;
	}
	public boolean getIsPlayerProjectile()
	{
		return isPlayerProjectile;
	}
	public boolean getIsOutOfRange()
	{
		return isOutOfRange;
	}
}
