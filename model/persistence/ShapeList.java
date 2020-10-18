package model.persistence;
import java.util.List;
import model.interfaces.IShape;
import model.interfaces.IIterator;
import java.util.ArrayList; 
import java.util.Iterator;
import model.StateChangeHandler;

public class ShapeList implements Iterable<IShape> {
    ArrayList<IShape> SL;
    StateChangeHandler SCH;
    String type;
    
    public ShapeList (StateChangeHandler SCH, String type) { 
        this.SL = new ArrayList<IShape>();
        this.SCH = SCH;
        this.type = type;
    }

    public ShapeIterator iterator() { return new ShapeIterator(); }
    
    /*
    public void add(IShape shape) { 
        System.out.println("[SHAPELIST] value added to ("+this.type+") ShapeList...");
        this.SL.add(shape);
        if (this.SCH != null) this.SCH.update();
    }
    */

    public void add(IShape shape, boolean notifyAfterAdd) { 
        System.out.println("[SHAPELIST] value added to ("+this.type+") ShapeList...");
        this.SL.add(shape);
        if (this.SCH != null && notifyAfterAdd) this.SCH.update();
    }

    public void remove(int i, boolean notifyAfterRemove) { 
        System.out.println("[SHAPELIST] value removed from ("+this.type+") ShapeList...");
        this.SL.remove(i);
        if (this.SCH != null && notifyAfterRemove) this.SCH.update();
    }

    public void notifyObserver(){
        if (this.SCH != null) this.SCH.update();
    }

    
    public int getShapeIndex(IShape shape) { return SL.indexOf(shape); }
    public List<IShape> getShapes() { return SL; }
    public void deleteAllShapes() { SL.clear(); }
    public int size() { return SL.size(); }
    public boolean areShapesSelected(){
        for(IShape shape: SL) if (shape.isSelected() == true) return true;
        return false;
    }

    class ShapeIterator implements Iterator<IShape>, IIterator {
        int currentIndex = 0;
        @Override public boolean hasNext() { return currentIndex < SL.size(); }
        @Override public IShape next() { return SL.get(currentIndex++); }
        @Override public void remove() { SL.remove(--currentIndex); }
    }
}
