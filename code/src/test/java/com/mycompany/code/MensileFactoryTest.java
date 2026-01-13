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
public class MensileFactoryTest {
    
    private MensileFactory factory;
    private Iscritto iscritto;
    private Date dataInizio;
    
    @BeforeEach
    void setUp() {
        factory = new MensileFactory();
        
        Calendar cal = Calendar.getInstance();
        cal.set(1995, 2, 15);
        Date dataNascita = cal.getTime();
        
        iscritto = new Iscritto("Luca", "Bianchi", dataNascita, 
                                "BNCLCU95C15H501Z", "3331234567", "luca@email.it", "Via Roma 123");
        dataInizio = new Date();
    }
    
    @Test
    @DisplayName("Test creazione abbonamento mensile")
    void testCreaAbbonamentoMensile() {
        Abbonamento a = factory.creaAbbonamento(iscritto, dataInizio);
        
        assertNotNull(a);
        assertEquals(StatoAbbonamento.ATTIVO, a.getStatoabbonamento());
        assertEquals("BNCLCU95C15H501Z", a.getCFIscritto());
    }
    
    @Test
    @DisplayName("Test durata abbonamento mensile = 30 giorni")
    void testDurata30Giorni() {
        Abbonamento a = factory.creaAbbonamento(iscritto, dataInizio);
        
        long diffMillis = a.getDatafine().getTime() - a.getDatainizio().getTime();
        long diffGiorni = diffMillis / (1000 * 60 * 60 * 24);
        
        assertEquals(30, diffGiorni, "L'abbonamento mensile deve durare 30 giorni");
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
    @DisplayName("Test che MensileFactory è un'istanza di AbbonamentoFactory")
    void testEreditarieta() {
        assertTrue(factory instanceof AbbonamentoFactory);
    }
}