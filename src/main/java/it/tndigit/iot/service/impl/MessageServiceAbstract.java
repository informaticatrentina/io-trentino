package it.tndigit.iot.service.impl;

import it.tndigit.iot.repository.ServizioRepository;
import it.tndigit.iot.repository.NotificationRepository;
import it.tndigit.iot.service.MessageServiceSend;
import it.tndigit.iot.service.mapper.NotificationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;

public abstract class MessageServiceAbstract implements MessageServiceSend {

    @Autowired
    protected NotificationRepository notificationRepository;

    @Autowired
    protected NotificationMapper notificationMapper;

    @Autowired
    protected ServizioRepository servizioRepository;

    @Autowired
    JavaMailSender javaMailSender;



}
