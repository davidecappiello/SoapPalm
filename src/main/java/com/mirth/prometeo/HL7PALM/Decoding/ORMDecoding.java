package com.mirth.prometeo.HL7PALM.Decoding;

import ca.uhn.hl7v2.HL7Exception;
import ca.uhn.hl7v2.model.v25.message.ORM_O01;
import ca.uhn.hl7v2.parser.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public class ORMDecoding {

    public static ORM_O01 decodeORM_XML(String hl7) throws HL7Exception, IOException, ParserConfigurationException, SAXException {

        XMLParser xmlParser = new DefaultXMLParser(new CanonicalModelClassFactory("2.5"));

        return (ORM_O01) xmlParser.parse(hl7);
    }

    public static String decodeORM_PIPE(ORM_O01 ormO01) throws HL7Exception {

        Parser parser = new PipeParser(new CanonicalModelClassFactory("2.5"));

        return String.valueOf(parser.parse(String.valueOf(ormO01)));
    }


}
