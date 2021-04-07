/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele.element;

/**
 *
 * @author User
 */
public class Equipement extends Objet {
    
    double bonus_agilite;
    double bonus_protection;
    double bonus_force;
    
    public Equipement(String nom, String description, double poids) {
        super(nom,description,poids);
        this.bonus_force = 0.0;
        this.bonus_protection = 0.0;
        this.bonus_agilite = 0.0;
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
}
