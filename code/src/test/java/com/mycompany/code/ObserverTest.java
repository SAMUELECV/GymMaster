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
public class ObserverTest {
    
    @Test
    @DisplayName("Test che CalendarioLezioni implementa Observer")
    void testCalendarioLezioniImplementaObserver() {
        CalendarioLezioni cal = new CalendarioLezioni();
        assertTrue(cal instanceof Observer);
    }
    
    @Test
    @DisplayName("Test che un Observer custom implementa l'interfaccia")
    void testObserverCustom() {
        Observer obs = new Observer() {
            @Override
            public void update() {
            }
        };
        
        assertNotNull(obs);
        assertDoesNotThrow(() -> obs.update());
    }
}