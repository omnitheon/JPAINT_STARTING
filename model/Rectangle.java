package model;
import java.awt.*;

public class Rectangle extends Shape {

	
	public Rectangle(int numSides, ShapeShadingType SST, Color PC, Color SC, Point startingPoint, Point endingPoint) {
		super(numSides, SST, PC, SC, startingPoint, endingPoint);
	}

	public void draw(Graphics2D g2d){
		
		if (this.SST.equals(ShapeShadingType.FILLED_IN)){
			g2d.setColor(this.primaryColor);
			if (this.shapeWidth > 0 && this.shapeHeight > 0) g2d.fillRect(this.startingPoint.getX(), this.startingPoint.getY(), shapeWidth, shapeHeight);
			else if (this.shapeWidth < 0 && this.shapeHeight > 0) g2d.fillRect(this.endingPoint.getX(), this.startingPoint.getY(), Math.abs(shapeWidth), shapeHeight);
			else if (this.shapeWidth > 0 && this.shapeHeight < 0) g2d.fillRect(this.startingPoint.getX(), this.endingPoint.getY(), shapeWidth, Math.abs(shapeHeight));
			else if (this.shapeWidth < 0 && this.shapeHeight < 0) g2d.fillRect(this.endingPoint.getX(), this.endingPoint.getY(), Math.abs(shapeWidth), Math.abs(shapeHeight));
		}
		else if (this.SST.equals(ShapeShadingType.OUTLINE)){
			g2d.setColor(this.secondaryColor);
			g2d.setStroke(new BasicStroke(5));
			if (this.shapeWidth > 0 && this.shapeHeight > 0) g2d.drawRect(this.startingPoint.getX(), this.startingPoint.getY(), shapeWidth, shapeHeight);
			else if (this.shapeWidth < 0 && this.shapeHeight > 0) g2d.drawRect(this.endingPoint.getX(), this.startingPoint.getY(), Math.abs(shapeWidth), shapeHeight);
			else if (this.shapeWidth > 0 && this.shapeHeight < 0) g2d.drawRect(this.startingPoint.getX(), this.endingPoint.getY(), shapeWidth, Math.abs(shapeHeight));
			else if (this.shapeWidth < 0 && this.shapeHeight < 0) g2d.drawRect(this.endingPoint.getX(), this.endingPoint.getY(), Math.abs(shapeWidth), Math.abs(shapeHeight));
		}
		else { // FILLED_IN AND OUTLINE
			g2d.setColor(this.primaryColor);
			if (this.shapeWidth > 0 && this.shapeHeight > 0) g2d.fillRect(this.startingPoint.getX(), this.startingPoint.getY(), shapeWidth, shapeHeight);
			else if (this.shapeWidth < 0 && this.shapeHeight > 0) g2d.fillRect(this.endingPoint.getX(), this.startingPoint.getY(), Math.abs(shapeWidth), shapeHeight);
			else if (this.shapeWidth > 0 && this.shapeHeight < 0) g2d.fillRect(this.startingPoint.getX(), this.endingPoint.getY(), shapeWidth, Math.abs(shapeHeight));
			else if (this.shapeWidth < 0 && this.shapeHeight < 0) g2d.fillRect(this.endingPoint.getX(), this.endingPoint.getY(), Math.abs(shapeWidth), Math.abs(shapeHeight));
			g2d.setColor(this.secondaryColor);
			g2d.setStroke(new BasicStroke(5));
			if (this.shapeWidth > 0 && this.shapeHeight > 0) g2d.drawRect(this.startingPoint.getX(), this.startingPoint.getY(), shapeWidth, shapeHeight);
			else if (this.shapeWidth < 0 && this.shapeHeight > 0) g2d.drawRect(this.endingPoint.getX(), this.startingPoint.getY(), Math.abs(shapeWidth), shapeHeight);
			else if (this.shapeWidth > 0 && this.shapeHeight < 0) g2d.drawRect(this.startingPoint.getX(), this.endingPoint.getY(), shapeWidth, Math.abs(shapeHeight));
			else if (this.shapeWidth < 0 && this.shapeHeight < 0) g2d.drawRect(this.endingPoint.getX(), this.endingPoint.getY(), Math.abs(shapeWidth), Math.abs(shapeHeight));
		}
	}

	public String getString(){
		return "Rectangle";	
	}
}
