package model.interfaces;
import java.awt.*;

public interface IShape {
    String getString();
    void draw(Graphics2D g2d);
    int getNumSides();
    Color getPrimaryColor();
    Color getSecondaryColor();
}
 