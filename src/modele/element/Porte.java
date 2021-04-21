/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele.element;

import canvas.Canvas;
import canvas.Drawable;
import canvas.collision.CollisionBox;
import canvas.collision.CollisionEvent;
import canvas.collision.Collisionable;
import eventsystem.Dispatcher;
import eventsystem.SimpleListener;
import geometry.Box;
import geometry.Direction;
import geometry.Point;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;

/**
 *
 * @author Utilisateur
 */
public class Porte extends Element implements Collisionable {
    
    private Lieu lieu1;
    private Lieu lieu2;
    private Porte porte2;
    private String nom;
    
    int x;
    int y;
    int width;
    int height;
    
    // un dispatcher d'events
    Dispatcher dispatcher;
    
    Direction d;
    
    ArrayList<CollisionBox> collisionBoxList;
    
    public Porte(String nom, Lieu lieu1, Lieu lieu2) {
        this.nom = nom;
        this.lieu1 = lieu1;
        this.lieu2 = lieu2;
        x = 0;
        y = 0;
        width = 0;
        height = 0;
        this.collisionBoxList = new ArrayList<>();
        this.dispatcher = new Dispatcher();
    }
    
    public void connect(Porte p) {
        this.porte2 = p;
    }

    public String getNom() {
        return nom;
    }

    public Lieu getLieu1() {
        return lieu1;
    }

    public void setLieu1(Lieu lieu1) {
        this.lieu1 = lieu1;
    }

    public Lieu getLieu2() {
        return lieu2;
    }

    public void setLieu2(Lieu lieu2) {
        this.lieu2 = lieu2;
    }
    
    public String toString() {
        return this.nom + " (vers "+this.lieu2.getNom()+")";
    }
    
    public void setSize(Box b) {
        x = b.getX();
        y = b.getY();
        width = b.getWidth();
        height = b.getHeight();
        this.collisionBoxList.clear();
        CollisionBox c = new CollisionBox(0,0, width, height);
        c.apply(x,y);
        this.addCollisionBox(c);
    }
    
    public void setDirection(Direction d) {
        this.d = d;
    }
    
    public void teleport(Personnage p) {
        
        Point s = new Point(porte2.getX(),porte2.getY());
        s.append(d, width+2, height+2);
        
        p.setX(s.x);
        p.setY(s.y);
    }
    
    @Override
    public void draw(Canvas c, Graphics g) {
    
        g.setColor(Color.blue);
        g.drawRect(c.toWorldX(this.x), c.toWorldY(this.y), c.toScale(this.width), c.toScale(this.height));
        
        g.setColor(Color.white);
        g.setFont(new Font("Verdana", Font.PLAIN, c.toScale(17)));
        int widthtext = g.getFontMetrics().stringWidth(toString());
        g.drawString(toString(), c.toWorldX(x)+c.toScale(width/2)-widthtext/2, c.toWorldY(y)-c.toScale(10));
    
    }

    @Override
    public ArrayList<CollisionBox> getCollisionBoxList() {
        return this.collisionBoxList;
    }

    @Override
    public void addCollisionBox(CollisionBox b) {
        if (b == null) return;
        this.collisionBoxList.add(b);
    }
    
    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getY() {
        return y;
    }

    @Override
    public int getNewX() {
        return x;
    }

    @Override
    public int getNewY() {
        return y;
    }

    @Override
    public void applyMove() {
        // les portes ne peuvent pas bouger
    }

    @Override
    public void cancelMove() {
        // les portes ne peuvent pas bouger
    }

    @Override
    public Dispatcher getDispatcher() {
        return this.dispatcher;
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
    
}
