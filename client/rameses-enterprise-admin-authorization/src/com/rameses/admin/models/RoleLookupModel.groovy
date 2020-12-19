package com.rameses.admin.models;

import com.rameses.rcp.annotations.*;
import com.rameses.rcp.common.*;
import com.rameses.seti2.models.*;
import com.rameses.osiris2.common.*;
import com.rameses.util.*;
import com.rameses.osiris2.client.*;
         
public class RoleLookupModel extends AbstractUserRoleModel  {
    
    def existingRoles;
    
    def selectedRole;
    def onselect;
    def queryService;
    def org;

    def excludeRoleFilter;
    
    @PropertyChangeListener
    def listener = [
        "org" : { o->
            buildExcludeRoleFilter();
            listHandler.reload();
        }
    ];
    
    def listHandler = [
        isMultiSelect: {
            return true;
        },
        fetchList: { o->
            def m = [_schemaname: "sys_role"];
            if( excludeRoleFilter ) {
                m.where = ["name NOT IN (" + excludeRoleFilter + ")"];
            }
            else {
                m.where = ["1=1"];
            }
            return queryService.getList(m);
        }
    ] as BasicListModel;
    
    
    void buildExcludeRoleFilter() {
        def rlist;
        if( org?.objid == null ) {            
            rlist = existingRoles.findAll{ !it.org?.objid }*.role;
        }
        else {
            rlist = existingRoles.findAll{ it.org?.objid == org.objid }*.role;            
        }
        if( rlist ) {
            excludeRoleFilter = "'" + rlist.join("','") + "'";
        }
        else {
            excludeRoleFilter = null;
        }
    }
    
    void init() {
        buildExcludeRoleFilter();
    }
    
    def doOk() {
        def param = [:];
        param.org = org;
        param.roles = listHandler.getSelectedValue()*.name;
        onselect( param );
        return "_close";
    }
    
    def doCancel() {
        return "_close";
    }
}

        