package model;

import model.interfaces.IShape;
import java.awt.Color;
/*
        ShapeFactory Client Code ... AKA "Consumer"... we receive IShapes


	        IShape shape;

			if (shapeType.toLowerCase().equals("ellipse")) {
                shape = ShapeFactory.getEircle();
			} else if (shapeType.toLowerCase().equals("rectangle")) {
                shape = ShapeFactory.getRectangle();
            } else if (shapeType.toLowerCase().equals("triangle")) {
                shape = ShapeFactory.getTriangle(false);
            } else {
				throw new InvalidParameterException("Parameter must be the correct shape");
			}

            System.out.println(shape.getNumSides());

*/

public class ShapeFactory {
    private ShapeFactory() { }

    public static IShape createEllipse(ShapeShadingType SST, Color PC, Color SC, Point Starting, Point Ending) { 
        return new Ellipse(1, SST, PC, SC, Starting, Ending); 
    }
    public static IShape createRectangle(ShapeShadingType SST, Color PC, Color SC, Point Starting, Point Ending) { 
        return new Rectangle(4, SST, PC, SC, Starting, Ending); 
    }
    //public static IShape createTriangle(boolean isEquilateral) { 
        //return new Triangle(3, isEquilateral, SST, PC, SC, Starting, Ending); 
    //}
}
