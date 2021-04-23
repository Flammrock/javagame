/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package embellishment;

import canvas.Animation;
import canvas.Canvas;
import canvas.Drawable;
import canvas.Sprite;
import canvas.SpriteSheet;
import canvas.collision.CollisionBox;
import canvas.collision.Collisionable;
import canvas.light.Light;
import eventsystem.Dispatcher;
import eventsystem.SimpleListener;
import java.awt.Graphics;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 *
 * @author User
 */
public class Embellishment implements Collisionable {
    
    String type;
    Sprite sprite;
    ArrayList<CollisionBox> collisionboxlist;
    ArrayList<Drawable> drawables;
    
    Drawable parent;
    
    boolean collideEmbellishment;
    
    public Embellishment(String type, Sprite sprite) {
        this.type = type;
        this.sprite = sprite.copie();
        this.collisionboxlist = new ArrayList<>();
        this.collideEmbellishment = true;
        this.drawables = new ArrayList<>();
        this.parent = null;
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
        return this.sprite.getNewX();
    }

    @Override
    public int getNewY() {
        if (this.sprite==null) return 0;
        return this.sprite.getNewY();
    }

    @Override
    public void applyMove() {
        if (this.sprite==null) return;
        this.sprite.applyMove();
    }

    @Override
    public void cancelMove() {
        if (this.sprite==null) return;
        this.sprite.cancelMove();
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
        return this.drawables;
    }
    
    public void addDrawable(Drawable d) {
        this.drawables.add(d);
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
    
    public void setWidth(int w) {
        if (this.sprite==null) return;
        this.sprite.setScaleWidth(w);
        this.sprite.setWidth(w);
    }
    
    public void setHeight(int h) {
        if (this.sprite==null) return;
        this.sprite.setScaleHeight(h);
        this.sprite.setHeight(h);
    }

    public int getWidth() {
        if (this.sprite==null) return 0;
        return this.sprite.getWidth();
    }

    public int getHeight() {
        if (this.sprite==null) return 0;
        return this.sprite.getHeight();
    }
    
    public void setBox(int x, int y, int width, int height) {
        this.setX(x);
        this.setY(y);
        this.setWidth(width);
        this.setHeight(height);
        if (this.collideEmbellishment) this.computeCollisonBox();
        if (this.sprite==null) return;
        if (this.sprite.getDrawables()==null) return;
        for (Drawable d : this.sprite.getDrawables()) {
            d.moveTo(this.getX()+this.sprite.getWidth()/2, this.getY()+this.sprite.getHeight()/2);
            d.setParent(this.parent);
            this.addDrawable(d);
        }
    }
    
    public void computeCollisonBox() {
        if (this.collisionboxlist==null) this.collisionboxlist = new ArrayList<>();
        this.collisionboxlist.clear();
        CollisionBox b = new CollisionBox(0,0, this.getWidth(), this.getHeight());
        b.apply(this.getX(), this.getY());
        this.collisionboxlist.add(b);
    }
    
    public void setCollisionBoxList(ArrayList<CollisionBox> list, int original_w, int original_h) {
        this.collisionboxlist.clear();
        if (list == null) return;
        int ws = this.getWidth();
        int hs = this.getHeight();
        for (CollisionBox box : list) {
            int x = box.getX()*((int)((double)ws/(double)original_w));
            int y = box.getY()*((int)((double)hs/(double)original_h));
            int w = box.getWidth()*((int)((double)ws/(double)original_w));
            int h = box.getHeight()*((int)((double)hs/(double)original_h));
            box.apply(this.getX(),this.getY());
            this.collisionboxlist.add(new CollisionBox(x,y,w,h,box.isDraw()));
        }
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
    
    /*public void copyLightFromSprite() {
        if (this.sprite==null) return;
        if (this.sprite.getDrawables()==null) return;
        for (Drawable d : this.sprite.getDrawables()) {
            if (d instanceof Light) {
                Light l = ((Light)d).copie();
                l.moveTo(this.getX()+this.sprite.getWidth()/2, this.getY()+this.sprite.getHeight()/2);
                l.setParent(this.parent);
                this.addDrawable(l);
            }
        }
    }*/
    
    @Override
    public void setParent(Drawable d) {
        this.parent = d;
        for (Drawable l : this.drawables) {
            l.setParent(d);
        }
    }
    
    @Override
    public Drawable getParent() {
        return this.parent;
    }
    
    
    public Embellishment copie() {
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(this);

            ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
            ObjectInputStream ois = new ObjectInputStream(bais);
            return (Embellishment) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            return null;
        }
    }
    
}
