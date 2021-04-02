package com.rameses.admin.plugin.models;

import com.rameses.rcp.annotations.*;
import com.rameses.rcp.common.*;
import com.rameses.seti2.models.*;
import com.rameses.osiris2.common.*;
import com.rameses.util.*;
import com.rameses.osiris2.client.*;
         
public class RoleUsersListModel extends CrudListModel {
    
    def _roleList;
    
    public boolean isShowClose() {
        return true;
    }
    
    public def getNodeList() {
        if( !_roleList ) {
            def m = [_schemaname: "sys_role"];
            m.select = "name";
            m.where = ["1=1"];
            _roleList = queryService.getList(m).collect{ [name:it.name, title: it.name] };
        }
        return _roleList;
    }
    
    public def getCustomFilter() {
        if(!selectedNode) return null;
        def str = "role = :role";
        def param = [role: selectedNode.name];
        return [str, param];
    }
    
    def addUserRole() {
        def r = Inv.lookupOpener("sys_user:lookup", [:]);
        return r;
    }
    
    
    
}

        