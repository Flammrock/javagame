/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geometry;

/**
 *
 * @author User
 */
public class Point {

    public int x;
    public int y;
    
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    public Point copy() {
        return new Point(this.x,this.y);
    }
    
    public void append(int x, int y) {
        this.x += x;
        this.y += y;
    }
    
    public void append(Direction d, int vx, int vy) {
        if (d.isLeft()) {
            this.x -= vx;
        } else if (d.isRight()) {
            this.x += vx;
        } else if (d.isUp()) {
            this.y -= vy;
        } else if (d.isDown()) {
            this.y += vy;
        }
    }

}
