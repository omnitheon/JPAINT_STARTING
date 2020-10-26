package model;
import model.interfaces.ICommand;
import model.interfaces.IShape;
import model.persistence.ShapeList;

public class CopyShapeCommand implements ICommand {
    ShapeList SL;
    ShapeList CLIPBOARD;
    
    public CopyShapeCommand(ShapeList SL, ShapeList CLIPBOARD){
        this.SL = SL; 
        this.CLIPBOARD = CLIPBOARD;
    }
    public void run(){
        CLIPBOARD.deleteAllShapes();
        for(IShape shape: SL) if(shape.isSelected()) CLIPBOARD.add(shape,false);
    }
}