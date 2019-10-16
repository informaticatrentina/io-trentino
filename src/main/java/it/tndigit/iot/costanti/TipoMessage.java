package it.tndigit.iot.costanti;

import it.tndigit.iot.service.impl.MessageServiceEmailImpl;
import it.tndigit.iot.service.impl.MessageServiceIoItaliaImpl;

/**
 * @author Mirko
 * @see java.lang.Enum
 *
 * Discriminate the type of message in order to send it
 */

public enum TipoMessage {
    IO_ITALIA(MessageServiceIoItaliaImpl.class),
    TELEGRAM(MessageServiceIoItaliaImpl.class),
    EMAIL(MessageServiceEmailImpl.class),
    SMS(MessageServiceIoItaliaImpl.class);

    private Class messageService;


    TipoMessage(Class messageService) {
        this.messageService = messageService;
    }

    public Class getMessageService() {
        return messageService;
    }
}

