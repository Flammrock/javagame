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
        joueur.init(20, 30, 30, 1);
        
        joueur.addListener(new SimpleListener("gameOver") {
            @Override
            public void onEvent(Object sender, SimpleEvent e) {
                System.out.println("GameOver");
            }
        });
        
        // on créé une aventure
        Aventure a = new Aventure(joueur);

        
        a.generateNiveau(7);
        
        AppliGraphique g = new AppliGraphique(a);
        g.setVisible(true);
        
        //a.debutCombat(a.getJoueur(),null);
    }
}
