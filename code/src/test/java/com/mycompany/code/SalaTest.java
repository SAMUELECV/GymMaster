/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.code;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;

/**
 *
 * @author carlo
 */
public class SalaTest {
    
    private Sala sala;
    
    @BeforeEach
    void setUp() {
        sala = new Sala(1);
    }
    
    @Test
    @DisplayName("Test creazione Sala")
    void testCreazioneSala() {
        assertNotNull(sala);
        assertEquals(1, sala.getID());
        assertTrue(sala.isDisponibilita());
    }
    
    @Test
    @DisplayName("Test isDisponibile")
    void testIsDisponibile() {
        Date data = new Date();
        boolean disponibile = sala.isDisponibile(data, 10.0, 1.5, "Sala1");
        assertTrue(disponibile);
    }
    
    @Test
    @DisplayName("Test setDisponibilita")
    void testSetDisponibilita() {
        sala.setDisponibilita(false);
        assertFalse(sala.isDisponibilita());
        
        sala.setDisponibilita(true);
        assertTrue(sala.isDisponibilita());
    }
    
    @Test
    @DisplayName("Test setID")
    void testSetID() {
        sala.setID(2);
        assertEquals(2, sala.getID());
    }
}