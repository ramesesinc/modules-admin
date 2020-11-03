package com.rameses.admin.models;

import com.rameses.rcp.annotations.*;
import com.rameses.rcp.common.*;
import com.rameses.osiris2.client.*;
import com.rameses.osiris2.common.*;
import java.rmi.server.*;

public class UserEditController  {
        
    @Service( "UserService" )
    def service;
            
    @Caller 
    def caller;
            
    def entity = [:];
    def handler;
    def mode;
            
    void create() {
        entity = [objid: "USR" + new UID()];
        mode = 'create'
    }
            
    void edit() {
        entity = service.open( [objid: entity.objid] ); 
        mode = 'edit'
    }
            
    def save() {
        entity.name = entity.lastname + ", " + entity.firstname;
        if( entity.middlename ) entity.name += ' ' + entity.middlename;
        if(mode=='create') {
            if( MsgBox.confirm("You are about to save this record. Proceed?")) {
                service.create( entity );
                
                if ( caller != null && caller.metaClass.respondsTo(caller, 'search')) {
                    caller.search(); 
                }
                
                def op = Inv.lookupOpener( "sys_user:open", [entity:entity, initiator: 'create']);
                op.target = "_self";
                return op;
            }
        }
        else {
            service.update( entity );
            if(handler) handler(entity);
            return "_close";
        }
    }
}     