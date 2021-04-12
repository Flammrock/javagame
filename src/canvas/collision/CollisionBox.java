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
        return this.x > b.getX() && this.x < b.getX() + b.getWidth() && this.y > b.getY() && this.y < b.getY() + this.getHeight();
    }

    public void save() {
         this.sx = this.x;
         this.sy = this.x;
    }

    public void apply(int x, int y) {
        this.x += x;
        this.y += y;
    }

    public void restaure() {
        this.x = this.sx;
        this.y = this.sy;
    }
    
    public String toString() {
        return "(" + this.x + "," + this.y + "," + this.width + "," + this.height + ")";
    }
    
}
