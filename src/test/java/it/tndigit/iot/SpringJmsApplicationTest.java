package it.tndigit.iot;

import it.tndigit.iot.service.MessageService;
import it.tndigit.iot.service.dto.message.MessageDTO;
import it.tndigit.iot.service.impl.MessageServiceIoItaliaImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringJmsApplicationTest {

    @Autowired
    private MessageService messageService;


    @Autowired
    private MessageServiceIoItaliaImpl messageServiceIoItalia;


//    @Test
//    public void testReceive() throws Exception {
//
//
//        messageService.sendMessage(new MessageDTO());
//
//        messageServiceIoItalia.getLatch().await(10000, TimeUnit.MILLISECONDS);
//        assertThat(receiver.getLatch().getCount()).isEqualTo(0);
//    }
}