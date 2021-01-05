package com.rameses.admin.plugin.models;

import com.rameses.rcp.annotations.*;
import com.rameses.rcp.common.*;
import com.rameses.seti2.models.*;
import com.rameses.osiris2.common.*;
import com.rameses.util.*;
import com.rameses.osiris2.client.*;
         
public class UserRoleLookupModel extends CrudLookupModel {
    
    public def getColumnList() {
        return [
            [name: "username", caption:"User Name"],
            [name: "lastname", caption:"Last Name"],
            [name: "firstname", caption:"First Name"],
            [name: "middlename", caption:"Middle Name"],
            [name: "org.name", caption:"Org"],
        ];
    }
    
    void initLookup() {
        query.tag = "user_by_role";
        setCustomFilter( ["role = :role", query ] );
        super.init();
    }
}

        