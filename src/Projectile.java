import java.awt.*;
import java.util.ArrayList;

import java.awt.image.BufferedImage;
import java.io.*;

import javax.imageio.ImageIO;


public class Projectile extends SpaceObject
{
	private boolean isInvalid,
					isPlayerProjectile;
	
	private BufferedImage missile = null;
	
	/**
	 * empty constructor
	 */
	public Projectile(){}
	/**
	 * Main Constructor calls parent constructor (x,y) position;
	 * sets immutable projectile speed (based on isPlayerProjectile),
	 * and shape and color.
	 * 
	 * @param xCoord
	 * @param yCoord
	 * @param isPlayerProjectile
	 */
	public Projectile(int xCoord, int yCoord, 
					  boolean isPlayerProjectile)
	{
		//instantiate a new projectile
		super(xCoord,yCoord);
		
		//Projectiles are always circles with radius 10; 
		super.setSOWidth(11);
		super.setSOHeight(15);
		
		//projectiles only move vertically.
		super.setXVel(0);
		
		if(isPlayerProjectile)
			super.setYVel(-6);
		else
			super.setYVel(4);
		
		this.isPlayerProjectile = isPlayerProjectile;
		this.isInvalid = false;
		
		//load projectile image
		String dir;
		if(isPlayerProjectile)
			dir = "missile.bmp";
		else
			dir = "enemymissile.bmp";
		
		super.setImage(dir);
		
			
	}
	
	public void draw(Graphics g)
	{
		Image missile = super.getImage();
		
		g.drawImage(missile,super.getXCoord()-5,super.getYCoord(),this);
	}
	/**
	 * move method moves the projectile vertically.
	 * @param gp GamePanel gp for obtaining width.
	 * @param ships ArrayList<Spaceship> containing all enemies' data
	 * @param player Player object representing player
	 */
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
	/**
	 * checkCollision method takes the current list of ships (thus their positions)
	 * and checks whether or not a projectile (treated as a point particle with
	 * respect to its center) has collided with the hitbox.
	 * 
	 * @param ships ArrayList<Spaceship> holding current enemies
	 * @param player Player object representing the player
	 */
	public void checkCollision(ArrayList<Spaceship> ships, Player player)
	{
		//boundaries
		int xBoundLow, xBoundHigh, yBoundLow, yBoundHigh;
		
		//coords; treat projectiles as point particles at their
		//centers
		int xCenter = this.getXCenter(),
			yCenter = this.getYCenter();
		
		/*
		 * if it's a player projectile, check against the list of ships.
		 * 
		 * if it's an enemy projectile, check against the player data.
		 */
		if(isPlayerProjectile)
		{
			for(Spaceship s : ships)
			{
				if(s.getIsDestroyed())
					continue;
				
				xBoundLow = s.getXCoord();
				xBoundHigh = xBoundLow + s.getSOWidth();
				yBoundLow = s.getYCoord();
				yBoundHigh = yBoundLow+s.getSOHeight();
				
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
			xBoundHigh = xBoundLow + player.getSOWidth();
			yBoundLow = player.getYCoord();
			yBoundHigh = yBoundLow + player.getSOHeight();
			
			if(xCenter > xBoundLow && xCenter < xBoundHigh &&
			   yCenter > yBoundLow && yCenter < yBoundHigh)
			{
				if(player.getNLives() > 0)
					player.addLives(-1);
				else
					player.setIsDestroyed(true);
				
				this.isInvalid = true;
			}
		}
	}
	/**
	 * getXCenter method returns the current x coord of the center of the projectile
	 * @return xCenter
	 */
	public int getXCenter()
	{
		return super.getXCoord() + 5;
	}
	/**
	 * getYCenter method returns the current y coord of the center of the projectile
	 * @return
	 */
	public int getYCenter()
	{
		return super.getYCoord() + 5;
	}
	/**
	 * setIsPlayerProjectile method sets isPlayerProjectile
	 * @param isPlayerProjectile
	 */
	public void setIsPlayerProjectile(boolean isPlayerProjectile)
	{
		this.isPlayerProjectile = isPlayerProjectile;
	}
	/**
	 * getIsPlayerProjectile method returns isPlayerProjectile
	 * @return
	 */
	public boolean getIsPlayerProjectile()
	{
		return isPlayerProjectile;
	}
	/**
	 * setIsInvalid method sets isInvalid
	 * @param isInvalid
	 */
	public void setIsInvalid(boolean isInvalid)
	{
		this.isInvalid = isInvalid;
	}
	/**
	 * getIsInvalid method returns isInvalid
	 * @return
	 */
	public boolean getIsInvalid()
	{
		return isInvalid;
	}
}
