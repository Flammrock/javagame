/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele.element;

import java.util.List;

/**
 *
 * @author Utilisateur
 */
public class Donjon extends Element{
    private List<Lieu> salles;
    private Lieu entree;
    private Lieu sortie;

    void ajouterLieu(Lieu lieu) {
        salles.add(lieu);
    }
}
