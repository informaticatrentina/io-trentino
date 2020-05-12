package it.tndigit.iot.service.impl;

import it.tndigit.iot.domain.logging.LoggingPO;
import it.tndigit.iot.repository.LoggingRepository;
import it.tndigit.iot.service.LoggingService;
import it.tndigit.iot.service.dto.LoggingDTO;
import it.tndigit.iot.service.mapper.LoggingMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@Slf4j
public class LoggingServiceImpl implements LoggingService {


    @Autowired
    LoggingMapper loggingMapper;

    @Autowired
    LoggingRepository loggingRepository;

    @Override
    public LoggingDTO save(LoggingDTO loggingDTO) {
        LoggingPO loggingPO = loggingMapper.toEntity(loggingDTO);
        loggingPO =  loggingRepository.save(loggingPO);
        return loggingMapper.toDto(loggingPO);
    }

}
