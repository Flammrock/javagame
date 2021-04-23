/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exec;

import canvas.Animation;
import canvas.SpriteSheet;
import canvas.TileSet;
import canvas.collision.CollisionBox;
import canvas.collision.CollisionEvent;
import canvas.light.Light;
import embellishment.Embellishment;
import embellishment.TypeEmbellishment;
import java.util.ArrayList;
import modele.element.*;
import eventsystem.*;
import map.Squeleton;

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
        
        
        
        
        
        /////////////////////// TILESET TEST ////////////////////////////
        
        // tileset de la map
        TileSet tileset = new TileSet("/16x16DungeonTileset.v4.png");
        tileset.loadImage();
        tileset.setSprite("wall", 2, 12, 45, 25);
        tileset.setSprite("ground", 0, 38, 14, 10);
        tileset.setSprite("embellishment_ground",5,93,55,44);
        tileset.setSprite("embellishment_wall_1",96,53,16,33);
        
        // spritesheet light
        SpriteSheet lightsprite = new SpriteSheet("/light.png",0,0,16,26,16*3,26*3);
        lightsprite.loadImage();
        lightsprite.ajouterAnimation(new Animation("Lighting",new int[] {0, 1, 2, 3, 4, 5, 6, 7}));
        lightsprite.addCollisionBox(new CollisionBox(4, 14, 8, 11));
        lightsprite.setAnimation("Lighting");
        lightsprite.addDrawable(new Light(16/2,26/2,200));
        
        Embellishment e_ground = new Embellishment(TypeEmbellishment.GROUND, tileset.getSprite("embellishment_ground"));
        Embellishment e_wall_1 = new Embellishment(TypeEmbellishment.WALL, tileset.getSprite("embellishment_wall_1"));
        Embellishment e_light = new Embellishment(TypeEmbellishment.OBJECT, lightsprite);
        
        
        /////////////////////////////////////////////////////////////
        
        
        // tout les objets on les met ici :
        
        /****************** BIBLIOTHEQUE OBJET ******************/
        Arme epeeCasser = new Arme("épée cassé","une épée qui jadis tranchas des têtes et inspirait la terreur",1,0,0);
        Effet effetPomme = new Effet("","",false,true,0);
        effetPomme.setProperty(PropertyList.PV,10);
        Effet regeneration = new Effet("régeneration","augmente la vie dans le temmps",true,false,0);
        regeneration.setProperty(PropertyList.PV,3);
        Nourriture pomme = new Nourriture("Pomme","Une bonne petite pomme",2.0,effetPomme);
        Effet hommeDeFer = new Effet("Homme de fer","augmente la force",false,false,3);
        hommeDeFer.setProperty(PropertyList.FORCE, 5);
        Effet popo = new Effet("régeneration","augmente la vie dans le temmps",false,false,3);
        popo.setProperty(PropertyList.PV, 3);
        Nourriture potion = new Nourriture("Potion de regen","regen la vie",1,popo);
        Effet effetBanane = new Effet("","",false,true,0);
        effetBanane.setProperty(PropertyList.PV, 15);
        Nourriture banane = new Nourriture("banane","Une grosse banane",2.0,effetBanane);
        Armure armureBadass = new Armure("Armure de badass","Une armure crée par les geants pour encaiser des coups",15,0,10);
        /********************************************************/
        
        /***************** BIBLIOTHEQUE MONSTRE *****************/
        Personnage monstre = new Personnage("Goblin", "monstre de la mort");
        monstre.init(5, 10, 15, 20);
        monstre.ajouter(banane);
        monstre.ajouter(armureBadass);
        monstre.equip(1);
        
        //Personnage rock = new Personnage("rock", "inofensif");
        //rock.init(5, 10, 15, 20);
        //rock.addCollisionBox(new CollisionBox(10,20,465/3-25,420/3-40));
        
        /********************************************************/
        
        
        // on créé un joueur
        Personnage joueur = new Personnage("Héros","perso lambda nul");
        joueur.init(20, 15, 15, 100);
        joueur.ajouter(epeeCasser);
        joueur.ajouter(pomme);
        joueur.ajouter(regeneration);
        joueur.ajouter(hommeDeFer);
        hommeDeFer.setProperty(PropertyList.FORCE, 5);
        popo = new Effet("régeneration","augmente la vie dans le temmps",false,false,3);
        joueur.ajouter(potion);
        joueur.addCollisionBox(new CollisionBox(0,35,28,14));
        
        
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
        s.setOnDraw((canvas) -> {
            
            // LEFT
            if (canvas.isAppuyer(37)) {
                s.moveBy(-4, 0);
                s.setAnimationIfNot("Walk-Left");
                
            // UP
            } else if (canvas.isAppuyer(38)) {
                s.moveBy(0, -4);
                s.setAnimationIfNot("Walk-Up");
            
            // RIGHT
            } else if (canvas.isAppuyer(39)) {
                s.moveBy(4, 0);
                s.setAnimationIfNot("Walk-Right");
                
            // DOWN
            } else if (canvas.isAppuyer(40)) {
                s.moveBy(0, 4);
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
            
        });
        
        joueur.setSprite(s);
        
        
        
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
        niveau1.addEmbellishment(e_light);
        niveau1.onCollide(new SimpleListener("onCollide") {
            @Override
            public void onEvent(Object sender, SimpleEvent event) {
                CollisionEvent e = (CollisionEvent) event;
                if (e.getCollider2() == joueur) {
                    System.out.println("[COLLISION] "+e.getCollider1()+" ---> "+e.getCollider2());
                    if (e.getCollider1() instanceof Porte) {
                        Porte p = (Porte) e.getCollider1();
                        p.teleport(joueur);
                    }
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
        a.ajouterNiveau(niveau1);

        // on place le joueur dans le 1er lieu de ce niveau
        a.getJoueur().setPieceActuel(a.getLieu(niveau1,niveau1.getEntree().getNom()));
        
        AppliGraphique g = new AppliGraphique(a);
        g.setVisible(true);
        
        //a.debutCombat();
    }
}
