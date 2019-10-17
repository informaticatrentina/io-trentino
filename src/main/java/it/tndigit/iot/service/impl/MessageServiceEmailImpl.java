package it.tndigit.iot.service.impl;

import it.tndigit.iot.exception.IotException;
import it.tndigit.iot.service.MessageServiceSend;
import it.tndigit.iot.service.dto.message.MessageDTO;
import it.tndigit.iot.service.dto.message.NotificationDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.jms.Message;
import java.time.LocalDateTime;
import java.util.HashSet;

@Service
@Transactional
public class MessageServiceEmailImpl extends MessageServiceAbstract implements MessageServiceSend {
    private final Logger log = LoggerFactory.getLogger(MessageServiceEmailImpl.class);



    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    ApplicationContext applicationContext;


    @Override
    public MessageDTO sendMessage(MessageDTO messageDTO) throws IotException {
        return null;
    }

    @Override
    public MessageDTO getMessage(MessageDTO messageDTO) throws IotException {
        return null;
    }

    @Override
    @JmsListener(destination = "EMAIL_QUEUE", containerFactory = "myFactory")
    public void receiveSendMessage(MessageDTO messageDTO) throws IotException{


        log.info(" RICEVUTO MESSAGGIO IO_ITALIA CON ID " + messageDTO.getIdObj());

        try{
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setTo(messageDTO.getEmail());
            mailMessage.setSubject(messageDTO.getOggetto());
            mailMessage.setText(messageDTO.getTesto());
            mailMessage.setFrom("io-trentino@tndigit.it");
            javaMailSender.send(mailMessage);
            this.createNotification(messageDTO);
            this.saveNotification(messageDTO);
        }catch (Exception ex){
            log.error(ex.getMessage());

        }

    }


    private void createNotification(MessageDTO messageDTO) throws IotException {

        if (log.isDebugEnabled()) {
            log.debug("Inizio elaborazione di conversione notirifa");
        }

        NotificationDTO notificationDTO = applicationContext.getBean(NotificationDTO.class);
        notificationDTO.setMessageDTO(messageDTO);
        notificationDTO.seteMailNotification("SENT");
        notificationDTO.setStatus("OK");
        notificationDTO.setLastChance(LocalDateTime.now());

        if (messageDTO.getNotificationDTOS() == null) {
            messageDTO.setNotificationDTOS(new HashSet<>());
        }
        messageDTO.getNotificationDTOS().add(notificationDTO);

    }
//
//
    private void saveNotification(MessageDTO messageDTO) {

        messageDTO.getNotificationDTOS()
                .stream()
                .map(notificationDTO -> notificationMapper.toEntity(notificationDTO))
                .forEach(notificationPO -> notificationRepository.saveAndFlush(notificationPO));

    }

}
