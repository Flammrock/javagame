
package modele.element;

import canvas.*;
import canvas.collision.CollisionBox;
import canvas.collision.CollisionEvent;
import canvas.collision.Collisionable;
import eventsystem.Dispatcher;
import eventsystem.SimpleEvent;
import eventsystem.SimpleListener;
import geometry.Point;
import java.awt.Graphics;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import static java.lang.Math.random;
import static java.lang.StrictMath.random;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import map.Generable;
import map.GenerateListener;


public class Personnage extends Element implements Generable, Collisionable {
    
    private String nom;

    private ArrayList<Property> listproperties;
    
    private ArrayList<Objet> inventaire;
    private Equipement main;
    private Equipement armure;
    private Lieu pieceActuel;
    
    private ArrayList<Effet> effetCourant;
    
    private SpriteSheet sprite;
    
    private ArrayList<CollisionBox> collisionBoxList;
    
    // un dispatcher d'events
    Dispatcher dispatcher;
    
    
    boolean allowCopyListener;
    
    int follow_tick;
    int follow_tick_max;
    ArrayList<PointNode> follow_path;
    int follow_path_index;
    

    public Personnage(String nom, String description) {
        this.nom = nom;
        this.description = description;
        this.inventaire = new ArrayList<>();
        this.main = null;
        this.armure = null;
        this.effetCourant = new ArrayList<>();
        this.sprite = null;
        this.collisionBoxList = new ArrayList<>();
        this.listproperties = new ArrayList<>();
        this.dispatcher = new Dispatcher();
        this.allowCopyListener = false;
        
        this.follow_tick = 0;
        this.follow_tick_max = 100;
        this.follow_path = new ArrayList<>();
        this.follow_path_index = 0;
    }
    
    public void init(double age, double force, double agilite, double pv) {
        this.setProperty(PropertyList.AGE,age);
        this.setProperty(PropertyList.FORCE,force);
        this.setProperty(PropertyList.AGILITE,agilite);
        this.setProperty(PropertyList.PV,pv);
        this.setProperty(PropertyList.PVMAX,pv);
        this.setProperty(PropertyList.POIDSMAX,force*3);
    }
    
    
    public void initAleatoire(){
        String[] mots;
        InputStream in = getClass().getResourceAsStream("/monstres");
        try (Scanner scan = new Scanner(in)) {
            int nbrLigne = 23;  //A modifier a chaque modification du fichier montres
            Random random = new Random();
            int LigneRdm = random.nextInt(nbrLigne) + 1;
            for(int i = 0;i<LigneRdm;i++){
                scan.nextLine();
            }
            String phrase = scan.nextLine();
            mots = phrase.split("/");
            this.nom = mots[1];
            this.description = mots[2];
            double age = 0;
            double force = Double.parseDouble(mots[4]);
            double agilite = Double.parseDouble(mots[5]);
            double pvMax = Double.parseDouble(mots[6]);
            double poids = Double.parseDouble(mots[3]);
            init(age,force,agilite,pvMax);   
        }
        try {
            in.close();
        } catch (IOException ex) {}
        
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
        //pieceActuel.setVisible(true);
        this.dispatcher.fireEvent("onEnterSalle", pieceActuel, new SimpleEvent(null));
    }
    
    public void setProperty(String nom, double valeur){
        //System.out.println(nom + " "+ valeur);
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
        return this.effetCourant.add(o.copie());
    }
    
