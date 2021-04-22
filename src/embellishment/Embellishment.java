/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package embellishment;

import canvas.Canvas;
import canvas.Drawable;
import canvas.Sprite;
import canvas.collision.CollisionBox;
import canvas.collision.Collisionable;
import eventsystem.Dispatcher;
import eventsystem.SimpleListener;
import java.awt.Graphics;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import modele.element.Element;

/**
 *
 * @author User
 */
public class Embellishment implements Collisionable {
    
    String type;
    Sprite sprite;
    ArrayList<CollisionBox> collisionboxlist;
    
    int x;
    int y;
    int width;
    int height;
    
    boolean collideEmbellishment;
    
    public Embellishment(String type, Sprite sprite) {
        this.type = type;
        this.sprite = sprite;
        this.collisionboxlist = new ArrayList<>();
        this.x = 0;
        this.y = 0;
        this.width = 0;
        this.height = 0;
        this.collideEmbellishment = true;
    }
    
    public Embellishment(Embellishment e) {
        this.type = e.getType();
        this.sprite = e.getSprite();
        this.x = e.getX();
        this.y = e.getY();
        this.width = e.getWidth();
        this.height = e.getHeight();
        this.collideEmbellishment = e.getCollideEmbellishment();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Sprite getSprite() {
        return sprite;
    }

    public void setSprite(Sprite sprite) {
        this.sprite = sprite;
    }
    
    
    
    public boolean isLoaded() {
        if (this.sprite==null) return false;
        return this.sprite.isLoaded();
    }
    
    public boolean loadImage() {
        if (this.sprite==null) return false;
        return this.sprite.loadImage();
    }

    @Override
    public ArrayList<CollisionBox> getCollisionBoxList() {
        return this.collisionboxlist;
    }

    @Override
    public void addCollisionBox(CollisionBox b) {
        this.collisionboxlist.add(b);
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
        
    }

    @Override
    public void cancelMove() {
        
    }

    @Override
    public Dispatcher getDispatcher() {
        return null;
    }

    @Override
    public void collide(Collisionable c) {
        
    }

    @Override
    public void onCollide(SimpleListener l) {
        
    }

    @Override
    public void draw(Canvas c, Graphics g) {
        if (this.sprite==null) return;
        this.sprite.setX(x);
        this.sprite.setY(y);
        this.sprite.setScaleSize(width, height);
        this.sprite.setWidth(width);
        this.sprite.setHeight(height);
        this.sprite.draw(c,g);
    }

    @Override
    public ArrayList<Drawable> getDrawables() {
        return null;
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public void setX(int x) {
        this.x = x;
    }

    @Override
    public int getY() {
        return y;
    }

    @Override
    public void setY(int y) {
        this.y = y;
    }

    @Override
    public void moveTo(int x, int y) {
        this.setX(x);
        this.setY(y);
    }

    @Override
    public void moveBy(int x, int y) {
        this.setX(this.getX()+x);
        this.setY(this.getY()+y);
    }

    @Override
    public boolean isDraw() {
        return true;
    }
    
    public void setWidth(int w) {
        this.width = w;
    }
    
    public void setHeight(int h) {
        this.height = h;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
    
    public void setBox(int x, int y, int width, int height) {
        this.setX(x);
        this.setY(y);
        this.setWidth(width);
        this.setHeight(height);
        if (this.collideEmbellishment) this.computeCollisonBox();
    }
    
    public void computeCollisonBox() {
        if (this.collisionboxlist==null) this.collisionboxlist = new ArrayList<>();
        this.collisionboxlist.clear();
        System.out.println("??????");
        CollisionBox b = new CollisionBox(0,0, width, height);
        b.apply(x, y);
        this.collisionboxlist.add(b);
    }
    
    /**
     * Permet de faire en sorte que cet Embellishment ne soit pas en collision avec
     * d'autres Embellishment lors de la génération
     * @default true
     * @param v la nouvelle valeur
     */
    public void setCollideEmbellishment(boolean v) {
        this.collideEmbellishment = v;
    }
    
    public boolean getCollideEmbellishment() {
        return this.collideEmbellishment;
    }
    
    
}
