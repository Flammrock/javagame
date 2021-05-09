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
public interface Detruisable {
    
    public double getPoids();
    
    default public ArrayList<Morceau> detruire() {
        if (!this.isDetruisable()) return null;
        double p = this.getPoids()+1;
        ArrayList<Morceau> m = new ArrayList<>();
        while (p > 0) {
            double r = Math.random() * (this.getPoids()+1);
            p-=r;
            if (r*2 > 15) {
                m.add(new Morceau(Rarity.LEGENDARY));
            } else if (r*2 > 10) {
                m.add(new Morceau(Rarity.EPIC));
            } else if (r*2 > 5) {
                m.add(new Morceau(Rarity.RARE));
            } else {
                m.add(new Morceau(Rarity.COMMON));
            }
        }
        return m;
    }
    
    public boolean isDetruisable();
    
}
