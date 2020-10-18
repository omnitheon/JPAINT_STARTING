package controller;

import model.interfaces.IApplicationState;
import view.EventName;
import view.interfaces.IUiModule;
import model.UndoCommand;
import model.RedoCommand;
import model.DeleteShapeCommand;
import model.CopyShapeCommand;
import model.persistence.ShapeList;

public class JPaintController implements IJPaintController {
    private final IUiModule uiModule;
    private final IApplicationState applicationState;
    private ShapeList SL;
    public JPaintController(IUiModule uiModule, IApplicationState applicationState, ShapeList SL) {
        this.uiModule = uiModule;
        this.applicationState = applicationState;
        this.SL = SL;
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
        //uiModule.addEvent(EventName.COPY, () -> new CopyShapeCommand().run());
        //uiModule.addEvent(EventName.PASTE, () -> new PasteShapeCommand().run());
        
        //copy
        //pastes
    }
}
