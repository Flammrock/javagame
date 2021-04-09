
package modele.element;

import java.util.ArrayList;


public class Personnage extends Element implements Generable {
    
    private String nom;
    private double probaDeGeneration;
    private int age;
    private double force;
    private double agilite;
    private double pv;
    private double pvMax;
    private ArrayList<Objet> inventaire;
    private Equipement main;
    private Equipement armure;
    private Lieu pieceActuel;
    private double poidsMax;
    private ArrayList<Effet> effetCourant; 

    public Personnage(String nom, String description, int age, double force, double agilite, double pv) {
        this.nom = nom;
        this.probaDeGeneration = 1.0;
        this.description = description;
        this.age = age;
        this.force = force;
        this.agilite = agilite;
        this.pv = pv;
        this.pvMax = pv;
        this.inventaire = new ArrayList<>();
        this.main = null;
        this.armure = null;
        this.poidsMax = force;
        this.effetCourant = new ArrayList<>();
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
        if (pv > this.pvMax) {
            this.pv = this.pvMax;
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

    public ArrayList<Effet> getEffetCourant() {
        return effetCourant;
    }

    public Lieu getPieceActuel() {
        return pieceActuel;
    }

    public void setPieceActuel(Lieu pieceActuel) {
        this.pieceActuel = pieceActuel;
    }

    public double getPoidsMax() {
        return poidsMax;
    }


    public void ajoutePointVie(double valeur) {
        if (this.pv + valeur > this.pvMax) {
            this.pv = this.pvMax;
            return;
        }
        this.pv += valeur;
    }
    private double getEffetForce(){
        double force = 0;
        for(Effet effet : this.effetCourant){
            force += effet.getForceAjoute();
        }
        return force;
    }
    
    private Lieu getEffetTeleportation(){//TODO implementer la methode
        return null;
    }
    
    private double getEffetAgilite(){
        double agilite = 0;
        for(Effet effet : this.effetCourant){
            agilite += effet.getAgiliteAjoute();
        }
        return agilite;
    }
    
    private double getEffetPv(){
        double pv = 0;
        for(Effet effet : this.effetCourant){
            pv += effet.getPvAjoute();
        }
        return pv;
    }
    
    private double getEffetPvMax(){
        double pvMax = 0;
        for(Effet effet : this.effetCourant){
            pvMax += effet.getPvMaxAjoute();
        }
        return pvMax;
    }
    
    private double getEffetPoids(){
        double poids = 0;
        for(Effet effet : this.effetCourant){
            poids += effet.getPoidsAjoute();
        }
        return poids;
    }
    
    private double getEffetArmure(){
        double Armure = 0;
        for(Effet effet : this.effetCourant){
            Armure += effet.getArmureAjoute();
        }
        return Armure;
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
        double bonusArmure;
        if (this.getArmure()==null){
            bonusArmure = 0;
        }else{
            bonusArmure = this.getArmure().getModificateurAgilite();
        }
        double bonusEffet = this.getEffetAgilite();
        double valeurCombat = this.agilite+bonusArmure+bonusEffet;
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
     * @return retourn la chaine de charactere explicitant l'attaque
     */
    public String attaque(Personnage ennemie){
        double probabiliteDeToucher = this.valeurCombat()/(this.valeurCombat()+ennemie.valeurCombat());
        boolean toucher = aToucher(probabiliteDeToucher);
        double attaque = 0;
        if(toucher){
            attaque = this.blesse(ennemie);   
        }
        //this.actionEffetFinDuTour();
        if(toucher){
            return this.getNom()+" Attaque "+ennemie.getNom()+":\nL'attaque reussi.\nIl inflige "+attaque+" degats.\n";
        }else{
            return this.getNom()+" Attaque "+ennemie.getNom()+":\nL'attaque échoue.\n";
        }
    }

    private double blesse(Personnage ennemie) {
        double pointdevie = ennemie.getPv();
        double degats = caluleDegatsBrut() - ennemie.getArmureTotal();
        pointdevie -= degats;
        if(pointdevie>0){
            ennemie.setPv(pointdevie);
        }else{
            mort(ennemie);
        }
        return degats;
    }

    private double caluleDegatsBrut() {
        double bonusArme;
        if (this.getMain()==null){
            bonusArme = 0;
        }else{
            bonusArme = this.getMain().getModificateurForce();
        }
        double bonusForce = this.force;
        double bonusEffet = this.getEffetForce();
        double degats = bonusArme + bonusForce + bonusEffet;
        return degats;
    }

    /**
     *
     * @return l'armure totale
     */
    public double getArmureTotal() {
        double bonusArmure;
        if (this.getArmure()==null){
            bonusArmure = 0.0;
        }else{
            bonusArmure = this.getArmure().getModificateurProtection();
        }
        double bonusEffet = this.getEffetArmure();
        double armureTotal = bonusArmure + bonusEffet;
        return armureTotal;
    }

    private void mort(Personnage ennemie) {
        ennemie.setPv(0.0);
        ArrayList<Element> e = new ArrayList<>();
        e.addAll(ennemie.getInventaire());
        this.pieceActuel.ajouterPlusieurs(e);
    }
    
    
    public boolean utiliserObjet(int index) {
        if (index < 0 || index > this.inventaire.size()) return false;
        Objet obj = this.inventaire.get(index);
        if (obj == null) return false;
        if (!obj.peutUtiliser()) return false;
        boolean reussiAutiliser = obj.onUtiliser(this);
        if (!reussiAutiliser) return false;
        obj.mettreAJourUtiliser(this);
        actionEffetConsomable();
        if (obj.nbUtilisationRestante() <= 0 && obj.nbUtilisationRestante() != -1) this.inventaire.remove(index);
        return reussiAutiliser;
    }
    
    public double getPoidsInventaire(){
        double poidsInventaire = 0;
        for(Objet objet:this.inventaire){
            poidsInventaire += objet.getPoids();
        }
        return poidsInventaire;
    }
    
    public boolean ajouterObjet(Objet o){
        if(o.getPoids()+ this.getPoidsInventaire()<=(this.poidsMax + this.getEffetPoids())){
            return this.inventaire.add(o);
        }else{
            return false;
        }
    } 
    
    public boolean ajoutEffet(Effet o){
        return this.effetCourant.add(o);
    }
    
    public boolean ajoutEffets(ArrayList<Effet> o){
        return this.effetCourant.addAll(o);
    }
    public boolean actionEffetFinDuTour(){
        int[] effetASupp = new int[this.effetCourant.size()];
        int i = 0;
        for(Effet effet : this.effetCourant){
            if(effet.tourPasse()==false){
                effetASupp[i] = this.effetCourant.indexOf(effet);
                i++;
            }
        }
        for(int j=0;j<i;j++){
            this.effetCourant.remove(effetASupp[j]-j);
        }
        return true;
    } 
    
    public boolean actionEffetConsomable(){
        int[] effetASupp = new int[this.effetCourant.size()];
        int i = 0;
        for(Effet effet : this.effetCourant){
            if(effet.isConsomable()){
                if(effet.tourPasse()==false){
                    this.force += effet.getForceAjoute();
                    this.agilite += effet.getAgiliteAjoute();
                    this.pv += effet.getPvAjoute();
                    this.pvMax += effet.getPvMaxAjoute();
                    this.poidsMax += effet.getPoidsAjoute();
                    effetASupp[i] = this.effetCourant.indexOf(effet);
                    i++;
                }
            }
        }
            for(int j=0;j<i;j++){
            this.effetCourant.remove(effetASupp[j]-j);
        }
        return true;
    }
    
    @Override
    public String toString() {
        if (this.description.trim().equals("")) {
            return this.nom;
        }
        return this.nom + " (" + this.description + ") " + this.pv + "PV";
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
    public boolean generate(GenerableParametre p) {
        return false; // pas générable pour l'instant
    }

    public void ajouter(Element e) {
        if (e instanceof Objet) {
            this.inventaire.add((Objet)e);
        } else if (e instanceof Effet) {
            this.effetCourant.add((Effet)e);
        }
    }
    
}
