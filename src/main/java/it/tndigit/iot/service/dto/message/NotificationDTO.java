package it.tndigit.iot.service.dto.message;

import it.tndigit.iot.costanti.TipoStatus;
import it.tndigit.iot.service.dto.CommonDTO;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@Scope("prototype")
@Data
public class NotificationDTO extends CommonDTO {

    @Autowired
    private MessageDTO messageDTO;

    private TipoStatus emailNotification;
    private TipoStatus webhookNotification;
    private TipoStatus status;
    private LocalDateTime lastChance;
    private String note;

}
