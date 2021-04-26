package modele.element;

import canvas.Canvas;
import canvas.Drawable;
import canvas.Sprite;
import canvas.SpriteSheet;
import canvas.TileSet;
import canvas.collision.CollisionBox;
import canvas.collision.Collisionable;
import embellishment.Embellishment;
import embellishment.TypeEmbellishment;
import eventsystem.Dispatcher;
import eventsystem.SimpleEvent;
import eventsystem.SimpleListener;
import geometry.Box;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.Path2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;
import map.Generable;
import map.GenerateListener;

public class Lieu extends Element implements Generable, Collisionable {
    
    String nom;

    List<Porte> listePorte;
    List<Objet> objets;
    List<Personnage> monstres;
    
    boolean entree;
    boolean sortie;
    
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
    
    /**
     * Chaque lieu doit avoir un nom unique
     * @param nom le nom du lieu
     */
    public Lieu(String nom) {
        this.nom = nom;
        this.listePorte = new ArrayList<>();
        this.objets = new ArrayList<>();
        this.monstres = new ArrayList<>();
        this.drawables = new ArrayList<>();
        this.collisionBoxList = new ArrayList<>();
        this.dispatcher = new Dispatcher();
        this.tileset = null;
        this.sprite_wall = null;
        this.isVisible = false;
        this.entree = false;
        this.sortie = false;
        this.embellishmentsList = new ArrayList<>();
        this.embellishmentsListDrawed = new ArrayList<>();
    }
    
    public boolean isEntree() {
        return this.entree;
    }
    
    public void setEntree(boolean e) {
        this.entree = e;
    }
    
    public boolean isSortie() {
        return this.sortie;
    }
    
    public void setSortie(boolean s) {
        this.sortie = s;
    }

    public boolean isVisible() {
        return isVisible;
    }

