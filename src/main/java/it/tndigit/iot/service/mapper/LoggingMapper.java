package it.tndigit.iot.service.mapper;

import it.tndigit.iot.domain.logging.LoggingPO;
import it.tndigit.iot.service.dto.LoggingDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface LoggingMapper extends EntityMapper< LoggingDTO, LoggingPO > {

    @Mapping(source = "idObj", target = "idObj")
    LoggingDTO toDto(LoggingPO loggingPO);

    @Mapping(source = "idObj", target = "idObj")
    LoggingPO toEntity(LoggingDTO loggingDTO);

}
