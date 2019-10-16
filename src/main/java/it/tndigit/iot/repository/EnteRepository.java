package it.tndigit.iot.repository;

import it.tndigit.iot.domain.EntePO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface EnteRepository extends JpaRepository<EntePO, Long> {
    Optional<EntePO> findByEmailPec(String eMailPec);
}
