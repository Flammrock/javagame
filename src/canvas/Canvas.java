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
                    pile.addAll(itemss);
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
                            
                            // on place les boites de collisions aux nouvelles coordonnées à tester
                            c1.getCollisionBox().apply(c1.getNewX(),c1.getNewY());
                            c2.getCollisionBox().apply(c2.getNewX(),c2.getNewY());
                            
                            // on check s'il y a une collision
                            if (c1.getCollisionBox().isCollide(c2.getCollisionBox())) {
                                
                                // si oui, alors cet item ne peut pas bouger, on peut quitter cette boucle
                                canMove = false;
                                break;
                                
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
