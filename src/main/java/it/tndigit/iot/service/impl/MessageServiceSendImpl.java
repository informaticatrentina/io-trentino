package it.tndigit.iot.service.impl;

import it.tndigit.iot.common.UtilityCrypt;
import it.tndigit.iot.common.UtilityIot;
import it.tndigit.iot.domain.ServizioPO;
import it.tndigit.iot.domain.message.MessagePO;
import it.tndigit.iot.exception.IotException;
import it.tndigit.iot.repository.MessageRepository;
import it.tndigit.iot.repository.ServizioRepository;
import it.tndigit.iot.service.MessageServiceReceive;
import it.tndigit.iot.service.MessageServiceSend;
import it.tndigit.iot.service.dto.ServizioDTO;
import it.tndigit.iot.service.dto.message.MessageDTO;
import it.tndigit.iot.service.mapper.MessageMapper;
import it.tndigit.iot.service.mapper.ServizioMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.util.Optional;


@Service
@Slf4j
public class
MessageServiceSendImpl implements MessageServiceSend {

    private static String CODICEFISCALE= " codice fiscale ";

    @Autowired
    ApplicationContext applicationContext;

    @Autowired
    MessageRepository messageRepository;

    @Autowired
    protected ServizioRepository servizioRepository;

    @Autowired
    private MessageMapper messageMapper;

    @Autowired
    private ServizioMapper enteMapper;

    @Autowired
    private RabbitTemplate rabbitTemplate;


    @Autowired
    EntityManager entityManager;

    protected Optional<ServizioDTO> getServizio(){
        String utente  = UtilityIot.getUserName();

        if (utente==null || utente.isEmpty()){
            return Optional.empty();
        }else {
            Optional<ServizioPO> entePOOptional = servizioRepository.findAllByCodiceIdentificativo(utente);
            if(entePOOptional.isPresent()){
                return Optional.of(enteMapper.toDto(entePOOptional.get()));
            }
            return  Optional.empty();
        }
    }



    @Override
    @Transactional
    public MessageDTO sendMessageInCode(MessageDTO messageDTO) {
        Optional<ServizioDTO> servizioDTOOptional = getServizio();
        if (!servizioDTOOptional.isPresent()){
            throw  new IotException("Impossibile inviare il messaggio, Servizio NON presente");
        }
        messageDTO.setServizioDTO(servizioDTOOptional.get());

        //Assegno il codice identificativo al mesasggio

        String digestString = messageDTO.getTesto() + messageDTO.getOggetto() + messageDTO.getCodiceFiscale() + LocalDate.now().toString();
        messageDTO.setCodiceIdentificativo(DigestUtils.md5DigestAsHex(digestString.getBytes()));

        //Save the new message on the database
        MessagePO messagePO = messageMapper.toEntity(messageDTO);
        messagePO = messageRepository.saveAndFlush(messagePO);
        messageDTO = messageMapper.toDto(messagePO);
        rabbitTemplate.convertAndSend(messageDTO.getTipoMessage().name() + "_QUEUE", messageDTO );
        entityManager.flush();
        entityManager.detach(messagePO);
        return messageDTO;
    }


    @Override
    @Transactional
    public Optional<MessageDTO> checkMessage(Long idObj, String codiceFiscale)  {


        log.info("INIZIO elaborazione check per id " + idObj + ", "+ CODICEFISCALE+  codiceFiscale);


        //Cerco il messaggio nella base dati
        Optional<MessagePO> messagePO = messageRepository.findByIdObjAndAndCodiceFiscaleAndExternIDIsNotNull(idObj,codiceFiscale);

        if (messagePO.isPresent()){
            //Converto il messaggio
            MessageDTO messageDTO = messageMapper.toDto(messagePO.get());
            MessageServiceReceive messageService = (MessageServiceReceive) applicationContext.getBean(messagePO.get().getTipoMessage().getMessageService());
            messageDTO = messageService.getMessage(messageDTO);

            log.info("FINE elaborazione check per id " + idObj + "," + CODICEFISCALE + codiceFiscale);
            return Optional.of(messageDTO);
        }


        log.info("IMPOSSIBILE elaborare check per id " + idObj + ", " +CODICEFISCALE+ codiceFiscale);
        return Optional.empty();

    }


    @Override
    public Optional<MessageDTO> getMessage(Long idObj, String codiceFiscale) {

       Optional<MessagePO> messagePO = messageRepository.findByIdObjAndAndCodiceFiscale(idObj,codiceFiscale);

        if (messagePO.isPresent()){
            MessageDTO messageDTO = messageMapper.toDto(messagePO.get());
            return Optional.of(messageDTO);
        }

        return Optional.empty();

    }

    @Override
    public Optional<MessageDTO> getMessage(String codiceIdentificativo, String codiceFiscale) {
        Optional<MessagePO> messagePO = messageRepository.findByCodiceIdentificativoAndCodiceFiscale(codiceIdentificativo,codiceFiscale);

        if (messagePO.isPresent()){
            MessageDTO messageDTO = messageMapper.toDto(messagePO.get());
            return Optional.of(messageDTO);
        }

        return Optional.empty();

    }

}

