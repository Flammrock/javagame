/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geometry;

import java.util.ArrayList;

/**
 *
 * @author User
 */
public class Box {

    public Point position;
    public Point size;
    
    public Box(Point pos, Point size) {
        this.position = pos;
        this.size = size;
    }
    
    
    
    
    public int getX() {
        return this.position.x;
    }
    
    public int getY() {
        return this.position.y;
    }
    
    public int getWidth() {
        return this.size.x;
    }
    
    public int getHeight() {
        return this.size.y;
    }
    
    public void setX(int v) {
        this.position.x = v;
    }
    
    public void setY(int v) {
        this.position.y = v;
    }
    
    public void setWidth(int v) {
        this.size.x = v;
    }
    
    public void setHeight(int v) {
        this.size.y = v;
    }
    
    

    /**
     * permet de faire un raycast mais avec des rectangles :)
     * @param list
     * @return retourne true s'il y a une collision, false sinon
     */
    public boolean isCollide(ArrayList<Box> list) {
        for (Box b : list) {
            if (this.position.x < b.position.x + b.size.x &&
               this.position.x + this.size.x > b.position.x &&
               this.position.y < b.position.y + b.size.y &&
               this.position.y + this.size.y > b.position.y) {
                return true;
            }
        }
        return false;
    }
    
}
