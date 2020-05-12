package it.tndigit.iot.web.utils;

import it.tndigit.ioitalia.client.invoker.ApiClient;
import it.tndigit.iot.common.UtilityDate;
import it.tndigit.iot.common.UtilityIot;
import it.tndigit.iot.repository.ServizioRepository;
import it.tndigit.iot.service.client.ClientHttpAuthService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;
import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class JwtTokenUtilTest {

    private String TOKEN = "eyJhbGciOiJSUzI1NiJ9.eyJzdWIiOiJpb0FwcCIsImlkZW50aXR5IjoiQUFBQkJCQ0NDIiwicm9sZXMiOltdLCJpc0VuYWJsZWQiOnRydWUsImV4cCI6NDc0NDMwMzIwMCwiaWF0Ijp7InllYXIiOjIwMjAsIm1vbnRoIjoiTUFZIiwiY2hyb25vbG9neSI6eyJjYWxlbmRhclR5cGUiOiJpc284NjAxIiwiaWQiOiJJU08ifSwiZXJhIjoiQ0UiLCJkYXlPZlllYXIiOjEyNiwiZGF5T2ZXZWVrIjoiVFVFU0RBWSIsImxlYXBZZWFyIjp0cnVlLCJkYXlPZk1vbnRoIjo1LCJtb250aFZhbHVlIjo1fSwiZU1haWwiOiJ0ZXN0QHRuZGlnaXQuaXQifQ.I1nLLw9R0H5ERsiPGmnpCDC_LfdwmtLhqSGFEKVWk5xllGsLByulM5Vt3ZeBc6MGJL1zT5KpgFbapv1FsLPkDYtR3CKZeLDjKdFj79jyKgIORIAqwiGVOQV0rhzn9upwveXs8NcMsj-am6PmWEIHBdmCtFVlANck-NWQWcY_bC_BMTaMGtc4rUF_KyXNVUaJdTa-xLuOzoGLhOp0f05ULPJYufQ1f0SIyyOVxOwaV-7N36zus1M5WNV9yyorgxu63PEbInKqYngOsh7SWM63TqTpV2V1yj25IiWkRRhFLdmQ8JQ3r639sn9FLFnx2gcXJ9cOv8IVoHX0wytxN0itfQ";
    private final String EMAIL = "test@tndigit.it";
    private final String CODE = "AAABBBCCC";

    @Autowired
    RSA rsa;

    @Autowired
    JwtTokenUtil jwtTokenUtil;


    @Autowired
    ServizioRepository servizioRepository;

    @MockBean
    ClientHttpAuthService clientHttpAuthService;


    @BeforeEach
    void init(){
        Mockito.when(clientHttpAuthService.getPublicKey()).thenReturn(Optional.of(RSATest.PUBLIC_KEY));
    }

    @Test
    void getUsernameFromToken() {
        String username = jwtTokenUtil.getUsernameFromToken(TOKEN);
        assertEquals(username,CODE);
    }

    @Test
    void getUserDetails() {
        JwtUser jwtUser = jwtTokenUtil.getUserDetails(TOKEN);
        assertEquals(jwtUser.getUsername(),CODE);
    }

    @Test
    void getExpirationDateFromToken() {
        Date date = jwtTokenUtil.getExpirationDateFromToken(TOKEN);
        LocalDate localDate = UtilityDate.localDateOf(date);
        LocalDate localDateCalc = LocalDate.of(2120,5,5);
        Assertions.assertTrue(localDate.compareTo(localDateCalc)==0,"Expired date NON corretta");
    }

}