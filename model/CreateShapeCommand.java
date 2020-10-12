package model;
import model.interfaces.ICommand;
import model.interfaces.IShape;
import model.persistence.ShapeList;

public class CreateShapeCommand implements ICommand {
    ShapeType ST;
    ShapeList SL;
    Point startingPoint;
    Point endingPoint;
    int shapeHeight;
    int shapeWidth;

    public CreateShapeCommand(ShapeType ST, ShapeList SL, Point startingPoint, Point endingPoint, int shapeHeight, int shapeWidth){
        this.ST = ST; 
        this.SL = SL; 
        this.startingPoint = startingPoint; 
        this.endingPoint = endingPoint; 
        this.shapeHeight = shapeHeight; 
        this.shapeWidth = shapeWidth;
    }
    @Override public void run(){
        if (ST.equals(ShapeType.RECTANGLE)){
            System.out.println("CreateShapeCommand STARTING:...making RECTANGLE and adding to ShapeList: ");
            IShape rect = ShapeFactory.createRectangle(startingPoint.getX(), startingPoint.getY(), endingPoint.getX(), endingPoint.getY(), shapeHeight, shapeWidth);
            SL.add(rect);
            System.out.println("CreateShapeCommand DONE: modified ShapeList: "+SL.getShapes());
            System.out.println("\n\n");
            //currentCommand = new DrawRectangleCommand(rect,pcb,SL);
            //currentCommand.run();
        }
        else if (ST.equals(ShapeType.ELLIPSE)){

        }
        else {  // ShapeType.TRIANGLE
        }
    }
}
