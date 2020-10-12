package model;

import model.interfaces.IUndoable;
import java.awt.Color;
import java.awt.*;
import model.interfaces.ICommand;
import view.interfaces.PaintCanvasBase;
import model.interfaces.IShape;
import model.persistence.ShapeList;


public class DrawShapeCommand implements ICommand, IUndoable {
    PaintCanvasBase pcb;
    ShapeList SL;
    IShape shape;

    public DrawShapeCommand (IShape shape, PaintCanvasBase pcb, ShapeList SL) {
    this.pcb = pcb;
    this.SL = SL;
    this.shape = shape;
    }
    @Override
    public void run(){
        System.out.println("DrawShapeCommand executing...");
        Graphics2D g2d = pcb.getGraphics2D();
        g2d.setColor(Color.BLACK);
        shape.draw(g2d);
        System.out.println("DrawShapeCommand added to CommandHistory...");
        CommandHistory.add(this);
    }

    @Override
    public void undo() { 
        System.out.println("DrawShapeCommand UNDO...");
        SL.remove(SL.getShapeIndex((shape)));
        /*
        Graphics2D g2d = pcb.getGraphics2D();
        g2d.setColor(pcb.getBackground());
        g2d.fillshape(0, 0, pcb.getWidth(), pcb.getHeight());
        
        SL.drawShapes(pcb.getGraphics2D());
        */
    }
    @Override
    public void redo() { 
        System.out.println("DrawShapeCommand REDO...");
        SL.add(shape); 
        /*
        Graphics2D g2d = pcb.getGraphics2D();
        g2d.setColor(pcb.getBackground());
        g2d.fillshape(0, 0, pcb.getWidth(), pcb.getHeight());
        SL.drawShapes(pcb.getGraphics2D());
        */
    }
    }
        
