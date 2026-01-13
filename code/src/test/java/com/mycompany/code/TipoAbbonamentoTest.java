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
import java.util.Calendar;

/**
 *
 * @author carlo
 */
public class TipoAbbonamentoTest {
    
    private TipoAbbonamento tipoAbbonamento;
    private Date durata;
    
    @BeforeEach
    void setUp() {
        Calendar cal = Calendar.getInstance();
        cal.set(1970, 0, 31);
        durata = cal.getTime();
        
        tipoAbbonamento = new TipoAbbonamento("Mensile Premium", "Accesso completo", durata, 49.99, true);
    }
    
    @Test
    @DisplayName("Test creazione TipoAbbonamento")
    void testCreazioneTipoAbbonamento() {
        assertNotNull(tipoAbbonamento);
        assertEquals("Mensile Premium", tipoAbbonamento.getNomeabbonamento());
        assertEquals("Accesso completo", tipoAbbonamento.getDescrizione());
        assertEquals(durata, tipoAbbonamento.getDurata());
        assertEquals(49.99, tipoAbbonamento.getPrezzo());
        assertTrue(tipoAbbonamento.isLezione());
    }
    
    @Test
    @DisplayName("Test verificaTipoAbbonamento con lezioni")
    void testVerificaTipoAbbonamentoConLezioni() {
        assertTrue(tipoAbbonamento.verificaTipoAbbonamento());
    }
    
    @Test
    @DisplayName("Test verificaTipoAbbonamento senza lezioni")
    void testVerificaTipoAbbonamentoSenzaLezioni() {
        TipoAbbonamento tipo = new TipoAbbonamento("Base", "Senza lezioni", durata, 19.99, false);
        assertFalse(tipo.verificaTipoAbbonamento());
    }
    
    @Test
    @DisplayName("Test setters individuali")
    void testSetters() {
        tipoAbbonamento.setNome("Nuovo Nome");
        assertEquals("Nuovo Nome", tipoAbbonamento.getNomeabbonamento());
        
        tipoAbbonamento.setDescrizione("Nuova Descrizione");
        assertEquals("Nuova Descrizione", tipoAbbonamento.getDescrizione());
        
        Calendar cal = Calendar.getInstance();
        cal.set(1970, 0, 90);
        Date nuovaDurata = cal.getTime();
        tipoAbbonamento.setDurata(nuovaDurata);
        assertEquals(nuovaDurata, tipoAbbonamento.getDurata());
        
        tipoAbbonamento.setPrezzo(99.99);
        assertEquals(99.99, tipoAbbonamento.getPrezzo());
        
        tipoAbbonamento.setLezioneDiGruppo(false);
        assertFalse(tipoAbbonamento.isLezione());
    }
    
    @Test
    @DisplayName("Test aggiornaDati - Information Expert")
    void testAggiornaDati() {
        assertEquals("Mensile Premium", tipoAbbonamento.getNomeabbonamento());
        assertEquals("Accesso completo", tipoAbbonamento.getDescrizione());
        assertEquals(49.99, tipoAbbonamento.getPrezzo());
        assertTrue(tipoAbbonamento.isLezione());
        
        Calendar cal = Calendar.getInstance();
        cal.set(1970, 0, 90);
        Date nuovaDurata = cal.getTime();
        
        tipoAbbonamento.aggiornaDati("Trimestrale", "Completo", nuovaDurata, 79.99, false);
        
        assertEquals("Trimestrale", tipoAbbonamento.getNomeabbonamento());
        assertEquals("Completo", tipoAbbonamento.getDescrizione());
        assertEquals(nuovaDurata, tipoAbbonamento.getDurata());
        assertEquals(79.99, tipoAbbonamento.getPrezzo());
        assertFalse(tipoAbbonamento.isLezione());
    }
    
    @Test
    @DisplayName("Test aggiornaDati - tutti i campi cambiano")
    void testAggiornaDatiTuttiCambi() {
        Calendar cal = Calendar.getInstance();
        cal.set(1970, 0, 365);
        Date durataAnnuale = cal.getTime();
        
        tipoAbbonamento.aggiornaDati("ANNUALE", "Un anno completo", durataAnnuale, 199.99, true);
        
        assertEquals("ANNUALE", tipoAbbonamento.getNomeabbonamento());
        assertEquals("Un anno completo", tipoAbbonamento.getDescrizione());
        assertEquals(durataAnnuale, tipoAbbonamento.getDurata());
        assertEquals(199.99, tipoAbbonamento.getPrezzo());
        assertTrue(tipoAbbonamento.isLezione());
    }
}