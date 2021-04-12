/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele.element;

import canvas.Canvas;
import canvas.Drawable;
import java.awt.Graphics;
import java.util.ArrayList;

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
    
    
}
