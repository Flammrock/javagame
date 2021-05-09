/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele.element;


import canvas.Drawable;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Utilisateur
 */
public class Donjon extends Element{
    
    HashMap<String, Niveau> list_niveaux;
    
    public Donjon() {
        this.list_niveaux = new HashMap<>();
    }

    /**
     * Permet de récupérer un niveau via son nom
     * @param nom le nom du niveau
     * @return retourne le niveau s'il existe sinon retourne null
     */
    public Niveau getNiveau(String nom) {
        return this.list_niveaux.get(nom);
    }
    
    /**
     * Permet d'enlever un niveau ia son nom
     * @param nom le nom du niveau
     * @return retourne le niveau qui a été enlevé, si rien n'a été enlevé alors retourne null
     */
    public Niveau enleverNiveau(String nom) {
        return this.list_niveaux.remove(nom);
    }
    
    /**
     * Permet d'ajouter un niveau (attention si un niveau existe déjà sous le même nom alors il sera remplacé)
     * @param n le niveau à ajouter
     * @return retourne true si le niveau a été ajouté
     */
    public boolean ajouterNiveau(Niveau n) {
        if (n==null) return false;
        return this.list_niveaux.put(n.getNom(), n)!=null;
    }
    
    /**
     * Permet de générer un niveau dans le donjon
     * 
     * @param nbLieu
     * @return
     */
    public boolean genererNiveau(int nbLieu) {
        return false;
    }
    
    
    @Override
    public Drawable copie() {
        Donjon c = new Donjon();
        
        for (Map.Entry<String, Niveau> entry : this.list_niveaux.entrySet()) {
            c.ajouterNiveau((Niveau)entry.getValue().copie());
        }
        
        return c;
    }

    @Override
    public int getZIndex() {
        return Integer.MIN_VALUE; // aucun impact
    }
    
}
