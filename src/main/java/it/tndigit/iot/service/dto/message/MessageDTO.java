package it.tndigit.iot.service.dto.message;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import it.tndigit.iot.costanti.TipoMessage;
import it.tndigit.iot.service.dto.CommonDTO;
import it.tndigit.iot.service.dto.EnteDTO;
import org.springframework.context.annotation.Scope;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Set;

@Component
@Scope("prototype")
public class MessageDTO extends CommonDTO implements Serializable {


    private static final long serialVersionUID = 6540592208421994223L;
    @Size(min = 16, max = 16)
    private String codiceFiscale;

    private TipoMessage TipoMessage;

    private EnteDTO enteDTO;

    private String externID;

    //@Size(min = 3600, max = 604800)
    private Integer timeToLive = 3600;

    @Size(min = 10, max = 120)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String oggetto;

    @Size(min = 80, max = 10000)
    private String testo;

    private String email;

    //@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
//    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
//    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ")

    //@DateTimeFormat(iso = DateTimeFormatter.ISO_LOCAL_DATE_TIME)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private String scadenza;

    private Set<NotificationDTO> notificationDTOS;


    public Set<NotificationDTO> getNotificationDTOS() {
        return notificationDTOS;
    }

    public void setNotificationDTOS(Set<NotificationDTO> notificationDTOS) {
        this.notificationDTOS = notificationDTOS;
    }

    public String getCodiceFiscale() {
        return codiceFiscale;
    }

    public void setCodiceFiscale(String codiceFiscale) {
        this.codiceFiscale = codiceFiscale;
    }

    public it.tndigit.iot.costanti.TipoMessage getTipoMessage() {
        return TipoMessage;
    }

    public void setTipoMessage(it.tndigit.iot.costanti.TipoMessage tipoMessage) {
        TipoMessage = tipoMessage;
    }

    public String getExternID() {
        return externID;
    }

    public void setExternID(String externID) {
        this.externID = externID;
    }

    public String getOggetto() {
        return oggetto;
    }

    public void setOggetto(String oggetto) {
        this.oggetto = oggetto;
    }

    public String getTesto() {
        return testo;
    }

    public void setTesto(String testo) {
        this.testo = testo;
    }

    public Integer getTimeToLive() {
        return timeToLive;
    }

    public void setTimeToLive(Integer timeToLive) {
        this.timeToLive = timeToLive;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

//    public LocalDateTime getScadenza() {
//        return scadenza;
//    }
//
//    public void setScadenza(LocalDateTime scadenza) {
//        this.scadenza = scadenza;
//    }

    public EnteDTO getEnteDTO() {
        return enteDTO;
    }

    public void setEnteDTO(EnteDTO enteDTO) {
        this.enteDTO = enteDTO;
    }


    public String getScadenza() {
        return scadenza;
    }

    public void setScadenza(String scadenza) {
        this.scadenza = scadenza;
    }
}