/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package canvas.Combat;

import modele.element.Personnage;

/**
 *
 * @author User
 */
public class CombatGraphiqueContainerPersonnage extends javax.swing.JPanel {

    private Personnage p;
    
    /**
     * Creates new form CombatGraphiqueContainerPersonnage
     */
    public CombatGraphiqueContainerPersonnage() {
        initComponents();
    }
    
    public void bind(Personnage p) {
        this.p = p;
    }
    
    public void redessiner() {
        canvas1.repaint();
    }
    
    public void miseAJour() {
        combatGraphiquePersonnage1.miseAJour(this.p);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        combatGraphiquePersonnage1 = new canvas.Combat.CombatGraphiquePersonnage();
        canvas1 = new canvas.Canvas();

        setOpaque(false);

        canvas1.setOpaque(false);

        javax.swing.GroupLayout canvas1Layout = new javax.swing.GroupLayout(canvas1);
        canvas1.setLayout(canvas1Layout);
        canvas1Layout.setHorizontalGroup(
            canvas1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        canvas1Layout.setVerticalGroup(
            canvas1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 296, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(canvas1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(60, Short.MAX_VALUE)
                .addComponent(combatGraphiquePersonnage1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(111, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(combatGraphiquePersonnage1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(canvas1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private canvas.Canvas canvas1;
    private canvas.Combat.CombatGraphiquePersonnage combatGraphiquePersonnage1;
    // End of variables declaration//GEN-END:variables
}
