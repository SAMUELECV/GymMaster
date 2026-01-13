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
public class IscrittoTest {
    
    private Iscritto iscritto;
    private Date dataNascita;
    
    @BeforeEach
    void setUp() {
        Calendar cal = Calendar.getInstance();
        cal.set(1995, 2, 15);
        dataNascita = cal.getTime();
        
        iscritto = new Iscritto("Luca", "Bianchi", dataNascita, 
                                "BNCLCU95C15H501Z", "3331234567", 
                                "luca@email.it", "Via Roma 123");
    }
    
    @Test
    @DisplayName("Test creazione Iscritto")
    void testCreazioneIscritto() {
        assertNotNull(iscritto);
        assertEquals("Luca", iscritto.getNomeiscritto());
        assertEquals("Bianchi", iscritto.getCognome());
        assertEquals("BNCLCU95C15H501Z", iscritto.getCF());
        assertEquals("3331234567", iscritto.getTelefono());
        assertEquals("luca@email.it", iscritto.getEmail());
        assertEquals("Via Roma 123", iscritto.getIndirizzo());
    }
    
    @Test
    @DisplayName("Test codice tessera iniziale a zero")
    void testCodiceTesseraIniziale() {
        assertEquals(0, iscritto.getCodicetessera());
    }
    
    @Test
    @DisplayName("Test set codice tessera")
    void testSetCodiceTessera() {
        iscritto.setCodicetessera(12345);
        assertEquals(12345, iscritto.getCodicetessera());
    }
    
    @Test
    @DisplayName("Test abbonamento corrente iniziale null")
    void testAbbonamentoCorrenteIniziale() {
        assertNull(iscritto.getAbbonamentoCorrente());
    }
    
    @Test
    @DisplayName("Test set abbonamento corrente")
    void testSetAbbonamentoCorrente() {
        Calendar cal = Calendar.getInstance();
        Date dataInizio = cal.getTime();
        cal.add(Calendar.MONTH, 1);
        Date dataFine = cal.getTime();
        
        TipoAbbonamento tipo = new TipoAbbonamento("Mensile", "Test", dataFine, 29.99, true);
        Abbonamento abbonamento = new Abbonamento(1, dataInizio, dataFine, tipo, StatoAbbonamento.ATTIVO);
        
        iscritto.setAbbonamentoCorrente(abbonamento);
        
        assertNotNull(iscritto.getAbbonamentoCorrente());
        assertEquals(1, iscritto.getAbbonamentoCorrente().getIdabbonamento());
    }
    
    @Test
    @DisplayName("Test setters")
    void testSetters() {
        iscritto.setNomeiscritto("Marco");
        iscritto.setCognome("Rossi");
        iscritto.setTelefono("3339999999");
        iscritto.setEmail("marco@email.it");
        iscritto.setIndirizzo("Via Milano 456");
        
        assertEquals("Marco", iscritto.getNomeiscritto());
        assertEquals("Rossi", iscritto.getCognome());
        assertEquals("3339999999", iscritto.getTelefono());
        assertEquals("marco@email.it", iscritto.getEmail());
        assertEquals("Via Milano 456", iscritto.getIndirizzo());
    }
    
    @Test
    @DisplayName("Test data di nascita")
    void testDataNascita() {
        assertEquals(dataNascita, iscritto.getDatadinascita());
        
        Calendar newCal = Calendar.getInstance();
        newCal.set(1990, 0, 1);
        Date nuovaData = newCal.getTime();
        
        iscritto.setDatadinascita(nuovaData);
        assertEquals(nuovaData, iscritto.getDatadinascita());
    }
}