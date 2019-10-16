package it.tndigit.iot.service.impl;

import it.tndigit.iot.common.UtilityIot;
import it.tndigit.iot.domain.EntePO;
import it.tndigit.iot.domain.message.MessagePO;
import it.tndigit.iot.exception.IotException;
import it.tndigit.iot.repository.EnteRepository;
import it.tndigit.iot.repository.MessageRepository;
import it.tndigit.iot.service.MessageService;
import it.tndigit.iot.service.MessageServiceSend;
import it.tndigit.iot.service.dto.EnteDTO;
import it.tndigit.iot.service.dto.message.MessageDTO;
import it.tndigit.iot.service.mapper.EnteMapper;
import it.tndigit.iot.service.mapper.MessageMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
//@Transactional
public class MessageServiceImpl implements MessageService {
    private final Logger log = LoggerFactory.getLogger(MessageServiceImpl.class);

    @Autowired
    ApplicationContext applicationContext;

    @Autowired
    MessageRepository messageRepository;

    @Autowired
    protected EnteRepository enteRepository;



    @Autowired
    private MessageMapper messageMapper;

    @Autowired
    private EnteMapper enteMapper;

    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;


    protected Optional<EnteDTO> getEnte(){

        String utente  = UtilityIot.getUserName();

        if (utente==null || utente.isEmpty()){
            return Optional.empty();
        }else {
            Optional<EntePO> entePOOptional = enteRepository.findByEmailPec(utente);
            if(entePOOptional.isPresent()){
                return Optional.of(enteMapper.toDto(entePOOptional.get()));
            }
            return  Optional.empty();
        }
    }


    @Override
    public MessageDTO sendMessage(MessageDTO messageDTO) throws IotException {
        return null;


//        Optional<EnteDTO> enteDTOOptional = getEnte();
//        if (!enteDTOOptional.isPresent()){
//            throw  new IotException("Impossibile inviare il messaggio, Ente NON presente");
//        }
//        messageDTO.setEnteDTO(enteDTOOptional.get());
//
//
//        //Save the new message on the database
//        MessagePO messagePO = messageMapper.toEntity(messageDTO);
//        messagePO = messageRepository.saveAndFlush(messagePO);
//
//
//        MessageServiceSend messageService = (MessageServiceSend) applicationContext.getBean(messageDTO.getTipoMessage().getMessageService());
//        messageDTO = messageService.sendMessage(messageMapper.toDto(messagePO));
//
//        MessagePO messagePOCaricato = messageRepository.getOne(messagePO.getIdObj());
//        messagePOCaricato.setExternID(messageDTO.getExternID());
//        messagePO = messageRepository.saveAndFlush(messagePO);
//        messageDTO = messageMapper.toDto(messagePO);
//        return messageDTO;

    }

    @Override
    public MessageDTO sendMessageInCode(MessageDTO messageDTO) throws IotException {

        Optional<EnteDTO> enteDTOOptional = getEnte();
        if (!enteDTOOptional.isPresent()){
            throw  new IotException("Impossibile inviare il messaggio, Ente NON presente");
        }
        messageDTO.setEnteDTO(enteDTOOptional.get());

        //Save the new message on the database
        MessagePO messagePO = messageMapper.toEntity(messageDTO);
        messagePO = messageRepository.saveAndFlush(messagePO);
        messageDTO = messageMapper.toDto(messagePO);

        jmsMessagingTemplate.convertAndSend(messageDTO.getTipoMessage().name() + "_QUEUE", messageDTO );

        return messageDTO;
    }





    @Override
    public Optional<MessageDTO> checkMessage(Long idObj, String codiceFiscale) throws IotException {


        log.info("INIZIO elaborazione check per id " + idObj + ", codice fiscale "+ codiceFiscale);


        //Cerco il messaggio nella base dati
        Optional<MessagePO> messagePO = messageRepository.findByIdObjAndAndCodiceFiscale(idObj,codiceFiscale);

        if (messagePO.isPresent()){
            //Converto il messaggio
            MessageDTO messageDTO = messageMapper.toDto(messagePO.get());
            MessageServiceSend messageService = (MessageServiceSend) applicationContext.getBean(messagePO.get().getTipoMessage().getMessageService());
            messageDTO = messageService.getMessage(messageDTO);

            log.info("FINE elaborazione check per id " + idObj + ", codice fiscale "+ codiceFiscale);
            return Optional.of(messageDTO);
        }


        log.info("IMPOSSIBILE elaborare check per id " + idObj + ", codice fiscale "+ codiceFiscale);
        return Optional.empty();

    }


    @Override
    public Optional<MessageDTO> getMessage(Long idObj, String codiceFiscale) throws IotException {

        Optional<MessagePO> messagePO = messageRepository.findByIdObjAndAndCodiceFiscale(idObj,codiceFiscale);

        if (messagePO.isPresent()){
            MessageDTO messageDTO = messageMapper.toDto(messagePO.get());
            return Optional.of(messageDTO);
        }

        return Optional.empty();

    }
}

