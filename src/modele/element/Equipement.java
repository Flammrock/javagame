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
    
    double vie_Objet;
    
    public Equipement(String nom, String description, double poids) {
        super(nom,description,poids);
        this.vie_Objet = 1;
    }

    public void setVie_Objet(double vie_Objet) {
        this.vie_Objet = vie_Objet;
    }
    
    

    public double getVie_Objet() {
        return vie_Objet;
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

}
