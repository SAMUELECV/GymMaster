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
public class AnnualeFactoryTest {
    
    private AnnualeFactory factory;
    private Iscritto iscritto;
    private Date dataInizio;
    
    @BeforeEach
    void setUp() {
        factory = new AnnualeFactory();
        
        Calendar cal = Calendar.getInstance();
        cal.set(1990, 6, 20);
        Date dataNascita = cal.getTime();
        
        iscritto = new Iscritto("Paolo", "Verdi", dataNascita, 
                                "VRDPLA90L20H501X", "3337654321", "paolo@email.it", "Via Napoli 789");
        dataInizio = new Date();
    }
    
    @Test
    @DisplayName("Test creazione abbonamento annuale")
    void testCreaAbbonamentoAnnuale() {
        Abbonamento a = factory.creaAbbonamento(iscritto, dataInizio);
        
        assertNotNull(a);
        assertEquals(StatoAbbonamento.ATTIVO, a.getStatoabbonamento());
        assertEquals("VRDPLA90L20H501X", a.getCFIscritto());
    }
    
    @Test
    @DisplayName("Test durata abbonamento annuale = 365 giorni")
    void testDurata365Giorni() {
        Abbonamento a = factory.creaAbbonamento(iscritto, dataInizio);
        
        long diffMillis = a.getDatafine().getTime() - a.getDatainizio().getTime();
        long diffGiorni = diffMillis / (1000 * 60 * 60 * 24);
        
        assertEquals(365, diffGiorni, "L'abbonamento annuale deve durare 365 giorni");
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
    @DisplayName("Test che AnnualeFactory è un'istanza di AbbonamentoFactory")
    void testEreditarieta() {
        assertTrue(factory instanceof AbbonamentoFactory);
    }
    
    @Test
    @DisplayName("Test durata annuale maggiore di trimestrale")
    void testDurataMaggioreDiTrimestrale() {
        TrimestraleFactory trimestraleFactory = new TrimestraleFactory();
        
        Abbonamento trimestrale = trimestraleFactory.creaAbbonamento(iscritto, dataInizio);
        Abbonamento annuale = factory.creaAbbonamento(iscritto, dataInizio);
        
        long durataTrimestrale = trimestrale.getDatafine().getTime() - trimestrale.getDatainizio().getTime();
        long durataAnnuale = annuale.getDatafine().getTime() - annuale.getDatainizio().getTime();
        
        assertTrue(durataAnnuale > durataTrimestrale);
    }
    
    @Test
    @DisplayName("Test abbonamento annuale non è scaduto appena creato")
    void testNonScadutoAppenaCrato() {
        Abbonamento a = factory.creaAbbonamento(iscritto, dataInizio);
        
        boolean valido = a.controlloScadenza(new Date());
        assertTrue(valido, "Un abbonamento annuale appena creato non deve essere scaduto");
        assertEquals(StatoAbbonamento.ATTIVO, a.getStatoabbonamento());
    }
    
    @Test
    @DisplayName("Test abbonamento annuale scaduto dopo la data fine")
    void testScadutoDopDataFine() {
        Abbonamento a = factory.creaAbbonamento(iscritto, dataInizio);
        
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_MONTH, 400); // 400 giorni > 365
        Date dataFutura = cal.getTime();
        
        boolean valido = a.controlloScadenza(dataFutura);
        assertFalse(valido, "Un abbonamento annuale deve risultare scaduto dopo 400 giorni");
        assertEquals(StatoAbbonamento.SCADUTO, a.getStatoabbonamento());
    }
}