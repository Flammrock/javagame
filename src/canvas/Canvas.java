/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package canvas;

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
    
    public Canvas() {
        super();
        this.itemsdrawable = new ArrayList<>();
        this.keyMap = new HashMap<>();
    }
    
    public void ajouterItem(Drawable item) {
        this.itemsdrawable.add(item);
    }
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        //Graphics2D graphic2d = (Graphics2D) g;
        //graphic2d.setColor(Color.BLUE);
        //graphic2d.fillRect(230, 110, 60, 80);
        
        //Sprite s = new Sprite("/spritetest.png",-100,-100);
        //s.load();
        //s.draw(this, g);
        
        // générer la liste de les items
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
                    pile.addAll(itemss);
                }
            }
        }
        
        for (Drawable item : items) {
            
            if (item instanceof Collisionable) {
                
                Collisionable c1 = (Collisionable) item;
                
                boolean canMove = true;
                
                for (Drawable item2 : items) {
                    if (item2 instanceof Collisionable) {
                        
                        if (item != item2) {
                            
                            Collisionable c2 = (Collisionable) item2;
                            
                            c1.getCollisionBox().apply(item.getX(),item.getY());
                            c2.getCollisionBox().apply(item2.getX(),item2.getY());
                            
                            System.out.println(c1.getCollisionBox());
                            System.out.println(c2.getCollisionBox());
                            
                            if (c1.getCollisionBox().isCollide(c2.getCollisionBox())) {
                                canMove = false;
                                break;
                            }
                            
                        }
                        
                    }
                }
                
                if (canMove) {
                   item.applyMove();
                } else {
                    item.cancelMove();
                }
                
                
            }
        }
        
        
        for (Drawable item : items) {
            item.draw(this,g);
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
