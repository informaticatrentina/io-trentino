package it.tndigit.iot.web.rest;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import it.tndigit.iot.logging.LogExecutionTime;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;

@RestController
@RequestMapping("/v1/api")
@Api( value="API PROVA",
        description="Gestione di prova",
        tags = "GESTIONE PROVA",
        position = 1)
public class PrincipalResource {

    @ApiOperation("Legge i dati del client")
    @RequestMapping(value="/getInfo", method = RequestMethod.GET)
    @LogExecutionTime
    public ResponseEntity<Collection<?extends GrantedAuthority>> getInfo (HttpServletRequest request, HttpServletResponse response) throws IOException {
        Collection<?extends GrantedAuthority> granted = SecurityContextHolder.getContext().getAuthentication().getAuthorities();
        return new ResponseEntity<>(granted, null, HttpStatus.OK);
    }
}