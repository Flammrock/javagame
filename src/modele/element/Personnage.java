
package modele.element;

import java.util.ArrayList;


public class Personnage extends Element  {
    
    private String nom;
    private int age;
    private double force;
    private double agilite;
    private double pv;
    private ArrayList<Objet> inventaire;
    private Equipement main;
    private Equipement armure;
    private Lieu pieceActuel;

    public Personnage(String nom, int age, double force, double agilite, double pv, ArrayList<Objet> inventaire) {
        this.nom = nom;
        this.age = age;
        this.force = force;
        this.agilite = agilite;
        this.pv = pv;
        this.inventaire = inventaire;
    }
    
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public double getPv() {
        return pv;
    }

    public void setPv(double pv) {
        this.pv = pv;
    }

    public ArrayList<Objet> getInventaire() {
        return inventaire;
    }

    public void setInventaire(ArrayList<Objet> inventaire) {
        this.inventaire = inventaire;
    }

    public Equipement getMain() {
        return main;
    }

    public void setMain(Equipement main) {
        this.main = main;
    }

    public Equipement getArmure() {
        return armure;
    }

    public void setArmure(Equipement armure) {
        this.armure = armure;
    }



    public Lieu getPieceActuel() {
        return pieceActuel;
    }

    public void setPieceActuel(Lieu pieceActuel) {
        this.pieceActuel = pieceActuel;
    }


    public void ajoutePointVie(double valeur) {
        this.pv += valeur;
    }

    public double valeurCombat(){
        double bonusArme = this.getArmure().getModificateurAgilite();
        double valeurCombat = this.agilite+bonusArme;
        return this.agilite;
    }
    
    public boolean aToucher(double Probabilité){
        double NombreMin = 0.0;
        double NombreMax = 100;
        double nombreAleatoire = NombreMin + (Math.random() * (NombreMax - NombreMin));
        return nombreAleatoire<=Probabilité;
    }
    
    public void attaque(Personnage ennemie){
        double probabiliteDeToucher = this.valeurCombat()/(this.valeurCombat()+ennemie.valeurCombat());
        boolean toucher = aToucher(probabiliteDeToucher);
        if(toucher){
            this.blesse(ennemie);   
        }
    }

    private void blesse(Personnage ennemie) {
        double pv = ennemie.getPv();
        double degats = caluleDegatsBrut() - ennemie.getArmureTotal();
        pv -= degats;
        if(pv>0){
            ennemie.setPv(pv);
        }else{
            ennemie.mort();
        }
    }

    private double caluleDegatsBrut() {
        double bonusArme = this.main.getModificateurForce();
        double bonusForce = this.force;
        double degats = bonusArme + bonusForce;
        return degats;
    }

    private double getArmureTotal() {
        double armureTotal = this.getArmure().getModificateurProtection();
        return armureTotal;
    }

    private void mort() {
        this.setPv(0.0);
    }
}
