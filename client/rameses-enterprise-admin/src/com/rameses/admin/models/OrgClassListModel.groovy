package com.rameses.admin.models;

import com.rameses.rcp.annotations.*;
import com.rameses.rcp.common.*;
import com.rameses.osiris2.client.*;
import com.rameses.osiris2.common.*;
import com.rameses.seti2.models.CrudListModel;

class OrgClassListModel extends CrudListModel {

    
    public void refresh() {
        reload(); 
        
        reloadCallerOrgTypes(); 
    }
    
    void afterRemoveItem() {
        reloadCallerOrgTypes(); 
    }
    
    void reloadCallerOrgTypes() { 
        if ( hasCallerMethod( 'reloadOrgTypes', caller )) {
            caller.reloadOrgTypes(); 
        }
    }
} 