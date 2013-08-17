package draw4;

import java.awt.Graphics;

public class SquareBrush extends SetEndsShape {

	public SquareBrush(){}
	
	public SquareBrush(int size)
	{
		this.size = size;
	}
	
	/*
	 * Draws the filled square to the screen.  The size of the screen is determined
	 * from the Brush Size menu in the application and is drawn evenly around the 
	 * point where the user clicked.
	 */
	@Override
	public void draw(Graphics g) {
		int x = x1 - (size/2);
	    int y = y1 - (size/2);
	    int w = size; 
	    int h = size;
		if(color != null)
		{
			g.setColor(color);
		}
		g.fillRect(x, y, w, h);

	}

}
