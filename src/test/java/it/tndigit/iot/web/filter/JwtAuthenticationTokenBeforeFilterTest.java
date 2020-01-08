package it.tndigit.iot.web.filter;


import it.tndigit.iot.web.utils.JwtTokenUtil;
import it.tndigit.iot.web.utils.JwtUser;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockFilterChain;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.ServletException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringRunner.class)
@SpringBootTest
public class JwtAuthenticationTokenBeforeFilterTest {

    private MockHttpServletRequest request;
    private MockHttpServletResponse response;
    private MockFilterChain chain;
    private List<OncePerRequestFilter> invocations;


    @Autowired
    private JwtAuthenticationTokenBeforeFilter accessFilter;


    @Before
    public void setup() {

        this.request = new MockHttpServletRequest();
        this.request.setRequestURI("/api/v1/");
        this.response = new MockHttpServletResponse();
        this.chain = new MockFilterChain();
        this.invocations = new ArrayList<>();

        //accessFilter = Mockito.mock(JwtAuthenticationTokenBeforeFilter.class);
        accessFilter.tokenHeader = "Authorization";
        accessFilter.jwtTokenUtil = Mockito.mock(JwtTokenUtil.class);


    }


    @Test()
    public void filterWithOutToken() throws IOException, ServletException {
        accessFilter.doFilterInternal(request, response, chain);
        assertThat(response.getStatus()).isEqualTo(499);
    }


    @Test()
    public void filterWithTokenExpired() throws IOException, ServletException {
        request.addHeader("Authorization", "tokenFinto");
        JwtUser jwtUser = new JwtUser("pippo", "",null,Boolean.TRUE);
        Mockito.when(accessFilter.jwtTokenUtil.getUserDetails(Mockito.any())).thenReturn(jwtUser);
        Mockito.when(accessFilter.jwtTokenUtil.validateToken(Mockito.anyString() ,Mockito.any())).thenReturn(Boolean.FALSE);
        accessFilter.doFilterInternal(request, response, chain);
        assertThat(response.getStatus()).isEqualTo(499);
    }


    @Test()
    public void filterWithTokenValid() throws IOException, ServletException {
        request.addHeader("Authorization", "tokenFinto");
        JwtUser jwtUser = new JwtUser("pippo", "",null,Boolean.TRUE);
        Mockito.when(accessFilter.jwtTokenUtil.getUserDetails(Mockito.any())).thenReturn(jwtUser);
        Mockito.when(accessFilter.jwtTokenUtil.validateToken(Mockito.anyString() ,Mockito.any())).thenReturn(Boolean.TRUE);
        accessFilter.doFilterInternal(request, response, chain);
        assertThat(response.getStatus()).isEqualTo(200);
    }



}
