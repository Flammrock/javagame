
package modele.element;

import canvas.Canvas;
import canvas.Drawable;
import java.awt.Graphics;
import java.io.*;
import java.util.ArrayList;


public abstract class Element implements Drawable {
    String description;
    
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return this.getDescription();
    }
    
    
    
    
    @Override
    public ArrayList<Drawable> getDrawables() {return null;}
    
    
    @Override
    public int getX() {return 0;}

    @Override
    public void setX(int x) {}

    @Override
    public int getY() {return 0;}

    @Override
    public void setY(int y) {}
    
    
    
    @Override
    public void moveTo(int x, int y) {}
    
    @Override
    public void moveBy(int x, int y) {}
    
    
    @Override
    public void draw(Canvas c, Graphics g) {}
    
    @Override
    public boolean isDraw() {
        return true;
    }
    
}
