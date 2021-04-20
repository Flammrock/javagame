/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele.element;

import canvas.Canvas;
import canvas.Drawable;
import geometry.Box;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import map.Generable;
import map.Squeleton;

/**
 * Un niveau contient un début et une fin
 * @author User
 */
public class Niveau extends Element implements Generable {
    
    String nom;
    double probaDeGeneration;
    List<Lieu> salles;
    Lieu entree;
    Lieu sortie;
    
    ArrayList<Drawable> drawables;
    
    public Niveau(String nom,String description) {
        this.nom = nom;
        this.probaDeGeneration = 1.0;
        this.description = description;
        this.salles = new ArrayList<>();
        this.drawables = new ArrayList<>();
    }

    /**
     * Permet de récupérer le nom du niveau
     * @return retourne le nom du niveau
     */
    public String getNom() {
        return nom;
    }

    /**
     * Permet de récupérer l'entrée du niveau
     * @return retourne l'entrée du niveau
     */
    public Lieu getEntree() {
        return entree;
    }

    /**
     * Permet de récupérer la sortie du niveau
     * @return retourne la sortie du niveau
     */
    public Lieu getSortie() {
        return sortie;
    }
    
    
    public void setCurrentLieu(String nom) {
        this.drawables.clear();
        for (Lieu l : salles) {
            if (l.getNom().equals(nom)) {
                this.drawables.add(l);
                return;
            }
        }
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
     * Permet d'ajouter un element dans un lieu
     * @param e l'élément à ajouter
     * @param lieu le lieu dans le donjon
     * @return retourne true si l'élément a bien été ajouté, sinon false
     */
    public boolean ajouter(Element e, String lieu) {
        Lieu lieuobj = this.getLieu(lieu);
        if (lieuobj == null) return false;
        return lieuobj.ajouter(e);
    }

    @Override
    public boolean generate(Object o) {
        
        Squeleton s = (Squeleton) o;
        
        this.drawables.add(s);
        
        ArrayList<Box> bones = s.getBones();
        ArrayList<Box> ligaments = s.getLigaments();
        ArrayList<ArrayList<Integer>> ligaments_connections = s.getLigamentConnections();
        
        this.salles.clear();
        
        // on construit les salles à partir du squelette de la map
        int i = 0;
        for (Box bone : bones) {
            Lieu lieu = new Lieu("Lieu "+i);
            lieu.setSize(bone);
            lieu.generate(null); // @TODO
            this.salles.add(lieu);
            this.drawables.add(lieu);
            if (i == 0) {
                this.entree = lieu;
            }
            if (i == bones.size()-1) {
                this.sortie = lieu;
            }
            i++;
        }
        
        int k = 0;
        for (ArrayList<Integer> connections : ligaments_connections) {
            for (Integer j : connections) {
                this.salles.get(k).ajoutePorteVers("Porte", this.salles.get(j));
            }
            k++;
        }
        
        /*
        // on cast pour récupérer les paramètres
        GenerableNiveauParametre p = (GenerableNiveauParametre)s;
        
        // on recupère le nombre de salles à générer
        int nbsalles = p.getNombreSalles();
        nbsalles = nbsalles < 2 ? 2 : nbsalles;
        
        // on génère {nbsalles}
        ArrayList<Lieu> lieux = new ArrayList<>();
        for (int i = 0; i < nbsalles; i++) {
            Lieu lieu = new Lieu("Lieu "+i);
            lieu.generate(p.getGenerableLieuParametre());
            lieux.add(lieu);
        }
        
        // on enlève déjà toutes les salles du niveau (on génère à partir de zéro)
        this.salles.clear();
        
        // contiendra la dernière salle ajouté
        Lieu lastAjouter = null;
        
        // tant qu'il reste des salles à rajouter
        while (!lieux.isEmpty()) {
        
            // on choisit une salle aléatoirement (et on l'enlève de la liste)
            int index = (int)(Math.random() * lieux.size());
            Lieu lieu = lieux.remove(index);
            
            // on l'ajoute dans la liste des salles
            this.salles.add(lieu);
            
            // s'il s'agit de la 1ere salle
            if (this.salles.size()==1) {
                this.entree = lieu;
            }
            
            // s'il s'agit de la dernière salle
            else if (lieux.isEmpty()) {
                this.sortie = lieu;
            }
            
            
            // on essaie de connecter ce lieu avec le précédent lieu
            if (lastAjouter != null) {
                
                // on connecte les lieux
                lastAjouter.ajoutePorteVers("Porte", lieu);
                
            }
            
            
            
            // on définit cette salle comme la salle qu'on vient d'ajouter (utilie ensuite pour connecter les salles)
            lastAjouter = lieu;
            
        }
        
        this.setCurrentLieu(this.entree.getNom());*/
        
        return true;
    }
    
    
    
    @Override
    public double getProbabilite() {
        return this.probaDeGeneration;
    }

    @Override
    public void setProbabilite(double proba) {
        this.probaDeGeneration = proba;
    }
    
    @Override
    public ArrayList<Drawable> getDrawables() {
        return this.drawables;
    }

}
