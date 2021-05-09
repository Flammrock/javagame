
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

public abstract class Objet extends Element implements Ramassable, Generable, Detruisable, Reparable {
    
    String nom;
    double probaDeGeneration;
    double poids;
    int nbUtilisation; // -1 for infinite
    
    int zindex;
    Sprite sprite;
    String icon;
    
    Dispatcher dispatcher;
    
    int radiusramassable;
    
    Effet effet;
    
    double life;
    double maxlife;
    
    public Objet() {
        this.nom = "";
        this.probaDeGeneration = 1.0;
        this.poids = 0.0;
        this.nbUtilisation = 1;
        this.zindex = Integer.MIN_VALUE;
        this.icon = null;
        this.sprite = null;
        this.dispatcher = new Dispatcher();
        this.radiusramassable = 50;
        this.effet = null;
        this.life = 20;
        this.maxlife = 20;
    }
    
    public Objet(String nom, String description, double poids) {
        this.nom = nom;
        this.description = description;
        this.poids = poids;
        this.probaDeGeneration = 1.0;
        this.nbUtilisation = 1;
        this.zindex = Integer.MIN_VALUE;
        this.icon = null;
        this.sprite = null;
        this.dispatcher = new Dispatcher();
        this.radiusramassable = 50;
        this.effet = null;
        this.life = 20;
        this.maxlife = 20;
    }
    
    public void setEffet(Effet effet) {
        this.effet = effet;
    }
    
    public Effet getEffet() {
        return this.effet;
    }
    
    public void setSprite(Sprite s) {
        this.sprite = s.copie();
    }
    
    public Sprite getSprite() {
        return this.sprite;
    }
    
    public void setIcon(String icon) {
        this.icon = icon;
    }
    
    public String getIcon() {
        return this.icon;
    }
    
    public String effet(Personnage utilisateur, Element cible) {
        return "";
    }
    
    public String getNom() {
        return this.nom;
    }

    public String toString() {
        if (this.description.trim().equals("")) {
            return this.nom;
        }
        return this.nom + " (" + this.description + ")";
    }
    
    public boolean peutUtiliser() {
        return false;
    }

    public void mettreAJourUtiliser(Personnage p) {
        if (this.nbUtilisation == -1 || this.nbUtilisation > 0) {
            if (this.nbUtilisation != -1) {
                this.nbUtilisation--;
            }
        }
    }

    public double getPoids() {
        return poids;
    }
    
    public boolean onUtiliser(Personnage p) {
        return false;
    }

    public int nbUtilisationRestante() {
        return this.nbUtilisation;
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
    public int getRadiusRamassable() {
        return this.radiusramassable;
    }

    @Override
    public void setRadiusRamassable(int radius) {
        this.radiusramassable = radius;
    }

    @Override
    public boolean isRamassable(Personnage p) {
        if (this.sprite==null) return false;
        int dx = p.getX()+p.getWidth()/2-(this.getX()+this.getWidth()/2);
        int dy = p.getY()+p.getHeight()/2-(this.getY()+this.getHeight()/2);
        int r = this.getRadiusRamassable();
        return dx*dx + dy*dy <= r*r;
    }
    
    
    
    
    @Override
    public boolean isDetruisable() {
        return true;
    }

    @Override
    public double getLife() {
        return this.life;
    }

    @Override
    public void setLife(double l) {
        this.life = l;
    }

    @Override
    public double getMaxLife() {
        return this.maxlife;
    }

    @Override
    public void setMaxLife(double l) {
        this.maxlife = l;
    }

    @Override
    public boolean isReparable() {
        return false;
    }
}
