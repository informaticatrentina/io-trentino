package it.tndigit.iot.service.dto.message;

import it.tndigit.iot.service.dto.CommonDTO;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class PaymentDTO extends CommonDTO {

    private static final long serialVersionUID = 8754912410969106985L;

    private Integer importo;

    private String numeroAvviso;

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
}
