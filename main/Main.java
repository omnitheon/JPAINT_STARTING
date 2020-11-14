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
import model.persistence.ShapeList;
import model.StateChangeHandler;
import model.ShapeDrawer;
import java.util.ArrayList;
import model.interfaces.IShape;

public class Main {
    public static void main(String[] args){
        PaintCanvasBase PCB = new PaintCanvas(); //Probably wont need to modify, only ever want one instance
        IGuiWindow guiWindow = new GuiWindow(PCB); //Wont have to modify/know
        IUiModule uiModule = new Gui(guiWindow); //Wont have to modify/know
        StateChangeHandler SCH = StateChangeHandler.getInstance(); //Singleton StateChangeHandler
        ShapeList SL = ShapeList.getInstance(); //Singleton ShapeList
        ArrayList<IShape> Clipboard = new ArrayList<IShape>();
        ApplicationState appState = new ApplicationState(uiModule); //, only ever want one instance
        IJPaintController controller = new JPaintController(uiModule, appState, SL, Clipboard); //, only ever want one instance
        ShapeDrawer SD = new ShapeDrawer(PCB,SL);
        SCH.registerObserver(SD);
        controller.setup(); 
        PCB.addMouseListener(new MouseController(PCB,appState,SL,SCH)); //Extends MouseAdapter
        // For example purposes only; remove all lines below from your final project.
        try { Thread.sleep(500); } catch (InterruptedException e) { e.printStackTrace(); }
    }
}
