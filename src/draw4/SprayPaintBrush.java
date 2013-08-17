package draw4;

import java.awt.Graphics;
import java.math.*;

public class SprayPaintBrush extends SetEndsShape 
{
	/*
	 * This method overrides the clone method to allow for the random points used with the 
	 * spraypaint brush to be created.  The number of points used by the spraypaint brush
	 * is determined by the size of the brush.
	 */
	@Override
	public Object clone()
	{
		Object brush = new Object();
		try 
		{
			brush = super.clone();
		} 
		catch (CloneNotSupportedException e) 
		{
			e.printStackTrace();
		}
		
		spraySize = size * 4;
		xPoints = new int[spraySize];
		yPoints = new int[spraySize];
		/*
		 * This loop is used to properly distribute the random points around the 
		 * area where the user clicked the mouse.  Depending on the value of i
		 * the xSign and ySign values are either positive or negative.
		 */
		for(int i=0; i<spraySize; i++)
		{
			if((i+1)%4 == 0)
			{
				xSign = 1;
				ySign = 1;
			}
			else if((i+1)%4 == 1)
			{
				xSign = -1;
				ySign = 1;
			}
			else if((i+1)%4 == 2)
			{
				xSign = -1;
				ySign = -1;
			}
			else
			{
				xSign = 1;
				ySign = -1;
			}
			xPoints[i] = ((int)(Math.random() * 1000) % size) * xSign;
			yPoints[i] = ((int)(Math.random() * 1000) % size) * ySign;
		}
		
		return brush;
	}
	
	/*
	 * Draws the spraypaint to the screen.  The size of the brush determines how many
	 * random points are created and drawn to the screen.  The points are evenly
	 * distributed around the point where the user clicked.
	 */
	@Override
	public void draw(Graphics g) 
	{
		int i;
		int x = x1 - (size/2);
	    int y = y1 - (size/2);
	    spraySize = size * 4;
	    
	    if(xPoints == null)
	    {
	    	xPoints = new int[spraySize];
			yPoints = new int[spraySize];
			for(i=0; i<spraySize; i++)
			{
				if((i+1)%4 == 0)
				{
					xSign = 1;
					ySign = 1;
				}
				else if((i+1)%4 == 1)
				{
					xSign = -1;
					ySign = 1;
				}
				else if((i+1)%4 == 2)
				{
					xSign = -1;
					ySign = -1;
				}
				else
				{
					xSign = 1;
					ySign = -1;
				}
				xPoints[i] = ((int)(Math.random() * 1000) % size) * xSign; //Creates x points
				yPoints[i] = ((int)(Math.random() * 1000) % size) * ySign; //Creates y points
			}
	    }
			
		for(i=0; i<spraySize; i++)
		{
			if(color != null)
			{
				g.setColor(color);
			}
			g.drawOval(x + xPoints[i], y + yPoints[i], 1, 1);
		}
		
		
	}
	
	protected int xPoints[];
	protected int yPoints[];
	protected int spraySize;
	protected int xSign = 1;
	protected int ySign = 1;
}
