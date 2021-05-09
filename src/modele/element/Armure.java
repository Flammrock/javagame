/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele.element;

import canvas.Drawable;

/**
 *
 * @author User
 */
public class Armure extends Equipement {
    
    
    
    public Armure(String nom, String description, double poids) {
        super(nom, description, poids);
    }
    
    
    @Override
    public Drawable copie() {
        Armure c = new Armure(nom, description, poids);
        c.setSprite(sprite);
        c.setIcon(this.icon);
        if (this.effet!=null) c.setEffet(this.effet.copie());
        c.setRadiusRamassable(this.radiusramassable);
        c.setZIndex(this.zindex);
        return c;
    }
}
