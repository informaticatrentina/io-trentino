package it.tndigit.iot.repository;

import it.tndigit.iot.costanti.TipoMessage;
import it.tndigit.iot.domain.message.MessagePO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface MessageRepository extends JpaRepository<MessagePO, Long> {
    Optional<MessagePO> findByIdObjAndAndCodiceFiscale (Long idObj, String codiceFiscale);

    List<MessagePO> findByTipoMessageAndExternIDNotNull(TipoMessage tipoMessage);

//    @Query("SELECT messaggio FROM MessaggioPO messaggio WHERE
//
//    List<MessagePO> findByTipoMessageAndExternIDNotNullAndNotificationPOS
}
