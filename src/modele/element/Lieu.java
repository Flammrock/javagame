package modele.element;

import java.util.ArrayList;
import java.util.List;

public class Lieu extends Element implements Generable {
    
    String nom;
    double probaDeGeneration;

    List<Porte> listePorte;
    List<Objet> objets;
    List<Personnage> monstres;

    public String getNom() {
        return nom;
    }

    public List<Porte> getListePorte() {
        return listePorte;
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
        lieu2.getListePorte().add(p2);
        
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
                this.objets.add((Objet)o);
            } else if (o instanceof Personnage) {
                this.monstres.add((Personnage)o);
            }
        }
        return true;
    }
    
    public void enleverTout() {
        this.monstres.clear();
        this.objets.clear();
    }

    @Override
    public boolean generate(GenerableParametre s) {
        
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
        
    }

    @Override
    public double getProbabilite() {
        return this.probaDeGeneration;
    }

    @Override
    public void setProbabilite(double proba) {
        this.probaDeGeneration = proba;
    }

}
