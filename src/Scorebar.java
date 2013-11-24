import java.awt.*;

import javax.swing.*;

public class Scorebar extends JPanel
{
	private static final String SCORE = "Score: ";
	private static final String LEVEL = "Level: ";
	
	private FontMetrics fm;
	
	private int scoreValue = 0;
	private String scoreString = "0";
	
	private int	scoreValueWidth,
				levelWidth,
				levelValueWidth;
	
	/**
	 * default constructor
	 */
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		
		this.fm = g.getFontMetrics();
		
		
	}
	
	public void drawScorebar(Graphics g)
	{
		
		//int scoreWidth = fm.stringWidth("Score: ");
		
		g.setColor(Color.white);
        g.drawString(SCORE, 10, 600);
        g.drawString(LEVEL, 10, 615);
        
        
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
	 * the getScore method returns score
	 * @return score
	 */
	public int getScoreValue()
	{
		return scoreValue;
	}
}
