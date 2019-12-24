package it.tndigit.iot.web.rest;

import it.tndigit.iot.domain.ServizioPO;
import it.tndigit.iot.generate.ServizioGenerate;
import it.tndigit.iot.repository.ServizioRepository;
import it.tndigit.iot.service.ServizioService;
import it.tndigit.iot.service.dto.ServizioDTO;
import it.tndigit.iot.service.mapper.ServizioMapper;
import it.tndigit.iot.web.validator.ServizioValidator;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ServizioResourceTest extends AbstractResourceTest{


    @Autowired
    private ServizioService servizioService;

    @Autowired
    private ServizioValidator servizioValidator;

    @Autowired
    private ServizioRepository servizioRepository;

    @Autowired
    private ServizioMapper enteMapper;

    @Autowired
    private ServizioGenerate servizioGenerate;

    private MockMvc restServizioMockMvc;

    private ServizioPO servizioPO;


    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ServizioResource areaResource = new ServizioResource(servizioService, servizioValidator);
        this.restServizioMockMvc = MockMvcBuilders.standaloneSetup(areaResource)
                .setCustomArgumentResolvers(pageableArgumentResolver)
                .setControllerAdvice(exceptionTranslator)
                .setConversionService(TestUtil.createFormattingConversionService())
                .setMessageConverters(jacksonMessageConverter).build();
    }


    @Before
    public void initTest() {
        servizioPO =  servizioGenerate.getObjectPO(new ServizioPO());

    }

    @Test
    public void createServizio()  throws  Exception{

        int databaseSizeBeforeCreate = servizioRepository.findAll().size();

        // Create the Area
        ServizioDTO enteDTO = enteMapper.toDto(servizioPO);
        restServizioMockMvc.perform(post("/api/v1/servizio")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(enteDTO)))
                .andExpect(status().isCreated());

        // Validate the Area in the database
        List<ServizioPO> enteList = servizioRepository.findAll();
        assertThat(enteList).hasSize(databaseSizeBeforeCreate + 1);
        ServizioPO testEnte = enteList.get(enteList.size() - 1);
        assertThat(testEnte.getCodiceFiscale()).isEqualTo(servizioPO.getCodiceFiscale());
        assertThat(testEnte.getEmail()).isEqualTo(servizioPO.getEmail());
        assertThat(testEnte.getEmailPec()).isEqualTo(servizioPO.getEmailPec());
        assertThat(testEnte.getNomeDipartimento()).isEqualTo(servizioPO.getNomeDipartimento());
        assertThat(testEnte.getNomeEnte()).isEqualTo(servizioPO.getNomeEnte());
        assertThat(testEnte.getTokenIoItalia()).isEqualTo(servizioPO.getTokenIoItalia());



    }

    @Test
    public void deleteServizio()throws  Exception {

        ServizioPO servizioPODelete = servizioRepository.saveAndFlush(servizioGenerate.getObjectPO());
        int databaseSizeBeforeDelete = servizioRepository.findAll().size();

        restServizioMockMvc.perform(delete("/api/v1/servizio/{idObj}",servizioPODelete.getIdObj())
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        List<ServizioPO> servizioPOS = servizioRepository.findAll();
        assertThat(servizioPOS).hasSize(databaseSizeBeforeDelete -1);



    }
}
