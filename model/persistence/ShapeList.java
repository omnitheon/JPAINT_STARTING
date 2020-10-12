package model.persistence;
import java.util.List;
import model.interfaces.IShape;
import view.interfaces.PaintCanvasBase;
import model.interfaces.IIterator;
import java.util.ArrayList; 
import java.util.Iterator;
import model.StateChangeHandler;
import model.ShapeDrawer;

public class ShapeList implements Iterable<IShape> {
    ArrayList<IShape> SL;
    StateChangeHandler SCH;
    PaintCanvasBase PCB;
    
    public ShapeList (ArrayList<IShape> SL, StateChangeHandler SCH, PaintCanvasBase PCB) { 
        this.SL = SL; 
        this.SCH = SCH;
        this.PCB = PCB;
    }

    public ShapeIterator iterator() { return new ShapeIterator(); }
    
    public void add(IShape shape) { 
        System.out.println("[SHAPELIST] value added to ShapeList...");
        SL.add(shape);
        ShapeDrawer SD = new ShapeDrawer(PCB,this);
        SD.update();
        //SCH.update();
    }
    public void remove(int i) { 
        System.out.println("[SHAPELIST] value removed from ShapeList...");
        SL.remove(i);
        ShapeDrawer SD = new ShapeDrawer(PCB,this);
        SD.update();
        //SCH.update();
    }
    public int getShapeIndex(IShape shape) { return SL.indexOf(shape); }
    public List<IShape> getShapes() { return SL; }
    public void deleteAllShapes() { SL.clear(); }
    //public void drawShapes(Graphics2D graphics2D) { for(IShape shape : SL) shape.draw(graphics2D); } //legacy drawer
    
    class ShapeIterator implements Iterator<IShape>, IIterator {
        int currentIndex = 0;
        @Override
        public boolean hasNext() { return currentIndex < SL.size(); }
        @Override
        public IShape next() { return SL.get(currentIndex++); }
        @Override
        public void remove() { SL.remove(--currentIndex); }


    }
}
