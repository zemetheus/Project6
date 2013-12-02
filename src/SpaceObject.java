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
	
	public void move(int xBound, int yBound)
	{
		xCoord += xVel;
		yCoord += yVel;
		
		checkBounds(xBound,yBound);
	}
	
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
	//public int setStartYVel(){}
	public void setYVel(int yVel)
	{
		this.yVel = yVel;
	}
	public int getYVel()
	{
		return yVel;
	}
	public static void newGameResetID()
	{
		ID = 0;
	}
	public static void resetID()
	{
		ID = 1; //player always has 0
	}
}
