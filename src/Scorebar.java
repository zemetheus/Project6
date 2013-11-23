import java.awt.*;
import javax.swing.*;

public class Scorebar extends JPanel
{
	
	private int score = 0;
	
	/**
	 * default constructor
	 */
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);		
	}
	/**
	 * the setScore method sets score
	 * @param score
	 */
	public void setScore(int score)
	{
		this.score = score;
	}
	/**
	 * the getScore method returns score
	 * @return score
	 */
	public int getScore()
	{
		return score;
	}
}
