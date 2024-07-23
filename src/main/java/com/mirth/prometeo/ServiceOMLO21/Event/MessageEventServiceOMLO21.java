package com.mirth.prometeo.ServiceOMLO21.Event;

import ca.uhn.hl7v2.model.DataTypeException;
import ca.uhn.hl7v2.model.v25.message.OML_O21;
import ca.uhn.hl7v2.model.v25.message.ORL_O22;
import com.mirth.prometeo.Entity.MessageEvent;
import com.mirth.prometeo.HL7Config;
import com.mirth.prometeo.HL7Palm.SegmentFactory.SegmentFactoryORMOO1;
import com.mirth.prometeo.Repository.MessageEventRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageEventServiceOMLO21 {

    private static HL7Config hl7Config = null;

    @Autowired
    public MessageEventServiceOMLO21(HL7Config hl7Config) {
        MessageEventServiceOMLO21.hl7Config = hl7Config;
    }
    @Autowired
    private MessageEventRepository messageEventRepository;

    @Transactional
    public MessageEvent saveOMLO21Message(OML_O21 omlO21) throws DataTypeException {
        MessageEvent messageEvent = new MessageEvent();
        messageEvent.setCode(hl7Config.getMessageStructureOMLO21());
        messageEvent.setSource(hl7Config.getSourceDatabaseEventOMLO21PS());
        messageEvent.setPlacerGroupNumber(omlO21.getORDER().getORC().getPlacerGroupNumber().getEntityIdentifier().getValue());
        return messageEventRepository.save(messageEvent);
    }

    @Transactional
    public MessageEvent saveOMLO21MessageCheckStatus(OML_O21 omlO21) throws DataTypeException {
        MessageEvent messageEvent = new MessageEvent();
        messageEvent.setCode(hl7Config.getMessageStructureOMLO21());
        messageEvent.setSource(hl7Config.getSourceDatabaseEventOMLO21PSCheckStatus());
        if(omlO21.getORDER().getORC().getPlacerGroupNumber().getEntityIdentifier().getValue() != null)
            messageEvent.setPlacerGroupNumber(omlO21.getORDER().getORC().getPlacerGroupNumber().getEntityIdentifier().getValue());
        return messageEventRepository.save(messageEvent);
    }

    @Transactional
    public void updateLatestOMLO21RecordWithFillerOrderNumberAndStatus(ORL_O22 orlFromTD) throws DataTypeException {
        MessageEvent messageEvent = messageEventRepository.findLatestByCodeAndDate(hl7Config.getMessageStructureOMLO21());
        messageEvent.setFillerOrderNumber(orlFromTD.getRESPONSE().getPATIENT().getORDER().getORC().getFillerOrderNumber().getEntityIdentifier().getValue());
        String requestStatus = orlFromTD.getMSA().getAcknowledgmentCode().getValue();
        switch(requestStatus) {
            case "AA":
                messageEvent.setStatus(hl7Config.getStatusPositiveDatabase());
                messageEventRepository.save(messageEvent);
                break;
            case "AR":
                messageEvent.setStatus(hl7Config.getStatusRejectedDatabase());
                messageEventRepository.save(messageEvent);
                break;
            case "AE":
                messageEvent.setStatus(hl7Config.getStatusErrorDatabase());
                messageEventRepository.save(messageEvent);
                break;
        }
    }

}