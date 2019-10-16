package it.tndigit.iot.service.mapper;

import it.tndigit.iot.domain.message.MessagePO;
import it.tndigit.iot.domain.message.NotificationPO;
import it.tndigit.iot.service.dto.message.MessageDTO;
import it.tndigit.iot.service.dto.message.NotificationDTO;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.annotation.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2019-10-16T12:47:30+0200",
    comments = "version: 1.3.0.Final, compiler: javac, environment: Java 1.8.0_222 (Private Build)"
)
@Component
public class MessageMapperImpl implements MessageMapper {

    @Autowired
    private NotificationMapper notificationMapper;
    @Autowired
    private EnteMapper enteMapper;

    @Override
    public List<MessagePO> toEntity(List<MessageDTO> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<MessagePO> list = new ArrayList<MessagePO>( dtoList.size() );
        for ( MessageDTO messageDTO : dtoList ) {
            list.add( toEntity( messageDTO ) );
        }

        return list;
    }

    @Override
    public List<MessageDTO> toDto(List<MessagePO> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<MessageDTO> list = new ArrayList<MessageDTO>( entityList.size() );
        for ( MessagePO messagePO : entityList ) {
            list.add( toDto( messagePO ) );
        }

        return list;
    }

    @Override
    public MessageDTO toDto(MessagePO messagePO) {
        if ( messagePO == null ) {
            return null;
        }

        MessageDTO messageDTO = new MessageDTO();

        messageDTO.setNotificationDTOS( notificationPOSetToNotificationDTOSet( messagePO.getNotificationPOS() ) );
        messageDTO.setEnteDTO( enteMapper.toDto( messagePO.getEntePO() ) );
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

    @Override
    public MessagePO toEntity(MessageDTO messageDTO) {
        if ( messageDTO == null ) {
            return null;
        }

        MessagePO messagePO = new MessagePO();

        messagePO.setIdObj( messageDTO.getIdObj() );
        messagePO.setEntePO( enteMapper.toEntity( messageDTO.getEnteDTO() ) );
        messagePO.setNotificationPOS( notificationDTOSetToNotificationPOSet( messageDTO.getNotificationDTOS() ) );
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

    protected Set<NotificationDTO> notificationPOSetToNotificationDTOSet(Set<NotificationPO> set) {
        if ( set == null ) {
            return null;
        }

        Set<NotificationDTO> set1 = new HashSet<NotificationDTO>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( NotificationPO notificationPO : set ) {
            set1.add( notificationMapper.toDto( notificationPO ) );
        }

        return set1;
    }

    protected Set<NotificationPO> notificationDTOSetToNotificationPOSet(Set<NotificationDTO> set) {
        if ( set == null ) {
            return null;
        }

        Set<NotificationPO> set1 = new HashSet<NotificationPO>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( NotificationDTO notificationDTO : set ) {
            set1.add( notificationMapper.toEntity( notificationDTO ) );
        }

        return set1;
    }
}
