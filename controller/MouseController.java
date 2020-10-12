package controller;

import java.awt.event.MouseAdapter;
import model.Point;
import model.PointBuilder;
import model.ShapeType;
import view.interfaces.PaintCanvasBase;
import model.interfaces.ICommand;
import java.awt.event.MouseEvent;
import model.DrawRectangleCommand;
import model.persistence.ApplicationState;
import model.ShapeFactory;
import model.ShapeList;
import model.interfaces.IShape;

public class MouseController extends MouseAdapter {
    private PaintCanvasBase pcb;
    private Point sPoint;
    private ApplicationState appS;
    private ShapeFactory SF;
    private ShapeList SL;


    public MouseController(PaintCanvasBase pcb, ApplicationState appS, ShapeFactory SF, ShapeList SL ){ this.pcb = pcb; this.appS = appS; this.SF = SF; this.SL = SL;}
    
    @Override public void mousePressed(MouseEvent e) { 
        PointBuilder pb;
        pb = new PointBuilder();
        pb.setX(e.getX());
        pb.setY(e.getY());
        sPoint = pb.returnPoint();
        System.out.println("MousePressed: "+sPoint.toString());
    }
    @Override public void mouseReleased(MouseEvent e) {
        ICommand currentCommand;
        int w = e.getX() - sPoint.getX();
        int h = e.getY() - sPoint.getY();
        if (appS.getActiveShapeType().equals(ShapeType.RECTANGLE)) {
            IShape rect = SF.createRectangle(sPoint.getX(), sPoint.getY(), h, w, e.getX(), e.getY());
            currentCommand = new DrawRectangleCommand(rect,pcb,SL);
            currentCommand.run();
        }
    }

}
