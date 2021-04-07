/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele.element;

import java.util.ArrayList;
import java.util.List;

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

    /**
     * Permet d'ajouter un monstre dans un lieu
     * @param monstre le mon stre à ajouter
     * @param lieu le lieu dans le donjon
     * @return retourne true si le monstre a bien été ajouté, sinon false
     */
    public boolean ajouterMonstre(Personnage monstre, String lieu) {
        return donjon.ajouterMonstre(monstre, lieu);
    }

    /**
     * Permet d'ajouter un objet dans un lieu
     * @param obj l'objet à rajouter
     * @param lieu le lieu où on ajoute l'objet
     * @return retourne true si l'objet a bien été ahouté, sinon false
     */
    public boolean ajouterObjet(Objet obj, String lieu) {
        return donjon.ajouterObjet(obj,lieu);
    }

    public void onActionJoueur() {
        
        Lieu lieu = this.getJoueur().getPieceActuel();
        List<Personnage> monstres = lieu.getMonstres();
        int[] monstresMort = new int[monstres.size()];
        int i=0;
        for (Personnage monstre : monstres) {
            if(monstre.getPv()!=0){
                monstre.attaque(this.getJoueur());
            }else{
                monstresMort[i] = monstres.indexOf(monstre);
                i++;
            }
        }
        
        for (i=0;i<monstresMort.length;i++) {
            if(!(monstres.remove(i).getNom().equals("Cadavre"))){
                ArrayList<Objet> listeloot = new ArrayList<Objet>();
                listeloot.add(new Nourriture("chaire putidre", "il est déconseiller de la manger", 5, 3));
                monstres.add(new Personnage("Cadavre","une dépouille inutile",0,0,0,1,listeloot));
            }
        }
    }
}