    public void setVisible(boolean isVisible) {
        this.isVisible = isVisible;
    }
    
    
    public String getNom() {
        return nom;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
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
        Lieu _this = this;
        if (o instanceof Objet) {
            Element e = (Element)o.copie();
            this.objets.add((Objet)e);
            this.drawables.add(e);
            return true;
        }
        if (o instanceof Personnage) {
            Element e = (Element)o.copie();
            Personnage p = (Personnage)e;
            
            // on propage l'events de collisions dans le dispatcher de ce lieu
            p.onCollide(new SimpleListener("onCollide"){
                @Override
                public void onEvent(Object sender, SimpleEvent event) {
                    _this.getDispatcher().fireEvent("onCollide", _this, event);
                }
            });
            
            this.monstres.add(p);
            this.drawables.add(p);
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
        this.drawables.clear();
    }
    
    public void setSize(Box b) {
        x = b.getX();
        y = b.getY();
        width = b.getWidth();
        height = b.getHeight();
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
    public boolean generate(Object o) {
        
        this.computeCollisonBox();
        
        if (this.embellishmentsList.isEmpty()) return true;
        
        // on génère des éléments de décors un peu à la random de tel sorte qu'il n'y est pas de collisions
        ArrayList<Embellishment> list = new ArrayList<>();
        
        double proba = 1;
        
        int dh = this.sprite_wall == null ? 0 : this.sprite_wall.getHeight();
        
        while (Math.random() < proba) {
            
            Embellishment temp = this.embellishmentsList.get((int)(Math.random()*this.embellishmentsList.size()));
            Embellishment e = temp.copie();
            
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
            } else if (e.getType().equals(TypeEmbellishment.OBJECT)) {
                int ow = e.getSprite().getWidth();
                int oh = e.getSprite().getHeight();
                if (e.getSprite() instanceof SpriteSheet) {
                    SpriteSheet sp = (SpriteSheet)e.getSprite();
                    ow = sp.getSpriteWidth();
                    oh = sp.getSpriteHeight();
                }
                int w = e.getSprite().getWidth();
                int h = (int)(w*(1.0/e.getSprite().getRatio()));
                int x = this.x + (int)(Math.random()*(this.width-w));
                int y = this.y + dh + (int)(Math.random()*(this.height-dh-h));
                e.setBox(x,y,w,h);
                e.setCollisionBoxList(e.getSprite().getCollisionBoxList(), ow, oh);
            }
            
            if (!this.isValide(e)) {proba -= 0.01;continue;}
            
            proba -= 0.1;
            
            e.setParent(this);
            if (e.getType().equals(TypeEmbellishment.OBJECT)) {
                this.drawables.add(e);
            } else {
                this.embellishmentsListDrawed.add(e);
            }
            
            
            
        }
        
        return true;
        
    }
    
    public boolean isValide(Collisionable c) {
        Boolean r = false;
        if (this.entree) {
            Collisionable temp = new Lieu("");
            int s = 100;
            temp.addCollisionBox(new CollisionBox(x+width/2-s/2, y+height/2-s/2, s, s));
            r = temp.isCollide(c);
            if (r != null && r) {return false;}
        }
        r = false;
        for (Porte porte : this.listePorte) {
            r = porte.isCollide(c);
            if (r != null && r) {
                break;
            }
        }
        if (r != null && r) {return false;}
        r = false;
        for (Embellishment et : this.embellishmentsListDrawed) {
            r = et.isCollide(c);
            if (r != null && r) {
                break;
            }
        }
        if (r!=null && r) {return false;}
        r = this.isCollide(c);
        if (r!=null && r) {return false;}
        r = false;
        for (Drawable d : this.drawables) {
            if (d instanceof Collisionable) {
                Collisionable dc = (Collisionable)d;
                r = dc.isCollide(c);
                if (r != null && r) {
                    break;
                }
            }
        }
        if (r!=null && r) {return false;}
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
    
    @Override
    public Shape getClip(Canvas c) {
        
        Path2D clip_path = new Path2D.Double(Path2D.WIND_NON_ZERO);
        clip_path.append(new Rectangle2D.Double(c.toWorldX(this.x), c.toWorldY(this.y), c.toScale(this.width), c.toScale(this.height)), false);
        
        for (Porte p : this.listePorte) {
            if (p.getDirection().isUp()) {
                clip_path.append(new Rectangle2D.Double(c.toWorldX(p.getX()), c.toWorldY(this.y+this.sprite_wall.getHeight()-20), c.toScale(p.getWidth()), c.toScale(20)), false);
            } else if (p.getDirection().isRight()) {
                clip_path.append(new Rectangle2D.Double(c.toWorldX(p.getX()-50), c.toWorldY(p.getY()), c.toScale(p.getWidth()), c.toScale(p.getHeight())), false);
            } else if (p.getDirection().isDown()) {
                clip_path.append(new Rectangle2D.Double(c.toWorldX(p.getX()), c.toWorldY(p.getY()-50), c.toScale(p.getWidth()), c.toScale(p.getHeight())), false);
            } else if (p.getDirection().isLeft()) {
                clip_path.append(new Rectangle2D.Double(c.toWorldX(p.getX()+50), c.toWorldY(p.getY()), c.toScale(p.getWidth()), c.toScale(p.getHeight())), false);
            }
        }
        
        return (Shape)clip_path;
    }

    @Override
    public void onGenerate(GenerateListener l) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    @Override
    public Drawable copie() {
        return null;
    }

    public int randomX(Collisionable c) {
        int x = this.x;
        
        x += (int)(Math.random()*(this.width-c.computeWidth()));
        
        return x;
    }

    public int randomY(Collisionable c) {
        int y = this.y;
        int dh = this.sprite_wall == null ? 0 : this.sprite_wall.getHeight();
        
        y += (int)(Math.random()*(this.height-c.computeHeight()-dh)) + dh;
        
        return y;
    }
    
    public int randomX(Collisionable c, int w) {
        int x = this.x;
        
        x += (int)(Math.random()*(this.width-w));
        
        return x;
    }

    public int randomY(Collisionable c, int h) {
        int y = this.y;
        int dh = this.sprite_wall == null ? 0 : this.sprite_wall.getHeight();
        
        y += (int)(Math.random()*(this.height-h-dh)) + dh;
        
        return y;
    }
    
    
    public void kill(Personnage p) {
        if (p.isDead()) {
            this.drawables.remove(p);
            this.monstres.remove(p);
        } else {
            p.kill();
        }
    }

}
