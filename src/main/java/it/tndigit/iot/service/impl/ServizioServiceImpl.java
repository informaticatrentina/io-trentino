package it.tndigit.iot.service.impl;

import it.tndigit.iot.domain.ServizioPO;
import it.tndigit.iot.repository.ServizioRepository;
import it.tndigit.iot.service.ServizioService;
import it.tndigit.iot.service.dto.ServizioDTO;
import it.tndigit.iot.service.mapper.ServizioMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class ServizioServiceImpl implements ServizioService {


    @Autowired
    private ServizioMapper enteMapper;

    @Autowired
    private ServizioRepository servizioRepository;



    @Override
    public ServizioDTO save(ServizioDTO enteDTO) {
        ServizioPO areaPO = enteMapper.toEntity(enteDTO);
        areaPO = servizioRepository.save(areaPO);
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
    public Optional<ServizioDTO> findOne(Long id) {
        Optional<ServizioPO> entePO = servizioRepository.findById(id);

        if (entePO.isPresent()){
            ServizioDTO enteDTO = enteMapper.toDto(entePO.get());
            return Optional.of(enteDTO);
        }
        return Optional.empty();
    }

    @Override
    public void delete(Long id) {
        servizioRepository.deleteById(id);
    }
}
