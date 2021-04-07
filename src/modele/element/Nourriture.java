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
public class Nourriture extends Object {
    
    private double valeur;
    
    public String effet(Personnage utilisateur, Element cible) {
        utilisateur.ajoutePointVie(this.valeur);
        valeur = 0; //la nourriture ne peut etre utilisée deux fois
        return utilisateur.getNom() + " a mangé ";
    }

    
}
