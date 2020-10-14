package model;
import model.interfaces.ICommand;
import model.interfaces.IShape;
import model.interfaces.IUndoable;
import model.persistence.ShapeList;
import model.persistence.ApplicationState;


public class MoveShapeCommand implements ICommand, IUndoable {
    ShapeType ST;
    ShapeList SL;
    Point startingPoint;
    Point endingPoint;
    ApplicationState APPS;
    
    public MoveShapeCommand(ApplicationState APPS, ShapeList SL, Point startingPoint, Point endingPoint){
        this.SL = SL; 
        this.startingPoint = startingPoint; 
        this.endingPoint = endingPoint; 
        this.ST = APPS.getActiveShapeType();
    }
    public void run(){
        
    }
    public void redo(){
        
    }
    public void undo(){
        
    }
}
