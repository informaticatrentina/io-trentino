package it.tndigit.iot.web.rest;

import it.tndigit.ioitalia.client.invoker.ApiClient;
import it.tndigit.ioitalia.service.dto.InlineResponse201;
import it.tndigit.ioitalia.service.dto.LimitedProfile;
import it.tndigit.ioitalia.web.rest.DefaultApi;
import it.tndigit.iot.domain.ServizioPO;
import it.tndigit.iot.domain.message.MessagePO;
import it.tndigit.iot.generate.MessageGenerate;
import it.tndigit.iot.generate.ServizioGenerate;
import it.tndigit.iot.repository.MessageRepository;
import it.tndigit.iot.repository.ServizioRepository;
import it.tndigit.iot.service.MessageServiceSend;
import it.tndigit.iot.service.dto.message.MessageDTO;
import it.tndigit.iot.service.mapper.MessageMapper;
import it.tndigit.iot.web.validator.MessageValidator;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MessageResourceTest extends AbstractResourceTest{


    @Autowired
    MessageServiceSend messageService;

    @Autowired
    MessageRepository messageRepository;

    @Autowired
    MessageMapper messageMapper;


    @Autowired
    ServizioRepository servizioRepository;

    @Autowired
    MessageValidator messageValidator;

    @Autowired
    MessageGenerate messageGenerate;

    @Autowired
    ServizioGenerate servizioGenerate;

    @MockBean
    DefaultApi defaultApi;

    MockMvc restMessageMockMvc;


    ServizioPO servizioPO;


    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final MessageResource messageResource = new MessageResource(messageValidator,messageService);
        this.restMessageMockMvc = MockMvcBuilders.standaloneSetup(messageResource)
                .setCustomArgumentResolvers(pageableArgumentResolver)
                .setControllerAdvice(exceptionTranslator)
                .setConversionService(TestUtil.createFormattingConversionService())
                .setMessageConverters(jacksonMessageConverter).build();
    }


    @Before
    public void initTest() {
        ServizioPO servizioPO = servizioGenerate.getObjectPO();
        servizioPO.setEmailPec("aaa@aaa.it");
        servizioRepository.saveAndFlush(servizioPO);

        Mockito.when(defaultApi.getApiClient()).thenReturn(new ApiClient());
        LimitedProfile limitedProfile = new LimitedProfile();
        limitedProfile.setSenderAllowed(true);
        Mockito.when(defaultApi.getProfile(Mockito.any())).thenReturn(limitedProfile);

        InlineResponse201 inlineResponse201 = new InlineResponse201();
        inlineResponse201.setId(RandomStringUtils.randomAlphanumeric(20));
        Mockito.when(defaultApi.submitMessageforUser(Mockito.any(),Mockito.any())).thenReturn(inlineResponse201);

    }



    @Test
    @WithMockUser(username = "aaa@aaa.it",roles = "USER")
    @Transactional
    public void createMessage() throws Exception {

        MessagePO messagePO = messageGenerate.getObjectPO(new MessagePO());

        int databaseSizeBeforeCreate = messageRepository.findAll().size();

        // Create the Area
        MessageDTO messageDTO = messageMapper.toDto(messagePO);

        restMessageMockMvc.perform(post("/api/v1/message/{codiceFiscale}",messageDTO.getCodiceFiscale())
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(messageDTO)))
                .andExpect(status().isCreated());

        // Validate the Area in the database
        List<MessagePO> messagePOList = messageRepository.findAll();
        assertThat(messagePOList).hasSize(databaseSizeBeforeCreate + 1);
        MessagePO testMessage = messagePOList.get(messagePOList.size() - 1);
        assertThat(testMessage.getCodiceFiscale()).isEqualTo(messagePO.getCodiceFiscale());
        assertThat(testMessage.getEmail()).isEqualTo(messagePO.getEmail());
        assertThat(testMessage.getScadenza()).isEqualTo(messagePO.getScadenza());
        assertThat(testMessage.getExternID()).isEqualTo(messagePO.getExternID());
        assertThat(testMessage.getOggetto()).isEqualTo(messagePO.getOggetto());
        assertThat(testMessage.getTesto()).isEqualTo(messagePO.getTesto());
    }



    @Test
    public void createMessageWithOutServizio() throws Exception {

        MessagePO messagePO = messageGenerate.getObjectPO(new MessagePO());

        int databaseSizeBeforeCreate = messageRepository.findAll().size();
        // Create the Area
        MessageDTO messageDTO = messageMapper.toDto(messagePO);
        restMessageMockMvc.perform(post("/api/v1/message/{codiceFiscale}", messageDTO.getCodiceFiscale())
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(messageDTO)))
                .andExpect(status().isBadRequest())
                .andExpect(content().json("{'message':'Bad Request: Impossibile inviare il messaggio, Servizio NON presente'}"));


    }


}