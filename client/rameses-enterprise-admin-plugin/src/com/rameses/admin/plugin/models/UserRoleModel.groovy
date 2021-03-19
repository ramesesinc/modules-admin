package com.rameses.admin.plugin.models;

import com.rameses.rcp.annotations.*;
import com.rameses.rcp.common.*;
import com.rameses.seti2.models.*;
import com.rameses.osiris2.common.*;
import com.rameses.util.*;
import com.rameses.osiris2.client.*;
         
public class UserRoleModel extends CrudFormModel {
    
    String title = "User Role";

    def selectedObject;
    def objectList;
    def permissions;
    
    def unselected = [];
    
    public String getConnection() {
        return caller.getConnection();
    }

    void init() {
        def m = [_schemaname: 'sys_role_permission'];
        m.where = [ "role = :roleid", [roleid: entity.role ] ];
        permissions = queryService.getList(m);
        permissions.each {
            if( entity.exclude ) {
                def str = it.object + "." + it.permission;
                if( str.matches(entity.exclude) ) {
                    unselected << it;
                }
            }
        }
        objectList = permissions*.object.unique().collect{ [name: it] }.sort{};
        objectList.each { obj->
            obj.permissions = permissions.findAll{ it.object == obj.name }.sort{ it.permission };
        }        
    }
    
    void afterOpen() {
        init();
        mode = "open";
    }
    
    def permissionListModel = [
        isMultiSelect: {
            return true;
        },
        isItemSelected: { item-> 
            return !unselected.contains( item );
        }, 
        afterSelectionChange: { event-> 
            if(!unselected.remove( event.data )) {
                unselected << event.data;
            } 
        },
        fetchList: {
            if( !selectedObject ) return [];
            return selectedObject.permissions;
        }
    ] as EditorListModel; 
    
    def doOk() {
        if(mode=="create") {
            save();
        }
        else {
            def str = "{NULL}";
            if( unselected ) {
                def vv = unselected.groupBy{ it.object };
                def vl = [];
                vv.each { k,v->
                    vl << k + ".(" + v*.permission.join("|") + ")";
                }
                str = vl.join( "|" );
            }
            def m = [:];
            m._schemaname = schemaName;
            m.objid = entity.objid;
            m.exclude = str;
            persistenceService.update( m );
        }
        return "_close";
    }
    
    def doCancel() {
        return "_close";
    }
    
    @PropertyChangeListener
    def propertyListener = [
        "entity.user" : { o->
            entity.userid = o.objid;
            entity.username = o.username;
            binding.refresh("entity.user");
        }
    ];
    
    def create() {
        entity = [:];
        entity.role = caller.getSelectedNode().name;
        mode = "create";
        _inited_ = true;
        return "create"
    }
}
        