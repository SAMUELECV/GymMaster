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
public class CorsoTest {
    
    private Corso corso;
    
    @BeforeEach
    void setUp() {
        corso = new Corso("Yoga", "Corso di yoga per principianti", "Marco Zen");
    }
    
    @Test
    @DisplayName("Test creazione Corso")
    void testCreazioneCorso() {
        assertNotNull(corso);
        assertEquals("Yoga", corso.getNomecorso());
        assertEquals("Corso di yoga per principianti", corso.getDescrizione());
        assertEquals("Marco Zen", corso.getIstruttorepredefinito());
    }
    
    @Test
    @DisplayName("Test getCorso")
    void testGetCorso() {
        Corso result = corso.getCorso();
        assertEquals(corso, result);
    }
    
    @Test
    @DisplayName("Test lista lezioni iniziale vuota")
    void testListaLezioniIniziale() {
        assertNotNull(corso.getLezioni());
        assertTrue(corso.getLezioni().isEmpty());
    }
    
    @Test
    @DisplayName("Test addLezione")
    void testAddLezione() {
        Lezione lezione = new Lezione("Lunedi", 10.0, 15, 20, "Sala1", 1.5, 
                                      StatoLezione.NON_INIZIATA, new Date(), "Marco Zen", "YOG001");
        
        corso.addLezione(lezione);
        
        assertEquals(1, corso.getLezioni().size());
        assertTrue(corso.getLezioni().contains(lezione));
    }
    
    @Test
    @DisplayName("Test addLezione multiple")
    void testAddLezioneMultiple() {
        Lezione lezione1 = new Lezione("Lunedi", 10.0, 15, 20, "Sala1", 1.5, 
                                       StatoLezione.NON_INIZIATA, new Date(), "Marco Zen", "YOG001");
        Lezione lezione2 = new Lezione("Mercoledi", 18.0, 10, 15, "Sala1", 1.0, 
                                       StatoLezione.NON_INIZIATA, new Date(), "Marco Zen", "YOG002");
        
        corso.addLezione(lezione1);
        corso.addLezione(lezione2);
        
        assertEquals(2, corso.getLezioni().size());
    }
    
    @Test
    @DisplayName("Test setters")
    void testSetters() {
        corso.setNomecorso("Pilates");
        corso.setDescrizione("Corso di pilates");
        corso.setIstruttorepredefinito("Anna Flex");
        
        assertEquals("Pilates", corso.getNomecorso());
        assertEquals("Corso di pilates", corso.getDescrizione());
        assertEquals("Anna Flex", corso.getIstruttorepredefinito());
    }
}