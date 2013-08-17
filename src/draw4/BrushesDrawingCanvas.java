package draw4;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.*;

import scribble3.Shape;

import draw3.KeyboardDrawingCanvas;

public class BrushesDrawingCanvas extends KeyboardDrawingCanvas 
{
	public BrushesDrawingCanvas(){}
	
	public void setBrushSize(int brushSize)
	{
		this.brushSize = brushSize;
	}
	
	public void setBrush(String brush)
	{
		this.brush = brush;
	}
	
	public int getBrushSize()
	{
		return brushSize;
	}
	
	public String getBrush()
	{
		return brush;
	}
	
	public int getShapesSize()
	{
		return shapes.size();
	}
	
	//returns the last shape added to the shapes ArrayList
	public Shape getLastShape()
	{
		return (Shape) shapes.get(shapes.size() - 1);
	}
	
	public void setBackgroundColor(Color color)
	{
		backgroundColor = color;
	}
	
	public Color getBackgroundColor()
	{
		return backgroundColor;
	}
	
	/*
	 * Overwrites the Scribble paint method to allow for the background color to
	 * be changed and then the shapes redrawn over it
	 */
	public void paint(Graphics g) 
	{
	    Dimension dim = getSize(); 
	    g.setColor(backgroundColor);
	    g.fillRect(0, 0, dim.width, dim.height);    
	    g.setColor(Color.black);
	    if (shapes != null) 
	    { 
	    	Iterator iter = shapes.iterator(); 
	    	while (iter.hasNext()) 
	    	{ 
	    		Shape shape = (Shape) iter.next(); 
	    		if (shape != null) 
	    		{ 
	    			shape.draw(g); 
	    		}
	    	}
	    }
	}
	
	/*
	 * This method is used to undo the last action of the user.  A second List
	 * was created which holds anything removed from the shapes List in case
	 * the user wants to redo the item
	 */
	public void undoShape()
	{
		int last = shapes.size() - 1;
		if(last >= 0)
		{
			if(shapes.get(last) != null)
			{
				shapesUndo.add((Shape)shapes.get(last));
				shapes.remove(last);
				repaint();
			}
		}
	}
	
	/*
	 * This method will redo an action that was undone.  A second List
	 * holds shapes that are removed from the shapes list.  The shape is
	 * added back to the shape list when the method is run.
	 */
	public void redoShape()
	{
		int last = shapesUndo.size() - 1;
		if(last >= 0)
		{
			if(shapesUndo.get(last) != null)
			{
				shapes.add((Shape)shapesUndo.get(last));
				shapesUndo.remove(last);
				repaint();
			}
		}
	}
	
	// factory method 
	  protected EventListener makeCanvasListener() {
	    return (drawingCanvasListener = new BrushesCanvasListener(this)); 
	  }
	
	protected int brushSize = 8;
	protected String brush = new String("Square");
	protected List shapesUndo = new ArrayList();
	protected Color backgroundColor = Color.WHITE;
}
