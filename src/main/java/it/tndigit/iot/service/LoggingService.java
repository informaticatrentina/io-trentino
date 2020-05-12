package it.tndigit.iot.service;

import it.tndigit.iot.service.dto.LoggingDTO;
import it.tndigit.iot.service.dto.ServizioDTO;

import java.util.Optional;

public interface LoggingService {


    /**
     * Save a EnteDTO.
     *
     * @param loggingDTO the entity to save
     * @return the persisted entity
     */

    LoggingDTO save(LoggingDTO loggingDTO);

}
