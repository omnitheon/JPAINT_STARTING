package model;

import model.interfaces.IShape;
import view.interfaces.PaintCanvasBase;
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
    //public IShape createEllipse() { return new Ellipse(1); }
    public IShape createRectangle(int x, int y, int h, int w, int eX, int eY) { return new Rectangle(4, x, y, h, w, eX, eY); }
    //public IShape createTriangle(boolean isEquilateral) { return new Triangle(3, isEquilateral); }
}
