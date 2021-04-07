/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele.element;

/**
 *
 * @author Utilisateur
 */
public class Porte extends Element{
    private Lieu lieu1;
    private Lieu lieu2;
    private String name;
    
    public Porte(String name, Lieu lieu1, Lieu lieu2) {
        this.name = name;
        this.lieu1 = lieu1;
        this.lieu2 = lieu2;
    }

    public String getName() {
        return name;
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
    
    
}
