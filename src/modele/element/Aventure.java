/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele.element;

import canvas.Animation;
import canvas.Canvas;
import canvas.Combat.DebutCombatEvent;
import canvas.DrawListener;
import canvas.Drawable;
import canvas.Sprite;
import canvas.SpriteSheet;
import canvas.TileSet;
import canvas.collision.CollisionBox;
import canvas.collision.CollisionEvent;
import canvas.light.Light;
import embellishment.Embellishment;
import embellishment.TypeEmbellishment;
import eventsystem.Dispatcher;
import eventsystem.SimpleEvent;
import eventsystem.SimpleListener;
import exec.Settings;
import java.awt.Graphics;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import map.GenerateListener;
import map.Squeleton;

/**
 *
 * @author Utilisateur
 */
public class Aventure extends Element {
    
    private Personnage joueur;
    private Donjon donjon;
    private Personnage joueurSaved;
    private int nbsallesSaved;
    
    private ArrayList<Drawable> drawables;
    Dispatcher dispatcher;
    
    boolean pausedraw;
    
    public Aventure(Personnage joueur) {
        this.dispatcher = new Dispatcher();
        this.description = "mon aventure";
        
        this.drawables = new ArrayList<>();
        
        this.pausedraw = false;
        
        // on créé le perso ici
        this.joueur = joueur;
        joueur.addListener(new SimpleListener("gameOver") {
            @Override
            public void onEvent(Object sender, SimpleEvent e) {
                if(sender instanceof Personnage){
                    Personnage p = (Personnage)sender;
                    if(p == getJoueur()){
                        pausedraw = true;
                    }
                }
            }
        });
        
        // on créé le donjon ici
        this.donjon = new Donjon();
        
    }
    
    public boolean isPauseDraw() {
        return this.pausedraw;
    }

    public Personnage getJoueur() {
        return joueur;
    }
    
    public void debutCombat(Personnage Hero, Personnage Monstre){
        //Personnage monstre = new Personnage("Goblin", "monstre de la mort");
        //monstre.init(5, 20, 15, 60);
        //monstre.initAleatoire();
        this.dispatcher.fireEvent("onCombatCommence",this, new DebutCombatEvent(Hero,Monstre));
        this.pausedraw = true;
        System.out.println("Debut");
        
        //this.dispatcher.fireEvent("onCombatTermine",this, new DebutCombatEvent());
    }
    
    public void finCombat(Personnage Hero, Personnage Monstre){
        this.joueur = Hero;
        System.out.println("fin");
        this.dispatcher.fireEvent("onCombatTermine",this, new DebutCombatEvent(Hero,Monstre));
        this.pausedraw = false;
    }

    /**
     * Permet d'ajouter un lieu
     * @param niveaunom le nom du niveau
     * @param nom le nom du lieu
     * @return retourne true si le lieu a bien été ajouté au niveau
     */
    public boolean ajouterLieu(String niveaunom, String nom) {
        Niveau n = donjon.getNiveau(niveaunom);
        if (n==null) return false;
        n.ajouterLieu(new Lieu(nom));
        return true;
    }
    
    public boolean ajouterLieu(Niveau niveau, String nom) {
        return this.ajouterLieu(niveau.getNom(),  nom);
    }

    /**
     * Permet de récupérer un lieu via son nom
     * @param niveaunom le nom du niveau
     * @param nom le nom du lieu
     * @return retourne le lieu portant ce nom sinon reoturne null
     */
    public Lieu getLieu(String niveaunom, String nom) {
        Niveau n = donjon.getNiveau(niveaunom);
        if (n==null) return null;
        return n.getLieu(nom);
    }
    
    public Lieu getLieu(Niveau niveau, String nom) {
        return this.getLieu(niveau.getNom(), nom);
    }

