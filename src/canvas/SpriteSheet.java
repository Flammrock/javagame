/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package canvas;

import java.awt.Graphics;
import java.io.IOException;
import java.util.ArrayList;
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
    
    int index; // indice du tableau d'animations
    ArrayList<Integer> keyMap; // tableau des clés d'animations
    
    int tick; // le tick courrant
    int tickMax; // le nombre max de tick avant de passer à la next frame
    
    /**
     * Permet de créer un sprite depuis un spritesheet sans aucune animation
     * @param spritefile le nom du fichier
     * @param x la position en x du sprite sur le canvas
     * @param y la position en y du sprite sur le canvas
     * @param width la longueur du sprite sur le canvas
     * @param height la hauteur du sprite sur le canvas
     */
    public SpriteSheet(String spritefile, int x, int y, int width, int height) {
        super(spritefile,x,y);
        this.tick = 0;
        this.tickMax = 10;
        this.width = width;
        this.height = height;
        this.spriteWidth = 0;
        this.spriteHeight = 0;
        this.nx = 1;
        this.ny = 1;
        this.key = 0;
        keyMap = new ArrayList<Integer>();
    }
    
    /**
     * Permet de créer un sprite depuis un spritesheet sans aucune animation
     * @param spritefile le nom du fichier
     * @param x la position en x du sprite sur le canvas
     * @param y la position en y du sprite sur le canvas
     * @param spriteWidth la longueur du sous-sprite dans le spritesheet
     * @param spriteHeight la hauteur du sous-sprite dans le spritesheet
     * @param width la longueur du sprite sur le canvas
     * @param height la hauteur du sprite sur le canvas
     */
    public SpriteSheet(String spritefile, int x, int y, int spriteWidth, int spriteHeight, int width, int height) {
        this(spritefile,x,y,width,height);
        this.spriteWidth = spriteWidth;
        this.spriteHeight = spriteHeight;
    }

    
    public void SetIntervalTick(int tickMax) {
        this.tickMax = tickMax;
    }
    
    public int getIntervalTick() {
        return this.tickMax;
    }
    
    
    
    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public ArrayList<Integer> getKeyMap() {
        return keyMap;
    }

    public void setKeyMap(ArrayList<Integer> keyMap) {
        this.keyMap = keyMap;
    }
    
    public void setKeyMap(int[] keyMap) {
        this.keyMap.clear();
        for (int i = 0; i < keyMap.length; i++) {
            this.keyMap.add(keyMap[i]);
        }
    }
    
    
    
    
    public void setWidth(int width) {
        this.width = width;
    }
    
    public void setHeight(int height) {
        this.height = height;
    }
    
    public boolean loadImage() {
        boolean r = super.load();
        if (!r) return false;
        if (this.spriteWidth > this.image.getWidth()) this.spriteWidth = this.image.getWidth();
        if (this.spriteHeight > this.image.getHeight()) this.spriteHeight = this.image.getHeight();
        this.nx = (int)Math.floor(this.image.getWidth() / this.spriteWidth);
        this.ny = (int)Math.floor(this.image.getHeight() / this.spriteHeight);
        return true;
    }
    
    private int getSpriteX() {
        if (this.nx == 0) return 0;
        return (this.key % this.nx) * this.spriteWidth;
    }
    
    private int getSpriteY() {
        if (this.ny == 0) return 0;
        return (this.key / this.ny) * this.spriteHeight;
    }
    
    private void nextKeyAnimation() {
        
        if (this.keyMap.size()==0) return;
        
        this.tick++;
        if (this.tick < this.tickMax) return;
        
        this.tick = 0;
        
        this.index++;
        
        if (this.index < 0) this.index = this.keyMap.size()-1;
        if (this.index > this.keyMap.size()-1) this.index = 0;
        
        this.key = this.keyMap.get(this.index);
        
        if (this.key < 0) this.key = 0;
        if (this.key > this.nx*this.ny-1) this.key = 0;
        
    }
    
    @Override
    public void draw(Canvas c, Graphics g) {
        if (!this.isLoaded()) return;
        g.drawImage(this.image, this.x, this.y, this.x+this.width, this.y+this.height, this.getSpriteX(), this.getSpriteY(), this.getSpriteX()+this.spriteWidth, this.getSpriteY()+this.spriteHeight, null);
        this.nextKeyAnimation();
    }
    
    
    
}
