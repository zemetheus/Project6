import java.awt.*;
import javax.swing.*;

public class Projectile extends Spaceship
{
	private boolean isOutOfRange = false;
	
	public Projectile(){}
	
	public Projectile(int xCoord, int yCoord)
	{
		//instantiate a new projectile
		super(xCoord,yCoord);
		
		//Projectiles are always circles with radius 10; 
		super.setSSWidth(10);
		super.setSSHeight(10);
		
		//projectiles only move vertically towards the bottom.
		super.setXVel(0);
		super.setYVel(6);
		
		//projectiles are always white
		super.setColor(new Color(255,255,255));
	}
	
	public void move(GamePanel gp)
	{
		int w = gp.getHeight();
		
		super.setYCoord(super.getYCoord() + super.getYVel());
		
		if(super.getYCoord() > w)
			isOutOfRange = true;
		else
			isOutOfRange = false;
	}
	
	public boolean getIsOutOfRange()
	{
		return isOutOfRange;
	}
}
