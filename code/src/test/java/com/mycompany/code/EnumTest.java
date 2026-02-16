/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.code;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author carlo
 */
public class EnumTest {
    
    @Test
    @DisplayName("Test StatoAbbonamento values")
    void testStatoAbbonamentoValues() {
        StatoAbbonamento[] stati = StatoAbbonamento.values();
        assertEquals(3, stati.length);
        assertEquals(StatoAbbonamento.ATTIVO, StatoAbbonamento.valueOf("ATTIVO"));
        assertEquals(StatoAbbonamento.SOSPESO, StatoAbbonamento.valueOf("SOSPESO"));
        assertEquals(StatoAbbonamento.SCADUTO, StatoAbbonamento.valueOf("SCADUTO"));
    }

        @Test
    @DisplayName("Test StatoPrenotazione values")
    void testStatoPrenotazioneValues() {
        StatoPrenotazione[] stati = StatoPrenotazione.values();
        assertEquals(2, stati.length);
        assertEquals(StatoPrenotazione.EFFETTUATA, StatoPrenotazione.valueOf("EFFETTUATA"));
        assertEquals(StatoPrenotazione.ANNULLATA, StatoPrenotazione.valueOf("ANNULLATA"));
    }
    
    @Test
    @DisplayName("Test StatoLezione values")
    void testStatoLezioneValues() {
        StatoLezione[] stati = StatoLezione.values();
        assertEquals(4, stati.length);
        assertEquals(StatoLezione.IN_CORSO, StatoLezione.valueOf("IN_CORSO"));
        assertEquals(StatoLezione.TERMINATA, StatoLezione.valueOf("TERMINATA"));
        assertEquals(StatoLezione.NON_INIZIATA, StatoLezione.valueOf("NON_INIZIATA"));
        assertEquals(StatoLezione.ANNULLATA, StatoLezione.valueOf("ANNULLATA"));
    }
}