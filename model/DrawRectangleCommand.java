package model;

import model.interfaces.IUndoable;
import java.awt.Color;
import java.awt.*;
import model.interfaces.ICommand;
import view.interfaces.PaintCanvasBase;



public class DrawRectangleCommand implements ICommand, IUndoable {
    int x;
    int y;
    int h;
    int w;
    PaintCanvasBase pcb;
    int eX; 
    int eY;

    public DrawRectangleCommand (int x, int y, int h, int w, int eX, int eY, PaintCanvasBase pcb) {
    this.x = x;
    this.y = y;
    this.h = h;
    this.w = w;
    this.eX = eX;
    this.eY = eY;
    this.pcb = pcb;
    }
    @Override
    public void run(){
        Graphics2D g2d = pcb.getGraphics2D();
        g2d.setColor(Color.BLACK);
        if (this.w > 0 && this.h > 0) g2d.fillRect(x, y, w, h);
        else if (this.w < 0 && this.h > 0) g2d.fillRect(eX, y, Math.abs(w), h);
        else if (this.w > 0 && this.h < 0) g2d.fillRect(x, eY, w, Math.abs(h));
        else if (this.w < 0 && this.h < 0) g2d.fillRect(eX, eY, Math.abs(w), Math.abs(h));
    }

    @Override
    public void undo() { }
    @Override
    public void redo() {  }
}
