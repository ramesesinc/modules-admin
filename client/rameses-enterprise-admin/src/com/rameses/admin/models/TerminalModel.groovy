package com.rameses.admin.models;

import com.rameses.rcp.annotations.*;
import com.rameses.rcp.common.*;
import com.rameses.osiris2.client.*;
import com.rameses.osiris2.common.*;

public class TerminalModel {

    @Service('TerminalAdminService') 
    def svc;
        
    @Caller
    def caller;
            
    def entity = [:];
            
    def doOk() {
        svc.generateKeys([size: entity.size]); 
        caller?.reload(); 
        return '_close';
    } 
            
    def doCancel() {
        return '_close';
    }
            
    void unassign() { 
        def selitem = caller?.selectedItem; 
        if ( !selitem ) throw new Exception('No available selected item'); 
        
        if (MsgBox.confirm('You are about to unassign this terminal. Do you want to continue?')) { 
            svc.unassign( selitem ); 
            caller?.reload(); 
        } 
    } 
}      