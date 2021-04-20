
package modele.element;

import canvas.*;
import canvas.collision.CollisionBox;
import canvas.collision.Collisionable;
import java.awt.Graphics;
import java.util.ArrayList;
import javax.swing.SpringLayout;
import map.Generable;


public class Personnage extends Element implements Generable, Collisionable {
    
    private String nom;
    private double probaDeGeneration;

    private ArrayList<Property> listproperties;
    
    private ArrayList<Objet> inventaire;
    private Equipement main;
    private Equipement armure;
    private Lieu pieceActuel;
    
    private ArrayList<Effet> effetCourant;
    
    private SpriteSheet sprite;
    
    private ArrayList<CollisionBox> collisionBoxList;
    
    

    public Personnage(String nom, String description) {
        this.nom = nom;
        this.probaDeGeneration = 1.0;
        this.description = description;
        this.inventaire = new ArrayList<>();
        this.main = null;
        this.armure = null;
        this.effetCourant = new ArrayList<>();
        this.sprite = null;
        this.collisionBoxList = new ArrayList<>();
        this.listproperties = new ArrayList<>();
    }
    
    public void init(double age, double force, double agilite, double pv) {
        this.setProperty(PropertyList.AGE,age);
        this.setProperty(PropertyList.FORCE,force);
        this.setProperty(PropertyList.AGILITE,agilite);
        this.setProperty(PropertyList.PV,pv);
        this.setProperty(PropertyList.PVMAX,pv);
        this.setProperty(PropertyList.POIDSMAX,force*3);
    }

    public SpriteSheet getSprite() {
        return sprite;
    }

