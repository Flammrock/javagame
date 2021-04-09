
/**
 * @TODO : 
 * 
 *       - cycle jour/nuit
 *       - système de lumière
 *       - malédiction du temple => les objets disparaissent au bout d'un certain moment
 *       - ajouter effet de salles
 *       - génération de niveau aléaroire
 *       - prendre en compte le poids des objets dans l'inventaire                      Check
 *       - possibilité d'améliorer l'inventaire, les objets
 *       - possibilité de détuire les objets pour récupérer des matériaux
 *       - possibilité de réparer les objets
 *       - possibilité de crafter des objets
 *       - ajouter des évènements aléatoires
 *       - les monstres drops des matériaux, on peut vendre et gagner des matériaux
 *       - système d'expérience
 *       - système de clé/serrure
 *       - système d'effet (poison...)                                                  Check (pas pour le poison mais pour les stats de bases)
 *       - nouvelle classe (potion,....)
 *       - avoir plusieurs persos
 *       - ajouter le multijoueur
 *       - acheter/vendre
 *       - système d'argent (money)
 *       - des objets magiques (utiliser pour balancer des boules de feu,...)
 *       - des effets d'armes
 *       - ajouter des boss avec des trésors
 *       - customisation du perso
 *       - barre de faim, de soif,...
 *       - rajouter des modes de difficulté
 *       - charte graphique
 */













/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exec;

import java.util.ArrayList;
import modele.element.*;

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
        
        // tout les objets on les met ici :
        
        /****************** BIBLIOTHEQUE OBJET ******************/
        Arme epeeCasser = new Arme("épée cassé","une épée qui jadis tranchas des têtes et inspirait la terreur",1,0,0);
        Effet effetPomme = new Effet("","",false,true,0, 0, 0, 10,0,0,0, new Lieu("Rien"));
        Nourriture pomme = new Nourriture("Pomme","Une bonne petite pomme",2.0,effetPomme);
        Effet hommeDeFer = new Effet("Homme de fer","augmente la force",false,false,3,5,0,0,0,0,0,new Lieu("Rien"));
        Nourriture potion = new Nourriture("Potion de force","augmente la force",1,hommeDeFer);
        Effet effetBanane = new Effet("","",false,true,0, 0, 0, 15,0,0,0, new Lieu("Rien"));
        Nourriture banane = new Nourriture("banane","Une grosse banane",2.0,effetBanane);
        Armure armureBadass = new Armure("Armure de badass","Une armure crée par les geants pour encaiser des coups",15,0,10);
        /********************************************************/
        
        /***************** BIBLIOTHEQUE MONSTRE *****************/
        Personnage monstre = new Personnage("Goblin", "monstre de la mort", 5, 10, 15, 20);
        monstre.ajouter(banane);
        monstre.ajouter(armureBadass);
        monstre.equip(1);
        
        /********************************************************/
        
        
        // on créé un joueur
        Personnage joueur = new Personnage("Héros","perso lambda nul", 20, 15, 15, 100);
        joueur.ajouter(epeeCasser);
        joueur.ajouter(pomme);
        joueur.ajouter(hommeDeFer);
        joueur.ajouter(new Effet("Homme de fer","augmente la force",false,false,3,5,0,0,0,0,0,new Lieu("Rien")));
        joueur.ajouter(potion);
        
        
        
        // on créé une aventure
        Aventure a = new Aventure(joueur);
        
        // on créé un niveau 1
        Niveau niveau1 = new Niveau("Niveau 1","Le début de l'Aventure commence");
        
        
        // on créé les paramètres de génération
        GenerableLieuParametre lieup = new GenerableLieuParametre();
        ArrayList<Generable> generables = new ArrayList<>();
        generables.add(pomme);
        generables.add(monstre);
        lieup.setGenerables(generables);
        
        
        
        // on génère le niveau avec ces paramètres
        niveau1.generate(new GenerableNiveauParametre(5,lieup));
        
        
        // on ajoute le niveau dans le donjon
        a.ajouterNiveau(niveau1);

        // on place le joueur dans le 1er lieu de ce niveau
        a.getJoueur().setPieceActuel(a.getLieu(niveau1,niveau1.getEntree().getNom()));
        
        AppliGraphique g = new AppliGraphique(a);
        g.setVisible(true);
    }

}
