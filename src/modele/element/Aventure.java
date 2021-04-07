/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele.element;

import java.util.ArrayList;

/**
 *
 * @author Utilisateur
 */
public class Aventure extends Element {
    
    private Personnage joueur;
    private Donjon donjon;
    
    public Aventure(Personnage joueur) {
        
        // on créé le perso ici
        this.joueur = joueur;
        
        
        // on créé le donjon ici
        this.donjon = new Donjon();
        
    }

    public Personnage getJoueur() {
        return joueur;
    }

    /**
     * Permet d'ajouter un lieu
     * @param nom le nom du lieu
     */
    public void ajouterLieu(String nom) {
        donjon.ajouterLieu(new Lieu(nom));
    }

    /**
     * Permet de récupérer un lieu via son nom
     * @param nom le nom du lieu
     * @return retourne le lieu portant ce nom sinon reoturne null
     */
    public Lieu getLieu(String nom) {
        return donjon.getLieu(nom);
    }

    /**
     * Permet d'ajouter une porte entre deux lieux
     * @param nom_porte
     * @param nom_lieu1
     * @param nom_lieu2
     * @return retourne true si la porte a été ajouté, false sinon
     */
    public boolean ajouterPorte(String nom_porte, String nom_lieu1, String nom_lieu2) {
        return donjon.ajouterPorte(nom_porte, nom_lieu1, nom_lieu2);
    }
    
    
    
}
