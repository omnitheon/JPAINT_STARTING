package model;


public class Point {
    private final int x;
    private final int y;
    protected Point(int x, int y) { this.x = x; this.y = y; }
    public int getX() { return x; }
    public int getY() { return y; }
    public boolean isSamePoint(Point otherPoint) { return otherPoint.getX() == x && otherPoint.getY() == y; }
    public String toString(){ return "(" + String.valueOf(this.getX()) + ", " + String.valueOf(this.getY()) + ")"; }
}