package it.tndigit.iot.service.client.impl;

import it.tndigit.iot.service.client.ClientHttpAuthService;
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
public class ClientHttpAuthServiceImpl extends ClientHttpServiceImpl implements ClientHttpAuthService {

    private final Logger logger = LoggerFactory.getLogger(ClientHttpAuthServiceImpl.class);

    @Override
    public Optional<String> getPublicKey() throws RestClientException {
        try{
            configureApiClientAuth(getAuthRest().getApiClient());
            String pubicKey = getAuthRest().getPublicKeyUsingGET();

            return Optional.ofNullable(pubicKey);
        }catch (Exception ex){
            logger.error("ClientHttpAuthServiceImpl " + ex.getMessage() );
            throw ex;
        }
    }
}
