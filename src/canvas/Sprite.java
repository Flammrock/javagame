/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package canvas;

import canvas.collision.CollisionBox;
import canvas.collision.CollisionEvent;
import canvas.collision.Collisionable;
import eventsystem.Dispatcher;
import eventsystem.SimpleListener;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.function.Consumer;
import javax.imageio.ImageIO;
import modele.element.Element;

/**
 *
 * @author User
 */
public class Sprite implements Collisionable {
    
    // l'image une fois chargé
    static public HashMap<String,BufferedImage> images = new HashMap<>();
    
    // les coordonnées du sprite (Destination)
    int x;
    int y;
    int width;
    int height;
    
    // les coordonnées du sprite (Source)
    int sx;
    int sy;
    int swidth;
    int sheight;
    
    // les nouvelles coordonnées
    int mx;
    int my;
    
    
    boolean repeatX;
    boolean repeatY;
    
    // taille de scale
    int scalewidth;
    int scaleheight;
    
    
    // si le sprite a définit les nouvelles coordonnées mx et my
    boolean want2Move;
    
    // le nom du fichier de l'image
    String spritefile;
    
    // event ondraw
    Consumer<Canvas> ondraw;
    
    // un dispatcher d'events
    Dispatcher dispatcher;
    
    
    ArrayList<CollisionBox> collisionBoxList;
    ArrayList<Drawable> drawables;
    
    /**
     * Permet de créer un Sprite à partir d'un fichier image
     * @param spritefile le nom du fichier
     */
    public Sprite(String spritefile) {
        this.spritefile = spritefile;
        this.x = 0;
        this.y = 0;
        this.mx = 0;
        this.my = 0;
        this.width = -1;
        this.height = -1;
        this.sx = -1;
        this.sy = -1;
        this.swidth = -1;
        this.sheight = -1;
        this.scalewidth = -1;
        this.scaleheight = -1;
        this.ondraw = null;
        this.want2Move = false;
        this.repeatX = true;
        this.repeatY = true;
        this.dispatcher = new Dispatcher();
        this.collisionBoxList = new ArrayList<>();
        this.drawables = new ArrayList<>();
    }
    
    /**
     * Permet de créer un Sprite à partir d'un fichier image
     * @param spritefile le nom du fichier
     * @param x
     * @param y
     */
    public Sprite(String spritefile, int x, int y) {
        this(spritefile);
        this.x = x;
        this.y = y;
    }
    
    /**
     * Permet de créer un Sprite à partir d'un fichier image
     * @param spritefile le nom du fichier
     * @param x
     * @param y
     * @param width
     * @param height
     */
    public Sprite(String spritefile, int x, int y, int width, int height) {
        this(spritefile,x,y);
        this.width = width;
        this.height = height;
        this.scalewidth = width;
        this.scaleheight = height;
    }

    /**
     * Comme demander par l'interface Collisionable, getNewX() renvoie la nouvelle coordonnée X
     * qui est stocké ici dans this.mx
     * @return retourne la nouvelle coordonnée X
     */
    @Override
    public int getNewX() {
        
        // si moveTo() ou moveBy() a été appelé alors this.want2Move vaut true
        // dans ce cas, this.mx contient la nouvelle coordonnée X définit par l'appel
        // de moveTo() ou moveBy()
        if (this.want2Move) {
            return mx;
            
        // sinon on renvoie l'ancienne position
        // (on ignore en quelques sortes l'appel de moveTo() et de moveBy())
        } else {
            return x;
        }
        
    }

    /**
     * Comme demander par l'interface Collisionable, getNewX() renvoie la nouvelle coordonnée Y
     * qui est stocké ici dans this.my
     * @return retourne la nouvelle coordonnée Y
     */
    @Override
    public int getNewY() {
        
        // si moveTo() ou moveBy() a été appelé alors this.want2Move vaut true
        // dans ce cas, this.my contient la nouvelle coordonnée Y définit par l'appel
        // de moveTo() ou moveBy()
        if (this.want2Move) {
            return my;
            
        // sinon on renvoie l'ancienne position
        // (on ignore en quelques sortes l'appel de moveTo() et de moveBy())
        } else {
            return y;
        }
    }

