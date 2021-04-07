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
    
    public Aventure() {
        
        // on créé le perso ici
        this.joueur = new Personnage("Hero", 20, 15, 15, 100, new ArrayList<Objet>());
        
        // on créé le donjon ici
        this.donjon = new Donjon();
        
    }
}
