package draw4;

import java.awt.Graphics;
import java.awt.Point;
import java.math.*;

import draw1.TwoEndsShape;

public class StarShape extends TwoEndsShape {

	/*
	 * Implements the drawOutline method and draws the outline of the shape while it is being dragged.  
	 * This method similar to all the other shapes in the program and calculates the x, y, w and h 
	 * values to be used with the specific shape-drawing method.
	 */
	@Override
	public void drawOutline(Graphics g, int x1, int y1, int x2, int y2) 
	{
		int x = Math.min(x1, x2); 
	    int y = Math.min(y1, y2); 
	    int w = Math.abs(x1 - x2) + 1; 
	    int h = Math.abs(y1 - y2) + 1;     
	    if (color != null) 
	    {
	      g.setColor(color);
	    }
	    drawStar(g, x, y, w, h);
	}

	/*
	 * Implements the draw method and draws the shape to the screen.  This method similar 
	 * to all the other shapes in the program and calculates the x, y, w and h values to 
	 * be used with the specific shape-drawing method.
	 */
	@Override
	public void draw(Graphics g) 
	{
		int x = Math.min(x1, x2); 
	    int y = Math.min(y1, y2); 
	    int w = Math.abs(x1 - x2) + 1; 
	    int h = Math.abs(y1 - y2) + 1;     
	    drawStar(g, x, y, w, h);
	}
	
	/*
	 * This method calculates the various points on a star that will be used to outline and draw the star on the
	 * canvas.  In order to keep the star proportional and to have the correct angles the star's size is determined
	 * by the width of the star with the height not used for anything.
	 * Knowing the width of the star and that the angle at the points of a 5-point star is 36 degrees, each point of
	 * the star is calculated using various math operations and enough geometry to make an 8th grader weep.  Each
	 * point is stored in an array and the drawPolygon method is used to draw the points.
	 */
	protected void drawStar(Graphics g, int x, int y, int w, int h)
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
		
		g.drawPolygon(starXPoints, starYPoints, numPoints); //draws points to the screen
	}
	
	/*
	 * This method is used to convert degrees to radians.
	 */
	protected double toRadians(double d)
	{
		return d * (Math.PI/180.0);
	}
	
	protected int[] starXPoints;
	protected int[] starYPoints;
	protected int numPoints;

}
