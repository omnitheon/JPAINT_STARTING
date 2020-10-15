package model;

public class PointBuilder {
    private int x;
    private int y;
    public PointBuilder() { this.x = 0; this.y = 0; }
    public PointBuilder setX(int x) { this.x = x; return this; }
    public PointBuilder setY(int y) { this.y = y; return this; }
    public Point returnPoint() {
        return new Point(x,y);
        /*
        if (x <= 0 || y <= 0 ) throw new NullPointerException();
        else return new Point(x,y);
        */
    }
}