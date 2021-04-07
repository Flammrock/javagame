
package modele.element;

public abstract class Objet extends Element {
    
    private String nom;
    private double poids;
    
    public String effet(Personnage utilisateur, Element cible) {
        return "";
    }

    
}
