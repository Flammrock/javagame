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

    /**
     * Permet de récupérer un lieu via son nom
     * @param nom le nom du lieu
     * @return retourne le lieu s'il y a un lieu portant ce nom sinon reoturne null
     */
    public Lieu getLieu(String nom) {
        for (Lieu l : salles) {
            if (l.getNom().equals(nom)) return l;
        }
        return null;
    }
}
