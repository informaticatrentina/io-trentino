package it.tndigit.iot.web.rest;

import it.tndigit.iot.domain.EntePO;
import it.tndigit.iot.generate.EnteGenerate;
import it.tndigit.iot.repository.EnteRepository;
import it.tndigit.iot.service.EnteService;
import it.tndigit.iot.service.dto.EnteDTO;
import it.tndigit.iot.service.mapper.EnteMapper;
import it.tndigit.iot.web.validator.EnteValidator;
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
public class EnteResourceTest  extends AbstractResourceTest{


    @Autowired
    private EnteService enteService;

    @Autowired
    private EnteValidator enteValidator;

    @Autowired
    private EnteRepository enteRepository;

    @Autowired
    private EnteMapper enteMapper;

    @Autowired
    private EnteGenerate enteGenerate;

    private MockMvc restEnteMockMvc;

    private EntePO entePO;


    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final EnteResource areaResource = new EnteResource(enteService, enteValidator);
        this.restEnteMockMvc = MockMvcBuilders.standaloneSetup(areaResource)
                .setCustomArgumentResolvers(pageableArgumentResolver)
                .setControllerAdvice(exceptionTranslator)
                .setConversionService(TestUtil.createFormattingConversionService())
                .setMessageConverters(jacksonMessageConverter).build();
    }


    @Before
    public void initTest() {
        entePO =  enteGenerate.getObjectPO(new EntePO());

    }

    @Test
    public void createEnte()  throws  Exception{

        int databaseSizeBeforeCreate = enteRepository.findAll().size();

        // Create the Area
        EnteDTO enteDTO = enteMapper.toDto(entePO);
        restEnteMockMvc.perform(post("/v1/api/ente")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(enteDTO)))
                .andExpect(status().isCreated());

        // Validate the Area in the database
        List<EntePO> enteList = enteRepository.findAll();
        assertThat(enteList).hasSize(databaseSizeBeforeCreate + 1);
        EntePO testEnte = enteList.get(enteList.size() - 1);
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
