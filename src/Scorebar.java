import java.awt.*;

import javax.swing.*;

public class Scorebar extends JPanel
{
	private static final String SCORE = "Score: ",
								LEVEL = "Level: ",
								LIVES = "Lives: ";
	
	private static final int SCORE_WIDTH = 38,
						     LEVEL_WIDTH = 35,
						     LIVES_WIDTH = 35;
	
	private int scoreValue = 0,
				levelValue = 1,
				livesValue = 0;
	
	private String scoreString = "0",
				   levelString = "1",
				   livesString = "0";
	
	
	/**
	 * default constructor
	 */
	public Scorebar()
	{
		
	}
	/**
	 * drawScorebar method draws the score bar.
	 * @param g
	 */
	public void drawScorebar(Graphics g)
	{
		g.setColor(Color.white);
        g.drawString(SCORE, 10, 600);
        g.drawString(LEVEL, 10, 615);
        g.drawString(LIVES, 10, 630);
        g.drawString(scoreString, 10+SCORE_WIDTH,600);
        g.drawString(levelString, 10+LEVEL_WIDTH,615);
        g.drawString(livesString, 10+LIVES_WIDTH,630);
	}
	/**
	 * drawStartMenu method draws the start menu
	 * @param g
	 */
	public void drawStartMenu(Graphics g)
	{
		g.setColor(Color.white);
    	
    	centerString("Welcome to",g,0);
    	centerString("ALIEN INVADERS",g,1);
    	
    	g.drawLine(201,125,295,125);
    	
    	centerString("ENTER - start the game",g,4);
    	centerString("ESCAPE - exit the game",g,5);
    	
    	centerString("LEFT ARROW - move left",g,6);
    	centerString("RIGHT ARROW - move right",g,7);
    	centerString("SPACEBAR - FIRE ZE MISSILE!",g,8);
	}
	public void drawVictoryScreen(Graphics g,int highlight)
	{
		g.setColor(Color.white);
    	centerString("YOU HAVE DEFEATED THE ALIENS",g,10);
		centerString("ALL HAIL THE HERO OF THE GALAXY",g,11);
		drawHighlight(g,highlight);
		drawReplay(g);
	}
	
	public void centerString(String msg, Graphics g, int nLine)
	{
		int center, length;
		FontMetrics fm = g.getFontMetrics();
		
		length = fm.stringWidth(msg);
    	center = (500 - length) / 2;
    	g.drawString(msg, center, 100+(nLine * 20));
	}
	
	public void drawGameOverScreen(Graphics g,int highlight)
	{
		g.setColor(Color.white);
		centerString("You have died!",g, 10);
		g.setColor(Color.gray);
		drawHighlight(g,highlight);
		drawReplay(g);
	}
	public void drawHighlight(Graphics g, int highlight)
	{
		g.setColor(Color.gray);
		switch(highlight)
		{
			case 0:
			{
				g.fillRect(205,365,35,20);
				break;
			}
			case 1:
			{
				g.fillRect(260,365,35,20);
				break;
			}
			default:
			{
				System.out.println("Highlight Replay Error");
				System.exit(1);
			}
		}
	}
	public void drawReplay(Graphics g)
	{
		g.setColor(Color.white);
		centerString("Replay?", g, 12);
		centerString("YES            NO",g,14);
	}
	
	public void updateScorebar(int score, boolean hasUpgrade)
	{
		this.scoreValue = score;
		
		if(hasUpgrade)
			addLivesValue(1);
		
	}
	public void addLivesValue(int livesValue)
	{
		this.livesValue += livesValue;
		this.livesString = Integer.toString(livesValue);
	}
	/**
	 * the setScore method sets score
	 * 
	 * @param score
	 */
	public void setScore(int score)
	{
		this.scoreValue = score;
		this.scoreString = Integer.toString(scoreValue);
	}
	/**
	 * the setScore method sets score
	 * @param score
	 */
	public void setScoreValue(int score)
	{
		this.scoreValue = score;
		this.scoreString = Integer.toString(scoreValue);
	}
	/**
	 * the setLevelValue method sets levelValue
	 * @param level
	 */
	public void setLevelValue(int level)
	{
		this.levelValue = level;
		this.levelString = Integer.toString(levelValue);
	}
	/**
	 * the getScore method returns score
	 * @return score
	 */
	public int getScoreValue()
	{
		return scoreValue;
	}
}
