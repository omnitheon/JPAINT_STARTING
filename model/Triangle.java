package model;
import java.awt.*;

class Triangle extends Shape {
	public Triangle(int numSides, ShapeShadingType SST, Color PC, Color SC, Point startingPoint, Point endingPoint) {
		super(numSides, SST, PC, SC, startingPoint, endingPoint);
	}

	public void draw (Graphics2D g2d) {
        int[] xCoordinates = {this.getSmallestX(), this.getMidpoint(), this.getLargestX()};
        int[] YCoordinates = {this.getLargestY(), this.getSmallestY(), this.getLargestY()};
		if (this.SST.equals(ShapeShadingType.FILLED_IN)){ 
            g2d.setColor(this.primaryColor);
            g2d.fillPolygon(xCoordinates, YCoordinates, this.getNumSides());
		}
		else if (this.SST.equals(ShapeShadingType.OUTLINE)){
            g2d.setColor(this.secondaryColor);
            g2d.drawPolygon(xCoordinates, YCoordinates, this.getNumSides());
		}
		else if (this.SST.equals(ShapeShadingType.OUTLINE_AND_FILLED_IN)){
			g2d.setColor(this.primaryColor);
            g2d.fillPolygon(xCoordinates, YCoordinates, this.getNumSides());
            g2d.setColor(this.secondaryColor);
            g2d.drawPolygon(xCoordinates, YCoordinates, this.getNumSides());
		}
		if (this.isSelected){
			g2d.setStroke(new BasicStroke(3, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 1, new float[]{9}, 0));
			g2d.setColor(Color.BLACK);
			g2d.drawPolygon(xCoordinates, YCoordinates, this.getNumSides());
		}
	}

	public String getString() { return "Triangle"; }
}
