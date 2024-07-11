package com.mirth.prometeo.HL7PALM.Decoding;

import ca.uhn.hl7v2.DefaultHapiContext;
import ca.uhn.hl7v2.HL7Exception;
import ca.uhn.hl7v2.HapiContext;
import ca.uhn.hl7v2.model.Message;
import ca.uhn.hl7v2.model.v25.message.ORL_O22;
import ca.uhn.hl7v2.parser.*;

public class ORLDecoding {

    private static final XMLParser xmlParser = new DefaultXMLParser(new CanonicalModelClassFactory("2.5"));

    public static ORL_O22 decodeORL_XML(String hl7xml) throws HL7Exception {

        HapiContext context = new DefaultHapiContext();
        Parser parser = context.getGenericParser();

        Message message = parser.parse(hl7xml);

        return (ORL_O22) message;
    }

    public static String convertHl7ToXml(String hl7Message) throws HL7Exception {

        PipeParser pipeParser = new PipeParser(new CanonicalModelClassFactory("2.5"));
        Message message = pipeParser.parse(hl7Message);

        return xmlParser.encode(message);
    }
}