    public void setSprite(SpriteSheet sprite) {
        this.sprite = sprite;
    }

    
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPv(double pv) {
        if (pv > this.getAjoute(PropertyList.PVMAX)) {
            setProperty(PropertyList.PV, getAjoute(PropertyList.PVMAX));
            return;
        }
        setProperty(PropertyList.PV, pv);
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

    public ArrayList<Effet> getEffetCourant() {
        return effetCourant;
    }

    public Lieu getPieceActuel() {
        return pieceActuel;
    }

    public void setPieceActuel(Lieu pieceActuel) {
        this.pieceActuel = pieceActuel;
    }
    
    public void setProperty(String nom, double valeur){
        for (Property p : this.listproperties) {
            if(p.getNom().equals(nom)){
                p.setValeur(valeur);
                return;
            }
        }
        this.listproperties.add(new Property(nom,valeur));
    }
    
    public void removeProperty(String nom){
        for (Property p : this.listproperties) {
            if(p.getNom().equals(nom)){
                listproperties.remove(p);
                return;
            }
        }
    }
    
    public double getAjoute(String nom){
        double r = 0.0;
        for (Property p : this.listproperties) {
            if (p.getNom().equals(nom)) {
                r += p.getValeur();
            }
        }
        return r;
    }
    
    public void ajoutePointVie(double valeur) {
        if (this.getAjoute(PropertyList.PV) + valeur > this.getAjoute(PropertyList.PVMAX)) {
            setProperty(PropertyList.PV,this.getAjoute(PropertyList.PVMAX));
            return;
        }
        setProperty(PropertyList.PV,this.getAjoute(PropertyList.PV)+valeur);
    }
    
    public double getEffet(String nom){
        double r = 0;
        for(Effet effet : this.effetCourant){
            r += effet.getAjoute(nom);
        }
        return r;
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
        double bonusEffet = this.getEffet(PropertyList.AGILITE);
        double valeurCombat = this.getAjoute(PropertyList.AGILITE)+bonusArmure+bonusEffet;
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
        double pointdevie = ennemie.getAjoute(PropertyList.PV);
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
        double bonusForce = this.getAjoute(PropertyList.FORCE);
        double bonusEffet = this.getEffet(PropertyList.FORCE);
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
        double bonusEffet = this.getEffet(PropertyList.ARMURE);
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
        if(o.getPoids()+ this.getPoidsInventaire()<=(this.getAjoute(PropertyList.POIDSMAX) + this.getEffet(PropertyList.POIDS))){
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
            if(getAjoute(PropertyList.PV)+ getEffet(PropertyList.PV)>= getAjoute(PropertyList.PVMAX)){
                setProperty(PropertyList.PV, getAjoute(PropertyList.PVMAX));
            }else{
                setProperty(PropertyList.PV, getEffet(PropertyList.PV)+getAjoute(PropertyList.PV));
            }
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
                    setProperty(PropertyList.FORCE, getAjoute(PropertyList.FORCE) + effet.getAjoute(PropertyList.FORCE));
                    setProperty(PropertyList.AGILITE, getAjoute(PropertyList.AGILITE) + effet.getAjoute(PropertyList.AGILITE));
                    
                    if(getAjoute(PropertyList.PV)+ getEffet(PropertyList.PV)>= getAjoute(PropertyList.PVMAX)){
                        setProperty(PropertyList.PV, getAjoute(PropertyList.PVMAX));
                    }else{
                        setProperty(PropertyList.PV, getEffet(PropertyList.PV)+getAjoute(PropertyList.PV));
                    }
                    
                    setProperty(PropertyList.PVMAX, getAjoute(PropertyList.PVMAX) + effet.getAjoute(PropertyList.PVMAX));
                    setProperty(PropertyList.POIDS, getAjoute(PropertyList.POIDS) + effet.getAjoute(PropertyList.POIDS));
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
        
        return this.nom + " (" + this.description + ") " + getAjoute(PropertyList.PV) + "PV";
        
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

    public void ajouter(Element e) {
        if (e instanceof Objet) {
            this.inventaire.add((Objet)e);
        } else if (e instanceof Effet) {
            this.effetCourant.add((Effet)e);
        }
    }

    @Override
    public void draw(Canvas c, Graphics g) {
        
        // pour l'instant, on dessine juste le sprite
        if (this.sprite == null) return;
        
        for (CollisionBox b : this.collisionBoxList) {
            b.apply(this.getX(),this.getY());
            b.draw(c, g);
        }
        
        this.sprite.draw(c, g);
        
    }

    @Override
    public int getX() {
        return this.sprite.getX(); // pour l'instant, on propage ça dans le sprite
    }

    @Override
    public void setX(int x) {
        this.sprite.setX(x); // pour l'instant, on propage ça dans le sprite
    }

    @Override
    public int getY() {
        return this.sprite.getY(); // pour l'instant, on propage ça dans le sprite
    }

    @Override
    public void setY(int y) {
        this.sprite.setY(y); // pour l'instant, on propage ça dans le sprite
    }

    @Override
    public void moveTo(int x, int y) {
        this.sprite.moveTo(x,y); // pour l'instant, on propage ça dans le sprite
    }

    @Override
    public void moveBy(int x, int y) {
        this.sprite.moveBy(x,y); // pour l'instant, on propage ça dans le sprite
    }
    
    @Override
    public ArrayList<CollisionBox> getCollisionBoxList() {
        return this.collisionBoxList;
    }

    @Override
    public void addCollisionBox(CollisionBox b) {
        if (b == null) return;
        this.collisionBoxList.add(b);
    }

    @Override
    public ArrayList<Drawable> getDrawables() {
        // pas de sous-ensemble de sprite
        // (peut-être que lorsque l'on pourra dessiner des habits différents, ou bien des épées..)
        return null;
    }

    @Override
    public void cancelMove() {
        this.sprite.cancelMove(); // pour l'instant, on propage ça dans le sprite
    }
    
    @Override
    public void applyMove() {
        this.sprite.applyMove(); // pour l'instant, on propage ça dans le sprite
    }
    
    @Override
    public int getNewX() {
        return this.sprite.getNewX();  // pour l'instant, on propage ça dans le sprite
    }

    @Override
    public int getNewY() {
        return this.sprite.getNewY(); // pour l'instant, on propage ça dans le sprite
    }
    
}
