
package modele.element;

import map.Generable;

public abstract class Objet extends Element implements Generable {
    
    String nom;
    double probaDeGeneration;
    double poids;
    int nbUtilisation; // -1 for infinite
    
    
    public Objet() {
        this.nom = "";
        this.probaDeGeneration = 1.0;
        this.poids = 0.0;
        this.nbUtilisation = 1;
    }
    
    public Objet(String nom, String description, double poids) {
        this.nom = nom;
        this.description = description;
        this.poids = poids;
        this.probaDeGeneration = 1.0;
    }
    
    public String effet(Personnage utilisateur, Element cible) {
        return "";
    }

    public String toString() {
        if (this.description.trim().equals("")) {
            return this.nom;
        }
        return this.nom + " (" + this.description + ")";
    }
    
    public boolean peutUtiliser() {
        return false;
    }

    public void mettreAJourUtiliser(Personnage p) {
        if (this.nbUtilisation == -1 || this.nbUtilisation > 0) {
            if (this.nbUtilisation != -1) {
                this.nbUtilisation--;
            }
        }
    }

    public double getPoids() {
        return poids;
    }
    
    public boolean onUtiliser(Personnage p) {
        return false;
    }

    public int nbUtilisationRestante() {
        return this.nbUtilisation;
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
    public boolean generate(Object p) {
        return false; // pas générable pour l'instant
    }
    
    
    
}
