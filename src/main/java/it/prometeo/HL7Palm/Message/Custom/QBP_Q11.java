package it.prometeo.HL7Palm.Message.Custom;

import ca.uhn.hl7v2.HL7Exception;
import ca.uhn.hl7v2.model.v25.segment.*;
import ca.uhn.hl7v2.parser.*;

public class QBP_Q11 extends ca.uhn.hl7v2.model.v25.message.QBP_Q11 {

    public QBP_Q11() {
        this(new DefaultModelClassFactory());
    }

    public QBP_Q11(ModelClassFactory factory) {
        super(factory);
        this.init(factory);
    }



    private void init(ModelClassFactory factory) {
        try {
            this.add(EVN.class, false, false);
        } catch (HL7Exception var3) {
            log.error("Unexpected error creating QBP_Q11 - this is probably a bug in the source code generator.", var3);
        }
    }

    public EVN getEVN (){
        return (EVN) this.getTyped("EVN", EVN.class);
    }

}