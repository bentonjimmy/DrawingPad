package draw4;

import static org.junit.Assert.*;

import java.awt.Color;
import java.awt.Point;

import javax.swing.JFrame;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class SetEndsShapeToolTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception 
	{
		JFrame frame = new draw4.DrawingPad("Drawing Pad");
		p = new Point(20, 20);
		canvas = ((draw4.DrawingPad)frame).getDrawingCanvas();
		canvas.setBrush("Square");
		canvas.setBrushSize(8);
		canvas.setCurColor(Color.GREEN);
		
		tool = new SetEndsShapeTool(canvas, "Test");
	}

	@Test
	public void testStartShape() 
	{
		//test that shapes has a count of zero
		int precount = canvas.getShapesSize();
		tool.startShape(p);
		//get top shape in shapes
		SetEndsShape topShape = (SetEndsShape) canvas.getLastShape();
		//test if shape is instanceof square
		assertTrue("Test class of shape", topShape instanceof SquareBrush);
		//test if it has the properties from above
		assertTrue("Test brush size", 8 == topShape.getSize());
		assertTrue("Test color", Color.GREEN == topShape.getColor());
		assertTrue("Test starting points", p.equals(topShape.getStartPoint()));
		//test that shapes has a count of one
		assertTrue("Shapes ArrayList count test", (precount + 1) == canvas.getShapesSize());
	}

	@Test
	public void testAddPointToShape() 
	{
		p = new Point(21, 21);
		canvas.setBrushSize(10);
		canvas.setCurColor(Color.BLUE);
		//get the number of shapes before adding one
		int precount = canvas.getShapesSize();
		//Test that mouse is down
		assertTrue("Test Mouse button down", canvas.mouseButtonDown == true);
		tool.addPointToShape(p);
		//get top shape in shapes
		SetEndsShape topShape = (SetEndsShape) canvas.getLastShape();
		//test if shape is instanceof square
		assertTrue("Test class of shape", topShape instanceof SquareBrush);
		//test if it has the properties from above
		assertFalse("Test brush size.  Should not change.", 10 == topShape.getSize());
		assertTrue("Test color", Color.BLUE == topShape.getColor());
		assertTrue("Test starting points", p.equals(topShape.getStartPoint()));
		//test that shapes has a count of one
		assertTrue("Shapes ArrayList count test", (precount + 1) == canvas.getShapesSize());
	}

	@Test
	public void testEndShape() 
	{
		p = new Point(22, 22);
		canvas.setBrushSize(10);
		canvas.setCurColor(Color.RED);
		//get the number of shapes before adding one
		int precount = canvas.getShapesSize();
		//Test that mouse is down
		assertTrue("Test Mouse button down", canvas.mouseButtonDown == true);
		tool.endShape(p);
		//get top shape in shapes
		SetEndsShape topShape = (SetEndsShape) canvas.getLastShape();
		//test if shape is instanceof square
		assertTrue("Test class of shape", topShape instanceof SquareBrush);
		//test if it has the properties from above
		assertFalse("Test brush size.  Should not change.", 10 == topShape.getSize());
		assertTrue("Test color", Color.RED == topShape.getColor());
		assertTrue("Test starting points", p.equals(topShape.getStartPoint()));
		//test that shapes has a count of one
		assertTrue("Shapes ArrayList count test", (precount + 1) == canvas.getShapesSize());
		//Test that mouse is up
		assertTrue("Test Mouse button down", canvas.mouseButtonDown == false);
	}

	static Point p;
	static BrushesDrawingCanvas canvas;
	static SetEndsShapeTool tool;
}
