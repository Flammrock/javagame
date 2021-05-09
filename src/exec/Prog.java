/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exec;

import canvas.Animation;
import canvas.Canvas;
import canvas.Combat.DebutCombatEvent;
import canvas.DrawListener;
import canvas.Sprite;
import canvas.SpriteSheet;
import canvas.TileSet;
import canvas.collision.CollisionBox;
import canvas.collision.CollisionEvent;
import canvas.light.Light;
import embellishment.Embellishment;
import embellishment.TypeEmbellishment;
import java.util.ArrayList;
import eventsystem.*;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;
import map.GenerateListener;
import map.Squeleton;
import modele.element.Arme;
import modele.element.Armure;
import modele.element.Aventure;
import modele.element.Effet;
import modele.element.Lieu;
import modele.element.Niveau;
import modele.element.Porte;
import modele.element.Nourriture;
import modele.element.Personnage;
import modele.element.PropertyList;

public class Prog {

    public static void test1() {
        
        /*
        Personnage p = new Personnage("Toto", "Un Heros", 50, 50, 50, 80, 20); //un constructeur nom/desc/force/agilite/charisme/poids/Vie
        Personnage q = new Personnage("Titi", "Un Vilain", 50, 50, 50, 80, 10);
        Arme a = new Arme("Epee", "une belle epée en fer", 10, 8, 2);//un constructeur nom/desc/bonusagilite/dommagesMax/poids
        Arme b = new Arme("dague", "une petite dague", 15, 3, 1);
        p.equipe(a);
        q.equipe(b);

        System.out.println("Combat entre " + p + " et " + q);//redefinir toString dans la classe Personnage pour un affichage de qqs infos sur le perso (nom,desc?)

        while (p.getVie() > 0 && q.getVie() > 0) {
            System.out.prinlnt(p.attaque(q));
            if (q.getVie() == 0) {
                break;
            }
            System.out.prinlnt(q.attaque(p));
            if (p.getVie() == 0) {
                break;
            }
        }
        System.out.println("Quelqu'un est mort");

         */

    }

    public static void test2() {
        /*
        Lieu l1 = new Lieu("Une petite salle avec deux portes");
        Lieu l2 = new Lieu("Une grande salle avec une porte");
        Lieu l3 = new Lieu("Une chambre avec une porte");
        l1.ajoutePorteVers(l2); //attention ajoutePorteVers doit créer une porte et modifier a la fois l1 et l2
        l1.ajoutePorteVers(l3);
        Personnage q = new Personnage("Titi", "Un Vilain", 50, 50, 50, 80, 10);
        q.setLieu(l1);
        System.out.println(q.nom + " vous etes dans " + l1); //un toString() dans Lieu pour afficher la description
        System.out.println("Il y a " + l1.getNbPortes() + ", où voulez vous aller? (tapez un nombre entre 0 et " + l1.getNbPortes() + ")");
        int x = (new Scanner(System.in)).nextInt();
        Porte po = q.getLieu().nbPortes();
        System.out.println(q.empruntePorte(po));
        System.out.println("Vous etes dans " + q.getLieu());
         */
    }

    public static void test3() {
        /*AppliGraphique g = new AppliGraphique();
        g.setVisible(true);
        */
    }

    public static void main(String[] args) {
        
        
        /***************** EVENT SYSTEM TEST *****************/
        
        
        // un dispatcher qui contiendra différent listener pour différent event
        Dispatcher dispatcher = new Dispatcher();
        
        // on ajoute un listener pour l'event "onBonjour"
        // la méthode onEvent sera appelé dès qu'on envoie un event "onBonjour" dans le dispatcher
        dispatcher.addListener(new SimpleListener("onBonjour"){
            
            @Override
            public void onEvent(Object sender, SimpleEvent e) {
                if (e.getData() instanceof ArrayList) {
                    System.out.println("oui c'est bien un ArrayList<Integer> !");
                }
                System.out.println("Bonjour : "+e.getData());
            }
            
        });
        
        // on ajoute un listener pour l'event "onAurevoir"
        // la méthode onEvent sera appelé dès qu'on envoie un event "onAurevoir" dans le dispatcher
        dispatcher.addListener(new SimpleListener("onAurevoir"){
            
            @Override
            public void onEvent(Object sender, SimpleEvent e) {
                System.out.println("Au revoir : "+e.getData());
            }
            
        });
        
        
        // on envoie nos 2 events (et on observe sur la console ce qu'il se passe)
        ArrayList<Integer> data = new ArrayList<>();
        data.add(1);
        data.add(3);
        dispatcher.fireEvent("onBonjour",null, new SimpleEvent(data));
        dispatcher.fireEvent("onAurevoir",null, new SimpleEvent(3));
        
        
        
        
        /*****************************************************/
        
        
        // on créé un joueur
        Personnage joueur = new Personnage("Héros","perso lambda nul");
        joueur.init(20, 30, 30, 150);
        
        
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
        
        // on créé une aventure
        Aventure a = new Aventure(joueur);
        
        
        ///////////////////////////////////// TEMP
        Squeleton squeleton = new Squeleton(7);
        squeleton.generate(0,0);
        ///////////////////////////////////// TEMP
        
        //a.ajouterDrawable(rock);
        a.ajouterDrawable(joueur);
        a.ajouterDrawable(squeleton);
        
        // on créé un niveau 1
        Niveau niveau1 = new Niveau("Niveau 1","Le début de l'Aventure commence");
        a.ajouterDrawable(niveau1); // on l'ajoute dans les items à dessiner
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
                        a.debutCombat((Personnage)e.getCollider2(), (Personnage)e.getCollider1());
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
                    
                    // on le randomise
                    m.initAleatoire();
                    
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
        boolean nb = niveau1.generate(squeleton);
        
        
        // on ajoute le niveau dans le donjon
        a.ajouterNiveau(niveau1);

        // on place le joueur dans le 1er lieu de ce niveau
        a.getJoueur().setPieceActuel(a.getLieu(niveau1,niveau1.getEntree().getNom()));
        
        AppliGraphique g = new AppliGraphique(a);
        g.setVisible(true);
        
        //a.debutCombat(a.getJoueur(),null);
    }
}
