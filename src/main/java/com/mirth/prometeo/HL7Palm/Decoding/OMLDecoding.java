package com.mirth.prometeo.HL7Palm.Decoding;

import ca.uhn.hl7v2.HL7Exception;
import ca.uhn.hl7v2.model.v25.message.OML_O21;
import ca.uhn.hl7v2.parser.CanonicalModelClassFactory;
import ca.uhn.hl7v2.parser.DefaultXMLParser;
import ca.uhn.hl7v2.parser.XMLParser;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public class OMLDecoding {

    public static OML_O21 decodeOML_XML(String hl7) throws HL7Exception, IOException, ParserConfigurationException, SAXException {

        XMLParser xmlParser = new DefaultXMLParser(new CanonicalModelClassFactory("2.5"));

        return (OML_O21) xmlParser.parse(hl7);
    }

}
