package model;
import java.awt.*;

class Ellipse extends Shape {
	public Ellipse(int numSides, ShapeShadingType SST, Color PC, Color SC, Point startingPoint, Point endingPoint) {
		super(numSides, SST, PC, SC, startingPoint, endingPoint);
	}

	public void draw (Graphics2D g2d) {
		if (this.SST.equals(ShapeShadingType.FILLED_IN)){
			g2d.setColor(this.primaryColor);
			g2d.fillOval(this.getSmallestX(), this.getSmallestY(), this.getWidth(), this.getHeight());
		}
		else if (this.SST.equals(ShapeShadingType.OUTLINE)){
			g2d.setColor(this.secondaryColor);
			g2d.drawOval(this.getSmallestX(), this.getSmallestY(), this.getWidth(), this.getHeight());
		}
		else if (this.SST.equals(ShapeShadingType.OUTLINE_AND_FILLED_IN)){
			g2d.setColor(this.primaryColor);
			g2d.fillOval(this.getSmallestX(), this.getSmallestY(), this.getWidth(), this.getHeight());
			g2d.setColor(this.secondaryColor);
			g2d.drawOval(this.getSmallestX(), this.getSmallestY(), this.getWidth(), this.getHeight());
			}
		if (this.isSelected){
				g2d.setStroke(new BasicStroke(3, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 1, new float[]{9}, 0));
				g2d.setColor(Color.BLACK);
				g2d.drawOval(this.getSmallestX(), this.getSmallestY(), this.getWidth(), this.getHeight());
			}
		}
	

	public String getString() { return "Ellipse"; }
}
