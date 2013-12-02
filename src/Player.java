/** 
     * Cogan Shimizu
     * CS-1180L-90
     * Kurtis Glendenning
     * Michael Ondrasek
     * 
     * PURPOSE:
     * This class describes the player and extends the Spaceship class,
     * thus inheriting movement and image assignment functionality from
     * SpaceObject and fire/destroy from Spaceship.
     * 
     * movement however, is handled by the GamePanels KeyAdapter.
     */

public class Player extends Spaceship
{
	private int score = 0,
				nLives = 0;
	
	private boolean hasUpgrade = false;
	
	/**
	 * empty constructor
	 */
	public Player()
	{
		super();
	}
	/**
	 * Main Constructor for creating the player; takes initial (x,y) position,
	 * sets initial velocity to 0i+0k
	 * 
	 * @param xCoord
	 * @param yCoord
	 */
	public Player(int xCoord, int yCoord)
	{
		super(xCoord,yCoord);
		
		super.setSOWidth(55);
		super.setSOHeight(30);
		
		super.setXVel(0);
		super.setYVel(0);
		
		super.setImage("Player.bmp");
	}
	/**
	 * addLives method adds int lives to current nLives
	 * @param lives
	 */
	public void addLives(int lives)
	{
		this.nLives += lives;
	}
	/**
	 * setNLives method sets int nLives
	 * @param nLives
	 */
	public void setNLives(int nLives)
	{
		this.nLives = nLives;
	}
	/**
	 * getNLives method returns int nLives
	 * @return
	 */
	public int getNLives()
	{
		return nLives;
	}
	/**
	 * setHasUpgrade sets boolean hasUpgrade
	 * @param hasUpgrade
	 */
	public void setHasUpgrade(boolean hasUpgrade)
	{
		this.hasUpgrade = hasUpgrade;
	}
	/**
	 * getHasUpgrade method returns hasUpgrade
	 * @return
	 */
	public boolean getHasUpgrade()
	{
		return hasUpgrade;
	}
	/**
	 * addScore adds score to existing score value
	 * @param score
	 */
	public void addScore(int score)
	{
		this.score += score;
	}
	/**
	 * setScore method sets score
	 * @param score
	 */
	public void setScore(int score)
	{
		this.score = score;
	}
	/**
	 * getScore method returns score
	 * @return
	 */
	public int getScore()
	{
		return score;
	}
}
