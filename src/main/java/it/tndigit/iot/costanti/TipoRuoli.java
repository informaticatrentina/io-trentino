package it.tndigit.iot.costanti;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Mirko Pianetti
 *
 *
 * Work in progress
 *
 */

public enum TipoRuoli {
    ROLE_ADMIN(TipoRuoli.ADMIN, "Utente amministratore, può fare tutto", 1L),
    ROLE_ENTE(TipoRuoli.ENTE, "Utente", 5L),

    ROLE_JOB(TipoRuoli.JOB, "Utente TIMER", 60L),
    ROLE_USERS(TipoRuoli.USERS, "Utente base, può modificare solamente i suoi dati", 90L);


    public static final String ADMIN = "ROLE_ADMIN";
    public static final String ENTE = "ROLE_ENTE";
        public static final String JOB = "ROLE_JOB";
    public static final String USERS = "ROLE_USERS";



    String nomeRuolo;
    Long livello;
    String descrizione;

    TipoRuoli(String nomeRuolo, String descrizione, Long livello) {
        this.nomeRuolo = nomeRuolo;
        this.livello = livello;
        this.descrizione = descrizione;
    }

    public static TipoRuoli getRuolo(Long livello) {
        for (TipoRuoli tipoRuoli : TipoRuoli.values()) {
            if (tipoRuoli.getLivello().equals(livello)) {
                return tipoRuoli;
            }
        }
        return null;
    }

    public static List<TipoRuoli> getListaRuoliApp(){
        List<TipoRuoli> listaRuoliApplicativi = new ArrayList<>();

        for (TipoRuoli tipoRuoli : TipoRuoli.values()) {
            if (tipoRuoli.getLivello().equals(1L)|| (tipoRuoli.getLivello()>=30 && tipoRuoli.getLivello()<=50)) {
                listaRuoliApplicativi.add(tipoRuoli);
            }
        }
        return listaRuoliApplicativi;


    }

    public String getNomeRuolo() {
        return nomeRuolo;
    }

    public void setNomeRuolo(String nomeRuolo) {
        this.nomeRuolo = nomeRuolo;
    }

    public Long getLivello() {
        return livello;
    }

    public void setLivello(Long livello) {
        this.livello = livello;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

}
