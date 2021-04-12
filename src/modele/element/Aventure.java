/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele.element;

import canvas.Canvas;
import canvas.Drawable;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Utilisateur
 */
public class Aventure extends Element {
    
    private Personnage joueur;
    private Donjon donjon;
    
    private ArrayList<Drawable> drawables;
    
    public Aventure(Personnage joueur) {
        
        this.description = "mon aventure";
        
        this.drawables = new ArrayList<>();
        
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
     * Permet d'ajouter un objet dans un lieu
     * @param niveaunom le nom du niveau
     * @param lieu le lieu où on ajoute l'objet
     * @param e l'element à rajouter
     * @return retourne true si l'objet a bien été ahouté, sinon false
     */
    public boolean ajouter(String niveaunom, String lieu, Element e) {
        Niveau n = donjon.getNiveau(niveaunom);
        if (n==null) return false;
        return n.ajouter(e,lieu);
    }
    
    public boolean ajouter(Niveau niveau, String lieu, Element e) {
        return this.ajouter(niveau.getNom(), lieu, e);
    }

    

    /**
     * Permet d'ajouter un niveau dans le donjon
     * @param niveau le niveau à ajouter
     * @return retourne true si le niveau a bien été ajouté, sinon false
     */
    public boolean ajouterNiveau(Niveau niveau) {
        return this.donjon.ajouterNiveau(niveau);
    }

    @Override
    public void draw(Canvas c, Graphics g) {
        
        
    }

    @Override
    public int getX() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setX(int x) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getY() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setY(int y) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void MoveTo(int x, int y) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void MoveBy(int x, int y) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void applyMove() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Drawable> getDrawables() {
        return this.drawables;
    }

    public void ajouterDrawable(Drawable d) {
        this.drawables.add(d);
    }
}
