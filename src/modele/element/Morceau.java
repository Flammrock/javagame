/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele.element;

import canvas.Drawable;

/**
 *
 * @author Utilisateur
 */
public class Morceau extends Objet {
    private int level;

    public Morceau(int level) {
        switch(level){
            case(Rarity.COMMON):
                this.nom = "Morceau commun";
                this.description = "Utile pour réparer et construre";
            break;
            case(Rarity.RARE):
                this.nom = "Morceau rare";
                this.description = "Utile pour la construction avancé";
            break;
            case(Rarity.EPIC):
                this.nom = "Morceau epic";
                this.description = "Utile pour la construction avancé";
            break;
            case(Rarity.LEGENDARY):
                this.nom = "Morceau legendaire";
                this.description = "Utile pour la construction avancé";
            break;
        }
        this.level = level;
        this.poids = 1;
    }

    public int getLevel() {
        return this.level;
    }

    @Override
    public Drawable copie() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getZIndex() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
