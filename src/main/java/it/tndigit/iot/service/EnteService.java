package it.tndigit.iot.service;

import it.tndigit.iot.service.dto.EnteDTO;

import java.util.Optional;

public interface EnteService {


    /**
     * Save a EnteDTO.
     *
     * @param enteDTO the entity to save
     * @return the persisted entity
     */

    EnteDTO save(EnteDTO enteDTO);

    /**
     * Get the "id" ente.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<EnteDTO> findOne(Long id);

    /**
     * Delete the "id" ente.
     *
     * @param id the id of the entity
     */
    void delete(Long id);


}
