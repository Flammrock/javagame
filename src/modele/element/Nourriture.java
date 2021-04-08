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
public class Nourriture extends Objet {
    
    private Effet effetConsommable;
    
    public Nourriture(String nom, String description, double poids ,Effet effetConsommable) {
        super(nom,description,poids);
        this.effetConsommable = effetConsommable;
    }
    

    
    public boolean peutUtiliser() {
        return true;
    }
    
    public boolean onUtiliser(Personnage p) {
        p.ajoutEffet(this.effetConsommable);
        return true;
    }
}
