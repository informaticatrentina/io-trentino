package it.tndigit.iot.generate;

import it.tndigit.iot.costanti.TipoMessage;
import it.tndigit.iot.domain.message.MessagePO;
import it.tndigit.iot.service.dto.message.MessageDTO;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;


@Service
public class MessageGenerate extends AbstractGenerate<MessagePO, MessageDTO> {

    @Override
    public MessagePO getObjectPO() {
        MessagePO po = applicationContext.getBean(MessagePO.class);
        return getObjectPO(po);
    }

    @Override
    public MessagePO getObjectPO(MessagePO po) {

        if (po == null){
            po = applicationContext.getBean(MessagePO.class);
        }

        po.setCodiceFiscale(RandomStringUtils.randomAlphabetic(16));
        po.setEmail(RandomStringUtils.randomAlphanumeric(10));
        po.setTipoMessage(TipoMessage.IO_ITALIA);
        po.setExternID(RandomStringUtils.randomAlphanumeric(10));
        po.setOggetto(RandomStringUtils.randomAlphanumeric(90));
        po.setTesto(RandomStringUtils.randomAlphanumeric(1000));
        po.setTimeToLive(3600);

        return po;
    }

    @Override
    public MessageDTO getObjectDTO(MessageDTO dto) {
        return null;
    }


}
