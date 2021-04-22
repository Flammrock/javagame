/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package canvas;

import canvas.collision.CollisionBox;
import canvas.collision.Collisionable;
import canvas.light.Light;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.geom.*;
import java.awt.image.BufferedImage;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import javax.swing.JFrame;
import javax.swing.JPanel;
import map.Camera;
import modele.element.Element;
import modele.element.Personnage;

/**
 *
 * @author User
 */
public class Canvas extends JPanel {
    
    ArrayList<Drawable> itemsdrawable;
    HashMap<Integer, Boolean> keyMap;
    
    BufferedImage buffImg;
    BufferedImage buffImg2;
    Graphics2D gbi;
    Graphics2D gbi2;
    
    Color background;
    
    // caméra courante
    Camera camera;
    
    public Canvas() {
        super();
        this.itemsdrawable = new ArrayList<>();
        this.keyMap = new HashMap<>();
        this.camera = new Camera();
        this.background = Color.black;
        
        this.buffImg = null;
        this.buffImg2 = null;
        this.gbi = null;
        this.gbi2 = null;
    }
    
    public void setColor(Color c) {
        this.setOpaque(true);
        this.setBackground(c);
        background = c;
    }
    
    public Color getColor() {
        return background;
    }
    
    public void ajouterItem(Drawable item) {
        this.itemsdrawable.add(item);
    }
    
    
    public void setCamera(Camera camera) {
        if (camera!=null) {
            this.camera = camera;
        }
    }
    
    
    public int getWorldX() {
        return camera.getWx();
    }
    
    public int getWorldY() {
        return camera.getWy();
    }
    
    public void setWorldX(int wx) {
        camera.setWx(wx);
    }
    
    public void setWorldY(int wy) {
        camera.setWy(wy);
    }
    
    public int getScreenX() {
        return camera.getSx();
    }
    
    public int getScreenY() {
        return camera.getSy();
    }
    
    public void setScreenX(int sx) {
        camera.setSx(sx);
    }
    
    public void setScreenY(int sy) {
        camera.setSy(sy);
    }
    
    
    public int toWorldX(int x) {
        return (int)(((double)(x-this.getWorldX()))*camera.getScale() + (double)this.getScreenX());
    }
    
    public int toWorldY(int y) {
        return (int)(((double)(y-this.getWorldY()))*camera.getScale() + (double)this.getScreenY());
    }
    
    
    public double getScale() {
        return camera.getScale();
    }
    
    public int toScale(int v) {
        return (int)(v*this.getScale());
    }
    
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        // générer la liste de les items en O(n)
        ArrayList<Light> lights = new ArrayList<>();
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
            if (item instanceof Light) {
                if (!lights.contains((Light)item)) {
                    lights.add((Light)item);
                }
                continue;
            }
            if (!items.contains(item)) {
                items.add(item);
                ArrayList<Drawable> itemss = item.getDrawables();
                if (itemss != null) {
                    for (Drawable dt : itemss) {
                        if (dt.isDraw()) {
                            pile.add(dt);
                        }
                    }
                    //pile.addAll(itemss);
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
                            
                            Boolean r = c1.isCollide(c2);
                            if (r == null) continue;
                            
                            if (r) canMove = false;
                            
                            
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
        
        // en O(nlog(n))
        Collections.sort(items, new Comparator<Drawable>() {
            public int compare(Drawable a, Drawable b) {
                return b.getY() < a.getY() ? 1 : b.getY() > a.getY() ? -1 : 0;
            }
        });
        
        
        Dimension d = getSize();
        int w = d.width;
        int h = d.height; 
        
        Graphics2D g2d = (Graphics2D) g;
        
        //if (this.buffImg == null || this.buffImg.getWidth() != w || this.buffImg.getHeight() != h) {
            this.buffImg = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
            this.buffImg2 = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
            this.gbi = this.buffImg.createGraphics();
            this.gbi2 = this.buffImg2.createGraphics();
        //}
        

        // Clears the previously drawn image.
        //g2d.setColor(this.getColor());
        //g2d.fillRect(0, 0, d.width, d.height);
        gbi.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));
        
        for (Light light : lights) {
            light.draw(this,gbi);
        }
        
        
        for (Drawable item : items) {
            item.draw(this,gbi2);
        }
        
        // on dessine l'obscurité ambiante
        gbi.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3f));
        gbi.drawImage(buffImg2, 0, 0, null);
        
        // on dessine les lights
        gbi.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_IN, 1.0f));
        gbi.drawImage(buffImg2, 0, 0, null);
        
        g2d.drawImage(buffImg, 0, 0, null);
        
        
        if (this.isAppuyer(90)) {
            camera.zoomIn(1.1);
        }
        if (this.isAppuyer(65)) {
            camera.zoomOut(1.1);
        }
        
        camera.update(this);
        
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
