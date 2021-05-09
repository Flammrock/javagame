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
public class Nourriture extends Objet {
    

    public Nourriture(String nom, String description, double poids) {
        super(nom,description,poids);
    }
    

    
    public boolean peutUtiliser() {
        return true;
    }
    
    public boolean onUtiliser(Personnage p) {
        p.ajouter(this.effet);
        return true;
    }

    
    
    @Override
    public Drawable copie() {
        Nourriture c = new Nourriture(nom,description,poids);
        if (sprite!=null) c.setSprite(sprite);
        c.setIcon(this.icon);
        if (this.effet!=null) c.setEffet(this.effet.copie());
        c.setRadiusRamassable(this.radiusramassable);
        c.setZIndex(this.zindex);
        return c;
    }
    
}
