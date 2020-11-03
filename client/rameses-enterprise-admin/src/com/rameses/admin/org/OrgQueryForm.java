/*
 * NewJPanel.java
 *
 * Created on July 28, 2013, 8:34 PM
 */

package com.rameses.admin.org;

import com.rameses.osiris2.themes.FormPage;
import com.rameses.rcp.ui.annotations.Template;

/**
 *
 * @author  Elmo
 */
public class OrgQueryForm extends javax.swing.JPanel {
    
    /** Creates new form NewJPanel */
    public OrgQueryForm() {
        initComponents();
        xLabel1.setLabelFor( xComboBox1 ); 
        xLabel1.setDisplayedMnemonic('S');
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        xLabel1 = new com.rameses.rcp.control.XLabel();
        xComboBox1 = new com.rameses.rcp.control.XComboBox();

        setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 5, 5, 5));
        com.rameses.rcp.control.layout.XLayout xLayout2 = new com.rameses.rcp.control.layout.XLayout();
        xLayout2.setSpacing(10);
        setLayout(xLayout2);

        xLabel1.setText("Select an Org Class :");
        add(xLabel1);

        xComboBox1.setCaption("Select an Org Class");
        xComboBox1.setExpression("#{item.title}");
        xComboBox1.setItems("orgTypes");
        xComboBox1.setName("orgclass"); // NOI18N
        xComboBox1.setAllowNull(false);
        xComboBox1.setCaptionWidth(120);
        add(xComboBox1);
    }// </editor-fold>//GEN-END:initComponents
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.rameses.rcp.control.XComboBox xComboBox1;
    private com.rameses.rcp.control.XLabel xLabel1;
    // End of variables declaration//GEN-END:variables
    
}