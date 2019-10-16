package it.tndigit.iot.web.filter;

import io.jsonwebtoken.ExpiredJwtException;
import it.tndigit.auth.web.controller.GestioneAuthApi;
import it.tndigit.iot.domain.EntePO;
import it.tndigit.iot.repository.EnteRepository;
import it.tndigit.iot.web.util.JwtUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.common.exceptions.InvalidTokenException;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class JwtAuthenticationTokenBeforeFilter extends OncePerRequestFilter {

    private static final String BEARER = "Bearer";

    @Value("${jwt.header}")
    private String tokenHeader;

    @Autowired
    protected GestioneAuthApi gestioneAuthApi;


    @Autowired
    protected EnteRepository enteRepository;


    @Value("${server.origin.auth}")
    String basePathAuth;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {


        try{

            if (request.getRequestURI().contains("/v1/api")){
                Optional<String> authToken = Optional.ofNullable(request.getHeader(this.tokenHeader))
                        .map(v -> v.replace(BEARER, "").trim())
                        .map(v -> v.replace("%20", "").trim())
                        .map(v -> v.replace("%2520", "").trim());

                UserDetails userDetails = null;

                if (authToken != null && authToken.isPresent()) {
                    gestioneAuthApi.getApiClient().setBasePath(basePathAuth);
                    String returnValue =  gestioneAuthApi.ctrTockenUsingGET(authToken.get()) ;
                    if (returnValue!=null && !returnValue.isEmpty()){
                        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
                        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
                        userDetails = new JwtUser(returnValue,"",authorities, true);
                        Optional<EntePO> entePOOptional = enteRepository.findByEmailPec(returnValue);
                        if (entePOOptional.isPresent()){
                            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                            authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                            SecurityContextHolder.getContext().setAuthentication(authentication);
                        }
                    }else{
                        String token = authToken.isPresent()?authToken.get():"NON VALIDATO";
                        logger.error(token);
                        throw new InvalidTokenException("TOKEN NON VALIDATO" + token);

                    }

                } else {
                    String token = authToken.isPresent()?authToken.get():"NON PRESENTE";
                    throw new InvalidTokenException("TOKEN NON VALIDO" + token);
                }

            }




            chain.doFilter(request, response);


        }catch (ExpiredJwtException eJE){

            final String expiredMsg = eJE.getMessage();
            logger.warn(expiredMsg);

            final String msg = (expiredMsg != null) ? expiredMsg : "Unauthorized";
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, msg);

        }catch (InvalidTokenException iTe){
            response.setStatus(499);
            //chain.doFilter(request, response);
        }


    }



}