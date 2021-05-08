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
    
    double bonus_agilite;
    double bonus_protection;
    double bonus_force;
    double vie_Objet;
    
    public Equipement(String nom, String description, double poids) {
        super(nom,description,poids);
        this.bonus_force = 0.0;
        this.bonus_protection = 0.0;
        this.bonus_agilite = 0.0;
        this.vie_Objet = 1;
    }

    public void setVie_Objet(double vie_Objet) {
        this.vie_Objet = vie_Objet;
    }
    
    public void setModificateurAgilite(double agilite) {
        this.bonus_agilite = agilite;
    }
    public void setModificateurForce(double force) {
        this.bonus_force = force;
    }
    public void setModificateurProtection(double protection) {
        this.bonus_protection = protection;
    }
   
    public double getModificateurAgilite() {
        return this.bonus_agilite;
    }
    public double getModificateurForce() {
        return this.bonus_force;
    }
    public double getModificateurProtection() {
        return this.bonus_protection;
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
    
    @Override
    public int getZIndex() {
        return Integer.MIN_VALUE; // pour l'instant
    }

}
