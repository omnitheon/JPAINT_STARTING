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

public class Main {
    public static void main(String[] args){
        PaintCanvasBase PCB = new PaintCanvas(); //Probably wont need to modify, only ever want one instance
        IGuiWindow guiWindow = new GuiWindow(PCB); //Wont have to modify/know
        IUiModule uiModule = new Gui(guiWindow); //Wont have to modify/know
        StateChangeHandler SCH = new StateChangeHandler();
        ShapeList SL = new ShapeList(SCH,"main");
        ApplicationState appState = new ApplicationState(uiModule); //, only ever want one instance
        IJPaintController controller = new JPaintController(uiModule, appState, SL); //, only ever want one instance
        ShapeDrawer SD = new ShapeDrawer(PCB,SL);
        SCH.registerObserver(SD);
        controller.setup(); 
        PCB.addMouseListener(new MouseController(PCB,appState,SL,SCH)); //Extends MouseAdapter

        // For example purposes only; remove all lines below from your final project.
        try { Thread.sleep(500); } catch (InterruptedException e) { e.printStackTrace(); }
    }
}
