package com.rameses.admin.models;

import com.rameses.rcp.annotations.*;
import com.rameses.rcp.common.*;
import com.rameses.seti2.models.*;
import com.rameses.osiris2.common.*;
import com.rameses.util.*;
import com.rameses.osiris2.common.*;
import com.rameses.rcp.common.*;
import com.rameses.osiris2.client.*;
import com.rameses.enterprise.models.*;
import com.rameses.osiris2.common.*;
import java.rmi.server.*;
         
public class UserModel  {
        
    @Service("UserService")
    def service;

    @Service("QueryService")
    def qrySvc;
    
    @Binding
    def binding;

    @FormTitle
    def title;
            
    def entity;
    def initiator = 'edit';
    def domainList;
    def selectedDomain;
    def selectedUsergroup;
    def usergroups;
    
    //this is the code for the new domain list
    def xdomain = "MAIN";
    def xdomainList;
    def _roleHandlerMap = [:];
    
    void refresh() {
        title = entity.name;
        init();
    }
            
    void open() {
        initiator = 'open';
        entity = service.open( [objid: entity.objid]);
        refresh();
    }

    void edit() {
        def h = { o->
            entity.putAll( o );
            refresh();
        }
        binding.fireNavigation( Inv.lookupOpener( "sys_user:edit", [entity: entity, handler: h] ) );
    }
    
    void initList() {
        usergroups = service.getUsergroups( [objid: entity.objid] );
        domainList = usergroups*.domain?.unique();
        domainList.sort{ it.toString() };
        
        //added domain list
        xdomainList = ["MAIN"];
        def m = [_schemaname: "sys_domain"];
        m.where = ["1=1"];
        xdomainList.addAll( qrySvc.getList( m )*.name );
    }
            
    void init() {
        initList();
    }
            
    def addUsergroup() { 
        def params = [
            view: 'user',
            allowExtActions: false, 
            entity : [objid: "UGM"+ new UID(), user: entity], 
            saveHandler:{ o->
                if( !usergroups ) usergroups = [];
                usergroups << o; 
                initList();
                binding.refresh();
                usergroupList.reload(); 
            }
        ]; 
        return InvokerUtil.lookupOpener('usergroup:add', params); 
    } 
            
    def editUsergroup() { 
        if(!selectedUsergroup) return;
        def params = [
            view: 'user',
            allowExtActions: false, 
            entity: selectedUsergroup,
            saveHandler: { o->
                initList();
                binding.refresh();
                usergroupList.reload(); 
            }
        ];
        return InvokerUtil.lookupOpener( 'usergroup:edit', params );
    }
            
    void removeUsergroup() { 
        if(!selectedUsergroup) return;
        if( MsgBox.confirm("You are about to remove this entry. Continue?")) {
            service.removeUsergroup(selectedUsergroup);
            usergroups.remove(selectedUsergroup);
            initList();
            usergroupList.reload(); 
        }
    }

    def usergroupList = [
        fetchList: { 
            if(!selectedDomain ) return [];
            return usergroups.findAll{ it.domain == selectedDomain }.sort{ it.role.toString() }
        }
    ] as BasicListModel;
    
 
    def resetPassword() {
        return Inv.lookupOpener('user:resetpassword', [entity: entity]); 
    } 
    
    public def getRoleHandler() {
        if( !_roleHandlerMap.containsKey(xdomain) ) {
            def inv = Inv.lookupOpener( "sys_user_role_list:" + xdomain.toLowerCase(), [user: entity ] );
            _roleHandlerMap.put( xdomain, inv );
        };
        return _roleHandlerMap.get( xdomain );
    }
    
}

        