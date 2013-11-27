import java.awt.*;
import java.util.ArrayList;

public class Projectile extends Spaceship
{
	private boolean isInvalid = false,
					isPlayerProjectile;
	
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
	}
	
	public void draw(Graphics g)
	{
		g.setColor(super.getColor());
    	g.fillOval(super.getXCoord()-5,super.getYCoord(),
    			   super.getSSWidth(),super.getSSHeight());
	}
	
	public void move(GamePanel gp,ArrayList<Spaceship> ships,Player player)
	{
		int w = gp.getHeight(),
			yCoord = super.getYCoord();
		
		super.setYCoord(yCoord + super.getYVel());
		
		if(yCoord > w || yCoord < 0)
			isInvalid = true;
		else
			isInvalid = false;
		
		checkCollision(ships,player);
	}
	
	public void checkCollision(ArrayList<Spaceship> ships, Player player)
	{
		ships.add(player);
		
		//boundaries
		int xBoundLow, xBoundHigh, yBoundLow, yBoundHigh;
		
		//coords; treat projectiles as point particles at their
		//centers
		int xCenter = this.getXCenter(),
			yCenter = this.getYCenter();
		
		if(isPlayerProjectile)
		{
			for(Spaceship s : ships)
			{
				if(s.getIsDestroyed())
					continue;
				
				xBoundLow = s.getXCoord();
				xBoundHigh = xBoundLow + s.getSSWidth();
				yBoundLow = s.getYCoord();
				yBoundHigh = yBoundLow+s.getSSHeight();
				
				if(xCenter > xBoundLow && xCenter < xBoundHigh &&
				   yCenter > yBoundLow && yCenter < yBoundHigh)
				{
					player.addScore(s.destroy());
					this.isInvalid = true;
				}
			}
		}
		else
		{
			xBoundLow = player.getXCoord();
			xBoundHigh = xBoundLow + player.getSSWidth();
			yBoundLow = player.getYCoord();
			yBoundHigh = yBoundLow + player.getSSHeight();
			
			if(xCenter > xBoundLow && xCenter < xBoundHigh &&
			   yCenter > yBoundLow && yCenter < yBoundHigh)
			{
				player.setIsDestroyed(true);
				this.isInvalid = true;
			}
		}
	}
	
	public int getXCenter()
	{
		return super.getXCoord() + 5;
	}
	public int getYCenter()
	{
		return super.getYCoord() + 5;
	}
	public void setIsPlayerProjectile(boolean isPlayerProjectile)
	{
		this.isPlayerProjectile = isPlayerProjectile;
	}
	public boolean getIsPlayerProjectile()
	{
		return isPlayerProjectile;
	}
	public void setIsInvalid(boolean isInvalid)
	{
		this.isInvalid = isInvalid;
	}
	public boolean getIsInvalid()
	{
		return isInvalid;
	}
}
