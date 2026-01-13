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
public class AbbonamentoFactoryTest {
    
    private Iscritto iscritto;
    private Date dataInizio;
    
    @BeforeEach
    void setUp() {
        Calendar cal = Calendar.getInstance();
        cal.set(1995, 2, 15);
        Date dataNascita = cal.getTime();
        
        iscritto = new Iscritto("Luca", "Bianchi", dataNascita, 
                                "BNCLCU95C15H501Z", "3331234567", "luca@email.it", "Via Roma 123");
        dataInizio = new Date();
    }
    
    @Test
    @DisplayName("Test che AbbonamentoFactory è astratta e non istanziabile direttamente")
    void testFactoryAstratta() {
        AbbonamentoFactory mensile = new MensileFactory();
        AbbonamentoFactory trimestrale = new TrimestraleFactory();
        AbbonamentoFactory annuale = new AnnualeFactory();
        
        assertTrue(mensile instanceof AbbonamentoFactory);
        assertTrue(trimestrale instanceof AbbonamentoFactory);
        assertTrue(annuale instanceof AbbonamentoFactory);
    }
    
    @Test
    @DisplayName("Test polimorfismo - tutte le factory creano abbonamenti validi")
    void testPolimorfismo() {
        AbbonamentoFactory[] factories = {
            new MensileFactory(),
            new TrimestraleFactory(),
            new AnnualeFactory()
        };
        
        for (AbbonamentoFactory factory : factories) {
            Abbonamento a = factory.creaAbbonamento(iscritto, dataInizio);
            
            assertNotNull(a, "La factory " + factory.getClass().getSimpleName() + " ha restituito null");
            assertEquals(StatoAbbonamento.ATTIVO, a.getStatoabbonamento());
            assertEquals("BNCLCU95C15H501Z", a.getCFIscritto());
            assertNotNull(a.getDatainizio());
            assertNotNull(a.getDatafine());
            assertTrue(a.getDatafine().after(a.getDatainizio()), 
                "La data fine deve essere dopo la data inizio per " + factory.getClass().getSimpleName());
        }
    }
    
    @Test
    @DisplayName("Test che ogni factory produce durate diverse")
    void testDurateDiverse() {
        AbbonamentoFactory mensile = new MensileFactory();
        AbbonamentoFactory trimestrale = new TrimestraleFactory();
        AbbonamentoFactory annuale = new AnnualeFactory();
        
        Abbonamento am = mensile.creaAbbonamento(iscritto, dataInizio);
        Abbonamento at = trimestrale.creaAbbonamento(iscritto, dataInizio);
        Abbonamento aa = annuale.creaAbbonamento(iscritto, dataInizio);
        
        long durataMensile = am.getDatafine().getTime() - am.getDatainizio().getTime();
        long durataTrimestrale = at.getDatafine().getTime() - at.getDatainizio().getTime();
        long durataAnnuale = aa.getDatafine().getTime() - aa.getDatainizio().getTime();
        
        assertTrue(durataTrimestrale > durataMensile, 
            "La durata trimestrale deve essere maggiore di quella mensile");
        
        assertTrue(durataAnnuale > durataTrimestrale, 
            "La durata annuale deve essere maggiore di quella trimestrale");
    }
    
    @Test
    @DisplayName("Test che il CF dell'iscritto viene correttamente associato")
    void testAssociazioneCFIscritto() {
        AbbonamentoFactory factory = new MensileFactory();
        Abbonamento a = factory.creaAbbonamento(iscritto, dataInizio);
        
        assertEquals(iscritto.getCF(), a.getCFIscritto());
    }
}