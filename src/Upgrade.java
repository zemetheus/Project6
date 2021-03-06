import java.awt.*;
import java.util.Random;

public class Upgrade extends SpaceObject
{
	
	private int upgradeType;
	private boolean isClaimed = false,
					isInvalid = false;
	
	private Random r = new Random();
	
	/**
	 * default constructor: upgrades
	 * have a definite size and y starting position and y vel;
	 * they do not move in the x direction.
	 * 
	 * upgradeType is determined at instantiation with a roughly
	 * equal chance of bonus points or extra life.
	 * 
	 * also sets image. Upgrade images are titled according to
	 * 
	 * upgrade + n + .bmp
	 */
	public Upgrade()
	{
		super();
		
		super.setXCoord(setStartXCoord());
		super.setYCoord(15);
		
		super.setSOHeight(20);
		super.setSOWidth(25);
		
		super.setXVel(0);
		super.setYVel(5);
		
		upgradeType = ((r.nextGaussian() * 10) > 5) ? 0 : 1;
		
		super.setImage("upgrade"+(upgradeType+1)+".bmp");
	}
	/**
	 * setStartXCoord method sets the starting x coord at a gaussian
	 * distribution about the center of the window width.
	 * 
	 * @return starting xCoord
	 */
	public int setStartXCoord()
	{
		int x,sign = 1;
		double ran = r.nextGaussian();
		
		if(ran*2 > 1)
			sign *= -1;
		
		x = (int)(250 - (sign*(ran * 250 ))/250);
		
		return x;
	}
	/**
	 * move method moves the upgrade vertically downwards towards the player
	 * checking for the player collision, and flagging for removal
	 * if it passes out of y or x bound.
	 * 
	 * @param xBound int x boundary
	 * @param yBound int y boundary
	 * @param player Player object holding player data
	 */
	public void move(int xBound, int yBound, Player player)
	{
		checkCollision(player);
		
		int yCoord = super.getYCoord(),
			height = super.getSOHeight();
		
		
		super.setYCoord(yCoord + super.getYVel());
		
		if(yCoord > (yBound-height))
		{
			isInvalid = true;
		}
		
	}
	/**
	 * checkCollision method checks for collision with the player, as the
	 * upgrades are non-interactable with enemies; this method
	 * will also convey unto the player the appropriate award and
	 * then flags the upgrade as isClaimed (marked for removal).
	 * 
	 * @param player Player object holding player data.
	 */
	public void checkCollision(Player player)
	{
		int xCenter1 = super.getXCoord() + (super.getSOWidth() / 2),
			yCenter1 = super.getYCoord() + (super.getSOHeight() / 2),
			xCenter2 = player.getXCoord() + (super.getSOWidth() / 2),
			yCenter2 = player.getYCoord() + (super.getSOHeight() / 2),
			xRadius1 = super.getSOWidth() / 2,
			yRadius1 = super.getSOHeight() / 2,
			xRadius2 = player.getSOWidth() / 2,
			yRadius2 = player.getSOHeight() / 2,
			dx = Math.abs(xCenter1 - xCenter2),
			dy = Math.abs(yCenter1 - yCenter2),
			rx = xRadius1 + xRadius2,
			ry = yRadius1 + yRadius2;
			
		if(dx < rx && dy < ry)
		{
			this.isClaimed = true;
			
			switch(upgradeType)
			{
				case 0:
				{
					player.addScore(30);
					break;
				}
				case 1:
				{
					player.addScore(15);
					player.addLives(1);
					break;
				}
				default:
				{
					System.out.println("upgradeType effect error");
					System.exit(1);
				}
			
			}
		}
	}
	/**
	 * draw method draws the specified upgrade image to the screen at the appropriate 
	 * coordinates.
	 * @param g
	 */
	public void draw(Graphics g)
	{
		int xCoord = super.getXCoord(),
			yCoord = super.getYCoord();
		
		Image image = super.getImage();
		
		g.drawImage(image,xCoord,yCoord,this);
	}
	/**
	 * setIsClaimed method sets isClaimed
	 * @param isClaimed boolean
	 */
	public void setIsClaimed(boolean isClaimed)
	{
		this.isClaimed = isClaimed;
	}
	/**
	 * getIsClaimed method returns isClaimed
	 * @return isClaimed boolean
	 */
	public boolean getIsClaimed()
	{
		return isClaimed;
	}
	/**
	 * setIsInvalid method sets boolean isInvalid
	 * @param isInvalid
	 */
	public void setIsInvalid(boolean isInvalid)
	{
		this.isInvalid = isInvalid;
	}
	/**
	 * getIsInvalid method returns boolean isInvalid
	 * @return
	 */
	public boolean getIsInvalid()
	{
		return isInvalid;
	}
	
}
