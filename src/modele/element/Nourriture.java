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
    
    private double NbrObjet;
    private Effet effetConsommable;
    
    public Nourriture(String nom, String description, double poids, double NbrObjet,Effet effetConsommable) {
        super(nom,description,poids);
        this.NbrObjet = NbrObjet;
        this.effetConsommable = effetConsommable;
    }
    
    public String effet(Personnage utilisateur, Element cible) {
        if(NbrObjet>0){
            utilisateur.ajoutEffet(this.effetConsommable);
            this.NbrObjet --; //la nourriture ne peut etre utilisée deux fois
        }
        return utilisateur.getNom() + " a mangé ";
    }
    
    public boolean peutUtiliser() {
        return true;
    }
    
    public boolean onUtiliser(Personnage p) {
        p.ajoutePointVie(this.effetConsommable.getPvAjoute());
        return true;
    }
}
