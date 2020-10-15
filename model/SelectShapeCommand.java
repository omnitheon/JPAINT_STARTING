package model;
import model.interfaces.ICommand;
import model.interfaces.IShape;
import model.persistence.ShapeList;
import model.persistence.ApplicationState;
import java.awt.Rectangle;


public class SelectShapeCommand implements ICommand {
    ShapeType ST;
    ShapeList SL;
    Point startingPoint;
    Point endingPoint;
    ShapeList selectedShapes;
    ApplicationState APPS;
    StateChangeHandler SCH;
    Rectangle intersection;
    int smallestX;
    int largestX;
    int smallestY;
    int largestY;
    int midpoint;
    int shapeHeight;
    int shapeWidth;
    
    public SelectShapeCommand(ApplicationState APPS, ShapeList SL, Point startingPoint, Point endingPoint, StateChangeHandler SCH){
        this.SL = SL; 
        this.startingPoint = startingPoint; 
        this.endingPoint = endingPoint; 
        this.APPS = APPS;
        this.SCH = SCH;
        this.selectedShapes = new ShapeList(SCH,"selected");
        this.smallestX = Math.min(this.startingPoint.getX(), this.endingPoint.getX());
        this.largestX = Math.max(this.startingPoint.getX(), this.endingPoint.getX());
        this.smallestY = Math.min(this.startingPoint.getY(), this.endingPoint.getY());
        this.largestY = Math.max(this.startingPoint.getY(), this.endingPoint.getY());
        int width = Math.abs(largestX-smallestX);
        int length = largestY-smallestY;
        if (width == 0 && length == 0) {width = 2; length = 2;} //single click, give it a 2x2 so it can actually intersect.
        /*
        if (width < 0 && length < 0) { // havent handled backwards selection
            this.smallestX = Math.min(this.startingPoint.getX(), this.endingPoint.getX());
        this.largestX = Math.max(this.startingPoint.getX(), this.endingPoint.getX());
        this.smallestY = Math.min(this.startingPoint.getY(), this.endingPoint.getY());
        this.largestY = Math.max(this.startingPoint.getY(), this.endingPoint.getY());
        }
        */
        this.intersection = new Rectangle(startingPoint.getX(), startingPoint.getY(), width, length); 
    }

    public void run(){
        System.out.println("[SelectShapeCommand] Finding shapes that touch selection point");
        System.out.println("\n\n");
        unselectAll(this.SL);
        for(IShape shape: SL){
            if(shape.intersectsWith(intersection)) {
                shape.select();
                selectedShapes.add(shape);
                System.out.println("Found Shape in intersection..."+shape);
            }
        }
        if (selectedShapes.size()==0) unselectAll(this.SL);

        System.out.println("[SelectShapeCommand] exiting!");
        System.out.println("\n\n");
    }
    public void unselectAll(ShapeList SL){
        for(IShape shape: SL) {
            shape.unselect(); 
            SCH.update();
        }
    }
}
   
