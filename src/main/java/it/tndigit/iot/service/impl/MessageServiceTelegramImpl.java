package it.tndigit.iot.service.impl;

import it.tndigit.iot.exception.IotException;
import it.tndigit.iot.service.MessageServiceSend;
import it.tndigit.iot.service.dto.message.MessageDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.jms.Message;

@Service
@Transactional
public class MessageServiceTelegramImpl implements MessageServiceSend {
    private final Logger log = LoggerFactory.getLogger(MessageServiceTelegramImpl.class);


    @Override
    public MessageDTO sendMessage(MessageDTO messageDTO) throws IotException {
        return null;
    }

    @Override
    public MessageDTO getMessage(MessageDTO messageDTO) throws IotException {
        return null;
    }

    @Override
    public MessageDTO receiveSendMessage(MessageDTO messageDTO) throws IotException {
        return null;
    }
}
