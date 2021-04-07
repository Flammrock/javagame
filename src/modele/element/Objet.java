
package modele.element;

public abstract class Objet extends Element {
    
    private String nom;
    private double poids;
    
    public Objet() {
        this.nom = "";
        this.poids = 0.0;
    }
    
    public Objet(String nom, String description, double poids) {
        this.nom = nom;
        this.description = description;
        this.poids = poids;
    }
    
    public String effet(Personnage utilisateur, Element cible) {
        return "";
    }

    public String toString() {
        return this.nom + "(" + this.description + ")";
    }
    
}
