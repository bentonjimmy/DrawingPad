package draw4;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

import draw1.TwoEndsShape;

import scribble3.AbstractTool;
import scribble3.ScribbleCanvas;

public class SetEndsShapeTool extends AbstractTool {

	public SetEndsShapeTool(ScribbleCanvas canvas, String name) 
	{
		super(canvas, name);
	}

	/*
	 * This method is used when the user clicks the canvas to draw using a brush.  The brush
	 * will be created using the Brush Factory.  The brush shape is then drawn to the canvas
	 * with the point where the user first clicked.
	 */
	@Override
	public void startShape(Point p) 
	{
		if(canvas instanceof BrushesDrawingCanvas)
		{
			brushFactory = new StaticBrushFactory();
			prototype = brushFactory.makeBrush((BrushesDrawingCanvas)canvas); //Uses brush factory to create new brush
			prototype.setSize(((BrushesDrawingCanvas)canvas).getBrushSize()); //Sets brush size
		}
		
		if (prototype != null) 
	    { 
			canvas.mouseButtonDown = true;
	    	xStart = p.x;
		    yStart = p.y;
		    xEnd = p.x;
		    yEnd = p.y;
		    prototype.setColor(canvas.getCurColor());
		    prototype.setAllEnds(xStart, yStart, xEnd, yEnd); 
		    try 
		    {
				SetEndsShape newShape = (SetEndsShape) prototype.clone();
				canvas.addShape(newShape);
			} 
		    catch (CloneNotSupportedException e) 
		    {
				e.printStackTrace();
			}
	    	Graphics g = canvas.getGraphics();
	    	g.setPaintMode();
	    	canvas.repaint();
	    }
	}

	/*
	 * This method is called with the user drags the mouse while the mouse button 
	 * is held down.  The prototype brush is cloned and set to the current position
	 * of the point.  THe new shape is added to the canvas allowing the user to 
	 * continue to draw shapes while dragging the mouse/brush.
	 */
	@Override
	public void addPointToShape(Point p) 
	{
		if (prototype != null && canvas.mouseButtonDown) 
		{
		    xStart = p.x;
		    yStart = p.y;
		    xEnd = p.x;
		    yEnd = p.y;
	
		    try 
		    {
				SetEndsShape newShape = (SetEndsShape) prototype.clone();
				newShape.setColor(canvas.getCurColor());
				newShape.setAllEnds(xStart, yStart, xEnd, yEnd); 
				canvas.addShape(newShape);
			} 
		    catch (CloneNotSupportedException e) 
		    {
				e.printStackTrace();
			}
	    	Graphics g = canvas.getGraphics();
	    	g.setPaintMode();
	    	canvas.repaint();
		}
	}
	
	/*
	 * This method is called when the user is no longer holding down the mouse and drawing
	 * with the paint brush.  The last shape is created for the current point and added
	 * to the canvas.
	 */
	@Override
	public void endShape(Point p) 
	{
		canvas.mouseButtonDown = false; 
	    if (prototype != null) 
	    { 
	    	xStart = p.x;
		    yStart = p.y;
		    xEnd = p.x;
		    yEnd = p.y;
		    prototype.setColor(canvas.getCurColor());
		    prototype.setAllEnds(xStart, yStart, xEnd, yEnd); 
	    	canvas.addShape(prototype);
	    	Graphics g = canvas.getGraphics();
	    	g.setPaintMode();
	    	canvas.repaint();
	    }
	}
	
	protected int xStart, yStart, xEnd, yEnd; 
	protected SetEndsShape prototype;
	protected StaticBrushFactory brushFactory;

}
