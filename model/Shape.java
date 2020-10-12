package model;
import model.interfaces.IShape;
import java.awt.Color;

public abstract class Shape implements IShape {
    int numSides;
    int startingPointX;
	int startingPointY;
	int endingPointX;
	int endingPointY;
	int shapeHeight;
    int shapeWidth;
    Color primaryColor;
    Color secondaryColor;


    public Shape(int numSides, Color PC, Color SC, int startingPointX, int startingPointY, int endingPointX, int endingPointY, int shapeHeight, int shapeWidth){ 
        this.numSides = numSides;
        this.startingPointX = startingPointX;
		this.startingPointY = startingPointY;
		this.endingPointX = endingPointX;
		this.endingPointY = endingPointY;
		this.shapeHeight = shapeHeight;
        this.shapeWidth = shapeWidth; 
        this.primaryColor = PC;
        this.secondaryColor = SC;
    }
    public int getNumSides() { return numSides; }

    public Color getPrimaryColor() { return primaryColor; }
    public Color getSecondaryColor() { return secondaryColor; }
}