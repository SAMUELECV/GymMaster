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
public class AbbonamentoTest {
    
    private Abbonamento abbonamento;
    private TipoAbbonamento tipoAbbonamento;
    private Date dataInizio;
    private Date dataFine;
    
    @BeforeEach
    void setUp() {
        Calendar cal = Calendar.getInstance();
        dataInizio = cal.getTime();
        
        cal.add(Calendar.MONTH, 1);
        dataFine = cal.getTime();
        
        Calendar calDurata = Calendar.getInstance();
        calDurata.set(1970, 0, 31);
        Date durata = calDurata.getTime();
        
        tipoAbbonamento = new TipoAbbonamento("Mensile Premium", "Accesso completo", durata, 49.99, true);
        abbonamento = new Abbonamento(1, dataInizio, dataFine, tipoAbbonamento, StatoAbbonamento.ATTIVO);
    }
    
    @Test
    @DisplayName("Test creazione Abbonamento")
    void testCreazioneAbbonamento() {
        assertNotNull(abbonamento);
        assertEquals(1, abbonamento.getIdabbonamento());
        assertEquals(dataInizio, abbonamento.getDatainizio());
        assertEquals(dataFine, abbonamento.getDatafine());
        assertEquals(tipoAbbonamento, abbonamento.getTipo());
        assertEquals(StatoAbbonamento.ATTIVO, abbonamento.getStatoabbonamento());
    }
    
    @Test
    @DisplayName("Test creazione Abbonamento tramite Factory")
    void testCreazioneAbbonamentoTramiteFactory() {
        Calendar cal = Calendar.getInstance();
        cal.set(1995, 2, 15);
        Date dataNascita = cal.getTime();
        
        Iscritto iscritto = new Iscritto("Luca", "Bianchi", dataNascita, 
                                          "BNCLCU95C15H501Z", "3331234567", "luca@email.it", "Via Roma 123");
        
        AbbonamentoFactory factory = new MensileFactory();
        Abbonamento abbFactory = factory.creaAbbonamento(iscritto, new Date());
        
        assertNotNull(abbFactory);
        assertEquals(StatoAbbonamento.ATTIVO, abbFactory.getStatoabbonamento());
        assertNotNull(abbFactory.getDatainizio());
        assertNotNull(abbFactory.getDatafine());
        assertEquals("BNCLCU95C15H501Z", abbFactory.getCFIscritto());
    }
    
    @Test
    @DisplayName("Test controllo scadenza - abbonamento valido")
    void testControlloScadenzaValido() {
        Date oggi = new Date();
        boolean risultato = abbonamento.controlloScadenza(oggi);
        assertTrue(risultato);
        assertEquals(StatoAbbonamento.ATTIVO, abbonamento.getStatoabbonamento());
    }
    
    @Test
    @DisplayName("Test controllo scadenza - abbonamento scaduto")
    void testControlloScadenzaScaduto() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MONTH, 2);
        Date dataFutura = cal.getTime();
        
        boolean risultato = abbonamento.controlloScadenza(dataFutura);
        assertFalse(risultato);
        assertEquals(StatoAbbonamento.SCADUTO, abbonamento.getStatoabbonamento());
    }
    
    @Test
    @DisplayName("Test set stato abbonamento")
    void testSetStatoAbbonamento() {
        abbonamento.setStatoAbbonamento(StatoAbbonamento.SOSPESO);
        assertEquals(StatoAbbonamento.SOSPESO, abbonamento.getStatoabbonamento());
        
        abbonamento.setStatoAbbonamento(StatoAbbonamento.SCADUTO);
        assertEquals(StatoAbbonamento.SCADUTO, abbonamento.getStatoabbonamento());
    }
    
    @Test
    @DisplayName("Test CF iscritto")
    void testCFIscritto() {
        assertNull(abbonamento.getCFIscritto());
        
        abbonamento.setCFIscritto("BNCLCU95C15H501Z");
        assertEquals("BNCLCU95C15H501Z", abbonamento.getCFIscritto());
    }
    
    @Test
    @DisplayName("Test setTipo abbonamento")
    void testSetTipo() {
        Calendar calDurata = Calendar.getInstance();
        calDurata.set(1970, 0, 90);
        Date durata = calDurata.getTime();
        
        TipoAbbonamento nuovoTipo = new TipoAbbonamento("Trimestrale", "3 mesi", durata, 79.99, true);
        abbonamento.setTipo(nuovoTipo);
        
        assertEquals(nuovoTipo, abbonamento.getTipo());
        assertEquals("Trimestrale", abbonamento.getTipo().getNomeabbonamento());
    }
    
    @Test
    @DisplayName("Test setters vari")
    void testSetters() {
        abbonamento.setIdabbonamento(99);
        assertEquals(99, abbonamento.getIdabbonamento());
        
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MONTH, 6);
        Date nuovaDataFine = cal.getTime();
        abbonamento.setDatafine(nuovaDataFine);
        assertEquals(nuovaDataFine, abbonamento.getDatafine());
        
        Date nuovaDataInizio = new Date();
        abbonamento.setDatainizio(nuovaDataInizio);
        assertEquals(nuovaDataInizio, abbonamento.getDatainizio());
    }
}