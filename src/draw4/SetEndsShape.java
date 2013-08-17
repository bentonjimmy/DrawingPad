package draw4;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

import scribble3.Shape;

/*
 * This class is used to help define the characteristics of the brushes in the application.
 * Since the brushes are created as a shape with a pre-defined size, the drawOutline method
 * has an empty implementation
 */
public abstract class SetEndsShape extends Shape implements Cloneable {

	public SetEndsShape() {}

	public SetEndsShape(Color color) {
		super(color);
	}
	
	//Method to set the size used when creating the brush
	public void setSize(int size)
	{
		this.size = size;
	}
	
	public void setAllEnds(int x1, int y1, int x2, int y2)
	{
		this.x1 = x1;
		this.x2 = x2;
		this.y1 = y1;
		this.y2 = y2;
	}
	
	public void setEnds(int x, int y)
	{
		this.x2 = x;
		this.y2 = y;
	}
	
	public Point getEndsPoint()
	{
		return new Point(x2, y2);
	}
	
	public void setStart(int x, int y)
	{
		this.x1 = x;
		this.y1 = y;
	}
	
	public Point getStartPoint()
	{
		return new Point(x1, y1);
	}
	
	public int getSize()
	{
		return size;
	}
	
	public Object clone() throws CloneNotSupportedException { 
	    return super.clone(); 
	  }
	
	//Empty method
	public void drawOutline(Graphics g, int x1, int y1, int x2, int y2){}
	
	protected int x1;
	protected int y1;
	protected int x2;
	protected int y2;
	protected int size;

}
