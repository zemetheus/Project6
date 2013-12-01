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
		
		super.setSOWidth(50);
		super.setSOHeight(30);
		
		super.setXVel(0);
		super.setYVel(0);
		
		super.setColor(super.setStartColor());
	}
	public void addLives(int lives)
	{
		this.nLives += lives;
	}
	public void setNLives(int nLives)
	{
		this.nLives = nLives;
	}
	public int getNLives()
	{
		return nLives;
	}
	public void setHasUpgrade(boolean hasUpgrade)
	{
		this.hasUpgrade = hasUpgrade;
	}
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
