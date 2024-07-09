//
// Questo file è stato generato dall'Eclipse Implementation of JAXB, v3.0.0 
// Vedere https://eclipse-ee4j.github.io/jaxb-ri 
// Qualsiasi modifica a questo file andrà persa durante la ricompilazione dello schema di origine. 
// Generato il: 2024.07.04 alle 07:02:47 PM CEST 
//


package com.mirth.connect.connectors.ws;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "acceptMessageResponse", propOrder = {
    "_return"
})
public class AcceptMessageResponse {

    @XmlElement(name = "return")
    protected String _return;

    public String get_Return() {
        return _return;
    }

    public void set_Return(String value) {
        this._return = value;
    }

}
