package modele.element;

import canvas.Canvas;
import canvas.Drawable;
import canvas.Sprite;
import canvas.TileSet;
import canvas.collision.CollisionBox;
import canvas.collision.CollisionEvent;
import canvas.collision.Collisionable;
import canvas.light.Light;
import embellishment.Embellishment;
import embellishment.TypeEmbellishment;
import eventsystem.Dispatcher;
import eventsystem.SimpleEvent;
import eventsystem.SimpleListener;
import geometry.Box;
import geometry.Enum_Direction;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;
import map.Generable;

public class Lieu extends Element implements Generable, Collisionable {
    
    String nom;
    double probaDeGeneration;

    List<Porte> listePorte;
    List<Objet> objets;
    List<Personnage> monstres;
    
    int x;
    int y;
    int width;
    int height;
    ArrayList<Drawable> drawables;
    
    ArrayList<CollisionBox> collisionBoxList;
    
    ArrayList<Embellishment> embellishmentsList;
    ArrayList<Embellishment> embellishmentsListDrawed;
    
    boolean isVisible;
    
    // un dispatcher d'events
    Dispatcher dispatcher;
    
    TileSet tileset;
    Sprite sprite_wall;
    Sprite sprite_ground;

    public String getNom() {
        return nom;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
    
    

    public List<Porte> getListePorte() {
        return listePorte;
    }
    
    public Porte getLastPorte() {
        return listePorte.get(listePorte.size()-1);
    }

    public void setListePorte(List<Porte> listePorte) {
        this.listePorte = listePorte;
    }

    public List<Objet> getObjets() {
        return objets;
    }

    public void setObjets(List<Objet> objets) {
        this.objets = objets;
    }

    public List<Personnage> getMonstres() {
        return monstres;
    }

    public void setMonstres(List<Personnage> monstres) {
        this.monstres = monstres;
    }


    /**
     * Chaque lieu doit avoir un nom unique
     * @param nom le nom du lieu
     */
    public Lieu(String nom) {
        this.nom = nom;
        this.listePorte = new ArrayList<>();
        this.objets = new ArrayList<>();
        this.monstres = new ArrayList<>();
        this.probaDeGeneration = 1.0;
        this.drawables = new ArrayList<>();
        this.collisionBoxList = new ArrayList<>();
        this.dispatcher = new Dispatcher();
        this.tileset = null;
        this.sprite_wall = null;
        this.isVisible = false;
        this.embellishmentsList = new ArrayList<>();
        this.embellishmentsListDrawed = new ArrayList<>();
    }

    public boolean isVisible() {
        return isVisible;
    }

    public void setVisible(boolean isVisible) {
        this.isVisible = isVisible;
    }
    
    
    
    /**
     * Permet de connecter deux lieux avec un porte
     *
     * @param nom le nom de la porte (attention le nom de la porte doit être unique)
     * @param lieu2 le lieu à connecter
     * @return retourne true si la porte a bien été ajouté, false sinon
     */
    public boolean ajoutePorteVers(String nom, Lieu lieu2) {
        
        // on regarde si une porte va au même endroit
        for (Porte p : this.listePorte) {
            if (p.getLieu1().getNom().equals(this.nom) && p.getLieu2().getNom().equals(lieu2.getNom())) return false;
            if (p.getLieu2().getNom().equals(this.nom) && p.getLieu1().getNom().equals(lieu2.getNom())) return false;
        }
        for (Porte p : lieu2.getListePorte()) {
            if (p.getLieu1().getNom().equals(this.nom) && p.getLieu2().getNom().equals(lieu2.getNom())) return false;
            if (p.getLieu2().getNom().equals(this.nom) && p.getLieu1().getNom().equals(lieu2.getNom())) return false;
        }

        Porte p1 = new Porte(nom, this, lieu2);
        Porte p2 = new Porte(nom, lieu2, this);
        
        p1.connect(p2);
        p2.connect(p1);
        
        Lieu _this = this;
        
        // on propage l'events de collisions dans le dispatcher de ce lieu
        p1.onCollide(new SimpleListener("onCollide"){
            @Override
            public void onEvent(Object sender, SimpleEvent event) {
                _this.getDispatcher().fireEvent("onCollide", _this, event);
            }
        });
        p2.onCollide(new SimpleListener("onCollide"){
            @Override
            public void onEvent(Object sender, SimpleEvent event) {
                _this.getDispatcher().fireEvent("onCollide", _this, event);
            }
        });
        

        this.listePorte.add(p1);
        this.addDrawable(p1);
        lieu2.getListePorte().add(p2);
        lieu2.addDrawable(p2);
        
        return true;
    }
    
    /**
     * Permet de récupérer une porte via son nom
     * @param nom le nom de la porte à récupérer
     * @return retourne la porte sinon retourne null
     */
    public Porte recupererPorte(String nom) {
        for (Porte p : this.listePorte) {
            if (p.getNom().equals(nom)) return p;
        }
        return null;
    }
    

    
    public String toString() {
        return this.nom;
    }
    
    public boolean ajouter(Element o) {
        if (o instanceof Objet) {
            this.objets.add((Objet)o.copie());
            return true;
        }
        if (o instanceof Personnage) {
            this.monstres.add((Personnage)o.copie());
            return true;
        }
        return false;
    }
    
    public boolean ajouterPlusieurs(ArrayList<Element> os) {
        for (Element o : os) {
            if (o instanceof Objet) {
                this.objets.add((Objet)o.copie());
            } else if (o instanceof Personnage) {
                this.monstres.add((Personnage)o.copie());
            }
        }
        return true;
    }
    
    public void enleverTout() {
        this.monstres.clear();
        this.objets.clear();
    }

    /*@Override
    public boolean generate(GenerableParametre s) {
        
        // on génère la taille ainsi que la position
        this.width = (int) (Math.random() * (500 - 200 + 1) + 200);
        this.height = (int) (Math.random() * (500 - 200 + 1) + 200);
        this.x = 200;
        this.y = 200;
        
        int epaisseur = 10;
        
        // on ajoute 4 collisionsBox
        this.collisionBoxList.clear();
        this.addCollisionBox(new CollisionBox(-epaisseur,-epaisseur,width+epaisseur*2,epaisseur*2,true));
        this.addCollisionBox(new CollisionBox(-epaisseur,-epaisseur+height,width+epaisseur*2,epaisseur*2,true));
        this.addCollisionBox(new CollisionBox(-epaisseur,-epaisseur,epaisseur*2,epaisseur*2+height,true));
        this.addCollisionBox(new CollisionBox(-epaisseur+width,-epaisseur,epaisseur*2,epaisseur*2+height,true));
        
        // on cast pour récupérer les paramètres
        GenerableLieuParametre p = (GenerableLieuParametre)s;
        
        // on vide le lieu
        this.enleverTout();
        
        // on récupère les Generable
        ArrayList<Generable> gs = p.getGenerables();
        
        // on itère sur tout les Generables et on ajoute
        for (Generable g : gs) {
            if (g instanceof Element) {
                double r = Math.random();
                if (r < g.getProbabilite()) {
                    this.ajouter((Element) g);
                }
            }
        }

        // la génération s'est bien passé
        return true;
        
    }*/
    
    public void setSize(Box b) {
        x = b.getX();
        y = b.getY();
        width = b.getWidth();
        height = b.getHeight();
    }

    @Override
    public double getProbabilite() {
        return this.probaDeGeneration;
    }

    @Override
    public void setProbabilite(double proba) {
        this.probaDeGeneration = proba;
    }
    
    @Override
    public void draw(Canvas c, Graphics g) {
        
        
        
        
        if (this.sprite_ground != null) {
            this.sprite_ground.draw(c, g);
        }
        
        if (this.sprite_wall != null) {
            this.sprite_wall.draw(c, g);
        }
        
        if (this.sprite_wall != null) {
            Sprite temp = tileset.getSprite("ground");
            if (temp != null) {
                for (Porte p : this.listePorte) {
                    if (p.getDirection().isUp()) {
                        g.setColor(c.getColor());
                        g.fillRect(c.toWorldX(p.getX()), c.toWorldY(this.y), c.toScale(p.getWidth()), c.toScale(this.sprite_wall.getHeight()));
                        temp.setX(p.getX());
                        temp.setY(this.y+this.sprite_wall.getHeight()-20);
                        temp.setWidth(p.getWidth());
                        temp.setHeight(20);
                        temp.setScaleSize(p.getWidth(),20);
                        temp.draw(c, g);
                    } else if (p.getDirection().isRight()) {
                        temp.setX(p.getX()-50);
                        temp.setY(p.getY());
                        temp.setWidth(p.getWidth());
                        temp.setHeight(p.getHeight());
                        temp.setScaleSize(p.getWidth(),p.getHeight());
                        temp.draw(c, g);
                    } else if (p.getDirection().isDown()) {
                        temp.setX(p.getX());
                        temp.setY(p.getY()-50);
                        temp.setWidth(p.getWidth());
                        temp.setHeight(p.getHeight());
                        temp.setScaleSize(p.getWidth(),p.getHeight());
                        temp.draw(c, g);
                    } else if (p.getDirection().isLeft()) {
                        temp.setX(p.getX()+50);
                        temp.setY(p.getY());
                        temp.setWidth(p.getWidth());
                        temp.setHeight(p.getHeight());
                        temp.setScaleSize(p.getWidth(),p.getHeight());
                        temp.draw(c, g);
                    }
                }
            }
        }
        
        int dh = this.sprite_wall == null ? 0 : this.sprite_wall.getHeight();
        //Graphics2D g2 = (Graphics2D)g.create();
        //g2.clipRect(c.toWorldX(x), c.toWorldY(y+dh), c.toScale(width), c.toScale(height-dh));
        for (Embellishment e : this.embellishmentsListDrawed) {
            e.draw(c, g);
        }
        //g2.dispose();
        //g.setClip(null);
        
        //g.setColor(Color.orange);
        //g.drawRect(c.toWorldX(this.x), c.toWorldY(this.y), c.toScale(this.width), c.toScale(this.height));
        
        
    }
    
    public void computeCollisonBox() {
    
        int epaisseur = 10;
        boolean display = false;
        
        // on ajoute 4 collisionsBox
        this.collisionBoxList.clear();
        
        int dh = this.sprite_wall == null ? 0 : this.sprite_wall.getHeight()-epaisseur*2-12;
        
        
        Porte up = null;
        Porte right = null;
        Porte down = null;
        Porte left = null;
        
        for (Porte p : this.listePorte) {
            if (p.getDirection().isUp()) {
                up = p;
            } else if (p.getDirection().isRight()) {
                right = p;
            } else if (p.getDirection().isDown()) {
                down = p;
            } else if (p.getDirection().isLeft()) {
                left = p;
            }
        }
        
        // top
        if (up==null) {
            this.addCollisionBox(new CollisionBox(-epaisseur,-epaisseur+dh,width+epaisseur*2,epaisseur*2,display));
        } else {
            this.addCollisionBox(new CollisionBox(
                    -epaisseur,
                    -epaisseur+dh,
                    up.getX()-x+epaisseur,
                    epaisseur*2,
                    display
            ));
            this.addCollisionBox(new CollisionBox(
                    up.getX()-x+up.getWidth(),
                    -epaisseur+dh,
                    x+width-(up.getX()+up.getWidth()),
                    epaisseur*2,
                    display
            ));
        }
        
        // bottom
        if (down == null) {
            this.addCollisionBox(new CollisionBox(-epaisseur,height,width+epaisseur*2,epaisseur*2,display));
        } else {
            this.addCollisionBox(new CollisionBox(
                    -epaisseur,
                    height,
                    down.getX()-x+epaisseur,
                    epaisseur*2,
                    display
            ));
            this.addCollisionBox(new CollisionBox(
                    down.getX()-x+down.getWidth(),
                    height,
                    x+width-(down.getX()+down.getWidth()),
                    epaisseur*2,
                    display
            ));
        }
        
        // left
        if (left == null) {
            this.addCollisionBox(new CollisionBox(-epaisseur*2,-epaisseur,epaisseur*2,epaisseur*2+height,display));
        } else {
            this.addCollisionBox(new CollisionBox(
                    -epaisseur*2,
                    -epaisseur,
                    epaisseur*2,
                    left.getY()-y+epaisseur,
                    display
            ));
            this.addCollisionBox(new CollisionBox(
                    -epaisseur*2,
                    left.getY()-y+left.getHeight(),
                    epaisseur*2,
                    y+height-(left.getY()+left.getHeight()),
                    display
            ));
        }
        
        // right
        if (right == null) {
            this.addCollisionBox(new CollisionBox(width,-epaisseur,epaisseur*2,epaisseur*2+height,display));
        } else {
            this.addCollisionBox(new CollisionBox(
                    width,
                    -epaisseur,
                    epaisseur*2,
                    right.getY()-y+epaisseur,
                    display
            ));
            this.addCollisionBox(new CollisionBox(
                    width,
                    right.getY()-y+right.getHeight(),
                    epaisseur*2,
                    y+height-(right.getY()+right.getHeight()),
                    display
            ));
        }
    
    }
    
    @Override
    public ArrayList<Drawable> getDrawables() {
        return this.drawables;
    }
    
    public void addDrawable(Drawable d) {
        this.drawables.add(d);
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
    public int getNewX() {
        return this.x;
    }

    @Override
    public int getNewY() {
         return this.y;
    }

    @Override
    public void applyMove() {
        
    }

    @Override
    public void cancelMove() {
        
    }

    @Override
    public boolean generate(Object p) {
        
        this.computeCollisonBox();
        
        int s = 200;
        this.drawables.add(new Light(this.x + s/2 + (int)(Math.random()*(this.width-s/2)),this.y + s/2 + (int)(Math.random()*(this.height-s/2)),s));
        
        if (this.embellishmentsList.isEmpty()) return true;
        
        // on génère des éléments de décors un peu à la random de tel sorte qu'il n'y est pas de collisions
        ArrayList<Embellishment> list = new ArrayList<>();
        
        double proba = 1;
        
        int dh = this.sprite_wall == null ? 0 : this.sprite_wall.getHeight();
        
        while (Math.random() < proba) {
            
            Embellishment temp = this.embellishmentsList.get((int)(Math.random()*this.embellishmentsList.size()));
            Embellishment e = new Embellishment(temp);
            
            // en fonction du type, on le place différemment
            if (e.getType().equals(TypeEmbellishment.GROUND)) {
                int w = (int)(Math.random()*(100-50+1)+50);
                int h = (int)(w*(1.0/e.getSprite().getRatio()));
                int x = this.x + (int)(Math.random()*(this.width-w));
                int y = this.y + dh + (int)(Math.random()*(this.height-dh-h));
                e.setBox(x,y,w,h);
            } else if (e.getType().equals(TypeEmbellishment.WALL)) {
                int w = 50;
                int h = (int)(w*(1.0/e.getSprite().getRatio()));
                int x = this.x + (int)(Math.random()*(this.width-w));
                int y = this.y + dh - h;
                e.setBox(x,y,w,h);
            }
            
            
            
            Boolean r = false;
            for (Porte porte : this.listePorte) {
                r = porte.isCollide(e);
                if (r!=null && r) break;
            }
            if (r!=null && r) {proba -= 0.01;continue;}
            for (Embellishment et : this.embellishmentsListDrawed) {
                r = et.isCollide(e);
                if (r!=null && r) break;
            }
            if (r!=null && r) {proba -= 0.01;continue;}
            
            proba -= 0.1;
            
            this.embellishmentsListDrawed.add(e);
            
            
            
        }
        
        return true;
    }
    
    @Override
    public Dispatcher getDispatcher() {
        return this.dispatcher;
    }

    @Override
    public void collide(Collisionable c) {
        // rien ne se passe
        //dispatcher.fireEvent("onCollide", this, new CollisionEvent(this,c));
    }

    @Override
    public void onCollide(SimpleListener l) {
        l.setType("onCollide"); // on force le type
        this.dispatcher.addListener(l);
    }

    public void setTileSet(TileSet tileset) {
        this.tileset = tileset;
        if (tileset != null) {
            this.sprite_wall = tileset.getSprite("wall");
            if (this.sprite_wall != null) {
                this.sprite_wall.setX(this.x);
                this.sprite_wall.setY(this.y);
                this.sprite_wall.setWidth(this.width);
                this.sprite_wall.setScaleSize(180, null);
                this.sprite_wall.setHeight(this.sprite_wall.getScaleHeight());
            }
            
            
            this.sprite_ground = tileset.getSprite("ground");
            if (this.sprite_ground != null) {
                int dh = this.sprite_wall == null ? 0 : this.sprite_wall.getHeight();
                this.sprite_ground.setX(this.x);
                this.sprite_ground.setY(this.y+dh);
                this.sprite_ground.setWidth(this.width);
                this.sprite_ground.setHeight(this.height-dh);
                this.sprite_ground.setScaleSize(this.width, this.height-dh);
            }
        }
    }
    
    
    public void addEmbellishment(Embellishment e) {
        this.embellishmentsList.add(e);
    }
    
    public void setEmbellishments(ArrayList<Embellishment> l) {
        this.embellishmentsList = l;
    }
    
    public ArrayList<Embellishment> getEmbellishments() {
        return this.embellishmentsList;
    }
    
    
    @Override
    public boolean isDraw() {
        return this.isVisible;
    }

}
