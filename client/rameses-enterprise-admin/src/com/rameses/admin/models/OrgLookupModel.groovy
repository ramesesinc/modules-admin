package com.rameses.admin.models;

import com.rameses.rcp.annotations.*;
import com.rameses.rcp.common.*;
import com.rameses.osiris2.client.*;
import com.rameses.osiris2.common.*;

class OrgLookupModel extends ServiceLookupController {

    @Service("OrgService")
    def orgSvc;

    def includes;
    def orgTypes;
    def orgclass;

    void init() {
        def list = orgSvc.getOrgClasses();
        if ( includes ) { 
            includes = includes.replace(",", "|");
            list = list.findAll{ it.name.matches( includes ) }
        } 
        orgTypes = list; 
    }

    @PropertyChangeListener 
    def listener = [
        orgclass : { o-> 
            query.orgclass = o?.name; 
            super.reload(); 
        } 
    ];
}