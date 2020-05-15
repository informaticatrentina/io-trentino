package it.tndigit.iot.service.client.impl;

import it.tndigit.iot.service.client.ClientHttpAuthService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestClientException;

import java.util.Optional;

@Service
@Transactional
@EnableAsync
@Slf4j
public class ClientHttpAuthServiceImpl extends ClientHttpServiceImpl implements ClientHttpAuthService {
    @Override
    public Optional<String> getPublicKey(){
        try{
            configureApiClientAuth(getAuthRest().getApiClient());
            String pubicKey = getAuthRest().getPublicKeyUsingGET();
            return Optional.ofNullable(pubicKey);
        }catch (Exception ex){
            log.error("ClientHttpAuthServiceImpl " + ex.getMessage() );
            throw ex;
        }
    }
}
