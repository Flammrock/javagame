/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package canvas;

import java.awt.Graphics;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author User
 */
public class SpriteSheet extends Sprite {
    
    int width;  // width destination
    int height; // height destination
    
    int spriteWidth; // width source
    int spriteHeight; // height source
    
    int nx; // nombre d'image en x
    int ny; // nombre d'image en y
    
    int key; // clé d'animation
    
    public SpriteSheet(String spritefile) {
        super(spritefile);
    }
    
    public SpriteSheet(String spritefile, int x, int y, int width, int height) {
        super(spritefile,x,y);
        this.width = width;
        this.height = height;
        this.spriteWidth = width;
        this.spriteHeight = height;
        this.nx = 1;
        this.ny = 1;
        this.key = 0;
    }
    
    public SpriteSheet(String spritefile, int x, int y, int spriteWidth, int spriteHeight, int width, int height) {
        super(spritefile,x,y);
        this.width = width;
        this.height = height;
        this.spriteWidth = spriteWidth;
        this.spriteHeight = spriteHeight;
        this.nx = 1;
        this.ny = 1;
        this.key = 0;
    }
    
    public void setWidth(int width) {
        this.width = width;
    }
    
    public void setHeight(int height) {
        this.height = height;
    }
    
    public boolean load() {
        boolean r = super.load();
        if (!r) return false;
        if (this.width > this.image.getWidth()) this.width = this.image.getWidth();
        if (this.height > this.image.getHeight()) this.height = this.image.getHeight();
        this.nx = (int)Math.floor(this.image.getWidth() / this.width);
        this.ny = (int)Math.floor(this.image.getHeight() / this.height);
        return true;
    }
    
    private int getSpriteX() {
        if (this.nx == 0) return 0;
        return this.key % this.nx;
    }
    
    private int getSpriteY() {
        if (this.ny == 0) return 0;
        return this.key / this.ny;
    }
    
    private void nextKeyAnimation() {
        this.key++;
        if (this.key > this.nx*this.ny-1) this.key = 0;
    }
    
    @Override
    public void draw(Canvas c, Graphics g) {
        if (!this.isLoaded()) return;
        g.drawImage(this.image, this.getSpriteX(), this.getSpriteY(), this.spriteWidth, this.spriteHeight, this.x, this.y, this.width, this.height, null);
        this.nextKeyAnimation();
    }
    
    
    
}
