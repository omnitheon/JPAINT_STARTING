package controller;

import model.interfaces.IApplicationState;
import view.EventName;
import view.interfaces.IUiModule;
import model.UndoCommand;
import model.GroupShape;
import model.UngroupShapeCommand;
import model.RedoCommand;
import model.DeleteShapeCommand;
import model.CopyShapeCommand;
import model.persistence.ShapeList;
import model.PasteShapeCommand;
import java.util.ArrayList;
import model.interfaces.IShape;

public class JPaintController implements IJPaintController {
    private final IUiModule uiModule;
    private final IApplicationState applicationState;
    private ShapeList SL;
    private ArrayList<IShape> CLIPBOARD;
    public JPaintController(IUiModule uiModule, IApplicationState applicationState, ShapeList SL, ArrayList<IShape> CLIPBOARD) {
        this.uiModule = uiModule;
        this.applicationState = applicationState;
        this.SL = SL;
        this.CLIPBOARD = CLIPBOARD;
    }


    @Override
    public void setup() {
        setupEvents();
    }

    private void setupEvents() {
        uiModule.addEvent(EventName.CHOOSE_SHAPE, () -> applicationState.setActiveShape());
        uiModule.addEvent(EventName.CHOOSE_PRIMARY_COLOR, () -> applicationState.setActivePrimaryColor());
        uiModule.addEvent(EventName.CHOOSE_SECONDARY_COLOR, () -> applicationState.setActiveSecondaryColor());
        uiModule.addEvent(EventName.CHOOSE_SHADING_TYPE, () -> applicationState.setActiveShadingType());
        uiModule.addEvent(EventName.CHOOSE_MOUSE_MODE, () -> applicationState.setActiveStartAndEndPointMode());
        uiModule.addEvent(EventName.UNDO, () -> new UndoCommand().run());
        uiModule.addEvent(EventName.REDO, () -> new RedoCommand().run());
        uiModule.addEvent(EventName.DELETE, () -> new DeleteShapeCommand(SL).run());
        uiModule.addEvent(EventName.COPY, () -> new CopyShapeCommand(SL,CLIPBOARD).run());
        uiModule.addEvent(EventName.PASTE, () -> new PasteShapeCommand(SL,CLIPBOARD).run());
        uiModule.addEvent(EventName.GROUP, () -> new GroupShape(SL));
        uiModule.addEvent(EventName.UNGROUP, () -> new UngroupShapeCommand(SL).run());
    }
}
