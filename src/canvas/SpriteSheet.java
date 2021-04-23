/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package canvas;

import java.awt.Color;
import java.awt.Graphics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
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
    
    int decalX;
    int decalY;
    int decalW;
    int decalH;
    
    HashMap<String, Animation> animations; // liste des animations
    String animationCourrante; // nom de l'animation courrante
    
    int savedSpriteX;
    int savedSpriteY;
    
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
        this.width = width;
        this.height = height;
        this.spriteWidth = 0;
        this.spriteHeight = 0;
        this.nx = 1;
        this.ny = 1;
        this.animationCourrante = "";
        this.animations = new HashMap<>();
        this.savedSpriteX = 0;
        this.savedSpriteY = 0;
        this.decalX = 0;
        this.decalY = 0;
        this.decalW = 0;
        this.decalH = 0;
    }
    
    public void setDecal(int x, int y, int w, int h) {
        this.decalX = x;
        this.decalY = y;
        this.decalW = w;
        this.decalH = h;
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
        System.out.println("??????????????????????:"+this.getWidth());
        this.spriteWidth = spriteWidth;
        this.spriteHeight = spriteHeight;
    }

    public HashMap<String, Animation> getAnimations() {
        return this.animations;
    }

    public Animation getAnimationCourrante() {
        if (this.animations.isEmpty()) return null;
        return this.animations.get(this.animationCourrante);
    }

    public Animation getAnimationParNom(String nom) {
        if (this.animations.isEmpty()) return null;
        return this.animations.get(nom);
    }
    
    public void ajouterAnimation(Animation a) {
        this.animations.put(a.getNom(), a);
    }
    
    public void enleverAnimation(String nom) {
        this.animations.remove(nom);
    }
    
    public boolean setAnimation(String nom) {
        if (this.animations.isEmpty()) return false;
        if (this.getAnimationParNom(nom)==null) return false;
        this.animationCourrante = nom;
        this.getAnimationParNom(nom).reset();
        return true;
    }
    
    public boolean setAnimationIfNot(String nom) {
        if (this.animations.isEmpty()) return false;
        if (this.getAnimationParNom(nom)==null) return false;
        if (this.animationCourrante.equals(nom)) return false;
        this.animationCourrante = nom;
        this.getAnimationParNom(nom).reset();
        return true;
    }
    
    
    public void setWidth(int width) {
        this.width = width;
    }
    
    public void setHeight(int height) {
        this.height = height;
    }
    
    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }
    
    public boolean loadImage() {
        boolean r = super.loadImage();
        if (!r) return false;
        if (this.spriteWidth > this.image.getWidth()) this.spriteWidth = this.image.getWidth();
        if (this.spriteHeight > this.image.getHeight()) this.spriteHeight = this.image.getHeight();
        this.nx = (int)Math.floor(this.image.getWidth() / this.spriteWidth);
        this.ny = (int)Math.floor(this.image.getHeight() / this.spriteHeight);
        return true;
    }
    
    private int getSpriteX() {
        if (this.nx == 0) return this.savedSpriteX;
        if (this.getAnimationCourrante()==null) return this.savedSpriteX;
        int key = this.getAnimationCourrante().getKey();
        if (key > this.nx*this.ny-1) key = 0;
        this.savedSpriteX = (key % this.nx) * this.spriteWidth;
        return this.savedSpriteX;
    }
    
    private int getSpriteY() {
        if (this.ny == 0) return this.savedSpriteY;
        if (this.getAnimationCourrante()==null) return this.savedSpriteY;
        int key = this.getAnimationCourrante().getKey();
        if (key > this.nx*this.ny-1) key = 0;
        this.savedSpriteY = (key / this.nx) * this.spriteHeight;
        return this.savedSpriteY;
    }
    
    private void nextKeyAnimation() {
        
        if (this.animations.isEmpty()) return;
        if (this.getAnimationCourrante()==null) return;
        
        this.getAnimationCourrante().nextKey();
        
    }
    
    @Override
    public void draw(Canvas c, Graphics g) {
        if (!this.isLoaded()) return;
        //g.drawImage(this.image, this.x, this.y, null);
        
        int w = this.x+this.width - (int)(((double)this.width/(double)this.spriteWidth)*(double)(this.decalW+this.decalX));
        int h = this.y+this.height - (int)(((double)this.height/(double)this.spriteHeight)*(double)(this.decalH+this.decalY));
        
        g.drawImage(
                this.image,
                
                // dest
                c.toWorldX(this.x),
                c.toWorldY(this.y),
                c.toWorldX(w),
                c.toWorldY(h),
                
                // src
                this.getSpriteX()+this.decalX,
                this.getSpriteY()+this.decalY,
                this.getSpriteX()+this.spriteWidth-this.decalW,
                this.getSpriteY()+this.spriteHeight-this.decalH,
                
                null
        );
        
        //g.setColor(Color.cyan);
        //g.drawRect(c.toWorldX(x), c.toWorldY(y), c.toScale(w-x), c.toScale(h-y));
        
        this.nextKeyAnimation();
        if (this.ondraw!=null) this.ondraw.accept(c);
    }
    
    
    
}
