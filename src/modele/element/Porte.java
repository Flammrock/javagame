/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele.element;

import canvas.Canvas;
import java.awt.Graphics;

/**
 *
 * @author Utilisateur
 */
public class Porte extends Element {
    
    private Lieu lieu1;
    private Lieu lieu2;
    private String nom;
    
    public Porte(String nom, Lieu lieu1, Lieu lieu2) {
        this.nom = nom;
        this.lieu1 = lieu1;
        this.lieu2 = lieu2;
    }

    public String getNom() {
        return nom;
    }

    public Lieu getLieu1() {
        return lieu1;
    }

    public void setLieu1(Lieu lieu1) {
        this.lieu1 = lieu1;
    }

    public Lieu getLieu2() {
        return lieu2;
    }

    public void setLieu2(Lieu lieu2) {
        this.lieu2 = lieu2;
    }
    
    public String toString() {
        return this.nom + " (vers "+this.lieu2.getNom()+")";
    }

    @Override
    public void draw(Canvas c, Graphics g) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getX() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setX(int x) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getY() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setY(int y) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void MoveTo(int x, int y) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void MoveBy(int x, int y) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void applyMove() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
