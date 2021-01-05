package com.rameses.admin.plugin.models;

import com.rameses.rcp.annotations.*;
import com.rameses.rcp.common.*;
import com.rameses.seti2.models.*;
import com.rameses.osiris2.common.*;
import com.rameses.util.*;
import com.rameses.osiris2.client.*;
         
public class AbstractUserRoleModel  {
    
    @Binding
    def binding;
    
    @Controller
    def workunit;
    
    def _connection;
    def persistenceSvc;
    def qryService;
    
    def user;

    public def getPersistenceService() {
        String conn = getConnection();
        if( conn!=null && conn.trim().length() > 0 ) {
            return InvokerProxy.instance.create("PersistenceService", null, conn );
        }
        else {
            return persistenceSvc;
        }
    }
    
    public def getQueryService() {
        String conn = getConnection();
        if( conn!=null && conn.trim().length() > 0 ) {
            return InvokerProxy.instance.create("QueryService", null, conn );
        }
        else {
            return qryService;
        }
    }
    
    public String getConnection() {
        if( _connection !=null && _connection.trim().length() > 0 ) return _connection;
        if( workunit?.workunit?.module?.properties?.connection ) {
            _connection = workunit.workunit.module.properties.connection;
        }
        return _connection;
    }

    
    
    
}

        