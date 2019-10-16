package it.tndigit.iot.service.impl;

import it.tndigit.iot.repository.EnteRepository;
import it.tndigit.iot.repository.NotificationRepository;
import it.tndigit.iot.service.MessageServiceSend;
import it.tndigit.iot.service.mapper.NotificationMapper;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class MessageServiceAbstract implements MessageServiceSend {

    @Autowired
    protected NotificationRepository notificationRepository;

    @Autowired
    protected NotificationMapper notificationMapper;

    @Autowired
    protected EnteRepository enteRepository;





}