    /**
     * Permet d'ajouter une porte entre deux lieux
     * @param niveaunom le nom du niveau
     * @param nom_porte
     * @param nom_lieu1
     * @param nom_lieu2
     * @return retourne true si la porte a été ajouté, false sinon
     */
    public boolean ajouterPorte(String niveaunom, String nom_porte, String nom_lieu1, String nom_lieu2) {
        Niveau n = donjon.getNiveau(niveaunom);
        if (n==null) return false;
        return n.ajouterPorte(nom_porte, nom_lieu1, nom_lieu2);
    }
    
    public boolean ajouterPorte(Niveau niveau, String nom_porte, String nom_lieu1, String nom_lieu2) {
        return this.ajouterPorte(niveau.getNom(), nom_porte, nom_lieu1, nom_lieu2);
    }

    /**
     * Permet d'ajouter un objet dans un lieu
     * @param niveaunom le nom du niveau
     * @param lieu le lieu où on ajoute l'objet
     * @param e l'element à rajouter
     * @return retourne true si l'objet a bien été ahouté, sinon false
     */
    public boolean ajouter(String niveaunom, String lieu, Element e) {
        Niveau n = donjon.getNiveau(niveaunom);
        if (n==null) return false;
        return n.ajouter(e,lieu);
    }
    
    public boolean ajouter(Niveau niveau, String lieu, Element e) {
        return this.ajouter(niveau.getNom(), lieu, e);
    }

    /**
     * Permet d'ajouter un niveau dans le donjon
     * @param niveau le niveau à ajouter
     * @return retourne true si le niveau a bien été ajouté, sinon false
     */
    public boolean ajouterNiveau(Niveau niveau) {
        return this.donjon.ajouterNiveau(niveau);
    }


    @Override
    public ArrayList<Drawable> getDrawables() {
        return this.drawables;
    }

    public void ajouterDrawable(Drawable d) {
        this.drawables.add(d);
    }
    
    
    @Override
    public void draw(Canvas c, Graphics g) {
    }

    public void addListerner(SimpleListener simpleListener) {
        dispatcher.addListener(simpleListener);
    }
    
    
    
    @Override
    public Drawable copie() {
        /*try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(this);

            ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
            ObjectInputStream ois = new ObjectInputStream(bais);
            return (Drawable)ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            return null;
        }*/
        return null;
    }
    
    @Override
    public int getZIndex() {
        return Integer.MIN_VALUE; // aucun impact
    }
    
    
    
