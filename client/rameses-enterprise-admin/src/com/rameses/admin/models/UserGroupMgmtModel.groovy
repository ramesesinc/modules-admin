package com.rameses.admin.models;

import com.rameses.rcp.annotations.*;
import com.rameses.rcp.common.*;
import com.rameses.seti2.models.*;
import com.rameses.osiris2.common.*;
import com.rameses.util.*;
import com.rameses.osiris2.client.*;
import com.rameses.enterprise.models.*;
import java.rmi.server.UID;
         
public class UserGroupMgmtModel  {
    
    @Service("QueryService")
    def qrySvc;
    
    def domain = "MAIN";
    def domainList;
    def _roleHandlerMap = [:];
    
     public def getRoleHandler() {
        if( !_roleHandlerMap.containsKey(domain) ) {
            def inv = Inv.lookupOpener( "sys_role_list:" + domain.toLowerCase(), [:] );
            _roleHandlerMap.put( domain, inv );
        };
        return _roleHandlerMap.get( domain );
    }

    void init() {
        def m = [_schemaname: "sys_domain"];
        m.where = ["1=1"];
        domainList = ["MAIN"] + qrySvc.getList( m )*.name;
    }
    
}


        