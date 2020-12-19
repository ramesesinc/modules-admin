package com.rameses.admin.models;

import com.rameses.rcp.annotations.*;
import com.rameses.rcp.common.*;
import com.rameses.seti2.models.*;
import com.rameses.osiris2.common.*;
import com.rameses.util.*;
import com.rameses.osiris2.client.*;
         
public class UserRoleListModel extends AbstractUserRoleModel {
    
    def selectedRole;
    def roles;
    
    def listHandler = [
        fetchList: { o->
            def m = [_schemaname: "sys_user_role"];
            m.findBy = [userid: user.objid];
            m.orderBy = "org.name ASC, role ASC";
            roles = queryService.getList(m);
            return roles;
        },
        openItem: { o,vol->
            return viewRole();
        }
    ] as BasicListModel;
    
    def addRole() {
        def s = { o->
            o.roles.each { r->
                def m = [_schemaname : "sys_user_role" ];
                m.userid = user.objid;
                m.role = r;
                m.org = o.org;
                m.uid = m.userid+"_"+m.role + ( m.org?.objid == null? "" : "_"+m.org.objid );
                persistenceService.create( m );
            }
            listHandler.reload();
        }
        return Inv.lookupOpener("sys_auth_role:lookup", [ existingRoles: roles, onselect: s, queryService: queryService ] );
    }
    
    void removeRole() {
        if(!selectedRole) return;
        def m = [_schemaname: "sys_user_role"];
        m.objid = selectedRole.objid;
        persistenceService.removeEntity( m );
        listHandler.reload();
    }
    
    def viewRole() {
        if(!selectedRole) return;
        return Inv.lookupOpener("sys_user_role:open", [entity: selectedRole, user: user]);
    }
    
    void init() {
        
    }
    
}

        