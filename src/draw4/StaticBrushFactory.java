package draw4;

/*
 * This class implements the BrushFactory interface and will return a new
 * SetEndsShape object depending on what has been selected in the Options
 * menu.
 */
public class StaticBrushFactory implements BrushFactory 
{
	@Override
	public SetEndsShape makeBrush(BrushesDrawingCanvas canvas) 
	{
		if(canvas.getBrush().equals("Square"))
		{
			return new SquareBrush();
		}
		else if(canvas.getBrush().equals("Spraypaint"))
		{
			return new SprayPaintBrush();
		}
		else
		{
			return new CircleBrush();
		}
	}
}
