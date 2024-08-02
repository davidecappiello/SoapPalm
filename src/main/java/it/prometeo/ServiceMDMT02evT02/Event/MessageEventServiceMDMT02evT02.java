package it.prometeo.ServiceMDMT02evT02.Event;

import ca.uhn.hl7v2.model.DataTypeException;
import ca.uhn.hl7v2.model.v25.message.MDM_T02;
import ca.uhn.hl7v2.model.v25.message.OML_O21;
import ca.uhn.hl7v2.model.v25.segment.ORC;
import it.prometeo.Configuration.HL7Config;
import it.prometeo.Entity.MessageEvent;
import it.prometeo.Repository.MessageEventRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageEventServiceMDMT02evT02 {

    private static HL7Config hl7Config = null;

    @Autowired
    public MessageEventServiceMDMT02evT02(HL7Config hl7Config) {
        MessageEventServiceMDMT02evT02.hl7Config = hl7Config;
    }
    @Autowired
    private MessageEventRepository messageEventRepository;

    @Transactional
    public MessageEvent saveMDMT02evT02Message(MDM_T02 mdmT02, OML_O21 omlO21) throws DataTypeException {
        MessageEvent messageEvent = new MessageEvent();
        ORC orcSegment = omlO21.getORDER().getORC();
        messageEvent.setCode(hl7Config.getMessageStructureMDMT02evT02());
        messageEvent.setSource(hl7Config.getSendingApplication());
        messageEvent.setPlacerGroupNumber(orcSegment.getPlacerGroupNumber().getEntityIdentifier().getValue());
        return messageEventRepository.save(messageEvent);
    }
}
