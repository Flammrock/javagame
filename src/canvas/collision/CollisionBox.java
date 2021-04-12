/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package canvas.collision;

import canvas.Canvas;
import canvas.Drawable;
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

/**
 *
 * @author User
 */
public class CollisionBox implements Drawable {
    
    int x;
    int y;
    int width;
    int height;
    
    int sx;
    int sy;
    
    public CollisionBox(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.sx = 0;
        this.sy = 0;
    }

    public int getAbsoluteX() {
        return this.x + this.sx;
    }

    public int getAbsoluteY() {
        return this.y + this.sy;
    }
    
    

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
    
    public boolean isCollide(CollisionBox b) {
        return this.getAbsoluteX() < b.getAbsoluteX() + b.getWidth() &&
               this.getAbsoluteX() + this.width > b.getAbsoluteX() &&
               this.getAbsoluteY() < b.getAbsoluteX() + b.getHeight() &&
               this.getAbsoluteY() + this.height > b.getAbsoluteY();
    }

    public void apply(int x, int y) {
        this.sx = x;
        this.sy = y;
    }
    
    public String toString() {
        return "(" + (this.sx+this.x) + "," + (this.sy+this.y) + "," + this.width + "," + this.height + ")";
    }

    @Override
    public void draw(Canvas c, Graphics g) {
        g.setColor(Color.RED);
        g.fillRect(this.sx+this.x,this.sy+this.y,this.width,this.height);
    }

    @Override
    public ArrayList<Drawable> getDrawables() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getNewX() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getNewY() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void MoveTo(int x, int y) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void MoveBy(int x, int y) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void applyMove() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void cancelMove() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
