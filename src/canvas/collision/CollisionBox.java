/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package canvas.collision;

/**
 *
 * @author User
 */
public class CollisionBox {
    
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

    public int getSx() {
        return sx;
    }

    public int getSy() {
        return sy;
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
        return this.sx + this.x > b.getSx() + b.getX() && this.sy + this.x < b.getSx() + b.getX() + b.getWidth() && this.sy + this.y > b.getSy() + b.getY() && this.sy + this.y < b.getSy() + b.getY() + this.getHeight();
    }

    public void apply(int x, int y) {
        this.sx = x;
        this.sy = y;
    }
    
    public String toString() {
        return "(" + (this.sx+this.x) + "," + (this.sy+this.y) + "," + this.width + "," + this.height + ")";
    }
    
}
