package it.tndigit.iot.costanti;

import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class TipoRuoliTest {

    @ParameterizedTest
    @EnumSource(TipoRuoli.class)
    void checkRuoli(@NotNull TipoRuoli ruoli) {
        assertTrue(ruoli.getLivello() >0);
        assertNotEquals(ruoli.getDescrizione(),"");
    }

    @ParameterizedTest
    @EnumSource(TipoRuoli.class)
    void getRuolo(@NotNull TipoRuoli ruoli) {
        TipoRuoli.getRuolo(ruoli.livello).equals(ruoli);
    }


}