package it.tndigit.iot.domain.message;


import it.tndigit.iot.domain.common.CommonPO;
import it.tndigit.iot.domain.common.DatePO;
import org.springframework.context.annotation.Scope;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "IOTTNOTIFICATION")
@Inheritance(strategy = InheritanceType.JOINED)
@EntityListeners({ AuditingEntityListener.class })
@Component
@Scope("prototype")
public class NotificationPO extends CommonPO {
    private static final long serialVersionUID = -8978926117208941821L;

    @ManyToOne( fetch = FetchType.EAGER )
    @JoinColumn(name = "IDMESSAGE",updatable = false)
    private MessagePO messagePO;

    @Column(name = "EMAIL_NOTIFICATION")
    private String emailNotification;

    @Column(name = "WEBHOOD_NOTIFICATION")
    private String webhookNotification;

    @Column(name = "STATUS")
    private String status;

    @CreatedDate
    @Column(name = "DATAINSERIMENTO", insertable = true, updatable = false)
    private LocalDateTime dataInserimento;

    @Column(name = "ULTIMOTENTATIVO")
    private LocalDateTime lastChance;


    @Column(name = "NOTE", length = 10000)
    private String note;



    public MessagePO getMessagePO() {
        return messagePO;
    }

    public void setMessagePO(MessagePO messagePO) {
        this.messagePO = messagePO;
    }


    public String getEmailNotification() {
        return emailNotification;
    }

    public void setEmailNotification(String emailNotification) {
        this.emailNotification = emailNotification;
    }

    public String getWebhookNotification() {
        return webhookNotification;
    }

    public void setWebhookNotification(String webhookNotification) {
        this.webhookNotification = webhookNotification;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getDataInserimento() {
        return dataInserimento;
    }

    public void setDataInserimento(LocalDateTime dataInserimento) {
        this.dataInserimento = dataInserimento;
    }

    public LocalDateTime getLastChance() {
        return lastChance;
    }

    public void setLastChance(LocalDateTime lastChance) {
        this.lastChance = lastChance;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
