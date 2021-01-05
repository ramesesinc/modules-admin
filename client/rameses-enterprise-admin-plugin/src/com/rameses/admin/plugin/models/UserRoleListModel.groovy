package com.rameses.admin.plugin.models;

import com.rameses.rcp.annotations.*;
import com.rameses.rcp.common.*;
import com.rameses.seti2.models.*;
import com.rameses.osiris2.common.*;
import com.rameses.util.*;
import com.rameses.osiris2.client.*;
         
public class UserRoleListModel extends AbstractUserRoleModel {
    
    def selectedRole;
    def listHandler;
   
    public def getUserQuery() {
        return [userid: user.objid];
    }
    
    def addRole() {
        def mq = [_schemaname: "sys_user_role"];
        mq.select = "role,org.*";
        mq.findBy = [userid: user.objid];
        def roles = queryService.getList( mq );
        
        def s = { o->
            o.roles.each { r->
                def m = [_schemaname : "sys_user_role" ];
                m.userid = user.objid;
                m.username = user.username;
                m.role = r;
                m.org = o.org;
                m.uid = m.userid+"_"+m.role + ( m.org?.objid == null? "" : "_"+m.org.objid );
                persistenceService.create( m );
            }
            listHandler.reload();
        }
        return Inv.lookupOpener("sys_auth_role:lookup", [ existingRoles: roles, onselect: s, queryService: queryService ] );
    }
    
    void init() {
        
    }
    
}

        