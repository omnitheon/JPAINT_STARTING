package controller;

import java.awt.event.MouseAdapter;
import model.Point;
import model.PointBuilder;
import model.interfaces.ICommand;
import java.awt.event.MouseEvent;
import model.MouseMode;
import model.persistence.ApplicationState;
import model.persistence.ShapeList;
import model.CreateShapeCommand;
import model.CommandHistory;
import model.interfaces.IUndoable;


public class MouseController extends MouseAdapter {
    private Point startingPoint;
    private ApplicationState APPS;
    private ShapeList SL;

    public MouseController(ApplicationState APPS, ShapeList SL){ this.APPS = APPS;  this.SL = SL; }
    
    @Override public void mousePressed(MouseEvent e) { 
        startingPoint = new PointBuilder().setX(e.getX()).setY(e.getY()).returnPoint();
        System.out.println("MousePressed: "+startingPoint.toString());
    }
    @Override public void mouseReleased(MouseEvent e) {
        ICommand c;
        Point endingPoint = new PointBuilder().setX(e.getX()).setY(e.getY()).returnPoint();
        System.out.println("MouseReleased: "+endingPoint.toString());
        if (APPS.getActiveMouseMode().equals(MouseMode.DRAW)) {             
            //CreateShapeCommand
            c = new CreateShapeCommand(APPS,SL,startingPoint,endingPoint);
            c.run();
            System.out.println("[CreateShapeCommand] action added to CommandHistory...");
            CommandHistory.add(((IUndoable)c));
            
            
        }
        else if (APPS.getActiveMouseMode().equals(MouseMode.SELECT)){
            //SelectShapeCommand
        }
            
        else if (APPS.getActiveMouseMode().equals(MouseMode.MOVE)){
            //MoveShapeCommand
        }
    }

}
