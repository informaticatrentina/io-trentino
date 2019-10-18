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

    private MockMvc restEnteMockMvc;

    private ServizioPO entePO;


    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ServizioResource areaResource = new ServizioResource(servizioService, servizioValidator);
        this.restEnteMockMvc = MockMvcBuilders.standaloneSetup(areaResource)
                .setCustomArgumentResolvers(pageableArgumentResolver)
                .setControllerAdvice(exceptionTranslator)
                .setConversionService(TestUtil.createFormattingConversionService())
                .setMessageConverters(jacksonMessageConverter).build();
    }


    @Before
    public void initTest() {
        entePO =  servizioGenerate.getObjectPO(new ServizioPO());

    }

    @Test
    public void createEnte()  throws  Exception{

        int databaseSizeBeforeCreate = servizioRepository.findAll().size();

        // Create the Area
        ServizioDTO enteDTO = enteMapper.toDto(entePO);
        restEnteMockMvc.perform(post("/v1/api/ente")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(enteDTO)))
                .andExpect(status().isCreated());

        // Validate the Area in the database
        List<ServizioPO> enteList = servizioRepository.findAll();
        assertThat(enteList).hasSize(databaseSizeBeforeCreate + 1);
        ServizioPO testEnte = enteList.get(enteList.size() - 1);
        assertThat(testEnte.getCodiceFiscale()).isEqualTo(entePO.getCodiceFiscale());
        assertThat(testEnte.getEmail()).isEqualTo(entePO.getEmail());
        assertThat(testEnte.getEmailPec()).isEqualTo(entePO.getEmailPec());
        assertThat(testEnte.getNomeDipartimento()).isEqualTo(entePO.getNomeDipartimento());
        assertThat(testEnte.getNomeEnte()).isEqualTo(entePO.getNomeEnte());
        assertThat(testEnte.getTokenIoItalia()).isEqualTo(entePO.getTokenIoItalia());



    }

    @Test
    public void getEnte()throws  Exception {
    }

    @Test
    public void updateEnte()throws  Exception {
    }

    @Test
    public void deleteEnte()throws  Exception {
    }
}
