package model;
import model.interfaces.ICommand;
import model.interfaces.IShape;
import model.interfaces.IUndoable;
import model.persistence.ShapeList;
import java.util.ArrayList;


public class PasteShapeCommand implements ICommand, IUndoable{
    ShapeList SL;
    ArrayList<IShape> CLIPBOARD;
    //ShapeList pastedShapes;
    ArrayList<IShape> pastedShapes;

    public PasteShapeCommand(ShapeList SL, ArrayList<IShape> CLIPBOARD){
        this.SL = SL; 
        this.CLIPBOARD = CLIPBOARD;
        //this.pastedShapes = new ShapeList(null,"pasted");//unobserved shape list
        this.pastedShapes = new ArrayList<IShape>();
    }
    public void run(){
        for(IShape shape: CLIPBOARD) {
            if(shape.isSelected()) {

            if (shape.getString().equals("Triangle")){
                //pastedShapes.add(ShapeFactory.createTriangle(shape.getShapeShadingType(),shape.getPrimaryColor(),shape.getSecondaryColor(),new PointBuilder().returnPoint(),new PointBuilder().setY(shape.getWidth()).setX(shape.getHeight()).returnPoint()),false);
                pastedShapes.add(ShapeFactory.createTriangle(shape.getShapeShadingType(),shape.getPrimaryColor(),shape.getSecondaryColor(),new PointBuilder().returnPoint(),new PointBuilder().setY(shape.getWidth()).setX(shape.getHeight()).returnPoint()));

            }
            else if (shape.getString().equals("Rectangle")){
                //pastedShapes.add(ShapeFactory.createRectangle(shape.getShapeShadingType(),shape.getPrimaryColor(),shape.getSecondaryColor(),new PointBuilder().returnPoint(),new PointBuilder().setY(shape.getWidth()).setX(shape.getHeight()).returnPoint()),false);
                pastedShapes.add(ShapeFactory.createRectangle(shape.getShapeShadingType(),shape.getPrimaryColor(),shape.getSecondaryColor(),new PointBuilder().returnPoint(),new PointBuilder().setY(shape.getWidth()).setX(shape.getHeight()).returnPoint()));

            }
            else{
                //pastedShapes.add(ShapeFactory.createEllipse(shape.getShapeShadingType(),shape.getPrimaryColor(),shape.getSecondaryColor(),new PointBuilder().returnPoint(),new PointBuilder().setY(shape.getWidth()).setX(shape.getHeight()).returnPoint()),false);
                pastedShapes.add(ShapeFactory.createEllipse(shape.getShapeShadingType(),shape.getPrimaryColor(),shape.getSecondaryColor(),new PointBuilder().returnPoint(),new PointBuilder().setY(shape.getWidth()).setX(shape.getHeight()).returnPoint()));
            }
            }
        }

        for (IShape shape: pastedShapes) SL.add(shape,false);
        SL.notifyObserver();
        CommandHistory.add(this);
    }

    public void undo(){
        for (IShape shape: pastedShapes) SL.remove(SL.getShapeIndex(shape),false);
        SL.notifyObserver();
    }

    public void redo(){
        for (IShape shape: pastedShapes) SL.add(shape,false);
        SL.notifyObserver();
    }
}