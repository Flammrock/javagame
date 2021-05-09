/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele.element;

import canvas.Canvas;
import canvas.Drawable;
import canvas.Sprite;
import canvas.collision.CollisionBox;
import canvas.collision.CollisionEvent;
import canvas.collision.Collisionable;
import eventsystem.Dispatcher;
import eventsystem.SimpleListener;
import exec.Settings;
import java.awt.Graphics;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import map.Generable;
import map.GenerateListener;

/**
 *
 * @author User
 */
public class Arme extends Equipement {
    
    public Arme(String nom, String description, double poids) {
        super(nom,description,poids);
    }
    
    
    public void initAleatoire(){
        String[] mots;
        InputStream in = getClass().getResourceAsStream("/armes");
        try (Scanner scan = new Scanner(in)) {
            int nbrLigne = 42;  //A modifier a chaque modification du fichier armes
            Random random = new Random();
            int LigneRdm = random.nextInt(nbrLigne);
            scan.nextLine();
            for(int i = 0;i<LigneRdm-1;i++){
                scan.nextLine();
            }
            String phrase = scan.nextLine();
            mots = phrase.split("/");
            this.nom = mots[0];
            this.description = mots[1];
            Effet effetArme = new Effet("", "", true, false, 0);
            effetArme.setProperty(PropertyList.FORCE, Double.parseDouble(mots[3]));
            this.effet = effetArme;
            Sprite sprite_epee = new Sprite("/items/Item__01.png",0,0,24,24);
            sprite_epee.addCollisionBox(new CollisionBox(0, 0, 24, 24,Settings.DEBUG,false));
            setSprite(sprite_epee);
            setIcon(sprite_epee.getFileName());
        }
        try {
            in.close();
        } catch (IOException ex) {}
    }

    @Override
    public Drawable copie() {
        Arme c = new Arme(nom, description, poids);
        c.setSprite(sprite);
        c.setIcon(this.icon);
        if (this.effet!=null) c.setEffet(this.effet.copie());
        c.setRadiusRamassable(this.radiusramassable);
        c.setZIndex(this.zindex);
        return c;
    }
}
