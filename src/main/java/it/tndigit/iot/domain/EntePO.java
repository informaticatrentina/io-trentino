package it.tndigit.iot.domain;

import it.tndigit.iot.domain.common.DatePO;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import javax.validation.constraints.NotNull;


@Entity
@Table(name = "IOTTENTE")
@Inheritance(strategy = InheritanceType.JOINED)
@Component
@Scope("prototype")
public class EntePO  extends DatePO {

    @NotNull
    @Column(name = "NOME_ENTE",unique = true)
    private String nomeEnte;

    @NotNull
    @Column(name = "NOME_DIPARTIMENTO")
    private String nomeDipartimento;

    @NotNull
    @Column(name = "CODICEFISCALE",unique = true)
    private String codiceFiscale;

    @NotNull
    @Column(name = "EMAIL")
    private String email;

    @NotNull
    @Column(name = "EMAIL_PEC",unique = true)
    private String emailPec;

    @Column(name = "TOKEN")
    private String tokenIoItalia;

    public String getNomeEnte() {
        return nomeEnte;
    }

    public void setNomeEnte(String nomeEnte) {
        this.nomeEnte = nomeEnte;
    }

    public String getNomeDipartimento() {
        return nomeDipartimento;
    }

    public void setNomeDipartimento(String nomeDipartimento) {
        this.nomeDipartimento = nomeDipartimento;
    }

    public String getCodiceFiscale() {
        return codiceFiscale;
    }

    public void setCodiceFiscale(String codiceFiscale) {
        this.codiceFiscale = codiceFiscale;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmailPec() {
        return emailPec;
    }

    public void setEmailPec(String emailPec) {
        this.emailPec = emailPec;
    }

    public String getTokenIoItalia() {
        return tokenIoItalia;
    }

    public void setTokenIoItalia(String tokenIoItalia) {
        this.tokenIoItalia = tokenIoItalia;
    }
}
