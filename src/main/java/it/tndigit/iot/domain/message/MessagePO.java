package it.tndigit.iot.domain.message;

import it.tndigit.iot.costanti.TipoMessage;
import it.tndigit.iot.domain.EntePO;
import it.tndigit.iot.domain.common.DatePO;
import org.springframework.context.annotation.Scope;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "IOTTMESSAGE",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"CODICEFISCALE","IDOBJ"})})
@Inheritance(strategy = InheritanceType.JOINED)
@EntityListeners({ AuditingEntityListener.class })
@Component
@Scope("prototype")
public class MessagePO extends DatePO {


    @ManyToOne()
    @JoinColumn(name = "IDOBJ_ENTE")
    private EntePO entePO;

    @NotNull
    @Column(name = "CODICEFISCALE")
    private String codiceFiscale;

    @Enumerated(EnumType.STRING)
    @Column(name = "TIPOMESSAGGIO")
    @NotNull
    private TipoMessage tipoMessage;

    @Column(name = "IDEXTERNAL")
    private String externID;

    @Column(name = "OGGETTO",length = 120)
    @NotNull
    private String oggetto;


    @Column(name = "TESTO",length = 10000)
    @NotNull
    private String testo;


    @Column(name = "SCADENZA")
    private LocalDateTime scadenza;

    @Column(name = "TIMETOLIVE")
    @NotNull
    private Integer timeToLive;


    @Column(name = "EMAIL")
    private String email;

    @OneToMany(mappedBy = "messagePO", fetch=FetchType.LAZY, cascade = CascadeType.ALL )
    private Set<NotificationPO> notificationPOS;

    public String getCodiceFiscale() {
        return codiceFiscale;
    }

    public void setCodiceFiscale(String codiceFiscale) {
        this.codiceFiscale = codiceFiscale;
    }


    public TipoMessage getTipoMessage() {
        return tipoMessage;
    }

    public void setTipoMessage(TipoMessage tipoMessage) {
        this.tipoMessage = tipoMessage;
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

    public LocalDateTime getScadenza() {
        return scadenza;
    }

    public void setScadenza(LocalDateTime scadenza) {
        this.scadenza = scadenza;
    }

    public String getTesto() {
        return testo;
    }

    public void setTesto(String testo) {
        this.testo = testo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<NotificationPO> getNotificationPOS() {
        return notificationPOS;
    }

    public void setNotificationPOS(Set<NotificationPO> notificationPOS) {
        this.notificationPOS = notificationPOS;
    }

    public Integer getTimeToLive() {
        return timeToLive;
    }

    public void setTimeToLive(Integer timeToLive) {
        this.timeToLive = timeToLive;
    }


    public EntePO getEntePO() {
        return entePO;
    }

    public void setEntePO(EntePO entePO) {
        this.entePO = entePO;
    }

    @Override
    public String toString() {
        return "MessagePO{" +
                "codiceFiscale='" + codiceFiscale + '\'' +
                ", tipoMessage=" + tipoMessage +
                ", externID='" + externID + '\'' +
                ", oggetto='" + oggetto + '\'' +
                ", testo='" + testo + '\'' +
                ", scadenza=" + scadenza +
                ", timeToLive=" + timeToLive +
                ", email='" + email + '\'' +
                ", notificationPOS=" + notificationPOS +
                ", idObj=" + idObj +
                '}';
    }
}