
package scribble2; 

import java.util.*;
import java.io.Serializable;
import java.awt.Point;
import java.awt.Color;

public class Stroke implements Serializable { 

  public Stroke() {} 
  
  public Stroke(Color color) {
    this.color = color; 
  } 

  public void setColor(Color color) {
    this.color = color; 
  } 

  public Color getColor() {
    return color; 
  }

  public void addPoint(Point p) {
    if (p != null) { 
      points.add(p); 
    }
  }

  public List getPoints() { 
    return points; 
  }

  // The list of points on the stroke
  // elements are instances of java.awt.Point 
  protected List points = new ArrayList();  
  
  protected Color color = Color.black; 

}
