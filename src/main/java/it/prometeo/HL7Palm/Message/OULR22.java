package it.prometeo.HL7Palm.Message;

import ca.uhn.hl7v2.HL7Exception;
import ca.uhn.hl7v2.model.v25.message.ACK;
import ca.uhn.hl7v2.model.v25.message.OUL_R22;
import ca.uhn.hl7v2.model.v25.segment.*;
import it.prometeo.HL7Palm.SegmentFactory.SegmentFactoryOULR22;

import java.io.IOException;

public class OULR22 {

    public OUL_R22 generateOUL_R22() throws HL7Exception, IOException {

        OUL_R22 oul = new OUL_R22();

        MSH mshSegmentIntegrate = oul.getMSH();
        SegmentFactoryOULR22.createMSHSegmentIntegrateOULR22(mshSegmentIntegrate);

        PID pidSegmentIntegrate = oul.getPATIENT().getPID();
        SegmentFactoryOULR22.createPIDSegmentIntegrateOULR22(pidSegmentIntegrate);

        PV1 pv1SegmentIntegrate = oul.getVISIT().getPV1();
        SegmentFactoryOULR22.createPV1SegmentIntegrateOULR22(pv1SegmentIntegrate);

        SPM spmSegmentIntegrate = oul.getSPECIMEN().getSPM();
        SegmentFactoryOULR22.createSPMSegmentIntegrateOULR22(spmSegmentIntegrate);

        OBR obrSegmentIntegrate = oul.getSPECIMEN().getORDER(0).getOBR();
        SegmentFactoryOULR22.createOBRSegmentIntegrateOULR22(obrSegmentIntegrate);

        ORC orcSegmentIntegrate = oul.getSPECIMEN().getORDER(0).getORC();
        SegmentFactoryOULR22.createORCSegmentIntegrateOULR22(orcSegmentIntegrate);

        TQ1 tq1SegmentIntegrate = oul.getSPECIMEN().getORDER(0).getTIMING_QTY().getTQ1();
        SegmentFactoryOULR22.createTQ1SegmentIntegrateOULR22(tq1SegmentIntegrate);

        OBX obxSegmentIntegrate = oul.getSPECIMEN().getOBX();
        SegmentFactoryOULR22.createOBXSegmentIntegrateOULR22(obxSegmentIntegrate);

        return oul;
    }

    public ACK ackResponseToOULR22(OUL_R22 oul) throws HL7Exception, IOException {

        ACK generatedACK = (ACK) oul.generateACK();
        generatedACK.getMSA().getTextMessage().setValue("");

        return generatedACK;
    }
}
