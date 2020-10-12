package controller;

import java.awt.event.MouseAdapter;
import model.Point;
import model.PointBuilder;
import view.interfaces.PaintCanvasBase;
import model.interfaces.ICommand;
import java.awt.event.MouseEvent;
import model.MouseMode;
import model.persistence.ApplicationState;
import model.persistence.ShapeList;
import model.CreateShapeCommand;


public class MouseController extends MouseAdapter {
    private PaintCanvasBase pcb;
    private Point startingPoint;
    private Point endingPoint;
    private ApplicationState appS;
    private ShapeList SL;

    public MouseController(PaintCanvasBase pcb, ApplicationState appS, ShapeList SL){ 
        this.pcb = pcb; this.appS = appS;  this.SL = SL;
    }
    
    @Override public void mousePressed(MouseEvent e) { 
        startingPoint = new PointBuilder().setX(e.getX()).setY(e.getY()).returnPoint();
        System.out.println("MousePressed: "+startingPoint.toString());
    }
    @Override public void mouseReleased(MouseEvent e) {
        ICommand c;
        int shapeWidth = e.getX() - startingPoint.getX();
        int shapeHeight = e.getY() - startingPoint.getY();
        endingPoint = new PointBuilder().setX(e.getX()).setY(e.getY()).returnPoint();
        System.out.println("MouseReleased: "+endingPoint.toString());
        if (appS.getActiveMouseMode().equals(MouseMode.DRAW)) {             
            //CreateShapeCommand
            c = new CreateShapeCommand(appS.getActiveShapeType(),SL,startingPoint,endingPoint,shapeHeight,shapeWidth);
            c.run();
            
            
        }
        else if (appS.getActiveMouseMode().equals(MouseMode.SELECT)){}
            //SelectShapeCommand
        else { //MouseMode.MOVE
            //MoveShapeCommand
        }
    }

}
