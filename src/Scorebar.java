import java.awt.*;

import javax.swing.*;

public class Scorebar extends JPanel
{
	private static final String SCORE = "Score: ";
	private static final String LEVEL = "Level: ";
	private static final int SCORE_WIDTH = 38;
	private static final int LEVEL_WIDTH = 35;
	
	private int scoreValue = 0;
	private int levelValue = 1;
	
	private String scoreString = "0";
	private String levelString = "1";
	
	private int	scoreValueWidth,
				levelValueWidth;
	
	/**
	 * default constructor
	 */
	public Scorebar()
	{
		
	}
	
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
	}
	
	public void drawScorebar(Graphics g)
	{
		
		FontMetrics fm = g.getFontMetrics();
		
		g.setColor(Color.white);
        g.drawString(SCORE, 10, 600);
        g.drawString(LEVEL, 10, 615);
        g.drawString(scoreString, 10+SCORE_WIDTH,600);
        g.drawString(levelString, 10+LEVEL_WIDTH,615);
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
