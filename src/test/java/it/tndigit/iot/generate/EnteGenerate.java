package it.tndigit.iot.generate;

import it.tndigit.iot.domain.EntePO;
import it.tndigit.iot.service.dto.EnteDTO;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;


@Service
public class EnteGenerate extends AbstractGenerate<EntePO, EnteDTO> {

    @Override
    public EntePO getObjectPO() {
        EntePO po = applicationContext.getBean(EntePO.class);
        return getObjectPO(po);
    }

    @Override
    public EntePO getObjectPO(EntePO po) {

        if (po == null){
            po = applicationContext.getBean(EntePO.class);
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
    public EnteDTO getObjectDTO(EnteDTO dto) {
        return null;
    }


}
