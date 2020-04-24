package it.tndigit.iot.web.rest;

import it.tndigit.iot.domain.ServizioPO;
import it.tndigit.iot.generate.ServizioGenerate;
import it.tndigit.iot.repository.ServizioRepository;
import it.tndigit.iot.service.ServizioService;
import it.tndigit.iot.service.dto.ServizioDTO;
import it.tndigit.iot.service.mapper.ServizioMapper;
import it.tndigit.iot.web.validator.ServizioValidator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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


    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ServizioResource areaResource = new ServizioResource(servizioService, servizioValidator);
        this.restServizioMockMvc = MockMvcBuilders.standaloneSetup(areaResource)
                .setCustomArgumentResolvers(pageableArgumentResolver)
                .setControllerAdvice(exceptionTranslator)
                .setConversionService(TestUtil.createFormattingConversionService())
                .setMessageConverters(jacksonMessageConverter).build();
    }


    @Test
    @Transactional
    public void createServizio()  throws  Exception{

        servizioRepository.deleteAll();
        int databaseSizeBeforeCreate = servizioRepository.findAll().size();

        // Create Servizio
        servizioPO =  servizioGenerate.getObjectPO(new ServizioPO());
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
        assertThat(testEnte.getCodiceIdentificativo()).isEqualTo(servizioPO.getCodiceIdentificativo());
        assertThat(testEnte.getIdObj()).isGreaterThan(0L);
        Long idObjfirst = testEnte.getIdObj();

    }


    @Test
    @Transactional
    public void createServizioSequence()  throws  Exception{

        servizioRepository.deleteAll();
        int databaseSizeBeforeCreate = servizioRepository.findAll().size();
        // Create Servizio
        servizioPO =  servizioGenerate.getObjectPO(new ServizioPO());
        ServizioDTO enteDTO = enteMapper.toDto(servizioPO);
        restServizioMockMvc.perform(post("/api/v1/servizio")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(enteDTO)))
                .andExpect(status().isCreated());

        // Validate the Area in the database
        List<ServizioPO> enteList = servizioRepository.findAll();
        assertThat(enteList).hasSize(databaseSizeBeforeCreate + 1);
        ServizioPO testEnte = enteList.get(enteList.size() - 1);
        Long idObjfirst = testEnte.getIdObj();


        servizioPO =  servizioGenerate.getObjectPO(new ServizioPO());


        ServizioDTO servizioDTO = enteMapper.toDto(servizioPO);
        restServizioMockMvc.perform(post("/api/v1/servizio")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(servizioDTO)))
                .andExpect(status().isCreated());

        // Validate the Area in the database
        enteList = servizioRepository.findAll();
        assertThat(enteList).hasSize(databaseSizeBeforeCreate + 2);
        ServizioPO testEnteTwo = enteList.get(enteList.size() - 1);
        assertEquals(testEnteTwo.getIdObj(),idObjfirst+1);

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
