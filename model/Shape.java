package model;
import model.interfaces.IShape;

public abstract class Shape implements IShape {
    int numSides;


    public Shape(int numSides){ this.numSides = numSides; }
    public int getNumSides(){ return numSides; }
}