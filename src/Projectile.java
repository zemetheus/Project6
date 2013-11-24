import java.awt.*;
import javax.swing.*;

public class Projectile extends Spaceship
{
	private static int projectileID = 0;
	
	private boolean isOutOfRange = false,
					isPlayerProjectile;
	
	public Projectile(){}
	
	public Projectile(int xCoord, int yCoord, boolean isPlayerProjectile)
	{
		//instantiate a new projectile
		super(xCoord,yCoord);
		
		//Projectiles are always circles with radius 10; 
		super.setSSWidth(10);
		super.setSSHeight(10);
		
		//projectiles only move vertically towards the bottom.
		super.setXVel(0);
		
		if(isPlayerProjectile)
			super.setYVel(-6);
		else
			super.setYVel(6);
		
		//projectiles are always white
		super.setColor(new Color(255,255,255));
		
		this.isPlayerProjectile = isPlayerProjectile;
		
		projectileID++;
	}
	
	public void move(GamePanel gp)
	{
		int w = gp.getHeight(),
			yCoord = super.getYCoord();
		
		super.setYCoord(super.getYCoord() + super.getYVel());
		
		if(yCoord > w || yCoord < 0)
			isOutOfRange = true;
		else
			isOutOfRange = false;
	}

	public boolean getIsPlayerProjectile()
	{
		return isPlayerProjectile;
	}
	
	public int getProjectileID()
	{
		return projectileID;
	}
	
	public boolean getIsOutOfRange()
	{
		return isOutOfRange;
	}
}
