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
     * @param niveaunom le nom du niveau
     * @param nom le nom du lieu
     * @return retourne true si le lieu a bien été ajouté au niveau
     */
    public boolean ajouterLieu(String niveaunom, String nom) {
        Niveau n = donjon.getNiveau(niveaunom);
        if (n==null) return false;
        n.ajouterLieu(new Lieu(nom));
        return true;
    }
    
    public boolean ajouterLieu(Niveau niveau, String nom) {
        return this.ajouterLieu(niveau.getNom(),  nom);
    }

    /**
     * Permet de récupérer un lieu via son nom
     * @param niveaunom le nom du niveau
     * @param nom le nom du lieu
     * @return retourne le lieu portant ce nom sinon reoturne null
     */
    public Lieu getLieu(String niveaunom, String nom) {
        Niveau n = donjon.getNiveau(niveaunom);
        if (n==null) return null;
        return n.getLieu(nom);
    }
    
    public Lieu getLieu(Niveau niveau, String nom) {
        return this.getLieu(niveau.getNom(), nom);
    }

    /**
     * Permet d'ajouter une porte entre deux lieux
     * @param niveaunom le nom du niveau
     * @param nom_porte
     * @param nom_lieu1
     * @param nom_lieu2
     * @return retourne true si la porte a été ajouté, false sinon
     */
    public boolean ajouterPorte(String niveaunom, String nom_porte, String nom_lieu1, String nom_lieu2) {
        Niveau n = donjon.getNiveau(niveaunom);
        if (n==null) return false;
        return n.ajouterPorte(nom_porte, nom_lieu1, nom_lieu2);
    }
    
    public boolean ajouterPorte(Niveau niveau, String nom_porte, String nom_lieu1, String nom_lieu2) {
        return this.ajouterPorte(niveau.getNom(), nom_porte, nom_lieu1, nom_lieu2);
    }

    /**
     * Permet d'ajouter un monstre dans un lieu
     * @param niveaunom le nom du niveau
     * @param lieu le lieu dans le donjon
     * @param monstre le mon stre à ajouter
     * @return retourne true si le monstre a bien été ajouté, sinon false
     */
    public boolean ajouterMonstre(String niveaunom, String lieu, Personnage monstre) {
        Niveau n = donjon.getNiveau(niveaunom);
        if (n==null) return false;
        return n.ajouterMonstre(monstre, lieu);
    }
    
    public boolean ajouterMonstre(Niveau niveau, String lieu, Personnage monstre) {
        return this.ajouterMonstre(niveau.getNom(), lieu, monstre);
    }

    /**
     * Permet d'ajouter un objet dans un lieu
     * @param niveaunom le nom du niveau
     * @param lieu le lieu où on ajoute l'objet
     * @param obj l'objet à rajouter
     * @return retourne true si l'objet a bien été ahouté, sinon false
     */
    public boolean ajouterObjet(String niveaunom, String lieu, Objet obj) {
        Niveau n = donjon.getNiveau(niveaunom);
        if (n==null) return false;
        return n.ajouterObjet(obj,lieu);
    }
    
    public boolean ajouterObjet(Niveau niveau, String lieu, Objet obj) {
        return this.ajouterObjet(niveau.getNom(), lieu, obj);
    }

    public String onActionJoueur() {
        String logs = "";
        Lieu lieu = this.getJoueur().getPieceActuel();
        List<Personnage> monstres = lieu.getMonstres();
        int[] monstresMort = new int[monstres.size()];
        int i=0;
        for (Personnage monstre : monstres) {
            if(monstre.getPv()!=0){
                logs += monstre.attaque(this.getJoueur());
            }else{
                monstresMort[i] = monstres.indexOf(monstre);
                logs += monstre.getNom()+" est mort\n";
                i++;
            }
        }
        
        for (int j=0;j<i;j++) {
            if(!(monstres.remove(j).getNom().equals("Cadavre"))){
                ArrayList<Objet> listeloot = new ArrayList<Objet>();
                listeloot.add(new Nourriture("chaire putidre", "il est déconseiller de la manger", 5, 3));
                monstres.add(new Personnage("Cadavre","une dépouille inutile",0,0,0,1,listeloot));
            }
        }
        return logs;
    }

    /**
     * Permet d'ajouter un niveau dans le donjon
     * @param niveau le niveau à ajouter
     * @return retourne true si le niveau a bien été ajouté, sinon false
     */
    public boolean ajouterNiveau(Niveau niveau) {
        return this.donjon.ajouterNiveau(niveau);
    }
}
