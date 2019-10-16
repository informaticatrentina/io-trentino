package it.tndigit.iot.service.mapper;

import it.tndigit.iot.domain.EntePO;
import it.tndigit.iot.domain.message.MessagePO;
import it.tndigit.iot.service.dto.EnteDTO;
import it.tndigit.iot.service.dto.message.MessageDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Mapper for the entity DeliberaPO and its DTO DeliberaDTO.
 */

@Mapper(componentModel = "spring")
public interface EnteMapper extends EntityMapper<EnteDTO, EntePO> {


    @Mapping(source = "idObj", target = "idObj")
    EnteDTO toDto(EntePO entePO);

    @Mapping(source = "idObj", target = "idObj")
    EntePO toEntity(EnteDTO enteDTO);


    default EntePO fromId(Long id) {
        if (id == null) {
            return null;
        }
        EntePO entePO = new EntePO();
        entePO.setIdObj(id);
        return entePO;
    }
}
