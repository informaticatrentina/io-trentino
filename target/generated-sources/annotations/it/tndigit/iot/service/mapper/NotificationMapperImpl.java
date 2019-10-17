package it.tndigit.iot.service.mapper;

import it.tndigit.iot.domain.message.MessagePO;
import it.tndigit.iot.domain.message.NotificationPO;
import it.tndigit.iot.service.dto.message.MessageDTO;
import it.tndigit.iot.service.dto.message.NotificationDTO;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2019-10-17T11:16:33+0200",
    comments = "version: 1.3.0.Final, compiler: javac, environment: Java 1.8.0_222 (Private Build)"
)
@Component
public class NotificationMapperImpl implements NotificationMapper {

    @Override
    public List<NotificationPO> toEntity(List<NotificationDTO> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<NotificationPO> list = new ArrayList<NotificationPO>( dtoList.size() );
        for ( NotificationDTO notificationDTO : dtoList ) {
            list.add( toEntity( notificationDTO ) );
        }

        return list;
    }

    @Override
    public List<NotificationDTO> toDto(List<NotificationPO> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<NotificationDTO> list = new ArrayList<NotificationDTO>( entityList.size() );
        for ( NotificationPO notificationPO : entityList ) {
            list.add( toDto( notificationPO ) );
        }

        return list;
    }

    @Override
    public NotificationDTO toDto(NotificationPO notificationPO) {
        if ( notificationPO == null ) {
            return null;
        }

        NotificationDTO notificationDTO = new NotificationDTO();

        notificationDTO.setMessageDTO( messagePOToMessageDTO( notificationPO.getMessagePO() ) );
        notificationDTO.setIdObj( notificationPO.getIdObj() );
        notificationDTO.setDataInserimento( notificationPO.getDataInserimento() );
        notificationDTO.setWebhookNotification( notificationPO.getWebhookNotification() );
        notificationDTO.setStatus( notificationPO.getStatus() );
        notificationDTO.setLastChance( notificationPO.getLastChance() );
        notificationDTO.setNote( notificationPO.getNote() );

        return notificationDTO;
    }

    @Override
    public NotificationPO toEntity(NotificationDTO notificationDTO) {
        if ( notificationDTO == null ) {
            return null;
        }

        NotificationPO notificationPO = new NotificationPO();

        notificationPO.setMessagePO( messageDTOToMessagePO( notificationDTO.getMessageDTO() ) );
        notificationPO.setIdObj( notificationDTO.getIdObj() );
        notificationPO.setWebhookNotification( notificationDTO.getWebhookNotification() );
        notificationPO.setStatus( notificationDTO.getStatus() );
        notificationPO.setDataInserimento( notificationDTO.getDataInserimento() );
        notificationPO.setLastChance( notificationDTO.getLastChance() );
        notificationPO.setNote( notificationDTO.getNote() );

        return notificationPO;
    }

    protected MessageDTO messagePOToMessageDTO(MessagePO messagePO) {
        if ( messagePO == null ) {
            return null;
        }

        MessageDTO messageDTO = new MessageDTO();

        messageDTO.setIdObj( messagePO.getIdObj() );
        messageDTO.setVersion( messagePO.getVersion() );
        messageDTO.setDataModifica( messagePO.getDataModifica() );
        messageDTO.setDataInserimento( messagePO.getDataInserimento() );
        messageDTO.setCodiceFiscale( messagePO.getCodiceFiscale() );
        messageDTO.setTipoMessage( messagePO.getTipoMessage() );
        messageDTO.setExternID( messagePO.getExternID() );
        messageDTO.setOggetto( messagePO.getOggetto() );
        messageDTO.setTesto( messagePO.getTesto() );
        messageDTO.setTimeToLive( messagePO.getTimeToLive() );
        messageDTO.setEmail( messagePO.getEmail() );
        if ( messagePO.getScadenza() != null ) {
            messageDTO.setScadenza( DateTimeFormatter.ISO_LOCAL_DATE_TIME.format( messagePO.getScadenza() ) );
        }

        return messageDTO;
    }

    protected MessagePO messageDTOToMessagePO(MessageDTO messageDTO) {
        if ( messageDTO == null ) {
            return null;
        }

        MessagePO messagePO = new MessagePO();

        messagePO.setIdObj( messageDTO.getIdObj() );
        messagePO.setVersion( messageDTO.getVersion() );
        messagePO.setCodiceFiscale( messageDTO.getCodiceFiscale() );
        messagePO.setTipoMessage( messageDTO.getTipoMessage() );
        messagePO.setExternID( messageDTO.getExternID() );
        messagePO.setOggetto( messageDTO.getOggetto() );
        if ( messageDTO.getScadenza() != null ) {
            messagePO.setScadenza( LocalDateTime.parse( messageDTO.getScadenza() ) );
        }
        messagePO.setTesto( messageDTO.getTesto() );
        messagePO.setEmail( messageDTO.getEmail() );
        messagePO.setTimeToLive( messageDTO.getTimeToLive() );

        return messagePO;
    }
}
