package model;
import model.interfaces.ICommand;
import model.interfaces.IShape;
import model.interfaces.IUndoable;
import model.persistence.ShapeList;

public class DeleteShapeCommand implements ICommand, IUndoable {
    ShapeList SL;
    ShapeList deletedShapes;
    
    public DeleteShapeCommand(ShapeList SL){
        this.SL = SL; 
        this.deletedShapes = new ShapeList(null,"delete");//unobserved shape list
    }
    public void run(){
        for(IShape shape: SL){
            if(shape.isSelected()) {
                deletedShapes.add(shape,true);
                SL.remove(SL.getShapeIndex(shape),true);
                }
            }
        CommandHistory.add((IUndoable)this);
        }
        
        


    public void redo(){
        run();
        
    }
    public void undo(){
        for(IShape shape: deletedShapes) SL.add(shape,true);
    }
}