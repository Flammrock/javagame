/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package canvas;

import canvas.collision.CollisionBox;
import canvas.collision.Collisionable;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import javax.swing.JFrame;
import javax.swing.JPanel;
import modele.element.Element;

/**
 *
 * @author User
 */
public class Canvas extends JPanel {
    
    ArrayList<Drawable> itemsdrawable;
    HashMap<Integer, Boolean> keyMap;
    
    double scale;
    
    // world position
    int wx;
    int wy;
    
    // screen position
    int sx;
    int sy;
    
    // temp position
    int tx;
    int ty;
    
    public Canvas() {
        super();
        this.itemsdrawable = new ArrayList<>();
        this.keyMap = new HashMap<>();
        scale = 1;
        wx = 0;
        wy = 0;
        sx = this.getWidth()/2;
        sy = this.getHeight()/2;
        tx = 0;
        ty = 0;
    }
    
    public void ajouterItem(Drawable item) {
        this.itemsdrawable.add(item);
    }
    
    
    
    
    public int getTempX() {
        return tx;
    }
    
    public int getTempY() {
        return ty;
    }
    
    public void setTempX(int tx) {
        this.tx = tx;
    }
    
    public void setTempY(int ty) {
        this.ty = ty;
    }
    
    
    
    
    public int getWorldX() {
        return wx;
    }
    
    public int getWorldY() {
        return wy;
    }
    
    public void setWorldX(int wx) {
        this.wx = wx;
    }
    
    public void setWorldY(int wy) {
        this.wy = wy;
    }
    
    public int getScreenX() {
        return wx;
    }
    
    public int getScreenY() {
        return wy;
    }
    
    public void setScreenX(int sx) {
        this.sx = sx;
    }
    
    public void setScreenY(int sy) {
        this.sy = sy;
    }
    
    
    public int toWorldX(int x) {
        return (int)(((double)(x-wx))*scale + (double)sx)+this.getWidth()/2;
    }
    
    public int toWorldY(int y) {
        return (int)(((double)(y-wy))*scale + (double)sy)+this.getHeight()/2;
    }
    
    
    public double getScale() {
        return scale;
    }
    
    public int toScale(int v) {
        return (int)(v*scale);
    }
    
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        // générer la liste de les items en O(n)
        ArrayList<Drawable> items = new ArrayList<>();
        ArrayDeque<Drawable> pile = new ArrayDeque<>();
        for (Drawable item : this.itemsdrawable) {
            if (!items.contains(item)) {
                pile.add(item);
            }
        }
        while (!pile.isEmpty()) {
            Drawable item = pile.removeFirst();
            //Element h = (Element) item;
            //System.out.println(h.getDescription());
            if (!items.contains(item)) {
                items.add(item);
                ArrayList<Drawable> itemss = item.getDrawables();
                if (itemss != null) {
                    for (Drawable dt : itemss) {
                        if (dt.isDraw()) {
                            pile.add(dt);
                        }
                    }
                    pile.addAll(itemss);
                }
                if (item instanceof Collisionable) {
                    Collisionable b = (Collisionable)item;
                    ArrayList<CollisionBox> itemss2 = b.getCollisionBoxList();
                    if (itemss2 != null) {
                        for (CollisionBox bt : itemss2) {
                            if (bt.isDraw()) {
                                pile.add(bt);
                            }
                        }
                    }
                }
            }
        }
        
        
        // en O(n*n)
        for (Drawable item : items) {
            
            // on veut vérifier uniquement les items qui sont "collisionable"
            if (item instanceof Collisionable) {
                
                // on cast en "collisionable"
                Collisionable c1 = (Collisionable) item;
                
                // petite variable temporaire pour savoir si cet item "peut bouger"
                boolean canMove = true;
                
                // on vérifie cet item avec tout les autres items
                for (Drawable item2 : items) {
                    
                    // pour la même raison que précédemment, on vérifie que pour les "collisionable"
                    if (item2 instanceof Collisionable) {
                        
                        // les objets doivent être différents (pas la même adresse)
                        // (un item est toujours en collision avec lui-même)
                        if (item != item2) {
                            
                            // on cast le 2ème item
                            Collisionable c2 = (Collisionable) item2;
                            
                            // on récupère les ensembles de box
                            ArrayList<CollisionBox> list1 = c1.getCollisionBoxList();
                            ArrayList<CollisionBox> list2 = c2.getCollisionBoxList();
                            
                            // on les places au bon endroit pour le test
                            for (CollisionBox b : list1) b.apply(c1.getNewX(),c1.getNewY());
                            for (CollisionBox b : list2) b.apply(c2.getNewX(),c2.getNewY());
                            
                            // on check s'il y a une collision
                            for (CollisionBox b1 : list1) {
                                
                                for (CollisionBox b2 : list2) {
                                
                                    if (b1.isCollide(b2)) {
                                
                                        // si oui, alors cet item ne peut pas bouger, on peut quitter cette boucle
                                        canMove = false;
                                        break;

                                    }
                                
                                }
                                
                            }
                            
                            
                        }
                        
                    }
                }
                
                // on applique le mouvement si on peut bouger (i.e. aucune collision)
                // sinon on annule le mouvement
                if (canMove) {
                    c1.applyMove();
                } else {
                    c1.cancelMove();
                }
                
                
            }
        }
        
        // en O(n)
        for (Drawable item : items) {
            item.draw(this,g);
        }
        
        if (this.isAppuyer(90)) {
            tx = (int)((tx-sx) / scale + wx);
            ty = (int)((ty-sy) / scale + wy);
            this.scale = Math.min(5, this.scale*1.1);
            /*wx = (int)((double)(tx - sx) * (1 / scale)) + wx;
            wy = (int)((double)(ty - sy) * (1 / scale)) + wy;
            sx = tx;
            sy = ty;*/
        }
        if (this.isAppuyer(65)) {
            tx = (int)((tx-sx) / scale + wx);
            ty = (int)((ty-sy) / scale + wy);
            this.scale = Math.max(0.01, this.scale/1.1);
            /*wx = (int)((double)(tx - sx) * (1 / scale)) + wx;
            wy = (int)((double)(ty - sy) * (1 / scale)) + wy;
            sx = tx;
            sy = ty;*/
        }
        
    }
    
    public boolean isAppuyer(int keycode) {
        if (!this.keyMap.containsKey(keycode)) return false;
        return Objects.equals(this.keyMap.get(keycode), Boolean.TRUE);
    }

    public void onAppuiTouche(KeyEvent e) {
        this.keyMap.put(e.getKeyCode(), Boolean.TRUE);
    }
    
    public void onRelacheTouche(KeyEvent e) {
        this.keyMap.put(e.getKeyCode(), Boolean.FALSE);
    }
    

}
