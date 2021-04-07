



/**
 * @TODO : 
 * 
 *       - prendre en compte le poids des objets dans l'inventaire
 *       - possibilité d'améliorer l'inventaire, les objets
 *       - possibilité de réparer les objets
 *       - possibilité de crafter des objets
 *       - ajouter des évènements aléatoires
 *       - les monstres drops des matériaux, on peut vendre et gagner des matériaux
 *       - système d'expérience
 *       - système de clé/serrure
 *       - système d'effet (poison...)
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
        
        ArrayList<Objet> listeInventaire = new ArrayList<Objet>();
        listeInventaire.add(new Arme("épée cassé","une épée qui jadis tranchas des têtes et inspirait la terreur",1,0,0));
        listeInventaire.add(new Nourriture("Pomme","Une bonne petite pomme",2.0,10.0));
        
        Personnage joueur = new Personnage("Héros","perso lambda nul", 20, 15, 15, 100, listeInventaire);
        
        ArrayList<Objet> listeloot = new ArrayList<Objet>();
        listeloot.add(new Nourriture("banane","Une grosse banane",2.0,15.0));
        
        Personnage monstre = new Personnage("Goblin", "monstre de la mort", 2, 2, 2, 10, listeloot);
        
                
        Arme epee = new Arme("Epée","Epée Légendaire",25.2,-5,100);
        
        Aventure a = new Aventure(joueur);
        
        String pieceprincipal = "piece principal";
        String piecesecondaire = "piece secondaire";
        String piecetertiaire = "piece tertiare";
        
        
        
        a.ajouterLieu(pieceprincipal);
        a.ajouterLieu(piecesecondaire);
        a.ajouterLieu(piecetertiaire);
        
        a.ajouterMonstre(monstre,piecesecondaire);
        a.ajouterObjet(epee,piecetertiaire);
        
        a.ajouterPorte("porte nord",pieceprincipal,piecesecondaire);
        a.ajouterPorte("porte rouge",piecetertiaire,piecesecondaire);
        
        
        a.getJoueur().setPieceActuel(a.getLieu(pieceprincipal));
        
        AppliGraphique g = new AppliGraphique(a);
        g.setVisible(true);
    }

}
