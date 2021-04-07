/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exec;

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
        AppliGraphique g = new AppliGraphique();
        g.setVisible(true);
    }

}
