package modele.element;

import canvas.Canvas;
import canvas.Drawable;
import canvas.collision.CollisionBox;
import canvas.collision.Collisionable;
import geometry.Box;
import java.awt.Color;
import java.awt.Graphics;
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

    public String getNom() {
        return nom;
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
        g.setColor(Color.orange);
        g.drawRect(c.toWorldX(this.x), c.toWorldY(this.y), c.toScale(this.width), c.toScale(this.height));
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
        
        int epaisseur = 10;
        
        // on ajoute 4 collisionsBox
        this.collisionBoxList.clear();
        this.addCollisionBox(new CollisionBox(-epaisseur,-epaisseur,width+epaisseur*2,epaisseur*2,true));
        this.addCollisionBox(new CollisionBox(-epaisseur,-epaisseur+height,width+epaisseur*2,epaisseur*2,true));
        this.addCollisionBox(new CollisionBox(-epaisseur,-epaisseur,epaisseur*2,epaisseur*2+height,true));
        this.addCollisionBox(new CollisionBox(-epaisseur+width,-epaisseur,epaisseur*2,epaisseur*2+height,true));
        
        return true;
    }

}
