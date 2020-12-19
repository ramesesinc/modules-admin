package com.rameses.admin.models;

import com.rameses.rcp.annotations.*;
import com.rameses.rcp.common.*;
import com.rameses.seti2.models.*;
import com.rameses.osiris2.common.*;
import com.rameses.util.*;
import com.rameses.osiris2.client.*;
import java.rmi.server.*;
         
public class UserRoleUserModel  {
        
    @Service("UserService")
    def service;

    @Binding
    def binding;

    def user;
    def domainList;
    def selectedDomain;
    def selectedUsergroup;
    def usergroups;
    
    void refresh() {
        init();
    }
            
    void initList() {
        usergroups = service.getUsergroups( [objid: user.objid] );
        domainList = usergroups*.domain?.unique();
        domainList.sort{ it.toString() }
    }
            
    void init() {
        initList();
    }
            
    def addUsergroup() { 
        def params = [
            view: 'user',
            allowExtActions: false, 
            entity : [objid: "UGM"+ new UID(), user: user], 
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
    
    
}

        