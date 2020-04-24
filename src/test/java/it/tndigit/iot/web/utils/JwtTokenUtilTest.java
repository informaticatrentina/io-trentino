package it.tndigit.iot.web.utils;

import it.tndigit.ioitalia.client.invoker.ApiClient;
import it.tndigit.iot.common.UtilityDate;
import it.tndigit.iot.common.UtilityIot;
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

    private String TOKEN = "eyJhbGciOiJSUzI1NiJ9.eyJzdWIiOnsiYXV0aG9yaXRpZXMiOlt7ImF1dGhvcml0eSI6IlJPTEVfVVNFUiJ9XSwiZGV0YWlscyI6eyJzdWIiOiIxMDY3MTc2NjAzNjExOTc3MDEwMzMiLCJuYW1lIjoiVG9rZW4gVGVzdCIsImdpdmVuX25hbWUiOiJNaXJrbyIsImZhbWlseV9uYW1lIjoiUGlhbmV0dGkiLCJlbWFpbCI6InRlc3RAdG5kaWdpdC5pdCIsImVtYWlsX3ZlcmlmaWVkIjp0cnVlLCJsb2NhbGUiOiJpdCIsImhkIjoidG5kaWdpdC5pdCJ9LCJhdXRoZW50aWNhdGVkIjp0cnVlLCJwcmluY2lwYWwiOiJNaXJrbyBQaWFuZXR0aSIsImNyZWRlbnRpYWxzIjoiTi9BIiwibmFtZSI6Ik1pcmtvIFBpYW5ldHRpIn0sInJvbGVzIjpbXSwiaXNFbmFibGVkIjp0cnVlLCJleHAiOjE5ODAzMjc2NjIsImlhdCI6eyJ5ZWFyIjoyMTIwLCJtb250aCI6IkpBTlVBUlkiLCJjaHJvbm9sb2d5Ijp7ImNhbGVuZGFyVHlwZSI6Imlzbzg2MDEiLCJpZCI6IklTTyJ9LCJlcmEiOiJDRSIsImRheU9mWWVhciI6MjksImRheU9mV2VlayI6IldFRE5FU0RBWSIsImxlYXBZZWFyIjp0cnVlLCJkYXlPZk1vbnRoIjoyOSwibW9udGhWYWx1ZSI6MX0sImVNYWlsIjoidGVzdEB0bmRpZ2l0Lml0In0.v3GQJSx8V4e8kJdjrYGXx8BGOURKlDUDOCoXVzGe_QtuZ4uEGuWYPYoG8J2F71hQtoCAq1gUxUZQIhwkNBnD8XDjpN2-XjmpK6wMejmA7wLUGsOKrm62gS1BpfPNInZZMrz31_Wlh55qGwvOsfHg42Xk77Maw8B9siMVu-_rmgK4i_Gavgl-XvrXH-2QpFhJVX9E_9ktjqSndoNwy-Wmah0dJBXE_vkBSjjELtENNmUCtDXi4vO8CGrtk3zvHlW4UM7XnUiP3qtPJih2YgY_9xbdA8hB0Al3cZO2KYI9xmvsOJe4mRiC5uUKRf7mz7vJSUlo_DO1mqmmDLbnyFzuSg";
    private final String EMAIL = "test@tndigit.it";

    @Autowired
    RSA rsa;

    @Autowired
    JwtTokenUtil jwtTokenUtil;




    @MockBean
    ClientHttpAuthService clientHttpAuthService;


    @BeforeEach
    void init(){
        Mockito.when(clientHttpAuthService.getPublicKey()).thenReturn(Optional.of(RSATest.PUBLIC_KEY));

    }

    @Test
    void getUsernameFromToken() {

        String username = jwtTokenUtil.getUsernameFromToken(TOKEN);
        assertEquals(username,EMAIL);

    }

    @Test
    void getUserDetails() {
        JwtUser jwtUser = jwtTokenUtil.getUserDetails(TOKEN);
        assertEquals(jwtUser.getUsername(),EMAIL);

    }

    @Test
    void getExpirationDateFromToken() {
        Date date = jwtTokenUtil.getExpirationDateFromToken(TOKEN);
        LocalDate localDate = UtilityDate.localDateOf(date);
        LocalDate localDateCalc = LocalDate.of(2032,10,2);
        Assertions.assertTrue(localDate.compareTo(localDateCalc)==0,"Expired date NON corretta");


    }

    @Test
    void validateToken() {

    }
}