    public boolean isRepeatX() {
        return repeatX;
    }

    public void setRepeatX(boolean repeatX) {
        this.repeatX = repeatX;
    }

    public boolean isRepeatY() {
        return repeatY;
    }

    public void setRepeatY(boolean repeatY) {
        this.repeatY = repeatY;
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

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
    
    
    
    @Override
    public void moveTo(int x, int y) {
        this.mx = x;
        this.my = y;
        this.want2Move = true;
    }
    
    @Override
    public void moveBy(int x, int y) {
        this.mx = this.x + x;
        this.my = this.y + y;
        this.want2Move = true;
    }
    
    @Override
    public void cancelMove() {
        this.mx = 0;
        this.my = 0;
        this.want2Move = false;
    }
    
    @Override
    public void applyMove() {
        if (this.want2Move) {
            this.want2Move = false;
            this.x = this.mx;
            this.y = this.my;
            this.mx = 0;
            this.my = 0;
        }
    }
    
    public boolean isLoaded() {
        if (!Sprite.images.containsKey(this.spritefile)) return false;
        if (Sprite.images.get(this.spritefile)==null) return false;
        return true;
    }
    
    public boolean loadImage() {
        if (this.isLoaded()) return true;
        try {
            Sprite.images.put(this.spritefile,ImageIO.read(getClass().getResourceAsStream(this.spritefile)));
            return true;
        } catch (IOException e) {}
        return false;
    }
    
    public double getRatio() {
        if (!this.isLoaded()) return 1.0;
        BufferedImage image = this.getImage();
        int nsw = image.getWidth();
        int nsh = image.getHeight();
        if (this.swidth >= 0) nsw = this.swidth;
        if (this.sheight >= 0) nsh = this.sheight;
        return (double)nsw / (double)nsh;
    }

    @Override
    public void draw(Canvas c, Graphics g) {
        if (!this.isLoaded()) return;
        //g.drawImage(this.image, this.x, this.y, null);
        
        BufferedImage image = this.getImage();
        
        int nsx = 0;
        int nsy = 0;
        int nsw = image.getWidth();
        int nsh = image.getHeight();
        
        if (this.sx >= 0) nsx = this.sx;
        if (this.sy >= 0) nsy = this.sy;
        if (this.swidth >= 0) nsw = this.swidth;
        if (this.sheight >= 0) nsh = this.sheight;
        
        double ratio = (double)nsw / (double)nsh;
        
        int ww = (this.width < 0 ? image.getWidth() : this.width);
        int hh = (this.height < 0 ? image.getHeight() : this.height);
        
        int w2 = this.scalewidth < 0 ? nsw : this.scalewidth;
        int h2 = this.scaleheight < 0 ? nsh : this.scaleheight;
        /*if ((int)(hh * (1.0/ratio)) > ww) {
            h2 = (int)(ww * (1.0/ratio));
        } else {
            w2 = (int)(hh * ratio);
        }*/
        
        //g.setColor(Color.cyan);
        //g.drawRect(c.toWorldX(x), c.toWorldY(y), c.toScale(ww), c.toScale(hh));
        
        
        int w = this.x + w2;
        int h = this.y + h2;
        
        int xi = 0;
        int yi = 0;
        
        int cutw = 0;
        int cuth = 0;
        
        while (true) {
            
            // on coupe si ça dépasse
            if (xi + w2 > ww) cutw = xi + w2 - ww;
            else cutw = 0;
            if (yi + h2 > hh) cuth = yi + h2 - hh;
            else cuth = 0;
            int cutwp = (int)((double)cutw * (double)nsw / (double)w2);
            int cuthp = (int)((double)cuth * (double)nsh / (double)h2);
            
            // on dessine
            g.drawImage(
                    image,

                    // dest
                    c.toWorldX(this.x+xi),
                    c.toWorldY(this.y+yi),
                    c.toWorldX(w+xi-cutw),
                    c.toWorldY(h+yi-cuth),

                    // src
                    nsx,
                    nsy,
                    nsx+nsw-cutwp,
                    nsy+nsh-cuthp,

                    null
            );
            
            // on calcul les nouveaux indices
            xi += w2;
            if (xi >= ww) {
                yi += h2;
            }
            if (xi >= ww && yi >= hh) break;
            if (!this.repeatX && !this.repeatY) break;
            if (xi >= ww) xi = 0;
        }
        
        if (this.ondraw!=null) this.ondraw.accept(c);
    }
    
    public void setOnDraw(Consumer<Canvas> fn) {
        this.ondraw = fn;
    }
    
    public void addDrawable(Drawable d) {
        this.drawables.add(d);
    }

    @Override
    public ArrayList<Drawable> getDrawables() {
        return this.drawables;
    }

    @Override
    public ArrayList<CollisionBox> getCollisionBoxList() {
         return this.collisionBoxList;
    }

    @Override
    public void addCollisionBox(CollisionBox b) {
        this.collisionBoxList.add(b);
    }

    @Override
    public boolean isDraw() {
        return true;
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

    public void setImage(BufferedImage image) {
        if (image == null) return;
        if (Sprite.images.containsValue(image)) {
            for (Entry<String, BufferedImage> entry : Sprite.images.entrySet()) {
                if (entry.getValue().equals(image)) {
                    this.spritefile = entry.getKey();
                    break;
                }
            }
        }
    }
    
    public void setImage(String spritefile) {
        this.spritefile = spritefile;
        this.loadImage();
    }

    public void setSource(int sx, int sy, int swidth, int sheight) {
        this.sx = sx;
        this.sy = sy;
        this.swidth = swidth;
        this.sheight = sheight;
    }
    
    public void setScaleSize(Integer w, Integer h) {
        if (w == null && h == null) return;
        if (w == null && this.isLoaded()) {
            int nsw = this.getImage().getWidth();
            int nsh = this.getImage().getHeight();
            if (this.swidth >= 0) nsw = this.swidth;
            if (this.sheight >= 0) nsh = this.sheight;
            this.scalewidth = (int)((double)h * (double)nsw / (double)nsh);
        } else {
            this.scalewidth = w;
        }
        if (h == null && this.isLoaded()) {
            int nsw = this.getImage().getWidth();
            int nsh = this.getImage().getHeight();
            if (this.swidth >= 0) nsw = this.swidth;
            if (this.sheight >= 0) nsh = this.sheight;
            this.scaleheight = (int)((double)w * (double)nsh / (double)nsw);
        } else {
            this.scaleheight = h;
        }
    }
    
    public void setScaleWidth(int w) {
        this.scalewidth = w;
    }
    
    public void setScaleHeight(int h) {
        this.scaleheight = h;
    }
    
    public int getScaleWidth() {
        return this.scalewidth;
    }
    
    public int getScaleHeight() {
        return this.scaleheight;
    }

    public String getFileName() {
        return this.spritefile;
    }
    
    public int getSourceX() {
        return this.sx;
    }
    
    public int getSourceY() {
        return this.sy;
    }
    
    public int getSourceWidth() {
        return this.swidth;
    }
    
    public int getSourceHeight() {
        return this.sheight;
    }

    public BufferedImage getImage() {
        return Sprite.images.get(this.spritefile);
    }
    
    
    public Sprite copie() {
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(this);

            ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
            ObjectInputStream ois = new ObjectInputStream(bais);
            return (Sprite) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            return null;
        }
    }
    
}
