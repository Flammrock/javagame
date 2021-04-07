/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exec;

import javax.swing.DefaultListModel;
import modele.element.*;


/**
 *
 * @author pierrecharbit
 */
public class AppliGraphique extends javax.swing.JFrame {

    private Aventure aventure;
    
    /**
     * Creates new form AppliGraphique
     * @param aventure
     */
    public AppliGraphique(Aventure aventure) {
        this.aventure = aventure;
        initComponents();
        this.mettreAJourListePorte();
        this.mettreAJourListeObjet();
        this.mettreAJourListeMonstre();
        this.mettreAJourSalle();
        this.mettreAJourStatistiquePerso();
    }
    
    private void mettreAJourStatistiquePerso(){
        this.mettreAJourAgilite();
        this.mettreAJourArmeEnMain();
        this.mettreAJourArmure();
        this.mettreAJourForce();
        this.mettreAJourNom();
        this.mettreAJourPv();
        this.mettreAJourListeInventaire();
    }
    
    
    private void mettreAJourListeMonstre(){
        DefaultListModel<Personnage> g = new DefaultListModel<>();
        g.addAll(this.aventure.getJoueur().getPieceActuel().getMonstres());
        listeMonstre.setModel(g);
    }
    
    private void mettreAJourListeObjet(){
        DefaultListModel<Objet> g = new DefaultListModel<>();
        g.addAll(this.aventure.getJoueur().getPieceActuel().getObjets());
        listeObjet.setModel(g);
    }
    
    private void mettreAJourSalle(){
        jTextPane1.setText(this.aventure.getJoueur().getPieceActuel().toString());
    }
    
    private void mettreAJourListePorte() {
        DefaultListModel<Porte> g = new DefaultListModel<>();
        g.addAll(this.aventure.getJoueur().getPieceActuel().getListePorte());
        listePortes.setModel(g);
    }
    
    private void mettreAJourAgilite(){
        jTextPaneAgilite.setText(this.aventure.getJoueur().getAgilite()+"");
    }
    
    private void mettreAJourArmeEnMain(){
        Equipement main = this.aventure.getJoueur().getMain();
        if (main != null) {
            jTextPaneArmeEnMain.setText(main.toString());
        } else {
            jTextPaneArmeEnMain.setText("VIDE");
        }
    }
    
    private void mettreAJourArmure(){
        Equipement armure = this.aventure.getJoueur().getArmure();
        if (armure != null) {
            jTextPaneArmure.setText(armure.toString());
        } else {
            jTextPaneArmure.setText("VIDE");
        }
    }
    
    private void mettreAJourPv(){
        jTextPanePv.setText(this.aventure.getJoueur().getPv()+"");
    }
    
    private void mettreAJourNom(){
        jTextPaneNom.setText(this.aventure.getJoueur().getNom());
    }
    
    private void mettreAJourForce(){
        jTextPaneForce.setText(this.aventure.getJoueur().getForce()+"");
    }
    
