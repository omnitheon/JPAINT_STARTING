package model;
import model.interfaces.ICommand;
import model.interfaces.IShape;
import model.interfaces.IUndoable;
import model.persistence.ShapeList;
import java.util.ArrayList;

public class DeleteShapeCommand implements ICommand, IUndoable {
    ShapeList SL;
    //ShapeList deletedShapes;
    ArrayList<IShape> deletedShapes;
    public DeleteShapeCommand(ShapeList SL){
        this.SL = SL; 
        //this.deletedShapes = new ShapeList(null,"delete");//unobserved shape list
        this.deletedShapes = new ArrayList<IShape>();
    }
    public void run(){
        for(IShape shape: SL){
            if(shape.isSelected()) {
                //deletedShapes.add(shape,false);
                deletedShapes.add(shape);
                }
            }
        for(IShape shape: deletedShapes) SL.remove(SL.getShapeIndex(shape),true);
        
        CommandHistory.add((IUndoable)this);
        }
        
        


    public void redo(){
        run();
        
    }
    public void undo(){
        for(IShape shape: deletedShapes) SL.add(shape,true);
    }
}