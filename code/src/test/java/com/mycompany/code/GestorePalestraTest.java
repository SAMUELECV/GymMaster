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
public class GestorePalestraTest {
    
    private GestorePalestra gestorePalestra;
    private Date durataMensile;
    private Date durataTrimestrle;
    private Date durataAnnuale;
    
    @BeforeEach
    void setUp() {
        gestorePalestra = new GestorePalestra("Giuseppe", "Verdi");
        
        Calendar cal = Calendar.getInstance();
        cal.set(1970, 0, 31);
        durataMensile = cal.getTime();
        
        cal.set(1970, 0, 90);
        durataTrimestrle = cal.getTime();
        
        cal.set(1970, 0, 365);
        durataAnnuale = cal.getTime();
    }
    
    @Test
    @DisplayName("Test creazione GestorePalestra")
    void testCreazioneGestorePalestra() {
        assertNotNull(gestorePalestra);
        assertEquals("Giuseppe", gestorePalestra.getNomegestore());
        assertEquals("Verdi", gestorePalestra.getCognome());
    }
    
    @Test
    @DisplayName("Test mappa tipi abbonamento inizialmente vuota")
    void testMappaInizialmenteVuota() {
        assertNotNull(gestorePalestra.getTipoAbbonamentoMap());
        assertTrue(gestorePalestra.getTipoAbbonamentoMap().isEmpty());
        assertEquals(0, gestorePalestra.getTipiAbbonamento());
    }
    
    @Test
    @DisplayName("UC9 - Test crea nuovo tipo abbonamento MENSILE")
    void testCreaNuovoTipoAbbonamentoMensile() {
        TipoAbbonamento tipo = gestorePalestra.creaNuovoTipoAbbonamento("MENSILE", "Abbonamento mensile", durataMensile, 29.99, true);
        
        assertNotNull(tipo);
        assertEquals("MENSILE", tipo.getNomeabbonamento());
        assertEquals(29.99, tipo.getPrezzo());
        assertTrue(tipo.isLezione());
        assertEquals(1, gestorePalestra.getTipiAbbonamento());
    }
    
    @Test
    @DisplayName("UC9 - Test crea nuovo tipo abbonamento TRIMESTRALE")
    void testCreaNuovoTipoAbbonamentoTrimestrale() {
        TipoAbbonamento tipo = gestorePalestra.creaNuovoTipoAbbonamento("TRIMESTRALE", "Abbonamento trimestrale", durataTrimestrle, 69.99, true);
        
        assertNotNull(tipo);
        assertEquals("TRIMESTRALE", tipo.getNomeabbonamento());
        assertEquals(69.99, tipo.getPrezzo());
        assertEquals(1, gestorePalestra.getTipiAbbonamento());
    }
    
    @Test
    @DisplayName("UC9 - Test crea nuovo tipo abbonamento ANNUALE")
    void testCreaNuovoTipoAbbonamentoAnnuale() {
        TipoAbbonamento tipo = gestorePalestra.creaNuovoTipoAbbonamento("ANNUALE", "Abbonamento annuale", durataAnnuale, 199.99, true);
        
        assertNotNull(tipo);
        assertEquals("ANNUALE", tipo.getNomeabbonamento());
        assertEquals(199.99, tipo.getPrezzo());
        assertEquals(1, gestorePalestra.getTipiAbbonamento());
    }
    
    @Test
    @DisplayName("UC9 - Test crea multipli tipi abbonamento")
    void testCreaMultipliTipi() {
        gestorePalestra.creaNuovoTipoAbbonamento("MENSILE", "Mensile", durataMensile, 29.99, true);
        gestorePalestra.creaNuovoTipoAbbonamento("TRIMESTRALE", "Trimestrale", durataTrimestrle, 69.99, true);
        gestorePalestra.creaNuovoTipoAbbonamento("ANNUALE", "Annuale", durataAnnuale, 199.99, true);
        
        assertEquals(3, gestorePalestra.getTipiAbbonamento());
    }
    
