package it.tndigit.iot.service.impl;

import it.tndigit.iot.domain.EntePO;
import it.tndigit.iot.repository.EnteRepository;
import it.tndigit.iot.service.EnteService;
import it.tndigit.iot.service.dto.EnteDTO;
import it.tndigit.iot.service.mapper.EnteMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class EnteServiceImpl implements EnteService {


    @Autowired
    private EnteMapper enteMapper;

    @Autowired
    private EnteRepository enteRepository;



    @Override
    public EnteDTO save(EnteDTO enteDTO) {
        EntePO areaPO = enteMapper.toEntity(enteDTO);
        areaPO = enteRepository.save(areaPO);
        return enteMapper.toDto(areaPO);


    }

    /**
     * Get one Ente by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<EnteDTO> findOne(Long id) {
        Optional<EntePO> entePO = enteRepository.findById(id);

        if (entePO.isPresent()){
            EnteDTO enteDTO = enteMapper.toDto(entePO.get());
            return Optional.of(enteDTO);
        }
        return Optional.empty();
    }

    @Override
    public void delete(Long id) {
        enteRepository.deleteById(id);
    }
}
