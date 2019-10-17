package it.tndigit.iot.service.mapper;

import it.tndigit.iot.domain.EntePO;
import it.tndigit.iot.service.dto.EnteDTO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2019-10-17T11:08:40+0200",
    comments = "version: 1.3.0.Final, compiler: javac, environment: Java 1.8.0_222 (Private Build)"
)
@Component
public class EnteMapperImpl implements EnteMapper {

    @Override
    public List<EntePO> toEntity(List<EnteDTO> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<EntePO> list = new ArrayList<EntePO>( dtoList.size() );
        for ( EnteDTO enteDTO : dtoList ) {
            list.add( toEntity( enteDTO ) );
        }

        return list;
    }

    @Override
    public List<EnteDTO> toDto(List<EntePO> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<EnteDTO> list = new ArrayList<EnteDTO>( entityList.size() );
        for ( EntePO entePO : entityList ) {
            list.add( toDto( entePO ) );
        }

        return list;
    }

    @Override
    public EnteDTO toDto(EntePO entePO) {
        if ( entePO == null ) {
            return null;
        }

        EnteDTO enteDTO = new EnteDTO();

        enteDTO.setIdObj( entePO.getIdObj() );
        enteDTO.setVersion( entePO.getVersion() );
        enteDTO.setDataModifica( entePO.getDataModifica() );
        enteDTO.setDataInserimento( entePO.getDataInserimento() );
        enteDTO.setNomeEnte( entePO.getNomeEnte() );
        enteDTO.setNomeDipartimento( entePO.getNomeDipartimento() );
        enteDTO.setCodiceFiscale( entePO.getCodiceFiscale() );
        enteDTO.setEmail( entePO.getEmail() );
        enteDTO.setEmailPec( entePO.getEmailPec() );
        enteDTO.setTokenIoItalia( entePO.getTokenIoItalia() );

        return enteDTO;
    }

    @Override
    public EntePO toEntity(EnteDTO enteDTO) {
        if ( enteDTO == null ) {
            return null;
        }

        EntePO entePO = new EntePO();

        entePO.setIdObj( enteDTO.getIdObj() );
        entePO.setVersion( enteDTO.getVersion() );
        entePO.setNomeEnte( enteDTO.getNomeEnte() );
        entePO.setNomeDipartimento( enteDTO.getNomeDipartimento() );
        entePO.setCodiceFiscale( enteDTO.getCodiceFiscale() );
        entePO.setEmail( enteDTO.getEmail() );
        entePO.setEmailPec( enteDTO.getEmailPec() );
        entePO.setTokenIoItalia( enteDTO.getTokenIoItalia() );

        return entePO;
    }
}
