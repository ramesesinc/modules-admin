/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rameses.admin.components;

import com.rameses.rcp.common.MsgBox;
import com.rameses.rcp.control.XComponentPanel;
import com.rameses.rcp.ui.annotations.ComponentBean;

/**
 *
 * @author elmonazareno
 */
@ComponentBean("com.rameses.admin.components.OrgDropdownListModel")
public class OrgDropdownList extends XComponentPanel {

    private String orgclass;
    
    /**
     * Creates new form OrgDropdownList
     */
    public OrgDropdownList() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        xComboBox1 = new com.rameses.rcp.control.XComboBox();

        setLayout(new java.awt.BorderLayout());

        xComboBox1.setExpression("#{ item.name } ");
        xComboBox1.setItems("items");
        xComboBox1.setName("value"); // NOI18N
        add(xComboBox1, java.awt.BorderLayout.PAGE_START);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.rameses.rcp.control.XComboBox xComboBox1;
    // End of variables declaration//GEN-END:variables

    /**
     * @return the orgclass
     */
    public String getOrgclass() {
        return orgclass;
    }

    /**
     * @param orgclass the orgclass to set
     */
    public void setOrgclass(String orgclass) {
        this.orgclass = orgclass;
    }

    @Override
    protected void initComponentBean(com.rameses.rcp.common.ComponentBean bean) {
        bean.setProperty("orgclass", orgclass);
    }
    
    /*
    public void afterRefresh() {
        com.rameses.rcp.common.ComponentBean bean = (com.rameses.rcp.common.ComponentBean)getComponentBean(); 
        bean.get
        
        
    }
    */
    
    
}
