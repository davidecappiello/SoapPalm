package it.prometeo.HL7Palm.Decoding;

import ca.uhn.hl7v2.HL7Exception;
import ca.uhn.hl7v2.model.DataTypeException;
import ca.uhn.hl7v2.model.Message;
import ca.uhn.hl7v2.model.v25.message.OML_O21;
import ca.uhn.hl7v2.parser.CanonicalModelClassFactory;
import ca.uhn.hl7v2.parser.DefaultXMLParser;
import ca.uhn.hl7v2.parser.PipeParser;
import ca.uhn.hl7v2.parser.XMLParser;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public class OMLDecoding {

    public static OML_O21 decodeOML_XML(String hl7) throws HL7Exception, IOException, ParserConfigurationException, SAXException, DataTypeException {

        XMLParser xmlParser = new DefaultXMLParser(new CanonicalModelClassFactory("2.5"));

        return (OML_O21) xmlParser.parse(hl7);
    }

    public static OML_O21 decodeOML_PIPE(String hl7) throws HL7Exception, IOException, ParserConfigurationException, SAXException {

        PipeParser pipeParser = new PipeParser(new CanonicalModelClassFactory("2.5"));
        XMLParser xmlParser = new DefaultXMLParser(new CanonicalModelClassFactory("2.5"));

        Message message = pipeParser.parse(hl7);
        String encodedMessagePIPE = pipeParser.encode(message);

        return (OML_O21) pipeParser.parse(encodedMessagePIPE);
    }

}
