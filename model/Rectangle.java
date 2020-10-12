package model;
import java.awt.*;

public class Rectangle extends Shape {
	int startingPointX;
	int startingPointY;
	int endingPointX;
	int endingPointY;
	int shapeHeight;
	int shapeWidth;
	
	public Rectangle(int numSides, int startingPointX, int startingPointY, int endingPointX, int endingPointY, int shapeHeight, int shapeWidth) {
		super(numSides);
		this.startingPointX = startingPointX;
		this.startingPointY = startingPointY;
		this.endingPointX = endingPointX;
		this.endingPointY = endingPointY;
		this.shapeHeight = shapeHeight;
		this.shapeWidth = shapeWidth;
	}

	public void draw(Graphics2D g2d){
		if (this.shapeWidth > 0 && this.shapeHeight > 0) g2d.fillRect(startingPointX, startingPointY, shapeWidth, shapeHeight);
        else if (this.shapeWidth < 0 && this.shapeHeight > 0) g2d.fillRect(endingPointX, startingPointY, Math.abs(shapeWidth), shapeHeight);
        else if (this.shapeWidth > 0 && this.shapeHeight < 0) g2d.fillRect(startingPointX, endingPointY, shapeWidth, Math.abs(shapeHeight));
        else if (this.shapeWidth < 0 && this.shapeHeight < 0) g2d.fillRect(endingPointX, endingPointY, Math.abs(shapeWidth), Math.abs(shapeHeight));

	}

	public String getString(){
		return "Rectangle";	
	}
}
