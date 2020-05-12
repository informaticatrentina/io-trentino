package it.tndigit.iot.domain.logging;

import it.tndigit.iot.domain.common.CommonPO;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.Scope;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Table(name = "IOTTLOGGING")
@Inheritance(strategy = InheritanceType.JOINED)
@Component
@Scope("prototype")
@Getter
@Setter
public class LoggingPO extends CommonPO {

    @CreatedDate
    @Column(name = "DATAINSERIMENTO", insertable = true, updatable = false)
    private LocalDateTime dataInserimento;

    @CreatedBy
    @Column(name = "UTENTEINSERIMENTO", insertable = true, updatable = false, length = 100, nullable = false)
    private String utenteInserimento;

    @NotNull
    @Column(name = "METHOD")
    private String method;

    @NotNull
    @Column(name = "RETURNCODE")
    private String returnCode;

    @NotNull
    @Column(name = "MESSAGE")
    private String message;

    @Column(name = "IDMESSAGE")
    private long idMessage;







}