    public boolean ajoutEffets(ArrayList<Effet> o){
        return this.effetCourant.addAll(o);
    }
    public boolean actionEffetFinDuTour(){
        int[] effetASupp = new int[this.effetCourant.size()];
        int i = 0;
        if(getAjoute(PropertyList.PV)+ getEffet(PropertyList.PV)>= getAjoute(PropertyList.PVMAX)){
                setProperty(PropertyList.PV, getAjoute(PropertyList.PVMAX));
            }else{
                setProperty(PropertyList.PV, getEffet(PropertyList.PV)+getAjoute(PropertyList.PV));
            }
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
        
        this.updateCollisionBox();
        
        this.sprite.draw(c, g);
        
        this.dispatcher.fireEvent("onUpdate", this, null);
        
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
    
    public void clearCollisionBox() {
        this.collisionBoxList.clear();
    }
    
    public void updateCollisionBox() {
        for (CollisionBox b : this.collisionBoxList) {
            b.apply(this.getX(),this.getY());
            //b.draw(c, g);
        }
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

    @Override
    public Dispatcher getDispatcher() {
        return this.dispatcher;
    }

    @Override
    public void collide(Collisionable c) {
        dispatcher.fireEvent("onCollide", this, new CollisionEvent(this,c));
    }

    @Override
    public void onCollide(SimpleListener l) {
        l.setType("onCollide"); // on force le type
        this.dispatcher.addListener(l);
    }

    public boolean essaiEnfuir(Personnage Ennemi) {
        double probabiliteDeToucher = this.valeurCombat()/(this.valeurCombat()+Ennemi.valeurCombat());
        return aToucher(probabiliteDeToucher);
    }

    @Override
    public void onGenerate(GenerateListener l) {
        this.dispatcher.addListener(l);
    }

    @Override
    public boolean generate(Object o) {
        
        if (o instanceof Lieu) {
            Lieu l = (Lieu)o;
            
            double p = 1;
            while (Math.random()<p) {
                this.setX(l.randomX(this));
                this.setY(l.randomY(this));
                if (l.isValide(this)) {
                    this.setPieceActuel(l);
                    return true;
                }
                p -= 0.1;
            }
            
        }
        
        return false;
        
    }
    
    
    @Override
    public Drawable copie() {
        Personnage c = new Personnage(nom, description);
        for (CollisionBox b : this.collisionBoxList) {
            c.addCollisionBox(b.copie());
        }
        
        for (Property p : this.listproperties) {
            c.setProperty(p.getNom(), p.getValeur());
        }
        
        for (Objet o : this.inventaire) {
            c.ajouter(o);
        }
        
        if (this.main != null) {
            int index = -1;
            int i = 0;
            for (Objet o : this.inventaire) {
                if (o == main) break;
                i++;
            }
            c.equip(index);
        }
        
        if (this.armure != null) {
            int index = -1;
            int i = 0;
            for (Objet o : this.inventaire) {
                if (o == armure) break;
                i++;
            }
            c.equip(index);
        }
        
        c.setPieceActuel(pieceActuel);
        
        for (Effet e : this.effetCourant) {
            c.ajoutEffet(e);
        }

        c.setSprite(sprite.copie());
        
        if (this.allowCopyListener) {
            for (SimpleListener sl : this.dispatcher.getListeners()) {
                c.addListener(sl);
            }
            c.allowCopyListener();
        }
    
        return c;
    }

    public void addListener(SimpleListener simpleListener) {
        this.dispatcher.addListener(simpleListener);
    }

    public void allowCopyListener() {
        this.allowCopyListener = true;
    }

    public void follow(Personnage p) {
        
        // temporaire
        /////////////////////////////////////////////////////////////////////////
        double speed = 2;
        double angle = Math.atan2(p.getY()-this.getY(), p.getX()-this.getX());
        int dxv = (int)(speed * Math.cos(angle));
        int dyv = (int)(speed * Math.sin(angle));
        this.moveBy(dxv, dyv); // try to go in this direction
        if (1==1) return;
        /////////////////////////////////////////////////////////////////////////
        
        // si le lieu où se trouve le personnage n'est pas défini alors on quitte
        if (this.getPieceActuel()==null) return;
        
        // plus petit déplacement infinitesimal que le personnage peut faire
        int dx = 1;
        int dy = 1;
        
        // taille de la pièce
        int w = this.getPieceActuel().getWidth();
        
        // algo A*
        
        // on init
        PointNode.g.clear();
        PointNode.f.clear();
        PointNode start = new PointNode(w,this.getX(),this.getY());
        PointNode end = new PointNode(w,p.getX(),p.getY());
        PriorityQueue<PointNode> pile = new PriorityQueue<>(Comparator.comparing(x -> x.fcost));
        start.setgCost(0);
        start.setfCost(PointNode.dist(start, end));
        pile.offer(start);
        
        // on créé un personnage temporaire pour pouvoir tester la validité
        Personnage temp = (Personnage)this.copie();
        
        this.clearCollisionBox();
        
        // on créé le tableau de direction possible
        int dxs[] = new int[]{1,0,-1,0};
        int dys[] = new int[]{0,1,0,-1};
        
        // tant qu'il y a des noeuds à visiter
        while (!pile.isEmpty()) {
        
            // on récup le noeud le + intéressant (et on l'enlève en même temps)
            PointNode pn = pile.poll();
            
            // on regarde si on est arrivé
            if (pn.equals(end)) {
                end.parent = pn.parent;
                break;
            }

            // on récup les voisins possibles
            for (int i = 0; i < dxs.length; i++) {
                PointNode pn1 = pn.add(dxs[i],dys[i]);

                // on estime le cout
                Integer tentative_gcost = pn.gcost + PointNode.dist(pn,pn1);
                if (tentative_gcost >= pn1.gcost) continue;
                
                // on regarde si ce nouveau point est valide
                temp.setX(pn1.x);
                temp.setY(pn1.y);
                temp.updateCollisionBox();
                if (this.getPieceActuel().isValide(temp)) {
                    
                    
                    
                    // on définit le parent
                    pn1.parent = pn;
                    
                    // on met à jour le cout de ce point
                    pn1.setgCost(tentative_gcost);
                    pn1.setfCost(pn1.gcost + PointNode.dist(pn1,end));
                    
                    // on l'ajoute dans les noeuds à visiter
                    if (!pile.contains(pn1)) {
                        pile.offer(pn1);
                    }
                }
            }
        
        }
        
        for (CollisionBox b : temp.getCollisionBoxList()) {
            this.addCollisionBox(b.copie());
        }
        
        if (end.parent != null) {
            // on remonte tout
            PointNode current = end;
            PointNode c = end;
            while (current.parent!=null) {
                c = current;
                current = current.parent;
            }
            this.moveTo(c.x, c.y);
        }
        
        
        
        //double speed = 2;
        //double angle = Math.atan2(p.getY()-this.getY(), p.getX()-this.getX());
        
        
        
        //int dx = (int)(speed * Math.cos(angle));
        //int dy = (int)(speed * Math.sin(angle));
        //this.moveBy(dx, dy); // try to go in this direction
        
    }

    private static class PointNode {

        public int x;
        public int y;
        public int w;
        public Integer gcost;
        public Integer fcost;
        public PointNode parent;
        static public HashMap<PointNode,Integer> g = new HashMap<>();
        static public HashMap<PointNode,Integer> f = new HashMap<>();
        
        public PointNode(int w, int x, int y) {
            this.w = w;
            this.x = x;
            this.y = y;
            if (PointNode.g.containsKey(this)) this.gcost = PointNode.g.get(this);
            else this.gcost = Integer.MAX_VALUE;
            if (PointNode.g.containsKey(this)) this.fcost = PointNode.f.get(this);
            else this.fcost = Integer.MAX_VALUE;
            this.parent = null;
        }
        
        public PointNode(int w, int x, int y, Integer gcost, Integer fcost) {
            this.w = w;
            this.x = x;
            this.y = y;
            this.gcost = gcost;
            this.fcost = fcost;
            this.parent = null;
        }
        
        public PointNode add(int dx, int dy) {
            return new PointNode(w,x+dx,y+dy);
        }
        
        public void setgCost(Integer g) {
            this.gcost = g;
            PointNode.g.put(this, g);
        }
        
        public void setfCost(Integer f) {
            this.fcost = f;
            PointNode.f.put(this, f);
        }
        
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof PointNode)) return false;
            PointNode key = (PointNode) o;
            return x == key.x && y == key.y;
        }

        @Override
        public int hashCode() {
            int result = x;
            result = w * result + y;
            return result;
        }
        
        static public int dist(PointNode a, PointNode b) {
            int dx = b.x-a.x;
            int dy = b.y-a.y;
            return dx*dx+dy*dy;
        }
    }
    
}
