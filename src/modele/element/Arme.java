/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele.element;

import canvas.Canvas;
import canvas.Sprite;
import java.awt.Graphics;

/**
 *
 * @author User
 */
public class Arme extends Equipement {
    
    Sprite sprite;
    
    public Arme(String nom, String description, double poids, double agilite, double force) {
        super(nom,description,poids);
        this.sprite = null;
        this.bonus_agilite = agilite;
        this.bonus_force = force;
    }
    
    public void setSprite(Sprite s) {
        this.sprite = s.copie();
    }
    
    @Override
    public int getX() {
        if (this.sprite == null) return 0;
        return this.sprite.getX(); // pour l'instant, on propage ça dans le sprite
    }

    @Override
    public void setX(int x) {
        if (this.sprite == null) return;
        this.sprite.setX(x); // pour l'instant, on propage ça dans le sprite
    }

    @Override
    public int getY() {
        if (this.sprite == null) return 0;
        return this.sprite.getY(); // pour l'instant, on propage ça dans le sprite
    }

    @Override
    public void setY(int y) {
        if (this.sprite == null) return;
        this.sprite.setY(y); // pour l'instant, on propage ça dans le sprite
    }
    
    public int getWidth() {
        if (this.sprite == null) return 0;
        return this.sprite.computeWidth();
    }
    
    public int getHeight() {
        if (this.sprite == null) return 0;
        return this.sprite.computeHeight();
    }

    @Override
    public void moveTo(int x, int y) {
        if (this.sprite == null) return;
        this.sprite.moveTo(x,y); // pour l'instant, on propage ça dans le sprite
    }

    @Override
    public void moveBy(int x, int y) {
        if (this.sprite == null) return;
        this.sprite.moveBy(x,y); // pour l'instant, on propage ça dans le sprite
    }
    
    @Override
    public void draw(Canvas c, Graphics g) {
        if (this.sprite == null) return;
        
        this.sprite.draw(c, g);
    }
    
}
