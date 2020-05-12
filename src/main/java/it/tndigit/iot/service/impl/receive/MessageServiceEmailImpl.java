package it.tndigit.iot.service.impl.receive;

import it.tndigit.iot.costanti.TipoStatus;
import it.tndigit.iot.domain.message.MessagePO;
import it.tndigit.iot.exception.IotException;
import it.tndigit.iot.repository.MessageRepository;
import it.tndigit.iot.service.MessageServiceReceive;
import it.tndigit.iot.service.dto.message.MessageDTO;
import it.tndigit.iot.service.dto.message.NotificationDTO;
import it.tndigit.iot.service.impl.MessageServiceAbstract;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Optional;

@Service
@Transactional
@Slf4j
public class MessageServiceEmailImpl extends MessageServiceAbstract implements MessageServiceReceive {

    @Autowired
    ApplicationContext applicationContext;


    @Autowired
    MessageRepository messageRepository;


    @Override
    public MessageDTO sendMessage(MessageDTO messageDTO) throws IotException {
        return null;
    }

    @Override
    public MessageDTO getMessage(MessageDTO messageDTO) throws IotException {
        return null;
    }

    @Override
    @RabbitListener(queues = "EMAIL_QUEUE")
    @Transactional
    public void receiveSendMessage(MessageDTO messageDTO){
        log.info(" RICEVUTO MESSAGGIO EMAIL CON ID " + messageDTO.getIdObj());
        //Controllo che il messaggio sia presente nella base dati, potrebbe essere stato cancellato
        Optional< MessagePO > messagePO = messageRepository.findById(messageDTO.getIdObj());

        if (!messagePO.isPresent()){
            log.warn("Attenzione, messaggio "+ messageDTO.getIdObj() + " NON trovato nella base dati");
        }else{
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
    }


    private void createNotification(MessageDTO messageDTO) {
        NotificationDTO notificationDTO = applicationContext.getBean(NotificationDTO.class);
        notificationDTO.setMessageDTO(messageDTO);
        notificationDTO.setEmailNotification(TipoStatus.SENT);
        notificationDTO.setStatus(TipoStatus.ACCEPTED);
        notificationDTO.setLastChance(LocalDateTime.now());

        if (messageDTO.getNotificationDTOS() == null) {
            messageDTO.setNotificationDTOS(new HashSet<>());
        }
        messageDTO.getNotificationDTOS().add(notificationDTO);

    }

    private void saveNotification(MessageDTO messageDTO) {
        try{
            messageDTO.getNotificationDTOS()
                    .stream()
                    .map(notificationDTO -> notificationMapper.toEntity(notificationDTO))
                    .forEach(
                            notificationPO -> notificationRepository.saveAndFlush(notificationPO)
                    );
        }catch (DataIntegrityViolationException diEx){
            log.error("Messaggio " + messageDTO.getIdObj()  + "Non presente nella base dati -- Attenzione!!");

        }
        catch (Exception ex){
            log.error(ex.getMessage());

        }


    }

}
