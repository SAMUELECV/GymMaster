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
public class PrenotazioneTest {
    
    private Prenotazione prenotazione;
    private Date dataPrenotazione;
    
    @BeforeEach
    void setUp() {
        dataPrenotazione = new Date();
        prenotazione = new Prenotazione(dataPrenotazione, "10.0", StatoPrenotazione.EFFETTUATA, 1);
    }
    
    @Test
    @DisplayName("Test creazione Prenotazione")
    void testCreazionePrenotazione() {
        assertNotNull(prenotazione);
        assertEquals(dataPrenotazione, prenotazione.getDataprenotazione());
        assertEquals("10.0", prenotazione.getOraprenotazione());
        assertEquals(StatoPrenotazione.EFFETTUATA, prenotazione.getStatoprenotazione());
        assertEquals(1, prenotazione.getIdprenotazione());
    }
    
    @Test
    @DisplayName("Test setStatoPrenotazione")
    void testSetStatoPrenotazione() {
        prenotazione.setStatoPrenotazione(prenotazione, StatoPrenotazione.ANNULLATA);
        assertEquals(StatoPrenotazione.ANNULLATA, prenotazione.getStatoprenotazione());
    }
    
    @Test
    @DisplayName("Test lezione iniziale null")
    void testLezioneIniziale() {
        assertNull(prenotazione.getLezione());
    }
    
    @Test
    @DisplayName("Test setLezione")
    void testSetLezione() {
        Lezione lezione = new Lezione("Lunedi", 10.0, 15, 20, "Sala1", 1.5, 
                                      StatoLezione.NON_INIZIATA, new Date(), "Marco Zen", "YOG001");
        
        prenotazione.setLezione(lezione);
        
        assertNotNull(prenotazione.getLezione());
        assertEquals(lezione, prenotazione.getLezione());
    }
    
    @Test
    @DisplayName("Test CF iscritto")
    void testCFIscritto() {
        assertNull(prenotazione.getCFIscritto());
        
        prenotazione.setCFIscritto("BNCLCU95C15H501Z");
        assertEquals("BNCLCU95C15H501Z", prenotazione.getCFIscritto());
    }
    
    @Test
    @DisplayName("Test getPrenotazioneSpecifica - trovata")
    void testGetPrenotazioneSpecificaTrovata() {
        Prenotazione result = prenotazione.getPrenotazioneSpecifica("1");
        assertNotNull(result);
        assertEquals(prenotazione, result);
    }
    
    @Test
    @DisplayName("Test getPrenotazioneSpecifica - non trovata")
    void testGetPrenotazioneSpecificaNonTrovata() {
        Prenotazione result = prenotazione.getPrenotazioneSpecifica("999");
        assertNull(result);
    }
    
    @Test
    @DisplayName("Test setters")
    void testSetters() {
        Date nuovaData = new Date();
        prenotazione.setDataprenotazione(nuovaData);
        prenotazione.setOraprenotazione("15.0");
        
        assertEquals(nuovaData, prenotazione.getDataprenotazione());
        assertEquals("15.0", prenotazione.getOraprenotazione());
    }
}