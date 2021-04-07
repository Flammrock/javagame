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
public class Armure extends Equipement {
    
    
    
    public Armure(String nom, String description, double poids, double agilite, double protection) {
        super(nom, description, poids);
        this.bonus_agilite = agilite;
        this.bonus_protection = protection;
    }
}
