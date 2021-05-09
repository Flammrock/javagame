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
public class Equipement extends Objet {
    
    public Equipement(String nom, String description, double poids) {
        super(nom,description,poids);
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
    
    
    
    @Override
    public Drawable copie() {
        return null;
    }
    
    
    @Override
    public boolean isReparable() {
        return true;
    }

}
