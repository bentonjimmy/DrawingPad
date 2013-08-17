package draw4;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;

import scribble2.Scribble;
import scribble3.ColorDialog;
import scribble3.ScribbleCanvas;

import draw2.TwoEndsShapeTool;
import draw3.KeyboardDrawingCanvas;

public class DrawingPad extends draw3.DrawingPad {
	
	/*
	 * This constructor adds an Option for changing the background color,
	 * an Edit menu with Undo and Redo and Options for selecting the size
	 * and shape of the paint brushes.
	 */
	public DrawingPad(String title) 
	{
	    super(title); 
	    JMenu optionMenu = menuBar.getMenu(2); 
	    addBrushOptions(optionMenu); 
	    JMenu menu = new JMenu("Set Background"); 
	    optionMenu.add(menu);
	    JMenuItem mi = new JMenuItem("Color");
	    menu.add(mi);
	    mi.addActionListener(new BackgroundColorListener());
	    
	    //Add a menu called Edit to the menu bar at the top of the screen
	    JMenu edit = new JMenu("Edit");
	    menuBar.add(edit, 1); //Put the Edit menu next to the File menue
	    mi = new JMenuItem("Undo"); //Add Undo option
	    edit.add(mi);
	    mi.addActionListener(new UndoListener());
	    
	    mi = new JMenuItem("Redo"); //Add Redo option
	    edit.add(mi);
	    mi.addActionListener(new RedoListener());
	}
	
	// factory method 
	  protected ScribbleCanvas makeCanvas() {
	    return (drawingCanvas = keyboardDrawingCanvas = brushesDrawingCanvas = new BrushesDrawingCanvas()); 
	  }
	  
	  public BrushesDrawingCanvas getDrawingCanvas()
	  {
		  return brushesDrawingCanvas;
	  }
	  
	  /*
	   * This method is used to add the brush options to the Option menu.  This allows
	   * the user to select what type of brush they want to use and how large the brush
	   * will be.
	   */
	  protected void addBrushOptions(JMenu optionMenu)
	  {
		String[] brushes = {"Square", "Circle", "Spraypaint"}; //The available brushes
		int[] brushSizes = {2, 4, 8, 10, 12, 16, 20, 24, 28, 32, 40, 48, 64}; //The size of the brush head
		
		int i; 
	    ActionListener brushAction = new ActionListener()//Action Listener used to determine brush shape
	    { 
	    	public void actionPerformed(ActionEvent event) 
	    	{ 
	    		Object source = event.getSource(); 
	    		if (source instanceof JCheckBoxMenuItem) 
	    		{ 
	    			JCheckBoxMenuItem mi = (JCheckBoxMenuItem) source; 
	    			String brush = mi.getText(); 
	    			brushesDrawingCanvas.setBrush(brush); 	 //change to type of brush to be used   
	    		}
	    	}
	    };
	    
	    JMenu brushFamilyMenu = new JMenu("Brushes"); 
	    ButtonGroup brushGroup = new ButtonGroup(); 
	    for (i = 0; i < brushes.length; i++) //loop to add available brushes
	    { 
	      JCheckBoxMenuItem mi = new JCheckBoxMenuItem(brushes[i]); 
	      brushFamilyMenu.add(mi); 
	      mi.addActionListener(brushAction);
	      brushGroup.add(mi); 
	    }
	    optionMenu.add(brushFamilyMenu); 
	    
	    ActionListener brushSizeAction = new ActionListener()//Action Listener used to set brush size
	    {
	    	public void actionPerformed(ActionEvent event)
	    	{
	    		Object source = event.getSource();
	    		if(source instanceof JCheckBoxMenuItem)
	    		{
	    			JCheckBoxMenuItem mi = (JCheckBoxMenuItem) source;
	    			String sizeString = mi.getText();
	    			brushesDrawingCanvas.setBrushSize(Integer.parseInt(sizeString));
	    		}
	    	}
	    };
	    
	    JMenu brushSizeMenu = new JMenu("Brush Sizes");
	    ButtonGroup sizeGroup = new ButtonGroup();
	    for(i = 0; i < brushSizes.length; i++)//Loop to add the different brush sizes
	    {
	    	JCheckBoxMenuItem mi = new JCheckBoxMenuItem(Integer.toString(brushSizes[i]));
	    	brushSizeMenu.add(mi);
	    	mi.addActionListener(brushSizeAction);
	    	sizeGroup.add(mi);
	    }
	    optionMenu.add(brushSizeMenu);
	}

	/*
	 * This method adds on to the previous initTools and adds functionality for 
	 * a star outline, a filled star and the brushes.
	 */
	protected void initTools()
	{
		super.initTools();
		//Add star to toolkit
	    toolkit.addTool(new TwoEndsShapeTool(canvas, "Star", new StarShape()));
	    //Add filled start to toolkit
	    toolkit.addTool(new TwoEndsShapeTool(canvas, "Filled Star", new FilledStarShape()));
	    //Add brushes to toolkit
	    toolkit.addTool(new SetEndsShapeTool(canvas, "Brushes"));
	}
	
	protected BrushesDrawingCanvas brushesDrawingCanvas;
	
	/*
	 * This ActionListener is used to help set the background color.  When the option is selected
	 * from the menu a ColorDialog window is shown.  The selected color is drawn in the background
	 * with the shapes drawn over it.
	 */
	protected class BackgroundColorListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e) 
		{
			Color result = dialog.showDialog();
		      	if (result != null) 
		      	{ 
		      		brushesDrawingCanvas.setBackgroundColor(result);
		      		brushesDrawingCanvas.repaint();
		      	}
		}
			protected ColorDialog dialog = new ColorDialog(DrawingPad.this, "Choose color", brushesDrawingCanvas.getBackground());
	}
	
	/*
	 * The listener class used to undo the last shape added  to the canvas
	 */
	protected class UndoListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e) 
		{
			brushesDrawingCanvas.undoShape();
		}
	}
	
	/*
	 * Listener class used to redo the last thing that was undone.
	 */
	protected class RedoListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e) 
		{
			brushesDrawingCanvas.redoShape();
		}
	}
	
	public static void main(String[] args) {
	    JFrame frame = new draw4.DrawingPad("Drawing Pad");
	    frame.setSize(width, height);
	    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	    frame.setLocation(screenSize.width/2 - width/2,
			      screenSize.height/2 - height/2);
	    frame.show();
	  }
}
