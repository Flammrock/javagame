package modele.element;

import java.util.ArrayList;
import java.util.List;

public class Lieu extends Element implements Generable {
    
    String nom;

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
        this.listePorte = new ArrayList<Porte>();
        this.objets = new ArrayList<Objet>();
        this.monstres = new ArrayList<Personnage>();
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
    

    /**
     * Permet d'ajouter un monstre dans le lieu
     * @param monstre
     * @return retourne true si le monstre a bien été ajouté
     */
    public boolean ajouterMonstre(Personnage monstre) {
        this.monstres.add(monstre);
        return true;
    }

    /**
     * Permet d'ajouter un objet dans le lieu
     * @param obj l'objet à ajouter
     * @return retoutne true si l'objet a bien été ajouté, sinon false
     */
    public boolean ajouterObjet(Objet obj) {
        this.objets.add(obj);
        return true;
    }
    
    public boolean ajouterPlusieursObjet(ArrayList<Objet> obj){
        this.objets.addAll(obj);
        return true;
    }
    
    public String toString() {
        return this.nom;
    }

    @Override
    public boolean generate(GenerableParametre p) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
