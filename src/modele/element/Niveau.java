/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele.element;

import java.util.ArrayList;
import java.util.List;

/**
 * Un niveau contient un début et une fin
 * @author User
 */
public class Niveau extends Element{
    
    String nom;
    List<Lieu> salles;
    Lieu entree;
    Lieu sortie;
    
    public Niveau(String nom) {
        this.nom = nom;
        this.salles = new ArrayList<>();
    }

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

    /**
     * Permet d'ajouter un monstre dans un lieu
     * @param monstre le mon stre à ajouter
     * @param lieu le lieu dans le donjon
     * @return retourne true si le monstre a bien été ajouté, sinon false
     */
    public boolean ajouterMonstre(Personnage monstre, String lieu) {
        Lieu lieuobj = this.getLieu(lieu);
        if (lieuobj == null) return false;
        return lieuobj.ajouterMonstre(monstre);
    }

    /**
     * Permet d'ajouter un objet dans un lieu
     * @param obj l'objet à rajouter
     * @param lieu le lieu où on ajoute l'objet
     * @return retourne true si l'objet a bien été ahouté, sinon false
     */
    public boolean ajouterObjet(Objet obj, String lieu) {
        Lieu lieuobj = this.getLieu(lieu);
        if (lieuobj == null) return false;
        return lieuobj.ajouterObjet(obj);
    }
    
    
    
    
    
    
}
