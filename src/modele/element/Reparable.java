/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele.element;

import java.util.ArrayList;

/**
 *
 * @author User
 */
public interface Reparable {
    
    public double getLife();
    
    public void setLife(double l);
    
    public double getMaxLife();
    
    public double setMaxLife();
    
    default public boolean isCasser() {
        return this.getLife() <= 0.0;
    }
    
    default public boolean abimer() {
        if (this.isCasser()) return false;
        this.setLife(this.getLife()-1.0);
        return true;
    }
    
    public boolean isReparable();
    
    default public ArrayList<Morceau> neededMorceaux() {
        return null;
    }
    
    default public ArrayList<Morceau> reparer(ArrayList<Morceau> u) {
        if (!this.isReparable()) return null;
        ArrayList<Morceau> n = this.neededMorceaux();
        ArrayList<Morceau> m = u;
        ArrayList<Morceau> e = new ArrayList<>();
        double v = 1.0;
        for (Morceau k : m) {
            switch (k.getLevel()) {
                case Rarity.COMMON:
                    v = 1.0;
                    break;
                case Rarity.RARE:
                    v = 3.0;
                    break;
                case Rarity.EPIC:
                    v = 7.0;
                    break;
                case Rarity.LEGENDARY:
                    v = 15.0;
                    break;
                default:
                    v = 1.0;
                    break;
            }
            this.setLife(this.getLife()+v);
            e.add(k);
            if (this.getLife()>this.getMaxLife()) {
                this.setLife(this.getMaxLife());
                break;
            }
        }
        return e;
    }
    
}
