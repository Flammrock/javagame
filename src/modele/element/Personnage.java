
package modele.element;

import java.util.ArrayList;


public class Personnage extends Element  {
private String nom;
private int age;
private int force;
private int agilite;
private int pv;
private ArrayList<Objet> inventaire;
private Objet main;
private Objet armure;
private Lieu pieceActuel;

    public int getPv() {
        return pv;
    }

    public void setPv(int pv) {
        this.pv = pv;
    }

    public ArrayList<Objet> getInventaire() {
        return inventaire;
    }

    public void setInventaire(ArrayList<Objet> inventaire) {
        this.inventaire = inventaire;
    }

    public Objet getMain() {
        return main;
    }

    public void setMain(Objet main) {
        this.main = main;
    }

    public Objet getArmure() {
        return armure;
    }

    public void setArmure(Objet armure) {
        this.armure = armure;
    }

    public Lieu getPieceActuel() {
        return pieceActuel;
    }

    public void setPieceActuel(Lieu pieceActuel) {
        this.pieceActuel = pieceActuel;
    }






}
