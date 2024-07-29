package it.prometeo.ServiceORLO22.Event;

import ca.uhn.hl7v2.model.DataTypeException;
import ca.uhn.hl7v2.model.v25.message.OML_O21;
import ca.uhn.hl7v2.model.v25.message.ORL_O22;
import it.prometeo.Entity.MessageEvent;
import it.prometeo.Configuration.HL7Config;
import it.prometeo.Repository.MessageEventRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageEventServiceORLO22 {

    private static HL7Config hl7Config = null;

    @Autowired
    public MessageEventServiceORLO22(HL7Config hl7Config) {
        MessageEventServiceORLO22.hl7Config = hl7Config;
    }

    @Autowired
    private MessageEventRepository messageEventRepository;

    @Transactional
    public MessageEvent saveORLO22Message(ORL_O22 orlo22, OML_O21 omlO21) throws DataTypeException {
        MessageEvent messageEvent = new MessageEvent();
        messageEvent.setCode(hl7Config.getMessageStructureORLO22());
        messageEvent.setSource(hl7Config.getSendingApplication());
        messageEvent.setPlacerGroupNumber(orlo22.getRESPONSE().getPATIENT().getORDER().getORC().getPlacerGroupNumber().getEntityIdentifier().getValue());
        messageEvent.setFillerOrderNumber(orlo22.getRESPONSE().getPATIENT().getORDER().getORC().getFillerOrderNumber().getEntityIdentifier().getValue());
        String requestStatus = orlo22.getMSA().getAcknowledgmentCode().getValue();
        switch (requestStatus) {
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
        return messageEventRepository.save(messageEvent);
    }

}