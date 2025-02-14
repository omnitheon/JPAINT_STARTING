package model.interfaces;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import model.Point;
import java.util.ArrayList;

import model.ShapeShadingType;

public interface IShape {
    String getString();
    void draw(Graphics2D g2d);
    int getNumSides();
    Color getPrimaryColor();
    Color getSecondaryColor();
    ShapeShadingType getShapeShadingType();
    int getLargestY();
    int getLargestX();
    int getSmallestY();
    int getSmallestX();
    int getWidth();
    int getHeight();
    boolean intersectsWith(Rectangle rect);
    void select();
    void unselect();
    boolean isSelected();
    Point getStartingPoint();
    Point getEndingPoint();
    ArrayList<IShape> getGroupOfShapes();
    void setGroupOfShapes(ArrayList<IShape> AL);
}
 