    private void mettreAJourListeInventaire(){
        DefaultListModel<Objet> g = new DefaultListModel<>();
        g.addAll(this.aventure.getJoueur().getInventaire());
        listeObjetInventaire.setModel(g);
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        logger = new javax.swing.JTextArea();
        jPanelAffichageSalle = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        listePortes = new javax.swing.JList<>();
        allerDansPorte = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTextPane1 = new javax.swing.JTextPane();
        jScrollPane4 = new javax.swing.JScrollPane();
        listeObjet = new javax.swing.JList<>();
        jScrollPane5 = new javax.swing.JScrollPane();
        listeMonstre = new javax.swing.JList<>();
        Combattre = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        jTextPane2 = new javax.swing.JTextPane();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane7 = new javax.swing.JScrollPane();
        jTextPaneNomFixe = new javax.swing.JTextPane();
        jScrollPane8 = new javax.swing.JScrollPane();
        jTextPanePvFixe = new javax.swing.JTextPane();
        jScrollPane9 = new javax.swing.JScrollPane();
        jTextPaneForceFixe = new javax.swing.JTextPane();
        jScrollPane10 = new javax.swing.JScrollPane();
        jTextPaneAgiliteFixe = new javax.swing.JTextPane();
        jScrollPane11 = new javax.swing.JScrollPane();
        jTextPaneArmeEnMainFixe = new javax.swing.JTextPane();
        jScrollPane12 = new javax.swing.JScrollPane();
        jTextPaneArmureFixe = new javax.swing.JTextPane();
        jScrollPane13 = new javax.swing.JScrollPane();
        jTextPaneNom = new javax.swing.JTextPane();
        jScrollPane14 = new javax.swing.JScrollPane();
        jTextPanePv = new javax.swing.JTextPane();
        jScrollPane15 = new javax.swing.JScrollPane();
        jTextPaneForce = new javax.swing.JTextPane();
        jScrollPane16 = new javax.swing.JScrollPane();
        jTextPaneAgilite = new javax.swing.JTextPane();
        jScrollPane17 = new javax.swing.JScrollPane();
        jTextPaneArmeEnMain = new javax.swing.JTextPane();
        jScrollPane18 = new javax.swing.JScrollPane();
        jTextPaneArmure = new javax.swing.JTextPane();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane19 = new javax.swing.JScrollPane();
        jTextPaneInventaireFixe = new javax.swing.JTextPane();
        jScrollPane20 = new javax.swing.JScrollPane();
        listeObjetInventaire = new javax.swing.JList<>();
        utiliserbouton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        logger.setEditable(false);
        logger.setBackground(new java.awt.Color(204, 204, 204));
        logger.setColumns(20);
        logger.setRows(5);
        jScrollPane1.setViewportView(logger);

        jScrollPane2.setViewportView(listePortes);

        allerDansPorte.setText("Aller");
        allerDansPorte.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                allerDansPorteActionPerformed(evt);
            }
        });

        jTextPane1.setEditable(false);
        jScrollPane3.setViewportView(jTextPane1);

        jScrollPane4.setViewportView(listeObjet);

        jScrollPane5.setViewportView(listeMonstre);

        Combattre.setText("Combattre");
        Combattre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CombattreActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelAffichageSalleLayout = new javax.swing.GroupLayout(jPanelAffichageSalle);
        jPanelAffichageSalle.setLayout(jPanelAffichageSalleLayout);
        jPanelAffichageSalleLayout.setHorizontalGroup(
            jPanelAffichageSalleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelAffichageSalleLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(Combattre)
                .addGap(207, 207, 207))
            .addGroup(jPanelAffichageSalleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanelAffichageSalleLayout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(jPanelAffichageSalleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanelAffichageSalleLayout.createSequentialGroup()
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 295, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addGroup(jPanelAffichageSalleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jScrollPane3)
                                .addGroup(jPanelAffichageSalleLayout.createSequentialGroup()
                                    .addComponent(allerDansPorte, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(0, 0, Short.MAX_VALUE))))
                        .addGroup(jPanelAffichageSalleLayout.createSequentialGroup()
                            .addGroup(jPanelAffichageSalleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 295, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 295, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(0, 0, Short.MAX_VALUE)))
                    .addContainerGap()))
        );
        jPanelAffichageSalleLayout.setVerticalGroup(
            jPanelAffichageSalleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelAffichageSalleLayout.createSequentialGroup()
                .addContainerGap(129, Short.MAX_VALUE)
                .addComponent(Combattre)
                .addGap(84, 84, 84))
            .addGroup(jPanelAffichageSalleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanelAffichageSalleLayout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(jPanelAffichageSalleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanelAffichageSalleLayout.createSequentialGroup()
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(allerDansPorte)))
                    .addGap(4, 4, 4)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        jTextPane2.setEditable(false);
        jTextPane2.setText("Feuille de personnage");
        jScrollPane6.setViewportView(jTextPane2);

        jTextPaneNomFixe.setEditable(false);
        jTextPaneNomFixe.setText("Nom");
        jScrollPane7.setViewportView(jTextPaneNomFixe);

        jTextPanePvFixe.setEditable(false);
        jTextPanePvFixe.setText("PV");
        jScrollPane8.setViewportView(jTextPanePvFixe);

        jTextPaneForceFixe.setEditable(false);
        jTextPaneForceFixe.setText("Force");
        jScrollPane9.setViewportView(jTextPaneForceFixe);

        jTextPaneAgiliteFixe.setEditable(false);
        jTextPaneAgiliteFixe.setText("Agilté");
        jScrollPane10.setViewportView(jTextPaneAgiliteFixe);

        jTextPaneArmeEnMainFixe.setEditable(false);
        jTextPaneArmeEnMainFixe.setText("Arme en main");
        jScrollPane11.setViewportView(jTextPaneArmeEnMainFixe);

        jTextPaneArmureFixe.setEditable(false);
        jTextPaneArmureFixe.setText("Armure");
        jScrollPane12.setViewportView(jTextPaneArmureFixe);

        jTextPaneNom.setEditable(false);
        jScrollPane13.setViewportView(jTextPaneNom);

        jTextPanePv.setEditable(false);
        jScrollPane14.setViewportView(jTextPanePv);

        jTextPaneForce.setEditable(false);
        jScrollPane15.setViewportView(jTextPaneForce);

        jTextPaneAgilite.setEditable(false);
        jScrollPane16.setViewportView(jTextPaneAgilite);

        jTextPaneArmeEnMain.setEditable(false);
        jScrollPane17.setViewportView(jTextPaneArmeEnMain);

        jTextPaneArmure.setEditable(false);
        jScrollPane18.setViewportView(jTextPaneArmure);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane7)
                    .addComponent(jScrollPane8)
                    .addComponent(jScrollPane9)
                    .addComponent(jScrollPane10)
                    .addComponent(jScrollPane11)
                    .addComponent(jScrollPane12))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane13)
                    .addComponent(jScrollPane14)
                    .addComponent(jScrollPane15, javax.swing.GroupLayout.DEFAULT_SIZE, 161, Short.MAX_VALUE)
                    .addComponent(jScrollPane16)
                    .addComponent(jScrollPane17)
                    .addComponent(jScrollPane18))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(21, Short.MAX_VALUE))
        );

        jTextPaneInventaireFixe.setEditable(false);
        jTextPaneInventaireFixe.setText("Inventaire:");
        jScrollPane19.setViewportView(jTextPaneInventaireFixe);

        jScrollPane20.setViewportView(listeObjetInventaire);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane19)
            .addComponent(jScrollPane20, javax.swing.GroupLayout.DEFAULT_SIZE, 323, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jScrollPane19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane20))
        );

        utiliserbouton.setText("Utiliser");
        utiliserbouton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                utiliserboutonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(utiliserbouton, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(utiliserbouton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addComponent(jPanelAffichageSalle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanelAffichageSalle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void allerDansPorteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_allerDansPorteActionPerformed
        // TODO add your handling code here:
        Porte p = listePortes.getSelectedValue();
        if (p!=null) {
            this.aventure.getJoueur().setPieceActuel(p.getLieu2());
            this.mettreAJourListePorte();
            this.mettreAJourListeObjet();
            this.mettreAJourListeMonstre();
            this.mettreAJourSalle();
        }
    }//GEN-LAST:event_allerDansPorteActionPerformed

    private void CombattreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CombattreActionPerformed
        // TODO add your handling code here:
        Personnage p = listeMonstre.getSelectedValue();
        if (p!=null) {
            this.aventure.getJoueur().attaque(p);
            this.mettreAJourStatistiquePerso();
            this.mettreAJourListeMonstre();
        }
        
        this.aventure.onActionJoueur();
    }//GEN-LAST:event_CombattreActionPerformed

    private void utiliserboutonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_utiliserboutonActionPerformed
        // TODO add your handling code here:
        int selected = listeObjetInventaire.getSelectedIndex();
        this.aventure.getJoueur().utiliserObjet(selected);
        this.mettreAJourListeInventaire();
        this.mettreAJourStatistiquePerso();
        
        this.aventure.onActionJoueur();
    }//GEN-LAST:event_utiliserboutonActionPerformed

    /**
     * @param args the command line arguments
     */
    /*****public static void main(String args[]) {*****/
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        /*********try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(AppliGraphique.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AppliGraphique.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AppliGraphique.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AppliGraphique.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }*******/
        //</editor-fold>

        /* Create and display the form */
        /****java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AppliGraphique().setVisible(true);
            }
        });
    }*****/

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Combattre;
    private javax.swing.JButton allerDansPorte;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanelAffichageSalle;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane12;
    private javax.swing.JScrollPane jScrollPane13;
    private javax.swing.JScrollPane jScrollPane14;
    private javax.swing.JScrollPane jScrollPane15;
    private javax.swing.JScrollPane jScrollPane16;
    private javax.swing.JScrollPane jScrollPane17;
    private javax.swing.JScrollPane jScrollPane18;
    private javax.swing.JScrollPane jScrollPane19;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane20;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JTextPane jTextPane1;
    private javax.swing.JTextPane jTextPane2;
    private javax.swing.JTextPane jTextPaneAgilite;
    private javax.swing.JTextPane jTextPaneAgiliteFixe;
    private javax.swing.JTextPane jTextPaneArmeEnMain;
    private javax.swing.JTextPane jTextPaneArmeEnMainFixe;
    private javax.swing.JTextPane jTextPaneArmure;
    private javax.swing.JTextPane jTextPaneArmureFixe;
    private javax.swing.JTextPane jTextPaneForce;
    private javax.swing.JTextPane jTextPaneForceFixe;
    private javax.swing.JTextPane jTextPaneInventaireFixe;
    private javax.swing.JTextPane jTextPaneNom;
    private javax.swing.JTextPane jTextPaneNomFixe;
    private javax.swing.JTextPane jTextPanePv;
    private javax.swing.JTextPane jTextPanePvFixe;
    private javax.swing.JList<Personnage> listeMonstre;
    private javax.swing.JList<Objet> listeObjet;
    private javax.swing.JList<Objet> listeObjetInventaire;
    private javax.swing.JList<Porte> listePortes;
    private javax.swing.JTextArea logger;
    private javax.swing.JButton utiliserbouton;
    // End of variables declaration//GEN-END:variables
}
