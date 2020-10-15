package model;
import model.interfaces.IShape;
import java.awt.Color;
import java.awt.Rectangle;

public abstract class Shape implements IShape {
    int numSides;
    Point startingPoint;
	Point endingPoint;
	int shapeHeight;
    int shapeWidth;
    Color primaryColor;
    Color secondaryColor;
    ShapeShadingType SST;
    int smallestX;
    int largestX;
    int smallestY;
    int largestY;
    int midpoint;
    boolean isSelected;

    public Shape(int numSides, ShapeShadingType SST, Color PC, Color SC, Point startingPoint, Point endingPoint){ 
        this.numSides = numSides;
        this.startingPoint = startingPoint;
		this.endingPoint = endingPoint;
        this.primaryColor = PC;
        this.secondaryColor = SC;
        this.SST = SST;
        this.isSelected = false;

        this.smallestX = Math.min(this.startingPoint.getX(), this.endingPoint.getX());
        this.largestX = Math.max(this.startingPoint.getX(), this.endingPoint.getX());

        this.smallestY = Math.min(this.startingPoint.getY(), this.endingPoint.getY());
        this.largestY = Math.max(this.startingPoint.getY(), this.endingPoint.getY());

        this.shapeWidth = largestX-smallestX;
        this.shapeHeight = largestY-smallestY;
        this.midpoint = (smallestX)+((largestX-smallestX)/2);
    }
    public int getNumSides() { return numSides; }

    public boolean intersectsWith(Rectangle intersection){
        Rectangle currentRectangle = new Rectangle(startingPoint.getX(), startingPoint.getY(), largestX-smallestX, largestY-smallestY);  
        return currentRectangle.intersects(intersection);
    } 
  
    
    public Color getPrimaryColor() { return primaryColor; }
    public Color getSecondaryColor() { return secondaryColor; }
    public ShapeShadingType getShapeShadingType() { return SST; }
    public int getLargestY(){ return largestY; }
    public int getLargestX(){ return largestX; }
    public int getSmallestY(){ return smallestY; }
    public int getSmallestX(){ return smallestX; }
    public int getWidth(){ return shapeHeight; }
    public int getHeight(){ return shapeWidth; }
    public int getMidpoint(){ return midpoint; }
    public void select() { this.isSelected = true; }
    public void unselect() { this.isSelected = false; }
    public boolean isSelected() { return this.isSelected; }
    public Point getStartingPoint(){ return startingPoint; }
    public Point getEndingPoint(){ return endingPoint; }
}