package controller;

import java.awt.event.MouseAdapter;
import model.Point;
import view.interfaces.PaintCanvasBase;
import model.interfaces.ICommand;
import java.awt.event.MouseEvent;
import model.DrawRectangleCommand;


public class MouseController extends MouseAdapter {
    private PaintCanvasBase pcb;
    private Point sPoint;


    public MouseController(PaintCanvasBase pcb){ this.pcb = pcb;}
    @Override
    public void mousePressed(MouseEvent e) { sPoint = new Point(e.getX(), e.getY());   }
    @Override
    public void mouseReleased(MouseEvent e) {
        ICommand currentCommand;
        int w = e.getX() - sPoint.getX();
        int h = e.getY() - sPoint.getY();
        currentCommand = new DrawRectangleCommand(sPoint.getX(), sPoint.getY(), h, w, e.getX(), e.getY(), pcb);
        currentCommand.run();
    }
}
