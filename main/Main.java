package main;

import controller.IJPaintController;
import controller.JPaintController;
import model.persistence.ApplicationState;
import view.gui.Gui;
import view.gui.GuiWindow;
import view.gui.PaintCanvas;
import view.interfaces.IGuiWindow;
import view.interfaces.PaintCanvasBase;
import view.interfaces.IUiModule;
import controller.MouseController;
import model.ShapeFactory;
import model.interfaces.IShape;
import model.ShapeList;
import java.util.ArrayList;;


public class Main {
    public static void main(String[] args){
        PaintCanvasBase paintCanvas = new PaintCanvas(); //Probably wont need to modify, only ever want one instance
        IGuiWindow guiWindow = new GuiWindow(paintCanvas); //Wont have to modify/know
        IUiModule uiModule = new Gui(guiWindow); //Wont have to modify/know
        ApplicationState appState = new ApplicationState(uiModule); //, only ever want one instance
        IJPaintController controller = new JPaintController(uiModule, appState); //, only ever want one instance
        final ShapeFactory SF = new ShapeFactory();
        ShapeList SL = new ShapeList(new ArrayList<IShape>());
        controller.setup(); 


        paintCanvas.addMouseListener(new MouseController(paintCanvas,appState,SF,SL)); //Extends MouseAdapter

        // For example purposes only; remove all lines below from your final project.

        try { Thread.sleep(500); } catch (InterruptedException e) { e.printStackTrace(); }

        /*
        // Filled in rectangle
        Graphics2D graphics2d = paintCanvas.getGraphics2D();
        graphics2d.setColor(Color.GREEN);
        graphics2d.fillRect(12, 13, 200, 400);

        // Outlined rectangle
        graphics2d.setStroke(new BasicStroke(5));
        graphics2d.setColor(Color.BLUE);
        graphics2d.drawRect(12, 13, 200, 400);

        // Selected Shape
        Stroke stroke = new BasicStroke(3, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 1, new float[]{9}, 0);
        graphics2d.setStroke(stroke);
        graphics2d.setColor(Color.BLACK);
        graphics2d.drawRect(7, 8, 210, 410);
        */
    }
}
