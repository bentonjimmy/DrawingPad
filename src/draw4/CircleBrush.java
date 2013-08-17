package draw4;

import java.awt.Graphics;

public class CircleBrush extends SetEndsShape {

	public CircleBrush(){}
	
	/*
	 * This will draw the filled circle on the canvas.  The x and y points are 
	 * determined by the size of the brush.  The circle is created so that the center
	 * of it is where the user clicked.
	 */
	@Override
	public void draw(Graphics g) 
	{
		int x = x1 - (size/2);
	    int y = y1 - (size/2);
	    int w = size; 
	    int h = size;
		if(color != null)
		{
			g.setColor(color);
		}
		g.fillOval(x, y, w, h);

	}

}
