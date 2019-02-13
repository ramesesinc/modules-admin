/*
 * OrgMainPage.java
 *
 * Created on February 7, 2014, 10:31 AM
 */

package com.rameses.admin.views;

import com.rameses.osiris2.themes.FormPage;
import com.rameses.rcp.ui.annotations.Template;

/**
 *
 * @author  Elmo
 */
@Template(FormPage.class)
public class OrgMainPage extends javax.swing.JPanel {
    
    /** Creates new form OrgMainPage */
    public OrgMainPage() {
        initComponents();
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        xTileView1 = new com.rameses.rcp.control.XTileView();

        setBackground(new java.awt.Color(255, 255, 255));
        setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5));
        setLayout(new java.awt.BorderLayout());

        xTileView1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        xTileView1.setCellSize(new java.awt.Dimension(75, 80));
        xTileView1.setName("orgModel"); // NOI18N
        xTileView1.setOpaque(false);
        xTileView1.setPadding(new java.awt.Insets(10, 20, 10, 0));
        add(xTileView1, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.rameses.rcp.control.XTileView xTileView1;
    // End of variables declaration//GEN-END:variables
    
}
