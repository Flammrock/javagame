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
public class Arme extends Equipement {
    
    private double bonus_agilite;
    private double bonus_force;
    
    public Arme(String nom, String description, double poids, double agilite, double force) {
        super(nom,description,poids);
        this.bonus_agilite = agilite;
        this.bonus_force = force;
    }

    public double getBonus_agilite() {
        return bonus_agilite;
    }

    public void setBonus_agilite(double bonus_agilite) {
        this.bonus_agilite = bonus_agilite;
    }

    public double getBonus_force() {
        return bonus_force;
    }

    public void setBonus_force(double bonus_force) {
        this.bonus_force = bonus_force;
    }
    
    
    
    @Override
    public double getModificateurAgilite() {
        return this.bonus_agilite;
    }
    @Override
    public double getModificateurForce() {
        return this.bonus_force;
    }
    
    
}
