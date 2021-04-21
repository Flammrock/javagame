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
import java.util.ArrayList;

/**
 *
 * @author User
 */
public class Embellishment implements Collisionable {
    
    String type;
    Sprite sprite;
    ArrayList<CollisionBox> collisionboxlist;
    
    public Embellishment(String type, Sprite sprite) {
        this.type = type;
        this.sprite = sprite;
        this.collisionboxlist = new ArrayList<>();
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
        if (this.sprite==null) return 0;
        return this.sprite.getX();
    }

    @Override
    public int getNewY() {
        if (this.sprite==null) return 0;
        return this.sprite.getY();
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
        this.sprite.draw(c,g);
    }

    @Override
    public ArrayList<Drawable> getDrawables() {
        return null;
    }

    @Override
    public int getX() {
        if (this.sprite==null) return 0;
        return this.sprite.getX();
    }

    @Override
    public void setX(int x) {
        if (this.sprite==null) return;
        this.sprite.setX(x);
    }

    @Override
    public int getY() {
        if (this.sprite==null) return 0;
        return this.sprite.getY();
    }

    @Override
    public void setY(int y) {
        if (this.sprite==null) return;
         this.sprite.setY(y);
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
    
    
}
