/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package canvas;

import canvas.collision.CollisionBox;
import canvas.collision.Collisionable;
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
public class Sprite implements Collisionable {
    
    // les coordonnées x et y du sprite
    int x;
    int y;
    
    // les nouvelles coordonnées
    int mx;
    int my;
    
    // si le sprite a définit les nouvelles coordonnées mx et my
    boolean want2Move;
    
    // le nom du fichier de l'image
    String spritefile;
    
    // l'image une fois chargé
    BufferedImage image;
    
    // event ondraw
    Consumer<Canvas> ondraw;
    
    /**
     * Permet de créer un Sprite à partir d'un fichier image
     * @param spritefile le nom du fichier
     */
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
    
    /**
     * Permet de créer un Sprite à partir d'un fichier image
     * @param spritefile le nom du fichier
     * @param x
     * @param y
     */
    public Sprite(String spritefile, int x, int y) {
        this.spritefile = spritefile;
        this.image = null;
        this.x = x;
        this.y = y;
    }

    /**
     * Comme demander par l'interface Collisionable, getNewX() renvoie la nouvelle coordonnée X
     * qui est stocké ici dans this.mx
     * @return retourne la nouvelle coordonnée X
     */
    @Override
    public int getNewX() {
        
        // si moveTo() ou moveBy() a été appelé alors this.want2Move vaut true
        // dans ce cas, this.mx contient la nouvelle coordonnée X définit par l'appel
        // de moveTo() ou moveBy()
        if (this.want2Move) {
            return mx;
            
        // sinon on renvoie l'ancienne position
        // (on ignore en quelques sortes l'appel de moveTo() et de moveBy())
        } else {
            return x;
        }
        
    }

    /**
     * Comme demander par l'interface Collisionable, getNewX() renvoie la nouvelle coordonnée Y
     * qui est stocké ici dans this.my
     * @return retourne la nouvelle coordonnée Y
     */
    @Override
    public int getNewY() {
        
        // si moveTo() ou moveBy() a été appelé alors this.want2Move vaut true
        // dans ce cas, this.my contient la nouvelle coordonnée Y définit par l'appel
        // de moveTo() ou moveBy()
        if (this.want2Move) {
            return my;
            
        // sinon on renvoie l'ancienne position
        // (on ignore en quelques sortes l'appel de moveTo() et de moveBy())
        } else {
            return y;
        }
    }

    
    
    @Override
    public int getX() {
        return x;
    }

    @Override
    public void setX(int x) {
        this.x = x;
    }

    @Override
    public int getY() {
        return y;
    }

    @Override
    public void setY(int y) {
        this.y = y;
    }
    
    
    
    @Override
    public void moveTo(int x, int y) {
        this.mx = x;
        this.my = y;
        this.want2Move = true;
    }
    
    @Override
    public void moveBy(int x, int y) {
        this.mx = this.x + x;
        this.my = this.y + y;
        this.want2Move = true;
    }
    
    @Override
    public void cancelMove() {
        this.mx = 0;
        this.my = 0;
        this.want2Move = false;
    }
    
    @Override
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
        return null; // un sprite peut posséder un sous-ensemble de sprite
    }

    @Override
    public ArrayList<CollisionBox> getCollisionBoxList() {
         return null; // un sprite n'a pas de collision box
    }

    @Override
    public void addCollisionBox(CollisionBox b) {
        // un sprite n'a pas de collision box
    }
    
}
