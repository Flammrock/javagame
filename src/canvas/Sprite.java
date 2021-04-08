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
import javax.imageio.ImageIO;

/**
 *
 * @author User
 */
public class Sprite implements Drawable {
    
    int x;
    int y;
    
    String spritefile;
    BufferedImage image;
    
    public Sprite(String spritefile) {
        this.spritefile = spritefile;
        this.image = null;
        this.x = 0;
        this.y = 0;
    }
    
    public Sprite(String spritefile, int x, int y) {
        this.spritefile = spritefile;
        this.image = null;
        this.x = x;
        this.y = y;
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
        this.x = x;
        this.y = y;
    }
    
    public void MoveBy(int x, int y) {
        this.x += x;
        this.y += y;
    }
    
    public boolean isLoaded() {
        return this.image != null;
    }
    
    public boolean load() {
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
    }
    
}