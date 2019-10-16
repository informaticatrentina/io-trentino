package it.tndigit.iot.repository;

import it.tndigit.iot.domain.message.MessagePO;
import it.tndigit.iot.domain.message.NotificationPO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface NotificationRepository extends JpaRepository<NotificationPO, Long> {

    Optional<NotificationPO> findByMessagePO_IdObjAndEmailNotificationAndWebhookNotificationAndStatus (Long idMessaggio, String eNot, String webNot, String status);


}
