package model;
import model.interfaces.ICommand;
import model.interfaces.IShape;
import model.persistence.ShapeList;
import java.util.ArrayList;

public class CopyShapeCommand implements ICommand {
    ShapeList SL;
    ArrayList<IShape> CLIPBOARD;
    
    public CopyShapeCommand(ShapeList SL, ArrayList<IShape> CLIPBOARD){
        this.SL = SL; 
        this.CLIPBOARD = CLIPBOARD;
    }
    public void run(){
        CLIPBOARD.clear();
        for(IShape shape: SL) if(shape.isSelected()) CLIPBOARD.add(shape);
    }
}