package model;


public class Point {
    public int x;
    public int y;
    public Point(int x, int y) { this.x = x; this.y = y; }
    public int getX() { return x; }
    public int getY() { return y; }
    public void setX(int x) { this.x = x; }
    public void setY(int y) { this.y = y; }
    public Boolean equals(Point otherPoint) { return otherPoint.getX() == x && otherPoint.getY() == y; }
    public String toString(){ return "(" + String.valueOf(this.getX()) + " , " + String.valueOf(this.getY()) + ")"; }
}