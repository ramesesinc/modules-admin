package com.rameses.admin.plugin.models;

import com.rameses.rcp.annotations.*;
import com.rameses.rcp.common.*;
import com.rameses.seti2.models.*;
import com.rameses.osiris2.common.*;
import com.rameses.util.*;
import com.rameses.osiris2.client.*;
         
public class RoleUsersModel {
    
    @Caller
    def caller;
    
    def entity;
    def selectedItem;
    
    public String getConnection() {
        return caller.getConnection();
    }
    
    def listHandler = [
        fetchList: { o->
            def  m =[ _schemaname: "sys_user_role" ];
            m.findBy = [role: entity.name];
            m.orderBy = "role";
            return caller.queryService.getList( m );
        }
        
    ] as BasicListModel;

    def viewUserRole() {
        def s = {
            refreshList();
        }
        return Inv.lookupOpener( "sys_user_role:open", [entity: [objid: selectedItem.objid ], onSaveHandler:s]);
    }
    
    def addUserRole() {
        def s = {
            refreshList();
        }
        return Inv.lookupOpener( "sys_user_role:create", [onSaveHandler:s]);
    }
    
    void removeUserRole() {
        if(!selectedItem ) return;
        if(!MsgBox.confirm("You are about to remove this user. Proceed?")) return;
        def m = [_schemaname: "sys_user_role"];
        m.objid = selectedItem.objid;
        caller.persistenceService.removeEntity(m);
        refreshList();
    }

    void refreshList() {
        listHandler.reload();
    }
    
}

        