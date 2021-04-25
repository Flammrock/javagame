/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package canvas;

import canvas.collision.CollisionBox;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

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
    Animation animationCourranteObj;
    
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
        this.animationCourranteObj = null;
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
        this.spriteWidth = spriteWidth;
        this.spriteHeight = spriteHeight;
    }

    public int getSpriteWidth() {
        return spriteWidth;
    }

    public int getSpriteHeight() {
        return spriteHeight;
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
        this.animationCourranteObj = this.getAnimationParNom(nom);
        return true;
    }
    
    public boolean setAnimationIfNot(String nom) {
        if (this.animations.isEmpty()) return false;
        if (this.getAnimationParNom(nom)==null) return false;
        if (this.animationCourrante.equals(nom)) return false;
        this.animationCourrante = nom;
        this.getAnimationParNom(nom).reset();
        this.animationCourranteObj = this.getAnimationParNom(nom);
        return true;
    }
    
    public void clearAnimation() {
        this.animations.clear();
        this.animationCourranteObj = null;
        this.animationCourrante = "";
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
    
    public int computeWidth() {
        BufferedImage image = this.getImage();
        int w = this.width - (int)(((double)this.width/(double)this.spriteWidth)*(double)(this.decalW+this.decalX));
        return w;
    }

    public int computeHeight() {
        BufferedImage image = this.getImage();
        int h = this.height - (int)(((double)this.height/(double)this.spriteHeight)*(double)(this.decalH+this.decalY));
        return h;
    }
    
    public boolean loadImage() {
        boolean r = super.loadImage();
        if (!r) return false;
        BufferedImage image = this.getImage();
        if (this.spriteWidth > image.getWidth()) this.spriteWidth = image.getWidth();
        if (this.spriteHeight > image.getHeight()) this.spriteHeight = image.getHeight();
        this.nx = (int)Math.floor(image.getWidth() / this.spriteWidth);
        this.ny = (int)Math.floor(image.getHeight() / this.spriteHeight);
        return true;
    }
    
    private int getSpriteX() {
        if (this.nx == 0) return this.savedSpriteX;
        if (this.animationCourranteObj==null) return this.savedSpriteX;
        int key = this.animationCourranteObj.getKey();
        if (key > this.nx*this.ny-1) key = 0;
        this.savedSpriteX = (key % this.nx) * this.spriteWidth;
        return this.savedSpriteX;
    }
    
    private int getSpriteY() {
        if (this.ny == 0) return this.savedSpriteY;
        if (this.animationCourranteObj==null) return this.savedSpriteY;
        int key = this.animationCourranteObj.getKey();
        if (key > this.nx*this.ny-1) key = 0;
        this.savedSpriteY = (key / this.nx) * this.spriteHeight;
        return this.savedSpriteY;
    }
    
    private void nextKeyAnimation() {
        
        if (this.animations.isEmpty()) return;
        if (this.animationCourranteObj==null) return;
        
        this.animationCourranteObj.nextKey();
        
    }
    
    @Override
    public void draw(Canvas c, Graphics g) {
        if (!this.isLoaded()) return;
        //g.drawImage(this.image, this.x, this.y, null);
        
        BufferedImage image = this.getImage();
        
        int w = this.x+this.width - (int)(((double)this.width/(double)this.spriteWidth)*(double)(this.decalW+this.decalX));
        int h = this.y+this.height - (int)(((double)this.height/(double)this.spriteHeight)*(double)(this.decalH+this.decalY));
        
        g.drawImage(
                image,
                
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
        if (this.ondraw!=null) this.ondraw.onDraw(c);
    }
    
    @Override
    public double getRatio() {
        if (!this.isLoaded()) return 1.0;
        BufferedImage image = this.getImage();
        int nsw = image.getWidth();
        int nsh = image.getHeight();
        if (this.spriteWidth >= 0) nsw = this.spriteWidth;
        if (this.spriteHeight >= 0) nsh = this.spriteHeight;
        return (double)nsw / (double)nsh;
    }

    public void setAnimationExternal(Animation animation) {
        this.animationCourranteObj = animation;
    }
    
    public SpriteSheet copie() {
        SpriteSheet c = new SpriteSheet(spritefile, x, y, spriteWidth, spriteHeight, width, height);
        c.setSource(sx, sy, swidth, sheight);
        c.setScaleWidth(this.scalewidth);
        c.setScaleHeight(this.scaleheight);
        c.setRepeatX(repeatX);
        c.setRepeatY(repeatY);
        for (CollisionBox b : this.collisionBoxList) {
            c.addCollisionBox(b.copie());
        }
        for (Drawable d : this.drawables) {
            c.addDrawable(d.copie());
        }
        for (Iterator<Map.Entry<String, Animation>> it = this.animations.entrySet().iterator(); it.hasNext();) {
            Map.Entry<String, Animation> entry = it.next();
            c.ajouterAnimation(entry.getValue().copie());
        }
        c.setAnimation(this.animationCourrante);
        c.setDecal(decalX,decalY,decalW,decalH);
        c.loadImage();
        return c;
    }
    
}
