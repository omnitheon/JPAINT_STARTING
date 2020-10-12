package model;

import model.interfaces.IShape;
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

    //public IShape createEllipse() { return new Ellipse(1); }
    public static IShape createRectangle(int x, int y, int eX, int eY, int h, int w) { 
        return new Rectangle(4, x, y, eX, eY, h, w); 
    }
    //public IShape createTriangle(boolean isEquilateral) { return new Triangle(3, isEquilateral); }
}
