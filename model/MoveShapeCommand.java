package model;
import model.interfaces.ICommand;
import model.interfaces.IShape;
import model.interfaces.IUndoable;
import model.persistence.ShapeList;
import model.persistence.ApplicationState;
import java.util.ArrayList; 
import view.interfaces.PaintCanvasBase;

public class MoveShapeCommand implements ICommand, IUndoable {
    ShapeList SL;
    Point startingPoint;
    Point endingPoint;
    ApplicationState APPS;
    StateChangeHandler SCH;
    int deltaX;
    int deltaY;
    ShapeList movedShapes;
    ArrayList<IShape> removedShapes;
    ShapeDrawer SD;
    PaintCanvasBase PCB;
    
    public MoveShapeCommand(PaintCanvasBase PCB, ApplicationState APPS, ShapeList SL, Point startingPoint, Point endingPoint, StateChangeHandler SCH){
        this.SL = SL; 
        this.startingPoint = startingPoint; 
        this.endingPoint = endingPoint; 
        this.APPS = APPS;
        this.SCH = SCH;
        this.deltaX = endingPoint.getX() - startingPoint.getX();
        this.deltaY = endingPoint.getY() - startingPoint.getX();
        this.movedShapes = new ShapeList(null,"moved");//unobserved shape list
        this.removedShapes = new ArrayList<IShape>();
        this.SD = new ShapeDrawer(PCB,SL);
        this.PCB = PCB;
    }
    public void run(){
        for(IShape shape: SL){
            if(shape.isSelected()) {
                int newStartingPointX = shape.getStartingPoint().getX()+deltaX;
                int newStartingPointY = shape.getStartingPoint().getY()+deltaY;
                int newEndingPointX = shape.getEndingPoint().getX()+deltaX;
                int newEndingPointY = shape.getEndingPoint().getY()+deltaY;
                Point newStartingPoint = new PointBuilder().setX(newStartingPointX).setY(newStartingPointY).returnPoint();
                Point newEndingPoint = new PointBuilder().setX(newEndingPointX).setY(newEndingPointY).returnPoint();
                IShape deltaShape;

                if (shape.getString().equals("Rectangle")){
                    deltaShape = ShapeFactory.createRectangle(shape.getShapeShadingType(),shape.getPrimaryColor(),shape.getSecondaryColor(),newStartingPoint,newEndingPoint);
                    swap(deltaShape,shape);
                }
                else if (shape.getString().equals("Triangle")){
                    deltaShape = ShapeFactory.createTriangle(shape.getShapeShadingType(),shape.getPrimaryColor(),shape.getSecondaryColor(),newStartingPoint,newEndingPoint);
                    swap(deltaShape,shape);
                }
                else if (shape.getString().equals("Ellipse")){
                    deltaShape = ShapeFactory.createEllipse(shape.getShapeShadingType(),shape.getPrimaryColor(),shape.getSecondaryColor(),newStartingPoint,newEndingPoint);
                    swap(deltaShape,shape);
                }
                
            }
        }
        
    }
    public void swap(IShape deltaShape, IShape originalShape){
        deltaShape.select();
        SL.remove(SL.getShapeIndex(originalShape));
        SL.add(deltaShape);
        movedShapes.add(deltaShape);
        removedShapes.add(originalShape);
    }

    public void redo(){
        run();
        
    }
    public void undo(){
        for(IShape shape: removedShapes) SL.add(shape);
        for(IShape shape: movedShapes) SL.remove(SL.getShapeIndex(shape));
    }
}
