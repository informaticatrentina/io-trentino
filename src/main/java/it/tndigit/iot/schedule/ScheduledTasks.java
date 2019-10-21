package it.tndigit.iot.schedule;


import it.tndigit.iot.costanti.TipoMessage;
import it.tndigit.iot.costanti.TipoRuoli;
import it.tndigit.iot.repository.MessageRepository;
import it.tndigit.iot.service.MessageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * @author  Mirko
 *
 * Class responsible for time management
 *
 */

public class ScheduledTasks {

    private final Logger log = LoggerFactory.getLogger(ScheduledTasks.class);

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private MessageService messageService;

    @Scheduled(fixedDelayString = "${iot.cron.fixedDelay}")
    public void timerCheckIoItalia() {

        AuthenticationUtil.configureAuthentication(TipoRuoli.JOB);
        log.info(" - Schedulazione cron partita alle ore " + LocalDateTime.now());

        messageRepository.findByTipoMessageAndExternIDNotNull(TipoMessage.IO_ITALIA)
                .stream()
                .peek(messagePO ->log.info("Elaborazione messaggio con id" + messagePO.getIdObj()))
                .forEach(messagePO ->{
                    messageService.checkMessage(messagePO.getIdObj(), messagePO.getCodiceFiscale());
                });



        log.info("Check effettuati");





        AuthenticationUtil.clearAuthentication();

    }


}



