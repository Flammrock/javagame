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
        this.level = level;
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
