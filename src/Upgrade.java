import java.awt.*;
import java.util.Random;

public class Upgrade extends SpaceObject
{
	
	private int upgradeType;
	private boolean isClaimed = false,
					isInvalid = false;
	
	private Random r = new Random();
	
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
	
	public int setStartXCoord()
	{
		int x,sign = 1;
		double ran = r.nextGaussian();
		
		if(ran*2 > 1)
			sign *= -1;
		
		x = (int)(250 - (sign*(ran * 250 ))/250);
		
		return x;
	}
	
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
	
	public void checkCollision(Player player)
	{
		int xCenter = super.getXCoord() + (super.getSOWidth() / 2),
			yCenter = super.getYCoord() + (super.getSOHeight() / 2),
			xBoundLow = player.getXCoord(),
			xBoundHigh = xBoundLow + player.getSOWidth(),
			yBoundLow = player.getYCoord(),
			yBoundHigh = yBoundLow+player.getSOHeight();
			
		if(xCenter > xBoundLow && xCenter < xBoundHigh &&
		   yCenter > yBoundLow && yCenter < yBoundHigh)
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
