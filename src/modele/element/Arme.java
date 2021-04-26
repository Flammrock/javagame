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
public class Arme extends Equipement implements Collisionable, Generable {
    
    Sprite sprite;
    Dispatcher dispatcher;
    int zindex;
    
    public Arme(String nom, String description, double poids, double agilite, double force) {
        super(nom,description,poids);
        this.sprite = null;
        this.zindex = 0;
        this.dispatcher = new Dispatcher();
        this.bonus_agilite = agilite;
        this.bonus_force = force;
    }
    
    public void setSprite(Sprite s) {
        this.sprite = s.copie();
    }
    
    @Override
    public int getX() {
        if (this.sprite == null) return 0;
        return this.sprite.getX(); // pour l'instant, on propage ça dans le sprite
    }

    @Override
    public void setX(int x) {
        if (this.sprite == null) return;
        this.sprite.setX(x); // pour l'instant, on propage ça dans le sprite
    }

    @Override
    public int getY() {
        if (this.sprite == null) return 0;
        return this.sprite.getY(); // pour l'instant, on propage ça dans le sprite
    }

    @Override
    public void setY(int y) {
        if (this.sprite == null) return;
        this.sprite.setY(y); // pour l'instant, on propage ça dans le sprite
    }
    
    public int getWidth() {
        if (this.sprite == null) return 0;
        return this.sprite.computeWidth();
    }
    
    public int getHeight() {
        if (this.sprite == null) return 0;
        return this.sprite.computeHeight();
    }

    @Override
    public void moveTo(int x, int y) {
        if (this.sprite == null) return;
        this.sprite.moveTo(x,y); // pour l'instant, on propage ça dans le sprite
    }

    @Override
    public void moveBy(int x, int y) {
        if (this.sprite == null) return;
        this.sprite.moveBy(x,y); // pour l'instant, on propage ça dans le sprite
    }
    
    @Override
    public void draw(Canvas c, Graphics g) {
        if (this.sprite == null) return;
        for (CollisionBox b : this.getCollisionBoxList()) {
            b.apply(this.getX(),this.getY());
        }
        this.sprite.draw(c, g);
    }
    
    @Override
    public Drawable copie() {
        Arme c = new Arme(nom, description, poids, bonus_agilite, bonus_force);
        c.setSprite(sprite);
        return c;
    }

    @Override
    public Dispatcher getDispatcher() {
        return this.dispatcher;
    }

    @Override
    public void onGenerate(GenerateListener l) {
        this.dispatcher.fireEvent("onGenerate", this, null);
    }

    @Override
    public boolean generate(Object o) {
        if (o instanceof Lieu) {
            Lieu l = (Lieu)o;
            
            double p = 1;
            while (Math.random()<p) {
                this.setX(l.randomX(this,this.getWidth()));
                this.setY(l.randomY(this,this.getHeight()));
                if (l.isValide(this)) {
                    return true;
                }
                p -= 0.1;
            }
            
        }
        
        return false;
    }

    @Override
    public ArrayList<CollisionBox> getCollisionBoxList() {
        if (this.sprite == null) return null;
        return this.sprite.getCollisionBoxList();
    }

    @Override
    public void addCollisionBox(CollisionBox b) {
        if (this.sprite == null) return;
        this.sprite.getCollisionBoxList().add(b);
    }

    @Override
    public int getNewX() {
        if (this.sprite == null) return 0;
        return this.sprite.getX();
    }

    @Override
    public int getNewY() {
        if (this.sprite == null) return 0;
        return this.sprite.getY();
    }

    @Override
    public void applyMove() {
        
    }

    @Override
    public void cancelMove() {
        
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
    
    @Override
    public int getZIndex() {
        return zindex; // défini par le zindex minimum du lieu
    }
    
    @Override
    public void setZIndex(int zindex) {
        this.zindex = zindex;
    }
    
}
