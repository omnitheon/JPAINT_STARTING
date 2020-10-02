package model.persistence;

import model.ShapeColor;
import model.ShapeShadingType;
import model.ShapeType;
import model.MouseMode;
import model.dialogs.DialogProvider;
import model.interfaces.IApplicationState;
import model.interfaces.IDialogProvider;
import view.interfaces.IUiModule;

public class ApplicationState implements IApplicationState {
    private final IUiModule uiModule;
    private final IDialogProvider dialogProvider;

    private ShapeType activeShapeType;
    private ShapeColor activePrimaryColor;
    private ShapeColor activeSecondaryColor;
    private ShapeShadingType activeShapeShadingType;
    private MouseMode activeMouseMode;

    public ApplicationState(IUiModule uiModule) {
        this.uiModule = uiModule;
        this.dialogProvider = new DialogProvider(this);
        setDefaults();
    }

    @Override //Dont worry, GUI does this.
    public void setActiveShape() { activeShapeType = uiModule.getDialogResponse(dialogProvider.getChooseShapeDialog()); }
    @Override //Dont worry, GUI does this.
    public void setActivePrimaryColor() { activePrimaryColor = uiModule.getDialogResponse(dialogProvider.getChoosePrimaryColorDialog()); }
    @Override //Dont worry, GUI does this. 
    public void setActiveSecondaryColor() { activeSecondaryColor = uiModule.getDialogResponse(dialogProvider.getChooseSecondaryColorDialog()); }
    @Override //Dont worry, GUI does this.
    public void setActiveShadingType() { activeShapeShadingType = uiModule.getDialogResponse(dialogProvider.getChooseShadingTypeDialog()); }
    @Override //Dont worry, GUI does this.
    public void setActiveStartAndEndPointMode() { activeMouseMode = uiModule.getDialogResponse(dialogProvider.getChooseStartAndEndPointModeDialog()); }
    @Override
    public ShapeType getActiveShapeType() { return activeShapeType; } //May need this for implementing the sprints, set by GUI.
    @Override
    public ShapeColor getActivePrimaryColor() { return activePrimaryColor; } //May need this for implementing the sprints, set by GUI.
    @Override
    public ShapeColor getActiveSecondaryColor() { return activeSecondaryColor; } //May need this for implementing the sprints, set by GUI.
    @Override
    public ShapeShadingType getActiveShapeShadingType() { return activeShapeShadingType; } //May need this for implementing the sprints, set by GUI.
    @Override
    public MouseMode getActiveMouseMode() { return activeMouseMode; } //May need this for implementing the sprints, set by GUI.

    private void setDefaults() {
        activeShapeType = ShapeType.RECTANGLE;
        activePrimaryColor = ShapeColor.BLUE;
        activeSecondaryColor = ShapeColor.GREEN;
        activeShapeShadingType = ShapeShadingType.FILLED_IN;
        activeMouseMode = MouseMode.DRAW;
    }
}
