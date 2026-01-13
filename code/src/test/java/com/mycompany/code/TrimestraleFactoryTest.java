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
public class TrimestraleFactoryTest {
    
    private TrimestraleFactory factory;
    private Iscritto iscritto;
    private Date dataInizio;
    
    @BeforeEach
    void setUp() {
        factory = new TrimestraleFactory();
        
        Calendar cal = Calendar.getInstance();
        cal.set(1995, 2, 15);
        Date dataNascita = cal.getTime();
        
        iscritto = new Iscritto("Maria", "Rossi", dataNascita, 
                                "RSSMRA95A41H501W", "3339876543", "maria@email.it", "Via Milano 456");
        dataInizio = new Date();
    }
    
    @Test
    @DisplayName("Test creazione abbonamento trimestrale")
    void testCreaAbbonamentoTrimestrale() {
        Abbonamento a = factory.creaAbbonamento(iscritto, dataInizio);
        
        assertNotNull(a);
        assertEquals(StatoAbbonamento.ATTIVO, a.getStatoabbonamento());
        assertEquals("RSSMRA95A41H501W", a.getCFIscritto());
    }
    
    @Test
    @DisplayName("Test durata abbonamento trimestrale = 90 giorni")
    void testDurata90Giorni() {
        Abbonamento a = factory.creaAbbonamento(iscritto, dataInizio);
        
        long diffMillis = a.getDatafine().getTime() - a.getDatainizio().getTime();
        long diffGiorni = diffMillis / (1000 * 60 * 60 * 24);
        
        // Tolleranza di 1 giorno per il cambio ora legale (CET → CEST)
        assertTrue(diffGiorni >= 89 && diffGiorni <= 90, 
            "L'abbonamento trimestrale deve durare circa 90 giorni, ma era: " + diffGiorni);
    }
    
    @Test
    @DisplayName("Test data inizio corretta")
    void testDataInizioCorretta() {
        Abbonamento a = factory.creaAbbonamento(iscritto, dataInizio);
        assertEquals(dataInizio, a.getDatainizio());
    }
    
    @Test
    @DisplayName("Test data fine dopo data inizio")
    void testDataFineDopoDataInizio() {
        Abbonamento a = factory.creaAbbonamento(iscritto, dataInizio);
        assertTrue(a.getDatafine().after(a.getDatainizio()));
    }
    
    @Test
    @DisplayName("Test ID incrementale per creazioni multiple")
    void testIdIncrementale() {
        Abbonamento a1 = factory.creaAbbonamento(iscritto, dataInizio);
        Abbonamento a2 = factory.creaAbbonamento(iscritto, dataInizio);
        
        assertTrue(a2.getIdabbonamento() > a1.getIdabbonamento(), 
            "L'ID del secondo abbonamento deve essere maggiore del primo");
    }
    
    @Test
    @DisplayName("Test che TrimestraleFactory è un'istanza di AbbonamentoFactory")
    void testEreditarieta() {
        assertTrue(factory instanceof AbbonamentoFactory);
    }
    
    @Test
    @DisplayName("Test durata trimestrale maggiore di mensile")
    void testDurataMaggioreDelMensile() {
        MensileFactory mensileFactory = new MensileFactory();
        
        Abbonamento mensile = mensileFactory.creaAbbonamento(iscritto, dataInizio);
        Abbonamento trimestrale = factory.creaAbbonamento(iscritto, dataInizio);
        
        long durataMensile = mensile.getDatafine().getTime() - mensile.getDatainizio().getTime();
        long durataTrimestrale = trimestrale.getDatafine().getTime() - trimestrale.getDatainizio().getTime();
        
        assertTrue(durataTrimestrale > durataMensile);
    }
}