import java.awt.*;
import javax.swing.*;

public class Player extends Spaceship
{
	private boolean hasProjectile = false;
	
	public Player()
	{
		super();
	}
	
	
	public Player(int xCoord, int yCoord)
	{
		super(xCoord,yCoord);
		
		super.setSSWidth(50);
		super.setSSHeight(30);
		
		super.setXVel(0);
		super.setYVel(0);
		
		super.setColor(super.setStartColor());
	}
}
