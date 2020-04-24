package it.tndigit.iot.service.dto.message;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import it.tndigit.iot.costanti.TipoCryptoMessage;
import it.tndigit.iot.costanti.TipoMessage;
import it.tndigit.iot.service.dto.CommonDTO;
import it.tndigit.iot.service.dto.ServizioDTO;
import lombok.Data;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Set;

@Component
@Scope("prototype")
@Data
public class MessageDTO extends CommonDTO implements Serializable {


    private static final long serialVersionUID = 6540592208421994223L;
    @Size(min = 16, max = 16)
    private String codiceFiscale;

    private TipoMessage TipoMessage;

    private TipoCryptoMessage tipoCryptoMessage;

    private ServizioDTO servizioDTO;

    private String externID;

    private Integer timeToLive = 3600;

    @Size(min = 10, max = 120)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String oggetto;

    @Size(min = 80, max = 10000)
    private String testo;

    private String email;

    private String telefono;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private String scadenza;

    private Set<NotificationDTO> notificationDTOS;

    private PaymentDTO paymentDTO;

    private String errorSend;


}
