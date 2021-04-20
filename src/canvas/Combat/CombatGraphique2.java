/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package canvas.Combat;

import canvas.SpriteSheet;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.DefaultListModel;
import modele.element.Aventure;
import modele.element.Equipement;
import modele.element.Objet;
import modele.element.Personnage;
import modele.element.PropertyList;

/**
 *
 * @author ssj-m_000
 */
public class CombatGraphique2 extends javax.swing.JPanel {
private Aventure aventure;
private Personnage Moi;
private Personnage Ennemi;
    /**
     * Creates new form CombatGraphique2
     */
    public CombatGraphique2(){
        initComponents();
        SpriteSheet sprite = new SpriteSheet("/HerosSpriteSheet.png",0,0,64,64,64*3,64*3);
        sprite.loadImage();
        DessinHero.ajouterItem(sprite);
        jPanel3.setVisible(false);
    }
    public void bindAventure(Aventure a) {
        this.aventure = a;
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        PanelEnnemi = new javax.swing.JPanel();
        VieHeroBar = new javax.swing.JProgressBar();
        VieHeroText = new javax.swing.JTextField();
        NomHeroText = new javax.swing.JTextField();
        PanelHero = new javax.swing.JPanel();
        VieEnnemiBar = new javax.swing.JProgressBar();
        VieEnnemiText = new javax.swing.JTextField();
        NomEnnemiText = new javax.swing.JTextField();
        PanelOptionsHero = new javax.swing.JPanel();
        InventaireBoutton = new javax.swing.JButton();
        AttaqueBoutton = new javax.swing.JButton();
        FuiteBoutton = new javax.swing.JButton();
        DessinEnnemi = new canvas.Canvas();
        DessinHero = new canvas.Canvas();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane19 = new javax.swing.JScrollPane();
        jTextPaneInventaire = new javax.swing.JTextPane();
        jScrollPane20 = new javax.swing.JScrollPane();
        listeObjetInventaire = new javax.swing.JList<>();
        utiliserBouton = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();

        VieHeroText.setText("jTextField2");

        NomHeroText.setText("jTextField1");

        javax.swing.GroupLayout PanelEnnemiLayout = new javax.swing.GroupLayout(PanelEnnemi);
        PanelEnnemi.setLayout(PanelEnnemiLayout);
        PanelEnnemiLayout.setHorizontalGroup(
            PanelEnnemiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelEnnemiLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PanelEnnemiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(VieHeroBar, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(PanelEnnemiLayout.createSequentialGroup()
                        .addComponent(VieHeroText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(NomHeroText)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        PanelEnnemiLayout.setVerticalGroup(
            PanelEnnemiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelEnnemiLayout.createSequentialGroup()
                .addContainerGap(13, Short.MAX_VALUE)
                .addGroup(PanelEnnemiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(VieHeroText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(NomHeroText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(VieHeroBar, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        VieEnnemiText.setText("jTextField1");

        NomEnnemiText.setText("jTextField1");

        javax.swing.GroupLayout PanelHeroLayout = new javax.swing.GroupLayout(PanelHero);
        PanelHero.setLayout(PanelHeroLayout);
        PanelHeroLayout.setHorizontalGroup(
            PanelHeroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelHeroLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PanelHeroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(VieEnnemiBar, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(PanelHeroLayout.createSequentialGroup()
                        .addComponent(VieEnnemiText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(NomEnnemiText)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        PanelHeroLayout.setVerticalGroup(
            PanelHeroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelHeroLayout.createSequentialGroup()
                .addContainerGap(18, Short.MAX_VALUE)
                .addGroup(PanelHeroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(VieEnnemiText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(NomEnnemiText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(VieEnnemiBar, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        InventaireBoutton.setText("Inventaire");
        InventaireBoutton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                InventaireBouttonActionPerformed(evt);
            }
        });

        AttaqueBoutton.setText("Attaque");
        AttaqueBoutton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AttaqueBouttonActionPerformed(evt);
            }
        });

        FuiteBoutton.setText("Fuir");
        FuiteBoutton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FuiteBouttonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout PanelOptionsHeroLayout = new javax.swing.GroupLayout(PanelOptionsHero);
        PanelOptionsHero.setLayout(PanelOptionsHeroLayout);
        PanelOptionsHeroLayout.setHorizontalGroup(
            PanelOptionsHeroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelOptionsHeroLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(AttaqueBoutton)
                .addGap(18, 18, 18)
                .addComponent(InventaireBoutton)
                .addGap(18, 18, 18)
                .addComponent(FuiteBoutton)
                .addContainerGap(603, Short.MAX_VALUE))
        );
        PanelOptionsHeroLayout.setVerticalGroup(
            PanelOptionsHeroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelOptionsHeroLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PanelOptionsHeroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(FuiteBoutton)
                    .addComponent(AttaqueBoutton)
                    .addComponent(InventaireBoutton))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout DessinEnnemiLayout = new javax.swing.GroupLayout(DessinEnnemi);
        DessinEnnemi.setLayout(DessinEnnemiLayout);
        DessinEnnemiLayout.setHorizontalGroup(
            DessinEnnemiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 665, Short.MAX_VALUE)
        );
        DessinEnnemiLayout.setVerticalGroup(
            DessinEnnemiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 363, Short.MAX_VALUE)
        );

        jTextPaneInventaire.setEditable(false);
        jTextPaneInventaire.setText("Inventaire:");
        jScrollPane19.setViewportView(jTextPaneInventaire);

        jScrollPane20.setViewportView(listeObjetInventaire);

        utiliserBouton.setText("Utiliser");
        utiliserBouton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                utiliserBoutonActionPerformed(evt);
            }
        });

        jButton1.setText("X");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane19)
            .addComponent(jScrollPane20, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 285, Short.MAX_VALUE)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(utiliserBouton, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton1))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(utiliserBouton)
                    .addComponent(jButton1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(121, 121, 121))
        );

        javax.swing.GroupLayout DessinHeroLayout = new javax.swing.GroupLayout(DessinHero);
        DessinHero.setLayout(DessinHeroLayout);
        DessinHeroLayout.setHorizontalGroup(
            DessinHeroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(DessinHeroLayout.createSequentialGroup()
                .addGap(204, 204, 204)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(226, Short.MAX_VALUE))
        );
        DessinHeroLayout.setVerticalGroup(
            DessinHeroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(DessinHeroLayout.createSequentialGroup()
                .addGap(69, 69, 69)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(97, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(54, 54, 54)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(DessinHero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(PanelHero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(DessinEnnemi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(261, 261, 261)
                                .addComponent(PanelEnnemi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(PanelOptionsHero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addComponent(PanelHero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(DessinHero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(DessinEnnemi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(PanelEnnemi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27)))
                .addComponent(PanelOptionsHero, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void InventaireBouttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_InventaireBouttonActionPerformed
        jPanel3.setVisible(true);
    }//GEN-LAST:event_InventaireBouttonActionPerformed

    private void AttaqueBouttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AttaqueBouttonActionPerformed
        this.Moi.attaque(this.Ennemi);
        Ennemi.attaque(this.Moi);
        this.Moi.actionEffetFinDuTour();
        this.Ennemi.actionEffetFinDuTour();
        if(this.Moi.getAjoute("pv") == 0 || this.Ennemi.getAjoute("pv") == 0){
            this.aventure.finCombat(Moi,Ennemi);
        }
        miseAJourComposants();
    }//GEN-LAST:event_AttaqueBouttonActionPerformed

    private void FuiteBouttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FuiteBouttonActionPerformed
        if(this.Moi.essaiEnfuir(this.Ennemi)){
            this.aventure.finCombat(Moi,Ennemi);
        }else{
            Ennemi.attaque(this.Moi);
        }
        miseAJourComposants();
    }//GEN-LAST:event_FuiteBouttonActionPerformed

    private void utiliserBoutonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_utiliserBoutonActionPerformed
        int selected = listeObjetInventaire.getSelectedIndex();
        Objet obj = listeObjetInventaire.getSelectedValue();
        if (obj == null) return;

        boolean needUpdate = false;

        if (obj instanceof Equipement) {
            if (this.aventure.getJoueur().getMain()==obj) {
                needUpdate = this.aventure.getJoueur().desequip(selected);
            } else {
                needUpdate = this.aventure.getJoueur().equip(selected);
            }
        } else if (obj.peutUtiliser()) {
            needUpdate = this.aventure.getJoueur().utiliserObjet(selected);
        }

        if (needUpdate) {
            miseAJourComposants();
        }
    }//GEN-LAST:event_utiliserBoutonActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        jPanel3.setVisible(false);
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton AttaqueBoutton;
    private canvas.Canvas DessinEnnemi;
    private canvas.Canvas DessinHero;
    private javax.swing.JButton FuiteBoutton;
    private javax.swing.JButton InventaireBoutton;
    private javax.swing.JTextField NomEnnemiText;
    private javax.swing.JTextField NomHeroText;
    private javax.swing.JPanel PanelEnnemi;
    private javax.swing.JPanel PanelHero;
    private javax.swing.JPanel PanelOptionsHero;
    private javax.swing.JProgressBar VieEnnemiBar;
    private javax.swing.JTextField VieEnnemiText;
    private javax.swing.JProgressBar VieHeroBar;
    private javax.swing.JTextField VieHeroText;
    private javax.swing.JButton jButton1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane19;
    private javax.swing.JScrollPane jScrollPane20;
    private javax.swing.JTextPane jTextPaneInventaire;
    private javax.swing.JList<Objet> listeObjetInventaire;
    private javax.swing.JButton utiliserBouton;
    // End of variables declaration//GEN-END:variables

    private void miseAJourComposants() {
        VieHeroBar.setMaximum((int)this.Moi.getAjoute("pv max"));
        VieHeroBar.setValue((int)this.Moi.getAjoute("pv"));
        VieHeroText.setText(this.Moi.getAjoute("pv") + "/" + this.Moi.getAjoute("pv max"));
        NomHeroText.setText(this.Moi.getNom());
        
        VieEnnemiBar.setMaximum((int)this.Ennemi.getAjoute("pv max"));
        VieEnnemiBar.setValue((int)this.Ennemi.getAjoute("pv"));
        VieEnnemiText.setText(this.Ennemi.getAjoute("pv") + "/" + this.Ennemi.getAjoute("pv max"));
        NomEnnemiText.setText(this.Ennemi.getNom());
        //DessinHero.(s);
        
        mettreAJourListeInventaire();
    }

    public void mettreAJourCanvas() {
        DessinEnnemi.repaint();
        DessinHero.repaint();
    }

    public void lancerCombat(Personnage perso1, Personnage perso2) {
        System.out.println(perso1+ " versus " + perso2);
        this.Moi = perso1;
        this.Ennemi = perso2;
        miseAJourComposants();
    }
    
    private void mettreAJourListeInventaire(){
        DefaultListModel<Objet> g = new DefaultListModel<>();
        g.addAll(this.aventure.getJoueur().getInventaire());
        listeObjetInventaire.setModel(g);
        jTextPaneInventaire.setText("Inventaire (" + this.aventure.getJoueur().getPoidsInventaire() + "/" + this.aventure.getJoueur().getAjoute(PropertyList.POIDSMAX)+")");
    }
}
