package draw4;

import java.awt.Graphics;

/*
 * This class defines the methods used to draw a star that is filled in with a color
 */
public class FilledStarShape extends StarShape 
{
	/*
	 * Implements the draw method.  This is similar to all the other shapes in the program
	 * and calculates the x, y, w and h values to be used with the specific shape-drawing
	 * method.
	 */
	public void draw(Graphics g)
	{
		int x = Math.min(x1, x2); 
	    int y = Math.min(y1, y2); 
	    int w = Math.abs(x1 - x2) + 1; 
	    int h = Math.abs(y1 - y2) + 1;     
	    if (color != null) 
	    {
	      g.setColor(color);
	    }
	    
	    fillStar(g, x, y, w, h); //draws the filled in star
	}
	
	/*
	 * This method is almost the exact same as the drawStar method except that 
	 * after the points are defined the fillPolygon method is used to draw a filled
	 * in star rather than the outline.  The math used in this method is the same
	 * as the drawStar method.
	 */
	protected void fillStar(Graphics g, int x, int y, int w, int h)
	{
		int numPoints = 10;
		starXPoints = new int[numPoints]; //array to hold all the x values
		starYPoints = new int[numPoints]; //array to hold all the y values
	
		int topPoint = (int)(x + (w/2.0)); //The very top point of the star
		int ay = (int)(Math.sqrt(Math.pow(w*Math.sin(toRadians(36.0))/Math.sin(toRadians(108.0)), 2) - Math.pow((w/2.0), 2)));
		int ax = (int) (ay * Math.sin(toRadians(18)) / Math.sin(toRadians(72))); 
		int ah = (int) (Math.sqrt(Math.pow(ay, 2) + Math.pow(ax, 2)));
		
		starXPoints[0] = topPoint;
		starYPoints[0] = y;
		starXPoints[1] = topPoint + ax;
		starYPoints[1] = ay + y;
		starXPoints[2] = x + w;
		starYPoints[2] = ay + y;
		starXPoints[3] = starXPoints[1] + (int)((2.0*ax*Math.sin(toRadians(18.0)))/Math.sin(toRadians(90.0)));
		starYPoints[3] = starYPoints[1] + (int)((2.0*ax*Math.sin(toRadians(72.0)))/Math.sin(toRadians(90.0)));
		starXPoints[4] = starXPoints[3] + (int)(ah * Math.sin(toRadians(18))/Math.sin(toRadians(90)));
		starYPoints[4] = starYPoints[3] + (int)(ah*Math.sin(toRadians(72))/Math.sin(toRadians(90))); 
		starXPoints[5] = topPoint;
		starYPoints[5] = starYPoints[3] + (int)((2.0*ax*Math.sin(toRadians(18.0)))/Math.sin(toRadians(90.0)));
		starXPoints[7] = topPoint - (int)((2.0*ax*Math.sin(toRadians(72.0)))/Math.sin(toRadians(90.0)));
		starYPoints[7] = starYPoints[3];
		starXPoints[6] = starXPoints[7] - (int)(ah * Math.sin(toRadians(18))/Math.sin(toRadians(90)));
		starYPoints[6] = starYPoints[4];
		starXPoints[8] = x;
		starYPoints[8] = ay + y;
		starXPoints[9] = topPoint - ax;
		starYPoints[9] = ay + y;
		
	    g.fillPolygon(starXPoints, starYPoints, numPoints);
	}
}