    @Test
    @DisplayName("UC9 - Test modifica tipo abbonamento con aggiornaDati")
    void testModificaTipoAbbonamento() {
        gestorePalestra.creaNuovoTipoAbbonamento("MENSILE", "Base", durataMensile, 29.99, false);
        
        Calendar cal = Calendar.getInstance();
        cal.set(1970, 0, 90);
        Date nuovaDurata = cal.getTime();
        
        gestorePalestra.modificaTipoAbbonamento("MENSILE", "Completo", nuovaDurata, 49.99, true);
        
        TipoAbbonamento ta = gestorePalestra.getTipoAbbonamento("MENSILE");
        assertNotNull(ta);
        assertEquals("MENSILE", ta.getNomeabbonamento());
        assertEquals("Completo", ta.getDescrizione());
        assertEquals(nuovaDurata, ta.getDurata());
        assertEquals(49.99, ta.getPrezzo());
        assertTrue(ta.isLezione());
    }
    
    @Test
    @DisplayName("UC9 - Test modifica tipo abbonamento inesistente")
    void testModificaTipoAbbonamentoInesistente() {
        assertDoesNotThrow(() -> gestorePalestra.modificaTipoAbbonamento("INESISTENTE", "Desc", durataMensile, 10.0, false));
    }
    
    @Test
    @DisplayName("UC9 - Test seleziona tipo abbonamento esistente")
    void testSelezionaTipoAbbonamentoEsistente() {
        gestorePalestra.creaNuovoTipoAbbonamento("MENSILE", "Test", durataMensile, 29.99, true);
        
        TipoAbbonamento tipo = gestorePalestra.selezionaTipologieAbbonamento("MENSILE");
        
        assertNotNull(tipo);
        assertEquals("MENSILE", tipo.getNomeabbonamento());
    }
    
    @Test
    @DisplayName("UC9 - Test seleziona tipo abbonamento inesistente")
    void testSelezionaTipoAbbonamentoInesistente() {
        TipoAbbonamento tipo = gestorePalestra.selezionaTipologieAbbonamento("Inesistente");
        assertNull(tipo);
    }
    
    @Test
    @DisplayName("UC9 - Test mostra tipologie - nessuna disponibile")
    void testMostraTipologieVuoto() {
        int numTipi = gestorePalestra.mostraTipologieAbbonamento();
        assertEquals(0, numTipi);
    }
    
    @Test
    @DisplayName("UC9 - Test mostra tipologie - con tipi disponibili")
    void testMostraTipologieConTipi() {
        gestorePalestra.creaNuovoTipoAbbonamento("MENSILE", "Test", durataMensile, 29.99, true);
        gestorePalestra.creaNuovoTipoAbbonamento("ANNUALE", "Test", durataAnnuale, 199.99, false);
        
        int numTipi = gestorePalestra.mostraTipologieAbbonamento();
        assertEquals(2, numTipi);
    }
    
    @Test
    @DisplayName("UC9 - Test esiste tipo abbonamento")
    void testEsisteTipoAbbonamento() {
        gestorePalestra.creaNuovoTipoAbbonamento("MENSILE", "Test", durataMensile, 29.99, true);
        
        assertTrue(gestorePalestra.esisteTipoAbbonamento("MENSILE"));
        assertFalse(gestorePalestra.esisteTipoAbbonamento("INESISTENTE"));
    }
    
    @Test
    @DisplayName("UC9 - Test getTipoAbbonamento esistente")
    void testGetTipoAbbonamentoEsistente() {
        gestorePalestra.creaNuovoTipoAbbonamento("MENSILE", "Test", durataMensile, 29.99, true);
        
        TipoAbbonamento ta = gestorePalestra.getTipoAbbonamento("MENSILE");
        assertNotNull(ta);
        assertEquals("MENSILE", ta.getNomeabbonamento());
    }
    
    @Test
    @DisplayName("UC9 - Test getTipoAbbonamento inesistente")
    void testGetTipoAbbonamentoInesistente() {
        TipoAbbonamento ta = gestorePalestra.getTipoAbbonamento("INESISTENTE");
        assertNull(ta);
    }
    
    @Test
    @DisplayName("Test getTipoAbbonamentoMap")
    void testGetTipoAbbonamentoMap() {
        assertNotNull(gestorePalestra.getTipoAbbonamentoMap());
        
        gestorePalestra.creaNuovoTipoAbbonamento("MENSILE", "Test", durataMensile, 29.99, true);
        
        assertEquals(1, gestorePalestra.getTipoAbbonamentoMap().size());
    }
}