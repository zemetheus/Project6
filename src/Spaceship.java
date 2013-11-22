import java.awt.*;

import javax.swing.*;

public class Spaceship extends JPanel
{
	private int xCoord,
				yCoord,
				ssHeight,
				ssWidth,
				xVel,
				yVel;
	private Color color;
	
	private boolean isDestroyed = false;
	
	/**
	 * Empty Constructor
	 */
	public Spaceship(){}
	
	public Spaceship(int xCoord, int yCoord)
	{
		this.xCoord = xCoord;
		this.yCoord = yCoord;
		this.ssHeight = setStartHeight();
		this.ssWidth = setStartWidth();
		this.xVel = setStartXVel();
		this.yVel = 0;
		this.color = setStartColor();
	}
	
	public void move(GamePanel gp)
	{
		int w = gp.getWidth(),
		    h = gp.getHeight();
		
		xCoord += xVel;
		if(xCoord < 0 || xCoord > (w-ssWidth))
			xVel *= -1;
		if(xCoord > (w - ssWidth))
		    xCoord = w - ssWidth;
		if(xCoord < 0)
			xCoord = 0;
		
		yCoord += yVel;
		if(yCoord > (h-ssHeight))
		{
			yVel *= -1;
			yCoord = h - ssHeight;
		}
		if(yCoord < 0)
		{
			yVel *= -1;
			yCoord = 0;
		}
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
		
		height = 50;
		
		return height;
	}
	/**
	 * setSSHeight method sets ssHeight
	 * @param height
	 */
	public void setSSHeight(int height)
	{
		this.ssHeight = height;
	}
	/**
	 * getSSHeight method returns ssHeight
	 * @return ssHeight
	 */
	public int getSSHeight()
	{
		return ssHeight;
	}
	/**
	 * the setStartWidth method sets a pseudorandom width for the spaceship
	 * @return
	 */
	public int setStartWidth()
	{
		int width;
		
		width = 30;
		
		return width;
	}
	/**
	 * setSSWidth sets ssWidth
	 * @param ssWidth
	 */
	public void setSSWidth(int ssWidth)
	{
		this.ssWidth = ssWidth;
	}
	/**
	 * getSSWidth method returns ssWidth
	 * @return ssWidth
	 */
	public int getSSWidth()
	{
		return ssWidth;
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
	 * the setStartColor sets a random color to the spaceship
	 * @return Color color
	 */
	public Color setStartColor()
	{
		int red = (int) (Math.random() * 256);
	    int green = (int) (Math.random() * 256);
	    int blue = (int) (Math.random() * 256);
	    Color ranColor = new Color(red, green, blue);
	    
	    return ranColor;
	}
	/**
	 * setColor method sets this.color
	 * @param color
	 */
	public void setColor(Color color)
	{
		this.color = color;
	}
	/**
	 * getColor method return this.color
	 * @return color
	 */
	public Color getColor()
	{
		return color;
	}
}