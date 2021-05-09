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
import java.awt.Graphics;
import java.util.ArrayList;
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
