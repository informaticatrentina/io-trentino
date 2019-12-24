package it.tndigit.iot.domain.message;


import it.tndigit.iot.domain.common.CommonPO;
import org.springframework.context.annotation.Scope;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "IOTTPAYMENT")
@Inheritance(strategy = InheritanceType.JOINED)
@EntityListeners({ AuditingEntityListener.class })
@Component
@Scope("prototype")
public class PaymentPO extends CommonPO {
   private static final long serialVersionUID = 6140202288313216199L;


    @NotNull
    @Column(name = "IMPORTO")
    private Integer importo;

    @Column(name = "NUMEROAVVISO")
    private String numeroAvviso;

    @Column(name = "INVALID_AFTER_DUE_DATE")
    private Boolean invalid_after_due_date;


    public Integer getImporto() {
        return importo;
    }

    public void setImporto(Integer importo) {
        this.importo = importo;
    }

    public String getNumeroAvviso() {
        return numeroAvviso;
    }

    public void setNumeroAvviso(String numeroAvviso) {
        this.numeroAvviso = numeroAvviso;
    }

    public Boolean getInvalid_after_due_date() {
        return invalid_after_due_date;
    }

    public void setInvalid_after_due_date(Boolean invalid_after_due_date) {
        this.invalid_after_due_date = invalid_after_due_date;
    }

    @Override
    public String toString() {
        return "PaymentPO{" +
                "importo=" + importo +
                ", numeroAvviso='" + numeroAvviso + '\'' +
                ", invalid_after_due_date=" + invalid_after_due_date +
                ", idObj=" + idObj +
                '}';
    }
}
