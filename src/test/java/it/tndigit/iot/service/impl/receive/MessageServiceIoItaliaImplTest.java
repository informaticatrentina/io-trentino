package it.tndigit.iot.service.impl.receive;

import it.tndigit.ioitalia.service.dto.NewMessage;
import it.tndigit.iot.domain.message.MessagePO;
import it.tndigit.iot.generate.MessageGenerate;
import it.tndigit.iot.service.dto.message.MessageDTO;
import it.tndigit.iot.service.mapper.MessageMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest()
@DisplayName( "Conversione messaggi io Italia")
@Transactional
@Slf4j
class MessageServiceIoItaliaImplTest {

    @Autowired
    MessageServiceIoItaliaImpl messageServiceIoItalia;

    @Autowired
    MessageGenerate messageGenerate;

    @Autowired
    MessageMapper messageMapper;

    @Autowired
    MessagePO messagePO;


    @Test
    @DisplayName("Conversione messaggio base")
    void convertMessage() {
        MessageDTO messageDTO = messageMapper.toDto(messageGenerate.getObjectPO());
        NewMessage newMessage = messageServiceIoItalia.convertMessage(messageDTO);
        assertNull(newMessage.getFiscalCode(), "Gestione Codice Fiscale");
        assertEquals(messageDTO.getScadenza(), newMessage.getContent().getDueDate(), "Gestione Scadenza");
        assertEquals(messageDTO.getOggetto(), newMessage.getContent().getSubject(), "Gestione oggetto messaggio");
        assertEquals(messageDTO.getTesto(), newMessage.getContent().getMarkdown(), "Gestione testo messaggio");

    }


    @Test
    @DisplayName("Conversione messaggio con Pagamento")
    void convertMessagePaymento() {
        MessageDTO messageDTO = messageMapper.toDto(messageGenerate.getObjectPOPayment(messagePO));
        messageDTO.getPaymentDTO().setIdObj(111111L);
        NewMessage newMessage = messageServiceIoItalia.convertMessage(messageDTO);

        assertEquals(messageDTO.getPaymentDTO().getImporto(), newMessage.getContent().getPaymentData().getAmount(), "Gestione Importo Pagamento");
        assertEquals(messageDTO.getPaymentDTO().getNumeroAvviso(), newMessage.getContent().getPaymentData().getNoticeNumber(), "Gestione Numero Avviso Pagamento");
        assertEquals(messageDTO.getPaymentDTO().getInvalid_after_due_date(), newMessage.getContent().getPaymentData().isInvalidAfterDueDate() , "Gestione Pagamento dopo scadenza");

    }

    @Test
    @DisplayName("Conversione messaggio con prescrizione")
    void convertMessagePrescription() {
        MessageDTO messageDTO = messageMapper.toDto(messageGenerate.getObjectPOPrescription(messagePO));
        messageDTO.getPrescriptionDTO().setIdObj(111111L);
        NewMessage newMessage = messageServiceIoItalia.convertMessage(messageDTO);

        assertEquals(messageDTO.getPrescriptionDTO().getCodiceFiscaleDottore(), newMessage.getContent().getPrescriptionData().getPrescriberFiscalCode(), "Gestione Codice Fiscale dottore in Prescrizione");
        assertEquals(messageDTO.getPrescriptionDTO().getIup(), newMessage.getContent().getPrescriptionData().getIup(), "Gestione IUP prescrizione");
        assertEquals(messageDTO.getPrescriptionDTO().getNre(), newMessage.getContent().getPrescriptionData().getNre(), "Gestione NRE prescrizione");

    }

}