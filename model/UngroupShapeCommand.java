package model;

import model.interfaces.IUndoable;
import model.interfaces.ICommand;
import model.interfaces.IShape;
import model.persistence.ShapeList;
import java.util.ArrayList;

public class UngroupShapeCommand implements ICommand, IUndoable {
    private ArrayList<IShape> gShapes = new ArrayList<IShape>();// unobserved shape list
    private ArrayList<IShape> removedShapes = new ArrayList<IShape>();
    private ShapeList SL;

    public UngroupShapeCommand(ShapeList SL) {
        this.SL = SL;
    }

    public void run(){
        ungroup();
        CommandHistory.add(this);
    }

    private void ungroup() {
        for (IShape shape : SL) {
            if (shape.isSelected() && shape.getString().equals("Group")) {
                System.out.println("[UngroundShapeCommand...found ShapeGroup to ungroup...");
                ArrayList<IShape> groupShapes = shape.getGroupOfShapes();
                
                for (IShape s : groupShapes) gShapes.add(s);
                removedShapes.add(shape);
                
            }

        }
        for (IShape s : gShapes) {
            s.select();
            SL.add(s,false);
        }

        for (IShape s : removedShapes ) SL.remove(SL.getShapeIndex(s),false);

        SL.notifyObserver();
    }


    public void undo() {
        for (IShape s : gShapes) SL.remove(SL.getShapeIndex(s),false);
        for (IShape s : removedShapes) SL.add(s,false);
        SL.notifyObserver();
        

    }

    public void redo() {
        for (IShape s : gShapes) {
            s.select();
            SL.add(s,false);
        }
        for (IShape s : removedShapes) SL.remove(SL.getShapeIndex(s),false);
        SL.notifyObserver();
    }
}
