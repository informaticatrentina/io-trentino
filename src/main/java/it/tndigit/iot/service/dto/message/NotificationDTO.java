package it.tndigit.iot.service.dto.message;

import it.tndigit.iot.service.dto.CommonDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@Scope("prototype")
public class NotificationDTO extends CommonDTO {

    @Autowired
    private MessageDTO messageDTO;

    private String eMailNotification;

    private String webhookNotification;

    private String status;

    private LocalDateTime lastChance;

    private String note;

    public MessageDTO getMessageDTO() {
        return messageDTO;
    }

    public void setMessageDTO(MessageDTO messageDTO) {
        this.messageDTO = messageDTO;
    }

    public String geteMailNotification() {
        return eMailNotification;
    }

    public void seteMailNotification(String eMailNotification) {
        this.eMailNotification = eMailNotification;
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
