package model;
import model.interfaces.ICommand;
import model.interfaces.IShape;
import model.interfaces.IUndoable;
import model.persistence.ShapeList;
import java.awt.Color;
import model.persistence.ApplicationState;

public class CreateShapeCommand implements ICommand, IUndoable {
    ShapeType ST;
    ShapeList SL;
    Point startingPoint;
    Point endingPoint;
    IShape createdShape;
    private Color primaryColor;
    private Color secondaryColor;
    ApplicationState APPS;
    private ShapeShadingType SST;
    
    public CreateShapeCommand(ApplicationState APPS, ShapeList SL, Point startingPoint, Point endingPoint){
        this.SL = SL; 
        this.startingPoint = startingPoint; 
        this.endingPoint = endingPoint; 
        this.ST = APPS.getActiveShapeType();
        this.primaryColor = convertShapeColor(APPS.getActivePrimaryColor());
        this.secondaryColor = convertShapeColor(APPS.getActiveSecondaryColor());
        this.SST = APPS.getActiveShapeShadingType();
        if (ST.equals(ShapeType.RECTANGLE)){
            this.createdShape = ShapeFactory.createRectangle(SST,primaryColor,secondaryColor,startingPoint,endingPoint);
        }
        else if (ST.equals(ShapeType.ELLIPSE)){
            this.createdShape = ShapeFactory.createEllipse(SST,primaryColor,secondaryColor,startingPoint,endingPoint);
        }
        else if (ST.equals(ShapeType.TRIANGLE)) {
            this.createdShape = ShapeFactory.createTriangle(SST,primaryColor,secondaryColor,startingPoint,endingPoint);
        }
        
        
    }
    @Override public void run(){
        System.out.println("[CreateShapeCommand] STARTING:...making shape and adding to ShapeList: ");
        SL.add(createdShape,true);
        System.out.println("[CreateShapeCommand] DONE: modified ShapeList: "+SL.getShapes());
        System.out.println("\n\n");
    }  

    @Override public void undo() { 
        System.out.println("[CreateShapeCommand] UNDO...");
        SL.remove(SL.getShapeIndex((createdShape)),true);
    }
    @Override public void redo() { 
        System.out.println("[CreateShapeCommand] REDO...");
        SL.add(createdShape,true); 
    }
    public static Color convertShapeColor(ShapeColor SC){
        switch (SC) { 
        case BLACK: return Color.BLACK;
        case BLUE: return Color.BLUE;
        case CYAN: return Color.CYAN;
        case DARK_GRAY: return Color.DARK_GRAY;
        case GRAY: return Color.GRAY;
        case GREEN: return Color.GREEN;
        case LIGHT_GRAY: return Color.LIGHT_GRAY;
        case MAGENTA: return Color.MAGENTA;
        case ORANGE: return Color.ORANGE;
        case PINK: return Color.PINK;
        case RED: return Color.RED;
        case WHITE: return Color.WHITE;
        case YELLOW: return Color.YELLOW;
        default: return Color.GREEN;
    }
}
}
