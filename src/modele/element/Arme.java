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
    public void draw(Canvas c, Graphics g) {
        if (this.sprite == null) return;
        
        this.sprite.draw(c, g);
    }
    
}
