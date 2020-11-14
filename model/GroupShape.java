package model;

import model.interfaces.IUndoable;
import model.interfaces.IShape;
import model.persistence.ShapeList;
import java.util.ArrayList;
import java.awt.*;

public class GroupShape extends Shape implements IUndoable {
    private Point topLeftMost = new PointBuilder().setX(1000000000).setY(1000000000).returnPoint();
    private Point bottomRightMost = new PointBuilder().setX(-1).setY(-1).returnPoint();
    private ShapeList SL;
    private int HX = -1; //inverse values for init
    private int HY = -1; //inverse values for init
    private int LX = 1000000000; //inverse values for init
    private int LY = 1000000000; //inverse values for init

    public GroupShape(ShapeList SL) {
        super(4, ShapeShadingType.OUTLINE, CreateShapeCommand.convertShapeColor(ShapeColor.BLACK),
                CreateShapeCommand.convertShapeColor(ShapeColor.BLACK),
                new PointBuilder().setX(1000000000).setY(1000000000).returnPoint(),
                new PointBuilder().setX(-1).setY(-1).returnPoint());
        this.SL = SL;
        driver();
        CommandHistory.add(this);
    }

    @Override public void setGroupOfShapes(ArrayList<IShape> AL){
        this.groupedShapes = AL;
        int HHX = -1; //inverse values for init
        int HHY = -1; //inverse values for init
        int LLX = 1000000000; //inverse values for init
        int LLY = 1000000000; //inverse values for init
        for (IShape shape : this.groupedShapes) {
            if (shape.getLargestX() > HHX) HHX = shape.getLargestX();
            if (shape.getLargestY() > HHY) HHY = shape.getLargestY();
            if (shape.getSmallestX() < LLX) LLX = shape.getSmallestX();
            if (shape.getSmallestY() < LLY) LLY = shape.getSmallestY();
        }
        topLeftMost = new PointBuilder().setX(LLX).setY(LLY).returnPoint();
        bottomRightMost = new PointBuilder().setX(HHX).setY(HHY).returnPoint();
        resetPoints(topLeftMost, bottomRightMost);
    }

    public void driver(){
        System.out.println("[GroupShape] Grouping shapes....");
        group();
        System.out.println("[GroupShape] Removing grouped shapes from main shapelist....");
        for (IShape shape : groupedShapes) SL.remove(SL.getShapeIndex(shape),false);
        System.out.println("[GroupShape] Adding new GroupShape to main shapelist....");
        SL.add((IShape)this,true);
        
    }

    public String getString() {
        return "Group";
    }

    public void group() {
        groupedShapes = new ArrayList<IShape>();// unobserved shape list;
        if (SL.areShapesSelected()){
        for (IShape shape : SL) {
            if (shape.isSelected()) {
                System.out.println("Found selected shape: "+shape.getStartingPoint().toString()+
                " "+shape.getEndingPoint().toString());
                if (shape.getLargestX() > HX) HX = shape.getLargestX();
                if (shape.getLargestY() > HY) HY = shape.getLargestY();
                if (shape.getSmallestX() < LX) LX = shape.getSmallestX();
                if (shape.getSmallestY() < LY) LY = shape.getSmallestY();
                groupedShapes.add(shape);
            }

        }
    }
    topLeftMost = new PointBuilder().setX(LX).setY(LY).returnPoint();
    bottomRightMost = new PointBuilder().setX(HX).setY(HY).returnPoint();
    resetPoints(topLeftMost, bottomRightMost);
    this.select();
    }

    public void draw(Graphics2D g2d) {
        System.out.println("[GroupShape]...executing Draw");
        for (IShape shape : groupedShapes) {
            shape.unselect();
            shape.draw(g2d);
        }
        if (this.isSelected()){
        g2d.setStroke(new BasicStroke(3, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 1, new float[]{9}, 0));
		g2d.setColor(Color.BLACK);
		if (this.shapeWidth > 0 && this.shapeHeight > 0) g2d.drawRect(this.startingPoint.getX(), this.startingPoint.getY(), shapeWidth, shapeHeight);
		else if (this.shapeWidth < 0 && this.shapeHeight > 0) g2d.drawRect(this.endingPoint.getX(), this.startingPoint.getY(), Math.abs(shapeWidth), shapeHeight);
		else if (this.shapeWidth > 0 && this.shapeHeight < 0) g2d.drawRect(this.startingPoint.getX(), this.endingPoint.getY(), shapeWidth, Math.abs(shapeHeight));
		else if (this.shapeWidth < 0 && this.shapeHeight < 0) g2d.drawRect(this.endingPoint.getX(), this.endingPoint.getY(), Math.abs(shapeWidth), Math.abs(shapeHeight));
        }
    }

    public void undo() {
        SL.remove(SL.getShapeIndex((IShape)this),false);
        for (IShape s : groupedShapes ) {
            s.select();
            SL.add(s,false);
        }
        SL.notifyObserver();
    }

    public void redo() {
        SL.add((IShape)this,false);
        for (IShape s : groupedShapes ) {
            s.unselect();
            SL.remove(SL.getShapeIndex(s),false);
        }
        SL.notifyObserver();
    }
}

