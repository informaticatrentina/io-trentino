package it.tndigit.iot.service.mapper;

import it.tndigit.iot.domain.message.MessagePO;
import it.tndigit.iot.service.dto.message.MessageDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Mapper for the entity DeliberaPO and its DTO DeliberaDTO.
 */

@Mapper(componentModel = "spring", uses = {NotificationMapper.class, EnteMapper.class})
public interface MessageMapper extends EntityMapper<MessageDTO, MessagePO> {


    @Mapping(source = "idObj", target = "idObj")
    @Mapping(source = "notificationPOS", target = "notificationDTOS")
    @Mapping(source = "entePO", target = "enteDTO")
    MessageDTO toDto(MessagePO messagePO);

    @Mapping(source = "idObj", target = "idObj")
    @Mapping(source = "notificationDTOS", target = "notificationPOS")
    @Mapping(source = "enteDTO", target = "entePO")
    MessagePO toEntity(MessageDTO messageDTO);


    default MessagePO fromId(Long id) {
        if (id == null) {
            return null;
        }
        MessagePO messagePO = new MessagePO();
        messagePO.setIdObj(id);
        return messagePO;
    }
}
