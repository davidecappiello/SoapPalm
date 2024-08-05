package it.prometeo.ServiceOMLO21.Event;

import ca.uhn.hl7v2.model.DataTypeException;
import ca.uhn.hl7v2.model.v25.message.ACK;
import ca.uhn.hl7v2.model.v25.message.OML_O21;
import ca.uhn.hl7v2.model.v25.message.ORL_O22;
import it.prometeo.Entity.MessageEvent;
import it.prometeo.Configuration.HL7Config;
import it.prometeo.Repository.MessageEventRepository;
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
    public MessageEvent saveOMLO21Message(OML_O21 omlO21, String msh3Value) throws DataTypeException {
        MessageEvent messageEvent = new MessageEvent();
        messageEvent.setCode(hl7Config.getMessageStructureOMLO21());
        if(msh3Value.equals("NGH"))
            messageEvent.setSource(hl7Config.getSourceDatabaseEventOMLO21PS());
        else if(msh3Value.equals("ONIX"))
            messageEvent.setSource(hl7Config.getSendingApplicationTransfusion());
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
    public void updateLatestOMLO21RecordWithError(OML_O21 omlO21, ACK ack) {
        MessageEvent messageEvent = messageEventRepository.findLatestByCodeAndDateAndSource(hl7Config.getMessageStructureOMLO21(), hl7Config.getReceivingApplication());
        messageEvent.setErrorDescription(ack.getMSA().getTextMessage().getValue());
    }

    @Transactional
    public void updateLatestOMLO21RecordWithFillerOrderNumberAndStatusPS(ORL_O22 orlFromTD) throws DataTypeException {
        MessageEvent messageEvent = messageEventRepository.findLatestByCodeAndDateAndSource(hl7Config.getMessageStructureOMLO21(), hl7Config.getReceivingApplication());
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

    @Transactional
    public void updateLatestOMLO21RecordWithFillerOrderNumberAndStatusTransfusion(ORL_O22 orlFromTD) throws DataTypeException {
        MessageEvent messageEvent = messageEventRepository.findLatestByCodeAndDateAndSource(hl7Config.getMessageStructureOMLO21(), hl7Config.getSendingApplicationTransfusion());
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