    public void recommencerNiveau() {
        this.joueur.setState(this.joueurSaved);
        this.generateNiveau(this.nbsallesSaved);
    }
    
    
    public void generateNiveau(int nbsalles) {
        
        this.drawables.clear();
        
        
        
        /////////////////////// TILESET TEST ////////////////////////////
        
        // tileset de la map
        TileSet tileset = new TileSet("/16x16DungeonTileset.v4.png");
        tileset.loadImage();
        tileset.setSprite("wall", 2, 12, 45, 25);
        tileset.setSprite("ground", 0, 38, 14, 10);
        tileset.setSprite("embellishment_ground",5,93,55,44);
        tileset.setSprite("embellishment_wall_1",96,53,16,33);
        tileset.setSprite("embellishment_wall_2",49,56,15,27);
        tileset.setSprite("embellishment_wall_3",66,2,12,22);
        
        // spritesheet light
        SpriteSheet lightsprite = new SpriteSheet("/light.png",0,0,16,26,16*3,26*3);
        lightsprite.loadImage();
        lightsprite.ajouterAnimation(new Animation("Lighting",new int[] {0, 1, 2, 3, 4, 5, 6, 7}));
        lightsprite.addCollisionBox(new CollisionBox(4, 14, 8, 11,Settings.DEBUG));
        lightsprite.setAnimation("Lighting");
        lightsprite.addDrawable(new Light(16/2,26/2,200));
        
        Embellishment e_ground = new Embellishment(TypeEmbellishment.GROUND, tileset.getSprite("embellishment_ground"));
        Embellishment e_wall_1 = new Embellishment(TypeEmbellishment.WALL, tileset.getSprite("embellishment_wall_1"));
        Embellishment e_wall_2 = new Embellishment(TypeEmbellishment.WALL, tileset.getSprite("embellishment_wall_2"));
        Embellishment e_wall_3 = new Embellishment(TypeEmbellishment.WALL, tileset.getSprite("embellishment_wall_3"));
        Embellishment e_light = new Embellishment(TypeEmbellishment.OBJECT, lightsprite);
        
        
        /////////////////////////////////////////////////////////////
        
        
        Sprite sprite_epee = new Sprite("/items/Item__01.png",0,0,24,24);
        sprite_epee.addCollisionBox(new CollisionBox(0, 0, 24, 24,Settings.DEBUG,false));
        
        Sprite sprite_epeecasser = new Sprite("/items/Item__01c.png",0,0,24,24);
        sprite_epeecasser.addCollisionBox(new CollisionBox(0, 0, 24, 24,Settings.DEBUG,false));
        
        Sprite sprite_pomme = new Sprite("/items/Item__64.png",0,0,24,24);
        sprite_pomme.addCollisionBox(new CollisionBox(0, 0, 24, 24,Settings.DEBUG,false));
        
        Sprite sprite_banane = new Sprite("/items/Item__72.png",0,0,24,24);
        sprite_banane.addCollisionBox(new CollisionBox(0, 0, 24, 24,Settings.DEBUG,false));
        
        Sprite sprite_armureBadass = new Sprite("/items/Item__57.png",0,0,24,24);
        sprite_armureBadass.addCollisionBox(new CollisionBox(0, 0, 24, 24,Settings.DEBUG,false));
        
        // tout les objets on les met ici :
        
        /****************** BIBLIOTHEQUE OBJET ******************/
        Arme epeeCasser = new Arme("épée cassé","une épée qui jadis tranchas des têtes et inspirait la terreur",1);
        epeeCasser.setSprite(sprite_epeecasser);
        epeeCasser.setIcon(sprite_epeecasser.getFileName());
        
        Effet effetEpeeDeBase = new Effet("attaque","attaque boosté",true,false,0);
        effetEpeeDeBase.setProperty(PropertyList.FORCE, 5);
        Arme epeeDeBase = new Arme("épée de base","une épée simple et basique",1);
        epeeDeBase.setSprite(sprite_epee);
        epeeDeBase.setIcon(sprite_epee.getFileName());
        epeeDeBase.setEffet(effetEpeeDeBase);
        
        Effet effetPomme = new Effet("","",false,true,0);
        effetPomme.setProperty(PropertyList.PV,10);
        
        Effet regeneration = new Effet("régeneration","augmente la vie dans le temmps",true,false,0);
        regeneration.setProperty(PropertyList.PV,3);
        
        Nourriture pomme = new Nourriture("Pomme","Une bonne petite pomme",2.0);
        pomme.setSprite(sprite_pomme);
        pomme.setIcon(sprite_pomme.getFileName());
        pomme.setEffet(effetPomme);
        
        Effet hommeDeFer = new Effet("Homme de fer","augmente la force",false,false,3);
        hommeDeFer.setProperty(PropertyList.FORCE, 5);
        
        Effet popo = new Effet("régeneration","augmente la vie dans le temmps",false,false,3);
        popo.setProperty(PropertyList.PV, 3);
        
        Nourriture potion = new Nourriture("Potion de regen","regen la vie",1);
        potion.setEffet(popo);
        
        Effet effetBanane = new Effet("","",false,true,0);
        effetBanane.setProperty(PropertyList.PV, 15);
        
        Nourriture banane = new Nourriture("banane","Une grosse banane",2.0);
        banane.setSprite(sprite_banane);
        banane.setIcon(sprite_banane.getFileName());
        banane.setEffet(effetBanane);
        
        
        Effet effetArmureBadass = new Effet("résistance","résistance contre les attaques",true,false,0);
        effetArmureBadass.setProperty(PropertyList.ARMURE, 10);
        popo.setProperty(PropertyList.PV, 3);
        Armure armureBadass = new Armure("Armure de badass","Une armure crée par les geants pour encaiser des coups",15);
        armureBadass.setSprite(sprite_armureBadass);
        armureBadass.setIcon(sprite_armureBadass.getFileName());
        armureBadass.setEffet(effetArmureBadass);
        
        /********************************************************/
        
        /***************** BIBLIOTHEQUE MONSTRE *****************/
        
        SimpleListener monstrelistener = new SimpleListener("onUpdate") {
            @Override
            public void onEvent(Object sender, SimpleEvent e) {
                
                if (!(sender instanceof Personnage)) return;
                
                Personnage current_monstre = (Personnage)sender;
                
                // si le monstre est dans la même pièce que le joueur,
                // alors le monstre suit le joueur
                
                if (joueur.getPieceActuel()==current_monstre.getPieceActuel()) {
                    current_monstre.follow(joueur);
                    current_monstre.tryFight(joueur);
                }
                
            }
        };
        
        Personnage monstre = new Personnage("Minautor", "monstre de la mort");
        monstre.init(5, 10, 15, 20);
        monstre.ajouter(banane);
        monstre.ajouter(armureBadass);
        monstre.equip(1);
        monstre.allowCopyListener();
        monstre.addListener(monstrelistener);
        
        //Personnage rock = new Personnage("rock", "inofensif");
        //rock.init(5, 10, 15, 20);
        //rock.addCollisionBox(new CollisionBox(10,20,465/3-25,420/3-40));
        
        /********************************************************/
        
        
        
        
        // on ajoute des trucs au joueur
        joueur.ajouter(epeeDeBase);
        joueur.ajouter(epeeCasser);
        joueur.ajouter(pomme);
        joueur.ajouter(regeneration);
        joueur.ajouter(hommeDeFer);
        joueur.equip(0);
        hommeDeFer.setProperty(PropertyList.FORCE, 5);
        popo = new Effet("régeneration","augmente la vie dans le temmps",false,false,3);
        joueur.ajouter(potion);
        joueur.setCanRamasse(true);
        joueur.addCollisionBox(new CollisionBox(0,35,28,14,Settings.DEBUG));
        joueur.addListener(new SimpleListener("onEnterSalle") {
            @Override
            public void onEvent(Object sender, SimpleEvent e) {
                if (sender instanceof Lieu) {
                    Lieu l = (Lieu)sender;
                    l.setVisible(true);
                }
            }
        });
        joueur.denyCopyListener();
        
        
        
        // on créé le sprite pour le joueur
        SpriteSheet s = new SpriteSheet("/HerosSpriteSheet.png",0,0,64,64,64*3,64*3);
        s.loadImage();
        s.setDecal(27,23,27,23);
        s.ajouterAnimation(new Animation("Idle-Left",new int[] {0, 1, 2, 3}));
        s.ajouterAnimation(new Animation("Idle-Up",new int[] {4, 5, 6, 7}));
        s.ajouterAnimation(new Animation("Idle-Right",new int[] {8, 9, 10, 11}));
        s.ajouterAnimation(new Animation("Idle-Down",new int[] {12, 13, 14, 15}));
        s.ajouterAnimation(new Animation("Walk-Left",new int[] {16, 17, 18, 19}));
        s.ajouterAnimation(new Animation("Walk-Up",new int[] {20, 21, 22, 23}));
        s.ajouterAnimation(new Animation("Walk-Right",new int[] {24, 25, 26, 27}));
        s.ajouterAnimation(new Animation("Walk-Down",new int[] {28, 29, 30, 31}));
        s.setAnimation("Idle-Left");
        s.setOnDraw(new DrawListener(){
            @Override
            public void onDraw(Canvas canvas) {
            
                int speed = 6;

                // LEFT
                if (canvas.isAppuyer(37)) {
                    s.moveBy(-speed, 0);
                    s.setAnimationIfNot("Walk-Left");

                // UP
                } else if (canvas.isAppuyer(38)) {
                    s.moveBy(0, -speed);
                    s.setAnimationIfNot("Walk-Up");

                // RIGHT
                } else if (canvas.isAppuyer(39)) {
                    s.moveBy(speed, 0);
                    s.setAnimationIfNot("Walk-Right");

                // DOWN
                } else if (canvas.isAppuyer(40)) {
                    s.moveBy(0, speed);
                    s.setAnimationIfNot("Walk-Down");

                } else {
                    if (s.getAnimationCourrante().getNom().equals("Walk-Left")) {
                        s.setAnimation("Idle-Left");
                    } else if (s.getAnimationCourrante().getNom().equals("Walk-Up")) {
                        s.setAnimation("Idle-Up");
                    } else if (s.getAnimationCourrante().getNom().equals("Walk-Right")) {
                        s.setAnimation("Idle-Right");
                    } else if (s.getAnimationCourrante().getNom().equals("Walk-Down")) {
                        s.setAnimation("Idle-Down");
                    }
                }
            }
            
        });
        
        joueur.setSprite(s);
        
        
        
        SpriteSheet s2 = new SpriteSheet("/minautor.png",0,0,96,96,96*2,92*2);
        s2.loadImage();
        s2.ajouterAnimation(new Animation("Idle-Right",new int[] {0, 1, 2, 3, 4}));
        s2.ajouterAnimation(new Animation("Idle-Left",new int[] {100, 101, 102, 103, 104}));
        s2.ajouterAnimation(new Animation("Walk-Right",new int[] {10, 11, 12, 13, 14, 15, 16, 17}));
        s2.ajouterAnimation(new Animation("Walk-Left",new int[] {110, 111, 112, 113, 114, 115, 116, 117}));
        s2.ajouterAnimation(new Animation("Death",new int[] {195}));
        s2.setAnimation("Walk-Right");
        s2.setDecal(0, 20, 0, 30);
        monstre.addCollisionBox(new CollisionBox(75,70,38,18,Settings.DEBUG));
        monstre.setSprite(s2);
        
        
        // test pour la collision
        //SpriteSheet srock = new SpriteSheet("/testcollision.png",0,0,465,420,465/3,420/3);
        //srock.loadImage();
        //srock.setX(500);
        //srock.setY(300);
        //rock.setSprite(srock);
        
        
        ///////////////////////////////////// TEMP
        Squeleton squeleton = new Squeleton(nbsalles);
        squeleton.generate(0,0);
        ///////////////////////////////////// TEMP
        
        //a.ajouterDrawable(rock);
        this.ajouterDrawable(joueur);
        this.ajouterDrawable(squeleton);
        
        // on créé un niveau 1
        Niveau niveau1 = new Niveau("Niveau 1","Le début de l'Aventure commence");
        this.ajouterDrawable(niveau1); // on l'ajoute dans les items à dessiner
        niveau1.setTileSet(tileset);
        niveau1.addEmbellishment(e_ground);
        niveau1.addEmbellishment(e_wall_1);
        niveau1.addEmbellishment(e_wall_2);
        niveau1.addEmbellishment(e_wall_3);
        niveau1.addEmbellishment(e_light);
        
        // dès qu'il y a une collision dans ce niveau, ceci est appelé
        niveau1.onCollide(new SimpleListener("onCollide") {
            @Override
            public void onEvent(Object sender, SimpleEvent event) {
                CollisionEvent e = (CollisionEvent) event;
                if (e.getCollider1() instanceof Personnage && e.getCollider2() == joueur) {
                    Personnage p1 = (Personnage)e.getCollider1();
                    Personnage p2 = (Personnage)e.getCollider2();
                    if (p1.canFight() && p2.canFight()) {
                        System.out.println("[FIGHT] "+e.getCollider1()+" ---> "+e.getCollider2());
                        debutCombat((Personnage)e.getCollider2(), (Personnage)e.getCollider1());
                    }
                } else if (e.getCollider2() == joueur) {
                    System.out.println("[COLLISION] "+e.getCollider1()+" ---> "+e.getCollider2());
                    if (e.getCollider1() instanceof Porte) {
                        Porte p = (Porte) e.getCollider1();
                        p.teleport(joueur);
                    }
                }
            }
        });
        
        // dès que quelque chose est généré dans ce niveau, ceci est appelé
        niveau1.onGenerate(new GenerateListener(){
            @Override
            public void onEvent(Object sender, SimpleEvent event) {
                
                // une salle a été généré
                if (event.getData() instanceof Lieu) {
                    
                    Lieu l = (Lieu)event.getData();
                    
                    if (l.isEntree()) return;
                    
                    if (Math.random() < 1.0) {
                        // on copie l'objet
                        Arme a = (Arme)epeeCasser.copie();
                        a.generate(l);
                        l.ajouter(a);
                    }
                    
                    // on copie le monstre
                    Personnage m = (Personnage)monstre.copie();
                    
                     if(l.isSortie()){
                        // on init le boss
                        m.init(40,50,50,100);
                        m.setNom("Boss");
                        m.setBoss(true);
                        
                        //on configure la salle
                        l.setNom("Salle du Boss");
                        SpriteSheet trape = new SpriteSheet("/trape.png",0,0,188,227,50,50);
                        trape.loadImage();
                        trape.ajouterAnimation(new Animation("Close",new int[] {0}));
                        trape.ajouterAnimation(new Animation("Open",new int[] {1}));
                        trape.setAnimation("Close");
                        trape.addCollisionBox(new CollisionBox(50,50,38,18,Settings.DEBUG));
                        trape.setZIndex(Integer.MIN_VALUE);
                        trape.setX(l.getMiddleX());
                        trape.setY(l.getMiddleY());
                        //on ajoute les eventListner
                        m.addListener(new SimpleListener("gameOver") {
                            @Override
                            public void onEvent(Object sender, SimpleEvent e) {
                                if(sender instanceof Personnage){
                                    trape.setAnimation("Open");
                                }
                            }
                        });
                        trape.onCollide(new SimpleListener("onCollide") {
                            @Override
                            public void onEvent(Object sender, SimpleEvent event) {
                                CollisionEvent e = (CollisionEvent) event;
                                if (e.getCollider1()==joueur || e.getCollider2()==joueur) {
                                    if(trape.getAnimationCourrante().getNom().equals("Open")){
                                        //newStage
                                        System.out.println("newStage");
                                    }
                                }
                            }                   
                        });
                        trape.setParent(l);
                        l.addDrawable(trape);
                    }else{
                        // on le randomise
                        m.initAleatoire();
                    }
                    
                    // on génère le monstre
                    m.generate(l);
                    
                    // on ajoute le monstre dans chaque salle
                    l.ajouter(m);
                    
                    System.out.println("[GENERATE] "+l);
                    
                }
                
            }
        });
        
        
        // on passe un squelette de don
        
        // on créé les paramètres de génération
        /*GenerableLieuParametre lieup = new GenerableLieuParametre();
        ArrayList<Generable> generables = new ArrayList<>();
        generables.add(pomme);
        generables.add(monstre);
        lieup.setGenerables(generables);*/
        
        
        
        // on génère le niveau à partir d'un squelette
        niveau1.generate(squeleton);
        
        
        // on ajoute le niveau dans le donjon
        this.donjon.list_niveaux.clear();
        this.ajouterNiveau(niveau1);

        // on place le joueur dans le 1er lieu de ce niveau
        this.getJoueur().setPieceActuel(this.getLieu(niveau1,niveau1.getEntree().getNom()));
        
        this.joueurSaved = (Personnage)this.joueur.copie();
        this.nbsallesSaved = nbsalles;
    }
    
}
