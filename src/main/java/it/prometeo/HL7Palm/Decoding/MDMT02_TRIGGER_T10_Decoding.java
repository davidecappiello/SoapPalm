package com.mirth.prometeo.HL7Palm.Decoding;

import ca.uhn.hl7v2.HL7Exception;
import ca.uhn.hl7v2.model.v25.message.MDM_T02;
import ca.uhn.hl7v2.parser.CanonicalModelClassFactory;
import ca.uhn.hl7v2.parser.DefaultXMLParser;
import ca.uhn.hl7v2.parser.XMLParser;
import org.springframework.stereotype.Service;

@Service
public class MDMT02_TRIGGER_T10_Decoding {


    public MDM_T02 decodeMDMT02_Trigger_T10 (String hl7) throws HL7Exception {

        XMLParser xmlParser = new DefaultXMLParser(new CanonicalModelClassFactory("2.5"));

        return (MDM_T02) xmlParser.parse(hl7);
    }

}
