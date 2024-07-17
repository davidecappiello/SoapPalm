package Prometeo.HL7Palm.Message.Custom;


import Prometeo.HL7Palm.Segment.ZET;
import ca.uhn.hl7v2.HL7Exception;
import ca.uhn.hl7v2.model.v25.segment.*;
import ca.uhn.hl7v2.parser.DefaultModelClassFactory;
import ca.uhn.hl7v2.parser.ModelClassFactory;


public class RSP_K11 extends ca.uhn.hl7v2.model.v25.message.RSP_K11 {


    public RSP_K11() {
        this(new DefaultModelClassFactory());
    }

    public RSP_K11(ModelClassFactory factory) {
        super(factory);
        this.init(factory);
    }

    private void init(ModelClassFactory factory) {
        try {
            this.add(SPM.class, true, false);
            this.add(ORC.class, true, false);
            this.add(TQ1.class, true, false);
            this.add(OBR.class, true, false);
            this.add(OBX.class, true, false);
            this.add(ZET.class, true, false);

        } catch (HL7Exception var3) {
            log.error("Unexpected error creating RSP_K11 - this is probably a bug in the source code generator.", var3);
        }
    }

    public SPM getSPM (){
        return (SPM) this.getTyped("SPM", SPM.class);
    }
    public ORC getORC (){
        return (ORC) this.getTyped("ORC", ORC.class);
    }
    public TQ1 getTQ1 (){
        return (TQ1) this.getTyped("TQ1", TQ1.class);
    }
    public OBR getOBR (){
        return (OBR) this.getTyped("OBR", OBR.class);
    }
    public OBX getOBX (){
        return (OBX) this.getTyped("OBX", OBX.class);
    }
    public ZET getZET (){
        return (ZET) this.getTyped("ZET", ZET.class);
    }

}