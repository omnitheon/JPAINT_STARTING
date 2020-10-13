package model;

import model.interfaces.IShape;
import java.awt.Color;

public class ShapeFactory {
    private ShapeFactory() { }

    public static IShape createEllipse(ShapeShadingType SST, Color PC, Color SC, Point Starting, Point Ending) { 
        return new Ellipse(1, SST, PC, SC, Starting, Ending); 
    }
    public static IShape createRectangle(ShapeShadingType SST, Color PC, Color SC, Point Starting, Point Ending) { 
        return new Rectangle(4, SST, PC, SC, Starting, Ending); 
    }
    public static IShape createTriangle(ShapeShadingType SST, Color PC, Color SC, Point Starting, Point Ending) { 
        return new Triangle(3, SST, PC, SC, Starting, Ending); 
    }
}
