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
    
    public Equipement(String nom, String description, double poids) {
        super(nom,description,poids);
    }
   
    public double getModificateurAgilite() {
        return 0.0;
    }
    public double getModificateurForce() {
        return 0.0;
    }
    public double getModificateurProtection() {
        return 0.0;
    }
    
    
}
