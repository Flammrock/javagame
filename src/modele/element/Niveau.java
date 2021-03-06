/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele.element;

import canvas.Drawable;
import canvas.TileSet;
import embellishment.Embellishment;
import eventsystem.Dispatcher;
import eventsystem.SimpleEvent;
import eventsystem.SimpleListener;
import geometry.Box;
import geometry.Direction;
import java.util.ArrayList;
import java.util.List;
import map.Generable;
import map.GenerateListener;
import map.Squeleton;

/**
 * Un niveau contient un début et une fin
 * @author User
 */
public class Niveau extends Element implements Generable {
    
    String nom;
    List<Lieu> salles;
    Lieu entree;
    Lieu sortie;
    
    ArrayList<Drawable> drawables;
    
    // un dispatcher d'events
    Dispatcher dispatcher;
    
    ArrayList<Embellishment> embellishmentsList;
    
    TileSet tileset;
    
    public Niveau(String nom,String description) {
        this.nom = nom;
        this.description = description;
        this.salles = new ArrayList<>();
        this.drawables = new ArrayList<>();
        this.dispatcher = new Dispatcher();
        this.tileset = null;
        this.embellishmentsList = new ArrayList<>();
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
    
    
    public Dispatcher getDispatcher() {
        return this.dispatcher;
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
        ArrayList<ArrayList<Box>> ligaments_bounds = s.getLigamentBounds();
        ArrayList<ArrayList<Direction>> ligaments_directions = s.getLigamentsDirections();
        
        this.salles.clear();
        
        Niveau _this = this;
        
        // on construit les salles à partir du squelette de la map
        int i = 0;
        for (Box bone : bones) {
            Lieu lieu = new Lieu("Lieu "+i);
            lieu.setSize(bone);
            lieu.setEmbellishments(embellishmentsList);
            lieu.setTileSet(this.tileset);
            this.salles.add(lieu);
            this.drawables.add(lieu);
            if (i == 0) {
                this.entree = lieu;
                lieu.setEntree(true);
            }
            if (i == bones.size()-1) {
                this.sortie = lieu;
                lieu.setSortie(true);
            }
            
            // on propage l'events de collisions dans le dispatcher de ce niveau
            lieu.onCollide(new SimpleListener("onCollide"){
                @Override
                public void onEvent(Object sender, SimpleEvent event) {
                    _this.getDispatcher().fireEvent("onCollide", _this, event);
                }
            });
            
            i++;
        }
        
        int k = 0;
        for (ArrayList<Integer> connections : ligaments_connections) {
            ArrayList<Box> bounds = ligaments_bounds.get(k);
            ArrayList<Direction> directions = ligaments_directions.get(k);
            int h = 0;
            int v = 0;
            for (Integer j : connections) {
                if (this.salles.get(k).ajoutePorteVers("Porte", this.salles.get(j-1))) {
                    this.salles.get(k).getLastPorte().setDirection(directions.get(v));
                    Direction m = directions.get(v).copy();
                    m.rotate();
                    m.rotate();
                    this.salles.get(j-1).getLastPorte().setDirection(m);
                    this.salles.get(k).getLastPorte().setSize(bounds.get(h));
                    this.salles.get(j-1).getLastPorte().setSize(bounds.get(h+1));
                }
                h+=2;
                v++;
            }
            k++;
        }
        
        // on calcul les nouvelles collisions box
        for (Lieu l : this.salles) {
            l.generate(this);
            this.dispatcher.fireEvent("onGenerate", this, new SimpleEvent(l));
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
    public ArrayList<Drawable> getDrawables() {
        return this.drawables;
    }
    
    
    public void onCollide(SimpleListener l) {
        l.setType("onCollide"); // on force le type
        this.dispatcher.addListener(l);
    }

    public void setTileSet(TileSet tileset) {
        this.tileset = tileset;
        for (Lieu l : this.salles) {
            l.setTileSet(tileset);
        }
    }

    public void addEmbellishment(Embellishment e) {
        this.embellishmentsList.add(e);
    }
    
    public void setEmbellishments(ArrayList<Embellishment> l) {
        this.embellishmentsList = l;
    }
    
    public ArrayList<Embellishment> getEmbellishments() {
        return this.embellishmentsList;
    }

    @Override
    public void onGenerate(GenerateListener l) {
        this.dispatcher.addListener(l);
    }
    
    
    @Override
    public Drawable copie() {
        return null;
    }
    
    @Override
    public int getZIndex() {
        return Integer.MIN_VALUE; // aucun impact
    }

}
