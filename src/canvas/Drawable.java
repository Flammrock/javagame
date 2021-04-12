/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package canvas;

import java.awt.Graphics;
import java.util.ArrayList;

/**
 *
 * @author User
 */
public interface Drawable {
    
    void draw(Canvas c, Graphics g);
    
    
    public ArrayList<Drawable> getDrawables();
    
    
    public int getX();

    public void setX(int x);

    public int getY();

    public void setY(int y);
    
    
    
    public void MoveTo(int x, int y);
    
    public void MoveBy(int x, int y);
    
    public void applyMove();
    
    public void cancelMove();
    
}
