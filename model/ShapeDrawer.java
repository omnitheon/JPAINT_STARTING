package model;

import view.interfaces.PaintCanvasBase;
import model.persistence.ShapeList;
import java.awt.*;
import model.interfaces.IShape;
import model.interfaces.IObserver;
import model.interfaces.ICommand;

public class ShapeDrawer implements IObserver {
    private ShapeList SL;
    private PaintCanvasBase PCB;
    
    public ShapeDrawer(PaintCanvasBase PCB, ShapeList SL){ this.SL = SL; this.PCB = PCB; }


    private void drawAllShapes(){
        System.out.println("[ShapeDrawer] is drawing the canvas by looping through shapeList and executing DrawShapeCommand for each shape....");
        Graphics2D g2d = PCB.getGraphics2D();
        g2d.setColor(PCB.getBackground());
        g2d.fillRect(0, 0, PCB.getWidth(), PCB.getHeight());
        int i = 1;
        for(IShape shape : SL) {
            System.out.println("[ShapeDrawer] Loop#: "+Integer.toString(i++));
            ICommand currentDrawCommand = new DrawShapeCommand(shape,PCB,SL);
            currentDrawCommand.run();
        }
    }
    @Override public void update() { 
        System.out.println("[OBSERVER]..Observer has been notified...executing draw....");
        this.drawAllShapes(); 
    }
 }

