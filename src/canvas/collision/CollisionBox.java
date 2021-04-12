/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package canvas.collision;

import canvas.Canvas;
import canvas.Drawable;
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

/**
 *
 * @author User
 */
public class CollisionBox implements Drawable {
    
    
    // les coordonnées relatives de la boite de collision
    // c'est-à-dire, si x = 0 et y = 0, alors la boite aura la même position absolue
    // que l'objet qui contient la boite de collision
    int x;
    int y;
    int width;
    int height;
    
    // les coordonnées de l'objet parent (peuvent être modifier via la méthode apply(int x, int y)
    int sx;
    int sy;
    
    /**
     * Constuire une boite de collision à l'aide de coordonnées relatives
     * c'est-à-dire, si x = 0 et y = 0, alors la boite aura la même position absolue
     * que l'objet qui contient la boite de collision
     * 
     * @param x la coordonnée x
     * @param y la coordonnée y
     * @param width la largeur
     * @param height la hauteur
     */
    public CollisionBox(int x, int y, int width, int height) {
        
        // on enregistre les valeurs
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        
        // pour l'instant, l'objet parent n'a pas encore appelé apply(int x, int y)
        this.sx = 0;
        this.sy = 0;
    }

    /**
     * Permet de récupérer la coordonnée X absolue de la boite
     * @return retourne la position relative + la position de l'objet parent
     */
    public int getAbsoluteX() {
        return this.x + this.sx;
    }

    /**
     * Permet de récupérer la coordonnée Y absolue de la boite
     * @return retourne la position relative + la position de l'objet parent
     */
    public int getAbsoluteY() {
        return this.y + this.sy;
    }
    
    

    /**
     * Permet de récupérer la position relative X
     * @return retourne la position relative X
     */
    @Override
    public int getX() {
        return x;
    }

    /**
     * Permet de définir la position relative X
     * @param x la nouvelle coordonnée relative X
     */
    @Override
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Permet de récupérer la position relative Y
     * @return retourne la position relative Y
     */
    @Override
    public int getY() {
        return y;
    }

    /**
     * Permet de définir la position relative Y
     * @param y la nouvelle coordonnée relative Y
     */
    @Override
    public void setY(int y) {
        this.y = y;
    }

    /**
     * Permet de récupérer la largeur (width) de la boite
     * @return retourne la largeur de la boite
     */
    public int getWidth() {
        return width;
    }

    /**
     * Permet de définir la largeur (width) de la boite
     * @param width la nouvelle largeur de la boite
     */
    public void setWidth(int width) {
        this.width = width;
    }

    /**
     * Permet de récupérer la hauteur (height) de la boite
     * @return retourne la heuteur de la boite
     */
    public int getHeight() {
        return height;
    }

    /**
     * Permet de définir la hauteur (height) de la boite
     * @param height la nouvelle hauteur de la boite
     */
    public void setHeight(int height) {
        this.height = height;
    }
    
    /**
     * Permet de savoir si cette boite est en collision avec une autre boite
     * @param b la 2ème boite
     * @return retourne true s'il y a collisions entre les 2 boites, sinon false
     */
    public boolean isCollide(CollisionBox b) {
        return this.getAbsoluteX() < b.getAbsoluteX() + b.getWidth() &&
               this.getAbsoluteX() + this.width > b.getAbsoluteX() &&
               this.getAbsoluteY() < b.getAbsoluteY() + b.getHeight() &&
               this.getAbsoluteY() + this.height > b.getAbsoluteY();
    }

    /**
     * Permet de positioner la boite de manière absolue sans changer les informations relatives de la boites
     * @param x la coordonnée X (ie.e la coorodnnée de l'objet qui contient cette collision box)
     * @param y la coordonnée Y (ie.e la coorodnnée de l'objet qui contient cette collision box)
     */
    public void apply(int x, int y) {
        this.sx = x;
        this.sy = y;
    }
    
    
    @Override
    public String toString() {
        return "(" + (this.sx+this.x) + "," + (this.sy+this.y) + "," + this.width + "," + this.height + ")";
    }

    @Override
    public void draw(Canvas c, Graphics g) {
        g.setColor(Color.RED);
        g.fillRect(this.sx+this.x,this.sy+this.y,this.width,this.height);
    }

    @Override
    public ArrayList<Drawable> getDrawables() {
        return null;
    }

    @Override
    public void moveTo(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public void moveBy(int x, int y) {
        this.x += x;
        this.y += y;
    }
    
}
