package com.rameses.admin.models;

import com.rameses.rcp.annotations.*;
import com.rameses.rcp.common.*;
import com.rameses.osiris2.client.*;
import com.rameses.osiris2.common.*;
import com.rameses.seti2.models.CrudListModel;

class TerminalListModel extends CrudListModel {

    public void initColumn( c ) { 
        if ( c.name == 'terminalid') {
            c.width = 120; 
            c.maxWidth = 150; 
        }
        else if ( c.name == 'macaddress' ) { 
            c.width = 120; 
            c.maxWidth = 150; 
        }
        else if ( c.name == 'dtregistered') {
            c.width = 140; 
            c.maxWidth = 150;
        }
    } 
}    