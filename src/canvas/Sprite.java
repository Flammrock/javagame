/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package canvas;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.function.Consumer;
import javax.imageio.ImageIO;

/**
 *
 * @author User
 */
public class Sprite implements Drawable {
    
    int x;
    int y;
    int mx;
    int my;
    boolean want2Move;
    
    String spritefile;
    BufferedImage image;
    Consumer<Canvas> ondraw;
    
    public Sprite(String spritefile) {
        this.spritefile = spritefile;
        this.image = null;
        this.x = 0;
        this.y = 0;
        this.mx = 0;
        this.my = 0;
        this.ondraw = null;
        this.want2Move = true;
    }
    
    public Sprite(String spritefile, int x, int y) {
        this.spritefile = spritefile;
        this.image = null;
        this.x = x;
        this.y = y;
    }

    public int getMx() {
        return mx;
    }

    public int getMy() {
        return my;
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
    
    
    
    public void MoveTo(int x, int y) {
        //this.x = x;
        //this.y = y;
        this.mx = x;
        this.my = y;
        this.want2Move = true;
    }
    
    public void MoveBy(int x, int y) {
        //this.x += x;
        //this.y += y;
        this.mx = this.x + x;
        this.my = this.y + y;
        this.want2Move = true;
    }
    
    public void cancelMove() {
        this.mx = 0;
        this.my = 0;
        this.want2Move = false;
    }
    
    public void applyMove() {
        if (this.want2Move) {
            this.want2Move = false;
            this.x = this.mx;
            this.y = this.my;
            this.mx = 0;
            this.my = 0;
        }
    }
    
    public boolean isLoaded() {
        return this.image != null;
    }
    
    public boolean loadImage() {
        try {
            this.image = ImageIO.read(getClass().getResourceAsStream(this.spritefile));
            return true;
        } catch (IOException e) {}
        return false;
    }

    @Override
    public void draw(Canvas c, Graphics g) {
        if (!this.isLoaded()) return;
        g.drawImage(this.image, this.x, this.y, null);
        if (this.ondraw!=null) this.ondraw.accept(c);
    }
    
    public void setOnDraw(Consumer<Canvas> fn) {
        this.ondraw = fn;
    }

    @Override
    public ArrayList<Drawable> getDrawables() {
        return null;
    }
    
}
