package model;
import java.util.ArrayList;
import java.util.List;
import model.interfaces.IShape;

import java.awt.*;

public class ShapeListBACKUP {
    ArrayList<IShape> SL;

    public ShapeListBACKUP (ArrayList<IShape> SL) { this.SL = SL;}
    public void add(IShape shape) { SL.add(shape); }
    public void remove(int i) { SL.remove(i); }
    public int getShapeIndex(IShape shape) {  return SL.indexOf(shape);  }
    public List<IShape> getShapes() { return  SL; }
    public void deleteAllShapes() { SL.clear(); }
    public void drawShapes(Graphics2D graphics2D) {
        for(IShape shape : SL) {
            shape.draw(graphics2D);
        }
    }
}
