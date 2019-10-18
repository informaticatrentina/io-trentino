package it.tndigit.iot.service.dto;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
@Scope("prototype")
public class ServizioDTO extends CommonDTO implements Serializable {

    private static final long serialVersionUID = 257211671246793156L;

    private String nomeEnte;

    private String nomeDipartimento;

    private String nomeServizio;

    private String codiceServizioIoItalia;

    private String codiceFiscale;

    private String email;

    private String emailPec;

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

    public String getNomeServizio() {
        return nomeServizio;
    }

    public void setNomeServizio(String nomeServizio) {
        this.nomeServizio = nomeServizio;
    }

    public String getCodiceServizioIoItalia() {
        return codiceServizioIoItalia;
    }

    public void setCodiceServizioIoItalia(String codiceServizioIoItalia) {
        this.codiceServizioIoItalia = codiceServizioIoItalia;
    }
}
