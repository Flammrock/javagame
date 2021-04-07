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

    /**
     * Permet d'ajouter un lieu
     * @param lieu
     */
    public void ajouterLieu(Lieu lieu) {
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

    /**
     * Permet d'ajouter une porte entre deux lieux
     * @param nom_porte
     * @param nom_lieu1
     * @param nom_lieu2
     * @return retourne true si la porte a été ajouté, false sinon
     */
    boolean ajouterPorte(String nom_porte, String nom_lieu1, String nom_lieu2) {
        Lieu lieu1 = this.getLieu(nom_lieu1);
        if (lieu1 == null) return false;
        Lieu lieu2 = this.getLieu(nom_lieu2);
        if (lieu2 == null) return false;
        return lieu1.ajoutePorteVers(nom_porte, lieu2);
    }
}
