public class Player extends Spaceship
{
	private int score = 0;
	
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
	
	public Projectile fire()
	{
		int xCoord = super.getXCoord(),
			yCoord = super.getYCoord(),
			width = super.getSSWidth();
		
		int centerX = xCoord + width/2;
		
		Projectile p = new Projectile(centerX,yCoord,true);
		
		super.setHasProjectile(true);
		
		return p;
	}
	public void addScore(int score)
	{
		this.score += score;
	}
	public void setScore(int score)
	{
		this.score = score;
	}
	public int getScore()
	{
		return score;
	}
}
