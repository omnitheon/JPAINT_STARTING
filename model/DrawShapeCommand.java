package model;

import model.interfaces.IUndoable;
import java.awt.Color;
import java.awt.*;
import model.interfaces.ICommand;
import view.interfaces.PaintCanvasBase;
import model.interfaces.IShape;
import model.persistence.ShapeList;


public class DrawShapeCommand implements ICommand {
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
    }
    }
        
