package it.tndigit.iot.web.rest;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.SwaggerDefinition;
import it.tndigit.iot.exception.IotException;
import it.tndigit.iot.logging.LogExecutionTime;
import it.tndigit.iot.service.EnteService;
import it.tndigit.iot.service.dto.EnteDTO;
import it.tndigit.iot.web.rest.util.HeaderUtil;
import it.tndigit.iot.web.validator.EnteValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Optional;

/**
 * REST controller for managing Area.
 */
@RestController
@SwaggerDefinition()
@Api( value="API Ente",
        description="Permette il CRUD completo dell' Ente",
        tags = "GESTIONE ENTE")
public class EnteResource extends AbstractResource {

    private final Logger log = LoggerFactory.getLogger(EnteResource.class);

    private static final String ENTITY_NAME = "entePO";


    private final EnteService enteService;

    private final EnteValidator enteValidator;

    public EnteResource(EnteService enteService, EnteValidator enteValidator) {

        this.enteService = enteService;
        this.enteValidator = enteValidator;
    }

    /**
     * POST  /ente : Create una nuova atto.
     *
     * @param enteDTO the enteDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new attoDTO, or with status 400 (Bad Request) if the area has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/ente")
    @ApiOperation("Inserisce un nuovo Ente")
    @LogExecutionTime

    public ResponseEntity<EnteDTO> createEnte(@RequestBody EnteDTO enteDTO, BindingResult bindingResult) throws URISyntaxException {
        if (enteDTO.getIdObj() != null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        enteValidator.validate(enteDTO,bindingResult);
        if (bindingResult.getErrorCount()>0){
            enteDTO.setErroreImprevisto(bindingResult.getAllErrors().get(0).toString());
            return new ResponseEntity<>(enteDTO, HttpStatus.NOT_ACCEPTABLE);
        }

        try{
            EnteDTO result = enteService.save(enteDTO);
            return ResponseEntity.created(new URI("/v1/api/ente/" + result.getIdObj()))
                    .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getIdObj().toString()))
                    .body(result);
        }catch (IotException e){
            enteDTO.setErroreImprevisto(e.getMessage());
            return new ResponseEntity<>(enteDTO, HttpStatus.UNPROCESSABLE_ENTITY);
        }catch (AccessDeniedException ex){
            return new ResponseEntity<>(enteDTO, HttpStatus.FORBIDDEN);
        }
        catch (Exception ex){
            return new ResponseEntity<>(enteDTO, HttpStatus.NOT_ACCEPTABLE);

        }
    }

    /**
     * GET  /ente/:id : get the "id" ente.
     *
     * @param idObj the id of the enteDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the areaDTO, or with status 404 (Not Found)
     */
    @GetMapping("/area/{idObj}")
    @ApiOperation("Ritorna un ente dato un determinato idObj")
    @LogExecutionTime
    public ResponseEntity<EnteDTO> getEnte(@PathVariable Long idObj) {
        log.debug("REST request to get Area : {}", idObj);

        Optional<EnteDTO> enteDTO = enteService.findOne(idObj);
        if (enteDTO.isPresent()) {
            return new ResponseEntity<>(enteDTO.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);


    }


    /**
     * PUT  /ente : Updates an existing atto.
     *
     * @param enteDTO the enteDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated attoDTO,
     * or with status 400 (Bad Request) if the areaDTO is not valid,
     * or with status 500 (Internal Server Error) if the areaDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/ente")
    @LogExecutionTime
    @ApiOperation("Aggiorna un ente esistente")

    public ResponseEntity<EnteDTO> updateEnte(@RequestBody EnteDTO enteDTO, BindingResult bindingResult) throws URISyntaxException {
        if (enteDTO.getIdObj() == null) {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
        enteValidator.validate(enteDTO,bindingResult);

        if (bindingResult.getErrorCount()>0){
            enteDTO.setErroreImprevisto(bindingResult.getAllErrors().get(0).toString());
            return new ResponseEntity<>(enteDTO, HttpStatus.NOT_ACCEPTABLE);
        }

        enteDTO = enteService.save(enteDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, enteDTO.getIdObj().toString()))
            .body(enteDTO);
    }


    /**
     * DELETE  /ente/:id : delete the "id" area.
     *
     * @param idObj the id of the areaDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/ente/{idObj}")
    @ApiOperation("Cancella un ente dato un determinato idObj")
    @LogExecutionTime
    public ResponseEntity<Void> deleteEnte(@PathVariable Long idObj) {
        enteService.delete(idObj);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, idObj.toString())).build();
    }
}
