package model;
import model.interfaces.ICommand;
import model.interfaces.IShape;
import model.interfaces.IUndoable;
import model.persistence.ShapeList;

public class CreateShapeCommand implements ICommand, IUndoable {
    ShapeType ST;
    ShapeList SL;
    Point startingPoint;
    Point endingPoint;
    int shapeHeight;
    int shapeWidth;
    IShape createdShape;

    public CreateShapeCommand(ShapeType ST, ShapeList SL, Point startingPoint, Point endingPoint, int shapeHeight, int shapeWidth){
        this.ST = ST; 
        this.SL = SL; 
        this.startingPoint = startingPoint; 
        this.endingPoint = endingPoint; 
        this.shapeHeight = shapeHeight; 
        this.shapeWidth = shapeWidth;
        if (ST.equals(ShapeType.RECTANGLE)){
            this.createdShape = ShapeFactory.createRectangle(startingPoint.getX(), startingPoint.getY(), endingPoint.getX(), endingPoint.getY(), shapeHeight, shapeWidth);
        }
        
    }
    @Override public void run(){
        if (ST.equals(ShapeType.RECTANGLE)){
            System.out.println("[CreateShapeCommand] STARTING:...making RECTANGLE and adding to ShapeList: ");
            SL.add(createdShape);
            System.out.println("[CreateShapeCommand] DONE: modified ShapeList: "+SL.getShapes());
            System.out.println("\n\n");
            //currentCommand = new DrawRectangleCommand(rect,pcb,SL);
            //currentCommand.run();
        }
        else if (ST.equals(ShapeType.ELLIPSE)){

        }
        else {  // ShapeType.TRIANGLE
        }
    }
    @Override
    public void undo() { 
        System.out.println("[CreateShapeCommand] UNDO...");
        SL.remove(SL.getShapeIndex((createdShape)));
        /*
        Graphics2D g2d = pcb.getGraphics2D();
        g2d.setColor(pcb.getBackground());
        g2d.fillshape(0, 0, pcb.getWidth(), pcb.getHeight());
        
        SL.drawShapes(pcb.getGraphics2D());
        */
    }
    @Override
    public void redo() { 
        System.out.println("[CreateShapeCommand] REDO...");
        SL.add(createdShape); 
        /*
        Graphics2D g2d = pcb.getGraphics2D();
        g2d.setColor(pcb.getBackground());
        g2d.fillshape(0, 0, pcb.getWidth(), pcb.getHeight());
        SL.drawShapes(pcb.getGraphics2D());
        */
    }
}
