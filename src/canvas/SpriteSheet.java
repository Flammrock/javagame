/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package canvas;

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
    
    HashMap<String, Animation> animations; // liste des animations
    String animationCourrante; // nom de l'animation courrante
    
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
        if (this.getAnimationCourrante()==null) return 0;
        int key = this.getAnimationCourrante().getKey();
        if (key > this.nx*this.ny-1) key = 0;
        return (key % this.nx) * this.spriteWidth;
    }
    
    private int getSpriteY() {
        if (this.ny == 0) return 0;
        if (this.getAnimationCourrante()==null) return 0;
        int key = this.getAnimationCourrante().getKey();
        if (key > this.nx*this.ny-1) key = 0;
        return (key / this.ny) * this.spriteHeight;
    }
    
    private void nextKeyAnimation() {
        
        if (this.animations.isEmpty()) return;
        if (this.getAnimationCourrante()==null) return;
        
        this.getAnimationCourrante().nextKey();
        
    }
    
    @Override
    public void draw(Canvas c, Graphics g) {
        if (!this.isLoaded()) return;
        g.drawImage(this.image, this.x, this.y, this.x+this.width, this.y+this.height, this.getSpriteX(), this.getSpriteY(), this.getSpriteX()+this.spriteWidth, this.getSpriteY()+this.spriteHeight, null);
        this.nextKeyAnimation();
        if (this.ondraw!=null) this.ondraw.accept(c);
    }
    
    
    
}
