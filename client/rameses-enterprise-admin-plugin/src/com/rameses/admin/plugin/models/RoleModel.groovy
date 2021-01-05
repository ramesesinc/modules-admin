package com.rameses.admin.plugin.models;

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
import com.rameses.seti2.models.*;
import java.rmi.server.UID;
         
public class RoleModel extends CrudFormModel {
    
    String title = "Role";
    def selectedItem;

    public String getConnection() {
        return caller.getConnection();
    }
    
    void removePermission() {
        if(!selectedItem) return;
        removeItem("permissions", selectedItem);
    }
    
    /*    
    def securitygroup;
    def securityGroupList = [
        fetchList: { o->
            def m = [_schemaname: 'sys_securitygroup'];
            m.where = ["usergroup.objid = :roleid", [roleid: entity.objid ] ];
            return queryService.getList(m);
        }
    ] as BasicListModel

    void addSecurityGroup() {
        def o = MsgBox.prompt("Enter Group Name");
        if(!o) return;
        def m = [_schemaname:'sys_securitygroup'];
        m.name = o;
        m.usergroup = [objid: entity.objid];
        m.exclude = "";
        persistenceService.create(m);
        securityGroupList.reload();
    }
    
    def viewSecurityGroup() {
        if( !securitygroup ) throw new Exception("Please select a security group first");
        def h = { o->
            def m = [_schemaname:'sys_securitygroup'];
            m.findBy = [objid: securitygroup.objid];
            m.exclude = o;
            persistenceService.update(m);
            securitygroup.exclude = o;
        }
        return Inv.lookupOpener( "securitygroup:open", [entity:securitygroup, handler: h]);
    }
    */
    
}
        