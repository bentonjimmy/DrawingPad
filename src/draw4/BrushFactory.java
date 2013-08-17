package draw4;

/*
 * Interface for factory that will create paint brushes
 */
public interface BrushFactory 
{
	public SetEndsShape makeBrush(BrushesDrawingCanvas canvas);
}
