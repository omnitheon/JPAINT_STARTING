package model;

public class PointBuilder {
    private int x;
    private int y;
    public PointBuilder() { this.x = 0; this.y = 0; }
    public void setX(int x) { this.x = x; }
    public void setY(int y) { this.y = y; }
    public Point returnPoint() {
        if (x <= 0 || y <= 0 ) throw new NullPointerException();
        else return new Point(x,y);
    }
}