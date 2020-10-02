package model;

import model.interfaces.IUndoable;
import java.awt.Color;
import java.awt.*;
import model.interfaces.ICommand;
import view.interfaces.PaintCanvasBase;
import model.interfaces.IShape;



public class DrawRectangleCommand implements ICommand, IUndoable {
    PaintCanvasBase pcb;
    ShapeList SL;
    IShape rect;

    public DrawRectangleCommand (IShape rect, PaintCanvasBase pcb, ShapeList SL) {
    this.pcb = pcb;
    this.SL = SL;
    this.rect = rect;
    }
    @Override
    public void run(){
        Graphics2D g2d = pcb.getGraphics2D();
        g2d.setColor(Color.BLACK);
        rect.draw(g2d);
        SL.add(rect);
        CommandHistory.add(this);
    }

    @Override
    public void undo() { 
        SL.remove(SL.getShapeIndex((rect)));
        Graphics2D g2d = pcb.getGraphics2D();
        g2d.setColor(pcb.getBackground());
        g2d.fillRect(0, 0, pcb.getWidth(), pcb.getHeight());
        SL.drawShapes(pcb.getGraphics2D());
    }
    @Override
    public void redo() { 
        SL.add(rect); 
        Graphics2D g2d = pcb.getGraphics2D();
        g2d.setColor(pcb.getBackground());
        g2d.fillRect(0, 0, pcb.getWidth(), pcb.getHeight());
        SL.drawShapes(pcb.getGraphics2D());
    }
    }
        
