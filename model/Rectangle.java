package model;
import java.awt.*;

public class Rectangle extends Shape {
	int x, y,  h,  w,  eX,  eY;
	
	public Rectangle(int numSides, int x, int y, int h, int w, int eX, int eY) {
		super(numSides);
		this.x = x;
		this.y = y;
		this.h = h;
		this.w = w;
		this.eX = eX;
		this.eY = eY;
	}

	public void draw(Graphics2D g2d){
		if (this.w > 0 && this.h > 0) g2d.fillRect(x, y, w, h);
        else if (this.w < 0 && this.h > 0) g2d.fillRect(eX, y, Math.abs(w), h);
        else if (this.w > 0 && this.h < 0) g2d.fillRect(x, eY, w, Math.abs(h));
        else if (this.w < 0 && this.h < 0) g2d.fillRect(eX, eY, Math.abs(w), Math.abs(h));

	}

	public String getString(){
		return "Rectangle";	
	}
}
