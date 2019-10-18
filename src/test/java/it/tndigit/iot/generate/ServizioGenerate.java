package it.tndigit.iot.generate;

import it.tndigit.iot.domain.ServizioPO;
import it.tndigit.iot.service.dto.ServizioDTO;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;


@Service
public class ServizioGenerate extends AbstractGenerate<ServizioPO, ServizioDTO> {

    @Override
    public ServizioPO getObjectPO() {
        ServizioPO po = applicationContext.getBean(ServizioPO.class);
        return getObjectPO(po);
    }

    @Override
    public ServizioPO getObjectPO(ServizioPO po) {

        if (po == null){
            po = applicationContext.getBean(ServizioPO.class);
        }

        po.setCodiceFiscale(RandomStringUtils.randomAlphabetic(16));
        po.setEmail(RandomStringUtils.randomAlphanumeric(10));
        po.setEmailPec(RandomStringUtils.randomAlphanumeric(10));
        po.setNomeEnte(RandomStringUtils.randomAlphabetic(200));
        po.setNomeDipartimento(RandomStringUtils.randomAlphabetic(200));
        po.setTokenIoItalia(RandomStringUtils.randomAlphabetic(100));


        return po;
    }

    @Override
    public ServizioDTO getObjectDTO(ServizioDTO dto) {
        return null;
    }


}
