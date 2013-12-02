/** 
     * Cogan Shimizu
     * CS-1180L-90
     * Kurtis Glendenning
     * Michael Ondrasek
     * 
     * PURPOSE:
     * This class describes the enemies against whom the
     * player faces. It extends the SpaceObject class
     * thus inheriting general position data and methods.
     * 
     * Adds a fire method and a destroy method.
     */

import java.awt.*;
import javax.swing.*;

public class Spaceship extends SpaceObject
{
	private boolean isDestroyed = false;
	private boolean hasProjectile = false;
	
	/**
	 * Empty Constructor
	 */
	public Spaceship(){}
	
	/**
	 * calls parent constructor- (x,y) for initial position
	 * @param xCoord
	 * @param yCoord
	 */
	public Spaceship(int xCoord, int yCoord)
	{
		super(xCoord,yCoord);
		
		int width = super.getSOWidth();
		
		String imageName;
		if(width == 55)
			imageName = "Enemy1.bmp";
		else
			imageName = "Enemy2.bmp";
		
		super.setImage(imageName);
			
	}
	/**
	 * fire method instantiates and returns a new projectile at center of 
	 * the firing entity's current x center, and top or bottom depending on
	 * isPlayerProjectile; it also ensures that this specific entity
	 * hasProjectile.
	 * 
	 * @param isPlayerProjectile boolean for denoting whether or not isPlayerProjectile
	 * @return Projectile p
	 */
	public Projectile fire(boolean isPlayerProjectile)
	{
		int xCoord = super.getXCoord(),
			yCoord = super.getYCoord(),
			ssWidth = super.getSOWidth(),
			soHeight = super.getSOHeight(),		
			centerX = xCoord + ssWidth/2,
		    bottomY = isPlayerProjectile ? yCoord : yCoord + soHeight;
		
		Projectile p = new Projectile(centerX,bottomY,isPlayerProjectile);
		
		this.hasProjectile = true;
		
		return p;
	}
	/**
	 * the destroy method destroys this ship by setting isDestroyed = true
	 * and adds a score based on its size and speed.
	 * @return
	 */
	public int destroy()
	{
		this.isDestroyed = true;
		
		int score;
		
		score = Math.abs(super.getXVel());
		
		score *= 1 + (55 - super.getSOWidth())/55;
		
		return score;
	}
	/**
	 * draw method void-returning method draws the spaceship
	 * @param g
	 */
	public void draw(Graphics g)
	{
		Image image = super.getImage();
		g.drawImage(image,super.getXCoord(),super.getYCoord(),this);
	}
	/**
	 * setIsDestroyed method sets isDestroyed
	 * @param isDestroyed
	 */
	public void setIsDestroyed(boolean isDestroyed)
	{
		this.isDestroyed = isDestroyed;
	}
	/**
	 * getIsDestroyed method returns isDestroyed
	 * @return
	 */
	public boolean getIsDestroyed()
	{
		return isDestroyed;
	}
	/**
	 * setHasProjectile method sets hasProjectile
	 */
	public void setHasProjectile(boolean hasProjectile)
	{
		this.hasProjectile = hasProjectile;
	}
	/**
	 * getHasProjectile method returns hasProjectile
	 * @return hasProjectile
	 */
	public boolean getHasProjectile()
	{
		return hasProjectile;
	}
}