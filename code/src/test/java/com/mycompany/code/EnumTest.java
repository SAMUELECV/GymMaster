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

}