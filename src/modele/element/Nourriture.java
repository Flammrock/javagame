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
    
    private double valeur;
    
    public Nourriture(String nom, String description, double poids, double valeur) {
        super(nom,description,poids);
        this.valeur = valeur;
    }
    
    public String effet(Personnage utilisateur, Element cible) {
        utilisateur.ajoutePointVie(this.valeur);
        valeur = 0; //la nourriture ne peut etre utilisée deux fois
        return utilisateur.getNom() + " a mangé ";
    }
    
    public boolean peutUtiliser() {
        return true;
    }
    
    public boolean onUtiliser(Personnage p) {
        p.ajoutePointVie(this.valeur);
        return true;
    }
}
