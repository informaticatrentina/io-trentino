package it.tndigit.iot.repository;

import it.tndigit.iot.domain.logging.LoggingPO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface LoggingRepository extends JpaRepository< LoggingPO, Long> {

}
