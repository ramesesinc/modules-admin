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
    
    def listHandler = [
        
        fetchList: { o->
            def  m =[ _schemaname: "sys_user_role" ];
            m.findBy = [role: entity.name];
            m.orderBy = "role";
            m.tag = "user_by_role";
            return caller.queryService.getList( m );
        }
        
    ] as BasicListModel;

    def viewUser() {
        return Inv.lookupOpener( "sys_user:open", [entity: [objid: selectedItem.userid ]]);
    }
    
    void refreshList() {
        listHandler.reload();
    }
    
}

        