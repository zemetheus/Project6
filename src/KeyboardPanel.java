import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class KeyboardPanel extends JPanel
{
	public KeyboardPanel()
	{
		addKeyListener(new KeyAdapter()
		{
			public void keyPressed(KeyEvent e)
			{
				switch(e.getKeyCode())
				{
					case KeyEvent.VK_LEFT:
					{
						break;
					}
					case KeyEvent.VK_RIGHT:
					{
						break;
					}
					case KeyEvent.VK_SPACE:
					{
						break;
					}
					case KeyEvent.VK_ESCAPE:
					{
						System.exit(0);
					}
					default:
					{
						break;
					}
				}
			}
		}
		);
	}
}
