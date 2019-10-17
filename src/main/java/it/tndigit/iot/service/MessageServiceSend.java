package it.tndigit.iot.service;

import it.tndigit.iot.exception.IotException;
import it.tndigit.iot.service.dto.message.MessageDTO;

import javax.jms.Message;

public interface MessageServiceSend {



    /**
     *
     * Invia il messaggio
     *
     * @return MessageDTO
     * @throws IotException
     */

    MessageDTO sendMessage(MessageDTO messageDTO) throws IotException;
    MessageDTO getMessage(MessageDTO messageDTO) throws IotException;
    void receiveSendMessage(MessageDTO messageDTO) throws IotException;;

}
