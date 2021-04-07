
package modele.element;

import java.util.ArrayList;


public class Personnage extends Element {
    
    private String nom;
    private int age;
    private double force;
    private double agilite;
    private double pv;
    private double pvmax;
    private ArrayList<Objet> inventaire;
    private Equipement main;
    private Equipement armure;
    private Lieu pieceActuel;

    public Personnage(String nom, String description, int age, double force, double agilite, double pv, ArrayList<Objet> inventaire) {
        this.nom = nom;
        this.description = description;
        this.age = age;
        this.force = force;
        this.agilite = agilite;
        this.pv = pv;
        this.pvmax = pv;
        this.inventaire = inventaire;
        this.main = null;
        this.armure = null;
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
        if (pv > this.pvmax) {
            this.pv = this.pvmax;
            return;
        }
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getForce() {
        return force;
    }

    public void setForce(double force) {
        this.force = force;
    }

    public double getAgilite() {
        return this.agilite;
    }

    public void setAgilite(double agilite) {
        this.agilite = agilite;
    }



    public Lieu getPieceActuel() {
        return pieceActuel;
    }

    public void setPieceActuel(Lieu pieceActuel) {
        this.pieceActuel = pieceActuel;
    }


    public void ajoutePointVie(double valeur) {
        if (this.pv + valeur > this.pvmax) {
            this.pv = this.pvmax;
            return;
        }
        this.pv += valeur;
    }
    
    public boolean equip(int index) {
        if (index < 0 || index > this.inventaire.size()) return false;
        Objet obj = this.inventaire.get(index);
        if (obj == null) return false;
        if (obj instanceof Arme) {
            this.setMain((Arme)obj);
            return true;
        } else if (obj instanceof Armure) {
            this.setArmure((Armure)obj);
            return true;
        }
        return false;
    }
    
    public boolean desequip(int index) {
        if (index < 0 || index > this.inventaire.size()) return false;
        Objet obj = this.inventaire.get(index);
        if (obj == null) return false;
        if (obj instanceof Arme) {
            this.setMain(null);
            return true;
        } else if (obj instanceof Armure) {
            this.setArmure(null);
            return true;
        }
        return false;
    }

    /**
     *
     * @return retourne la valeur de combat
     */
    public double valeurCombat(){
        if (this.getArmure()==null) return this.agilite;
        double bonusArme = this.getArmure().getModificateurAgilite();
        double valeurCombat = this.agilite+bonusArme;
        return valeurCombat;
    }
    
    private boolean aToucher(double Probabilité){
        double NombreMin = 0.0;
        double NombreMax = 1.0;
        double nombreAleatoire = NombreMin + (Math.random() * (NombreMax - NombreMin));
        return nombreAleatoire<=Probabilité;
    }
    
    /**
     * permet a un personnage d'attaquer un autre personnage
     * @param ennemie le personnage que l'on affronte
     */
    public void attaque(Personnage ennemie){
        double probabiliteDeToucher = this.valeurCombat()/(this.valeurCombat()+ennemie.valeurCombat());
        boolean toucher = aToucher(probabiliteDeToucher);
        if(toucher){
            this.blesse(ennemie);   
        }
    }

    private void blesse(Personnage ennemie) {
        double pointdevie = ennemie.getPv();
        double degats = caluleDegatsBrut() - ennemie.getArmureTotal();
        pointdevie -= degats;
        if(pointdevie>0){
            ennemie.setPv(pointdevie);
        }else{
            mort(ennemie);
        }
    }

    private double caluleDegatsBrut() {
        if (this.getMain()==null) return this.force;
        double bonusArme = this.getMain().getModificateurForce();
        double bonusForce = this.force;
        double degats = bonusArme + bonusForce;
        return degats;
    }

    /**
     *
     * @return l'armure totale
     */
    public double getArmureTotal() {
        if (this.getArmure()==null) return 0.0;
        double armureTotal = this.getArmure().getModificateurProtection();
        return armureTotal;
    }

    private void mort(Personnage ennemie) {
        ennemie.setPv(0.0);
        this.inventaire.addAll(ennemie.getInventaire());
    }
    
    
    public boolean utiliserObjet(int index) {
        if (index < 0 || index > this.inventaire.size()) return false;
        Objet obj = this.inventaire.get(index);
        if (obj == null) return false;
        if (!obj.peutUtiliser()) return false;
        boolean reussiAutiliser = obj.onUtiliser(this);
        if (!reussiAutiliser) return false;
        obj.mettreAJourUtiliser(this);
        if (obj.nbUtilisationRestante() <= 0 && obj.nbUtilisationRestante() != -1) this.inventaire.remove(index);
        return reussiAutiliser;
    }
    
    public boolean ajouterObjet(Objet o){
        return this.inventaire.add(o);
    } 
    
    public String toString() {
        if (this.description.trim().equals("")) {
            return this.nom;
        }
        return this.nom + " (" + this.description + ") " + this.pv + "PV";
    }
}
