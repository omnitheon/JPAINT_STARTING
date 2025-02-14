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
import model.MoveShapeCommand;
import model.SelectShapeCommand;
import model.StateChangeHandler;
import model.CommandHistory;
import model.interfaces.IUndoable;
import view.interfaces.PaintCanvasBase;


public class MouseController extends MouseAdapter {
    private Point startingPoint;
    private ApplicationState APPS;
    private ShapeList SL;
    private StateChangeHandler SCH;
    private PaintCanvasBase PCB;
    public MouseController(PaintCanvasBase PCB, ApplicationState APPS, ShapeList SL, StateChangeHandler SCH) { 
        this.APPS = APPS;  
        this.SL = SL; 
        this.SCH = SCH;
        this.PCB = PCB;
    }
    
    @Override public void mousePressed(MouseEvent e) { 
        startingPoint = new PointBuilder().setX(e.getX()).setY(e.getY()).returnPoint();
        System.out.println("\n\n");
        System.out.println("MousePressed: "+startingPoint.toString());
    }
    @Override public void mouseReleased(MouseEvent e) {
        ICommand c;
        Point endingPoint = new PointBuilder().setX(e.getX()).setY(e.getY()).returnPoint();
        
        System.out.println("MouseReleased: "+endingPoint.toString());
        System.out.println("\n\n");
        if (APPS.getActiveMouseMode().equals(MouseMode.DRAW)) {     
            System.out.println("\n\n*******[DRAW EVENT]*******\n\n");        
            //CreateShapeCommand
            SelectShapeCommand tempSSC;
            tempSSC = new SelectShapeCommand(APPS,SL,startingPoint,endingPoint,SCH);
            tempSSC.unselectAll(SL);
            c = new CreateShapeCommand(APPS,SL,startingPoint,endingPoint);
            c.run();
            System.out.println("[CreateShapeCommand] action added to CommandHistory...");
            CommandHistory.add(((IUndoable)c));
        }
        else if (APPS.getActiveMouseMode().equals(MouseMode.SELECT)){
            //SelectShapeCommand
            System.out.println("\n\n*******[SELECT EVENT]*******\n\n");
            c = new SelectShapeCommand(APPS,SL,startingPoint,endingPoint,SCH);
            c.run();
        }
            
        else if (APPS.getActiveMouseMode().equals(MouseMode.MOVE)){
            System.out.println("\n\n*******[MOVE EVENT]*******\n\n");
            //MoveShapeCommand
            if (SL.areShapesSelected()){
                c = new MoveShapeCommand(PCB,APPS,SL,startingPoint,endingPoint,SCH);
                c.run();
                System.out.println("[MoveShapeCommand] action added to CommandHistory...");
                CommandHistory.add(((IUndoable)c));
            }
        }
    }

}
