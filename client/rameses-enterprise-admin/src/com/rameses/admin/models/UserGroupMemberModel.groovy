package com.rameses.admin.models;

import com.rameses.rcp.annotations.*;
import com.rameses.rcp.common.*;
import com.rameses.osiris2.client.*;
import com.rameses.osiris2.common.*;
import com.rameses.util.BreakException;
import java.rmi.server.UID;

public class UserGroupMemberModel {

    @Binding 
    def binding; 
    
    @Service('UsergroupMemberService') 
    def service;

    @Service('SecuritygroupService')
    def secSvc;

    def saveHandler;
    def entity;
    def node;
    def view = "user";   //user or usergroup

    def mode;
    boolean allowExtActions = true; 
    
    void create() {
        if ( !node?.usergroupid )
        throw new BreakException(); 

        mode = 'create';
        view = "usergroup";
        entity = [objid:"UGM"+ new UID(), usergroup: [objid: node.usergroupid] ];
    }

    void open() {
        mode = 'open';
        view = "usergroup";
        entity = service.open( entity );
    }
            
    void edit() {
        mode = 'edit';        
        entity = service.open( entity );
    }
            
    def getLookupUsergroup() {
        return InvokerUtil.lookupOpener( "usergroup:lookup", [
                onselect: { o->
                    entity.usergroup = o;
                    entity.domain = o.domain;
                    entity.role = o.role;
                }
            ]);
    }
            
    def getLookupOrg() {
        return InvokerUtil.lookupOpener( "org:lookup", [
                onselect:{o->
                    entity.org = o;
                }
            ] );
    }
            
    //used for usergroup. 
    def getLookupUser() {
        return InvokerUtil.lookupOpener("user:lookup", [
                onselect: { u->
                    entity.user = [objid:u.objid, username:u.username, firstname:u.firstname, lastname:u.lastname];
                }
            ]);
    }
            
    def getSecuritygroups() {
        def usergroupid = entity.usergroup?.objid;
        if (!usergroupid) return null;
        return secSvc.getList([usergroupid: usergroupid]);             
    }

    def showPermissions() {
        def h = { o->
            entity.exclude = o;
        }
        return InvokerUtil.lookupOpener( "securitygroup:custom", 
            [usergroup:entity.usergroup, securitygroup: entity.securitygroup, 
                handler:h, customExclude: entity.exclude, forceExclude: entity.securitygroup?.exclude ] 
        );
    }
            
    def doOk() {
        service.save( entity );
        if(saveHandler) saveHandler(entity);
        return "_close";
    }
            
    def doCancel() {
        return "_close";
    } 
    
    void viewUser() {
        new Thread({ openUserInfo(); } as Runnable).start(); 
        binding.fireNavigation('_close');         
    }
    
    void openUserInfo() {
        def param = [entity: [:]]; 
        param.entity.objid = entity.user.objid;
        Modal.show('sys_user:open', param); 
    }
}
