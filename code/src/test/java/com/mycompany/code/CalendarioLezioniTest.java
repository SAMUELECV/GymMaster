/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.code;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;
/**
 *
 * @author carlo
 */
public class CalendarioLezioniTest {
    
    private CalendarioLezioni calendario;
    
    @BeforeEach
    void setUp() {
        calendario = new CalendarioLezioni();
    }
    
    @Test
    @DisplayName("Test creazione CalendarioLezioni")
    void testCreazione() {
        assertNotNull(calendario);
        assertEquals(0, calendario.getObserverState());
    }
    
    @Test
    @DisplayName("Test update incrementa observerState")
    void testUpdate() {
        assertEquals(0, calendario.getObserverState());
        
        calendario.update();
        assertEquals(1, calendario.getObserverState());
        
        calendario.update();
        assertEquals(2, calendario.getObserverState());
    }
    
    @Test
    @DisplayName("Test update multipli")
    void testUpdateMultipli() {
        for (int i = 0; i < 5; i++) {
            calendario.update();
        }
        assertEquals(5, calendario.getObserverState());
    }
    
    @Test
    @DisplayName("Test refreshView non genera eccezioni")
    void testRefreshView() {
        assertDoesNotThrow(() -> calendario.refreshView());
    }
    
    @Test
    @DisplayName("Test che CalendarioLezioni implementa Observer")
    void testImplementaObserver() {
        assertTrue(calendario instanceof Observer);
    }
    
    @Test
    @DisplayName("Test observerState iniziale a zero")
    void testStatoIniziale() {
        CalendarioLezioni nuovoCal = new CalendarioLezioni();
        assertEquals(0, nuovoCal.getObserverState());
    }
}
