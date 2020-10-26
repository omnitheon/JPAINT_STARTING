package model;
import model.interfaces.ICommand;
import model.interfaces.IShape;
import model.interfaces.IUndoable;
import model.persistence.ShapeList;

public class PasteShapeCommand implements ICommand, IUndoable{
    ShapeList SL;
    ShapeList CLIPBOARD;
    ShapeList pastedShapes;
    
    public PasteShapeCommand(ShapeList SL, ShapeList CLIPBOARD){
        this.SL = SL; 
        this.CLIPBOARD = CLIPBOARD;
        this.pastedShapes = new ShapeList(null,"pasted");//unobserved shape list
    }
    public void run(){
        for(IShape shape: CLIPBOARD) {
            if(shape.isSelected()) {

            if (shape.getString().equals("Triangle")){
                pastedShapes.add(ShapeFactory.createTriangle(shape.getShapeShadingType(),shape.getPrimaryColor(),shape.getSecondaryColor(),new PointBuilder().returnPoint(),new PointBuilder().setY(shape.getWidth()).setX(shape.getHeight()).returnPoint()),false);
            }
            else if (shape.getString().equals("Rectangle")){
                pastedShapes.add(ShapeFactory.createRectangle(shape.getShapeShadingType(),shape.getPrimaryColor(),shape.getSecondaryColor(),new PointBuilder().returnPoint(),new PointBuilder().setY(shape.getWidth()).setX(shape.getHeight()).returnPoint()),false);
            }
            else{
                pastedShapes.add(ShapeFactory.createEllipse(shape.getShapeShadingType(),shape.getPrimaryColor(),shape.getSecondaryColor(),new PointBuilder().returnPoint(),new PointBuilder().setY(shape.getWidth()).setX(shape.getHeight()).returnPoint()),false);
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