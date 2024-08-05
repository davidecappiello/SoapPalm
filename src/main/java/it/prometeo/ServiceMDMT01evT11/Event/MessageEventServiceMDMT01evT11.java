package it.prometeo.ServiceMDMT01evT11.Event;

import ca.uhn.hl7v2.model.DataTypeException;
import ca.uhn.hl7v2.model.v25.message.MDM_T01;
import ca.uhn.hl7v2.model.v25.message.OML_O21;
import ca.uhn.hl7v2.model.v25.segment.ORC;
import it.prometeo.Configuration.HL7Config;
import it.prometeo.Entity.MessageEvent;
import it.prometeo.Repository.MessageEventRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageEventServiceMDMT01evT11 {

    private static HL7Config hl7Config = null;

    @Autowired
    public MessageEventServiceMDMT01evT11(HL7Config hl7Config) {
        MessageEventServiceMDMT01evT11.hl7Config = hl7Config;
    }
    @Autowired
    private MessageEventRepository messageEventRepository;

    @Transactional
    public MessageEvent saveMDMT01evT11Message(MDM_T01 mdmT01, OML_O21 omlO21) throws DataTypeException {
        MessageEvent messageEvent = new MessageEvent();
        ORC orcSegment = omlO21.getORDER().getORC();
        messageEvent.setCode(hl7Config.getMessageStructureMDMT01evT11());
        messageEvent.setSource(hl7Config.getSendingApplication());
        messageEvent.setPlacerGroupNumber(orcSegment.getPlacerGroupNumber().getEntityIdentifier().getValue());
        return messageEventRepository.save(messageEvent);
    }
}
