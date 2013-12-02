/** 
     * Cogan Shimizu
     * CS-1180L-90
     * Kurtis Glendenning
     * Michael Ondrasek
     * 
     * PURPOSE:
     * This class is a parent class and never instantiated. It provides
     * basic position and speed tracking, as well as an interface
     * for image assignment. This class also provides a static integer
     * for tracking entity ids- thus matching projectiles to 
     * their respective ships ensuring that no object extended from
     * this class may have more than one projectile.
     */

import java.awt.*;
import javax.swing.*;
import java.util.Random;
import java.awt.image.BufferedImage;
import java.io.*;

import javax.imageio.ImageIO;

public class SpaceObject extends JPanel
{
	private static int ID = 0;
	private int entityID;
	
	
	private int xCoord,
				yCoord,
				soHeight,
				soWidth,
				xVel,
				yVel;
	
	private BufferedImage image = null;
	
	/**
	 * Empty Constructor
	 */
	public SpaceObject(){}
	/**
	 * default constructor- width is random between 55 and 80 (roughly equal chance)
	 * height is always 30px.
	 * 
	 * @param xCoord int initial x coordinate
	 * @param yCoord int initial y coordinate
	 */
	public SpaceObject(int xCoord, int yCoord)
	{
		this.xCoord = xCoord;
		this.yCoord = yCoord;
		this.soHeight = setStartHeight();
		this.soWidth = setStartWidth();
		this.xVel = setStartXVel();
		this.yVel = 0;
		
		this.entityID = ID;
		ID++;
	}
	/**
	 * move method provides a basic movement method for this class's 
	 * children; xCoord and yCoord positions are increased, respectively,
	 * by the xVel and yVel magnitudes; ensuring they don't move out of bounds.
	 */
	public void move(int xBound, int yBound)
	{
		xCoord += xVel;
		yCoord += yVel;
		
		checkBounds(xBound,yBound);
	}
	/**
	 * checkBounds method ensures that nothing moves off the screen; it
	 * will reverse directional movement if necessary.
	 * @param w x boundary integer
	 * @param h y boundary integer
	 */
	public void checkBounds(int w, int h)
	{
		if(xCoord > (w - soWidth))
		{
			xVel *= -1;
		    xCoord = w - soWidth;
		}
		if(xCoord < 0)
		{
			xVel *= -1;
			xCoord = 0;
		}
		if(yCoord > (h-soHeight))
		{
			yVel *= -1;
			yCoord = h - soHeight;
		}
		if(yCoord < 0)
		{
			yVel *= -1;
			yCoord = 0;
		}
	}
	/**
	 * setImage method assigns an image to this object based on its width.
	 * @param imageName
	 */
	public void setImage(String imageName)
	{
		String dir = "Images/" + imageName;
		
		try
		{
			image = ImageIO.read(new File(dir));
		}
		catch(IOException e)
		{
			System.out.println(imageName);
			e.printStackTrace();
			System.exit(0);
		}
	}
	/**
	 * getImage method returns the image assigned to this object
	 * @return image
	 */
	public Image getImage()
	{
		return image;
	}
	/**
	 * setShipID sets projectileID
	 * @param projectileID
	 */
	public void setEntityID(int projectileID)
	{
		this.entityID = projectileID;
	}
	/**
	 * getShipID method returns shipID
	 * @return
	 */
	public int getEntityID()
	{
		return entityID;
	}
	/**
	 * setXCoord method sets xCoord
	 * @param xCoord
	 */
	public void setXCoord(int xCoord)
	{
		this.xCoord = xCoord;
	}
	/**
	 * getXCoord method returns xCoord
	 * @return
	 */
	public int getXCoord()
	{
		return xCoord;
	}
	/**
	 * setYCoord method sets yCoord
	 * @param yCoord
	 */
	public void setYCoord(int yCoord)
	{
		this.yCoord = yCoord;
	}
	/**
	 * getYCoord method returns yCoord
	 * @return
	 */
	public int getYCoord()
	{
		return yCoord;
	}
	/**
	 * the setStartHeight method sets a pseudorandom height for the spaceship
	 * @return height 
	 */
	public int setStartHeight()
	{
		int height;
		
		height = 30;
		
		return height;
	}
	/**
	 * setSOHeight method sets soHeight
	 * @param height
	 */
	public void setSOHeight(int height)
	{
		this.soHeight = height;
	}
	/**
	 * getSOHeight method returns soHeight
	 * @return soHeight
	 */
	public int getSOHeight()
	{
		return soHeight;
	}
	/**
	 * the setStartWidth method sets a pseudorandom width for the spaceship
	 * @return
	 */
	public int setStartWidth()
	{
		int width;
		
		int temp =  (new Random().nextGaussian() < .5) ? 55 : 80;
		
		width = temp;
		
		return width;
	}
	/**
	 * setSOWidth sets soWidth
	 * @param soWidth
	 */
	public void setSOWidth(int soWidth)
	{
		this.soWidth = soWidth;
	}
	/**
	 * getSOWidth method returns soWidth
	 * @return soWidth
	 */
	public int getSOWidth()
	{
		return soWidth;
	}
	/**
	 * setStartXVel method sets a random x component velocity between 1 and 11.
	 * @return
	 */
	public int setStartXVel()
	{
		int vel = (int)(1 + Math.random() * 10);
		
		return vel;
	}
	/**
	 * setXVel method sets xVel
	 * @param xVel
	 */
	public void setXVel(int xVel)
	{
		this.xVel = xVel;
	}
	/**
	 * getXVel method returns xVel
	 * @return xVel
	 */
	public int getXVel()
	{
		return xVel;
	}
	/**
	 * setYVel method sets int yVel
	 * @param yVel
	 */
	public void setYVel(int yVel)
	{
		this.yVel = yVel;
	}
	/**
	 * getYVel method returns int yVel (velocity in the y direction)
	 * @return
	 */
	public int getYVel()
	{
		return yVel;
	}
	/**
	 * newGameResetID method resets ID to 0 thus accounting for a new Player
	 */
	public static void newGameResetID()
	{
		ID = 0;
	}
	/**
	 * resetID method resets ID to 1, as Player does not change, only its position.
	 */
	public static void resetID()
	{
		ID = 1; //player always has 0
	}
}
