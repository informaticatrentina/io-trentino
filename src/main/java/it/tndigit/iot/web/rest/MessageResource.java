package it.tndigit.iot.web.rest;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import it.tndigit.iot.service.MessageServiceSend;
import it.tndigit.iot.service.dto.message.MessageDTO;
import it.tndigit.iot.web.rest.util.HeaderUtil;
import it.tndigit.iot.web.validator.MessageValidator;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Optional;

@RestController
@Api( value="API MESSAGGI",
        tags = "GESTIONE MESSAGGI")
public class MessageResource extends AbstractResource  {


    private static final String ENTITY_NAME = "messageDTO";


    private MessageValidator messageValidator;

    private final MessageServiceSend messageService;


    public MessageResource(MessageValidator messageValidator,
                           MessageServiceSend messageService) {
        this.messageValidator = messageValidator;
        this.messageService = messageService;

    }

    /**
     * POST  /messages : Create a new Message.
     *
     * @param codiceFiscale the codiceFiscale to create
     * @return the ResponseEntity with status 201 (Created) and with body the new areaDTO, or with status 400 (Bad Request) if the area has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/message/{codiceFiscale}")
    @ApiOperation("Crea un nuovo messaggio dato un codicefiscale")
    public ResponseEntity<MessageDTO> createMessage(@Valid @RequestBody MessageDTO messageDTO,
                                                    @PathVariable String codiceFiscale,
                                                    BindingResult bindingResult) throws URISyntaxException {

        messageValidator.validate(messageDTO, bindingResult);

        if (bindingResult.getErrorCount() > 0) {
            messageDTO.setErroreImprevisto(bindingResult.getAllErrors().get(0).toString());
            return new ResponseEntity<>(messageDTO, HttpStatus.NOT_ACCEPTABLE);
        }

        if (codiceFiscale == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }else {
            messageDTO.setCodiceFiscale(codiceFiscale);
        }
        messageDTO = messageService.sendMessageInCode(messageDTO);
        //messageService.cryptoMessage(messageDTO);


        return ResponseEntity.created(new URI("/v1/api/messaggio/" + codiceFiscale))
                .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, messageDTO.getIdObj().toString()))
                .body(messageDTO);
    }


    /**
     * GET  /message/{id}/{codiceFiscale} : get the "id" messaggio.
     *
     * @return the ResponseEntity with status 200 (OK) and with body the areaDTO, or with status 404 (Not Found)
     */
    @GetMapping("/message/{id}/{codiceFiscale}")
    @ApiOperation("Ritorna il messaggio e relativo stato")
    public ResponseEntity<MessageDTO> getMessage(@PathVariable Long id,
                                         @PathVariable String codiceFiscale) {

        if (id ==null || codiceFiscale == null ||codiceFiscale.isEmpty() ){
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }

        Optional<MessageDTO> messageDTO = messageService.getMessage(id,codiceFiscale);
        if (messageDTO.isPresent())
            return new ResponseEntity<>(messageDTO.get(), HttpStatus.OK);

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }







}