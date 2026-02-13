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
public class LezioneTest {
    
    private Lezione lezione;
    private CalendarioLezioni calendario;
    
    @BeforeEach
    void setUp() {
        lezione = new Lezione("Lunedi", 10.0, 15, 20, "SalaUnica", 1.5,
                              StatoLezione.NON_INIZIATA, new Date(), "Marco Zen", "YOG001");
        calendario = new CalendarioLezioni();
    }
    
    @Test
    @DisplayName("Test creazione Lezione")
    void testCreazioneLezione() {
        assertNotNull(lezione);
        assertEquals("Lunedi", lezione.getGiorno());
        assertEquals(10.0, lezione.getOrainizio());
        assertEquals(15, lezione.getPostidisponibili());
        assertEquals(20, lezione.getMaxposti());
        assertEquals("SalaUnica", lezione.getSala());
        assertEquals(1.5, lezione.getDurata());
        assertEquals(StatoLezione.NON_INIZIATA, lezione.getStatolezione());
        assertEquals("Marco Zen", lezione.getIstruttore());
        assertEquals("YOG001", lezione.getIDLezione());
    }
    
    @Test
    @DisplayName("Test decrementaPostiDisponibili")
    void testDecrementaPostiDisponibili() {
        assertEquals(15, lezione.getPostidisponibili());
        lezione.decrementaPostiDisponibili();
        assertEquals(14, lezione.getPostidisponibili());
    }
    
    @Test
    @DisplayName("Test decrementaPostiDisponibili - nessun posto disponibile")
    void testDecrementaPostiDisponibiliZero() {
        Lezione lezionePiena = new Lezione("Lunedi", 10.0, 0, 20, "SalaUnica", 1.5,
                                            StatoLezione.NON_INIZIATA, new Date(), "Marco Zen", "YOG002");
        lezionePiena.decrementaPostiDisponibili();
        assertEquals(0, lezionePiena.getPostidisponibili());
    }
    
    @Test
    @DisplayName("Test incrementaPostiDisponibili")
    void testIncrementaPostiDisponibili() {
        assertEquals(15, lezione.getPostidisponibili());
        lezione.incrementaPostiDisponibili(lezione);
        assertEquals(16, lezione.getPostidisponibili());
    }
    
    @Test
    @DisplayName("Test setStatoLezione")
    void testSetStatoLezione() {
        lezione.setStatoLezione(StatoLezione.IN_CORSO);
        assertEquals(StatoLezione.IN_CORSO, lezione.getStatolezione());
        
        lezione.setStatoLezione(StatoLezione.TERMINATA);
        assertEquals(StatoLezione.TERMINATA, lezione.getStatolezione());
        
        lezione.setStatoLezione(StatoLezione.ANNULLATA);
        assertEquals(StatoLezione.ANNULLATA, lezione.getStatolezione());
    }
    
    @Test
    @DisplayName("Test getByGiorno")
    void testGetByGiorno() {
        Lezione trovata = lezione.getByGiorno("Lunedi");
        assertNotNull(trovata);
        assertEquals(lezione, trovata);
        
        Lezione nonTrovata = lezione.getByGiorno("Martedi");
        assertNull(nonTrovata);
    }
    
    @Test
    @DisplayName("Test getByIstruttore")
    void testGetByIstruttore() {
        Lezione trovata = lezione.getByIstruttore("Marco Zen");
        assertNotNull(trovata);
        
        Lezione nonTrovata = lezione.getByIstruttore("Altro Istruttore");
        assertNull(nonTrovata);
    }
    
    @Test
    @DisplayName("Test getLezioneSpecifica")
    void testGetLezioneSpecifica() {
        Lezione trovata = lezione.getLezioneSpecifica("YOG001");
        assertNotNull(trovata);
        
        Lezione nonTrovata = lezione.getLezioneSpecifica("INESISTENTE");
        assertNull(nonTrovata);
    }
    
    @Test
    @DisplayName("Test getLezione")
    void testGetLezione() {
        assertEquals(lezione, lezione.getLezione());
    }
    
    @Test
    @DisplayName("Observer - Lezione estende Subject")
    void testLezioneEstendeSubject() {
        assertTrue(lezione instanceof Subject);
    }
    
    @Test
    @DisplayName("Observer - attach CalendarioLezioni alla Lezione")
    void testAttachCalendario() {
        lezione.attach(calendario);
        
        assertEquals(1, lezione.getObservers().size());
        assertTrue(lezione.getObservers().contains(calendario));
    }
    
    @Test
    @DisplayName("Observer - detach CalendarioLezioni dalla Lezione")
    void testDetachCalendario() {
        lezione.attach(calendario);
        assertEquals(1, lezione.getObservers().size());
        
        lezione.detach(calendario);
        assertEquals(0, lezione.getObservers().size());
    }
    
    @Test
    @DisplayName("Observer - decrementaPostiDisponibili notifica CalendarioLezioni")
    void testDecrementaNotificaObserver() {
        lezione.attach(calendario);
        
        assertEquals(0, calendario.getObserverState());
        
        lezione.decrementaPostiDisponibili();
        
        assertEquals(1, calendario.getObserverState());
        assertEquals(14, lezione.getPostidisponibili());
    }
    
    @Test
    @DisplayName("Observer - incrementaPostiDisponibili notifica CalendarioLezioni")
    void testIncrementaNotificaObserver() {
        lezione.attach(calendario);
        
        assertEquals(0, calendario.getObserverState());
        
        lezione.incrementaPostiDisponibili(lezione);
        
        assertEquals(1, calendario.getObserverState());
        assertEquals(16, lezione.getPostidisponibili());
    }
    
    @Test
    @DisplayName("Observer - notifiche multiple incrementano observerState")
    void testNotificheMultiple() {
        lezione.attach(calendario);
        
        lezione.decrementaPostiDisponibili(); 
        lezione.decrementaPostiDisponibili(); 
        lezione.decrementaPostiDisponibili(); 
        
        assertEquals(3, calendario.getObserverState());
        assertEquals(12, lezione.getPostidisponibili());
    }
    
    @Test
    @DisplayName("Observer - più observer ricevono la stessa notifica")
    void testPiuObserverNotificati() {
        CalendarioLezioni calendario2 = new CalendarioLezioni();
        
        lezione.attach(calendario);
        lezione.attach(calendario2);
        
        lezione.decrementaPostiDisponibili();
        
        assertEquals(1, calendario.getObserverState());
        assertEquals(1, calendario2.getObserverState());
    }
    
    @Test
    @DisplayName("Observer - nessuna notifica senza observer registrati")
    void testNessunaNotificaSenzaObserver() {
        assertDoesNotThrow(() -> lezione.decrementaPostiDisponibili());
        assertEquals(14, lezione.getPostidisponibili());
    }
    
    @Test
    @DisplayName("Observer - nessuna notifica se posti a zero")
    void testNessunaNotificaPostiZero() {
        Lezione lezionePiena = new Lezione("Lunedi", 10.0, 0, 20, "SalaUnica", 1.5,
                                            StatoLezione.NON_INIZIATA, new Date(), "Marco Zen", "YOG002");
        lezionePiena.attach(calendario);
        
        lezionePiena.decrementaPostiDisponibili();
        
        assertEquals(0, calendario.getObserverState());
        assertEquals(0, lezionePiena.getPostidisponibili());
    }
    
    @Test
    @DisplayName("Observer - observer rimosso non riceve notifiche")
    void testObserverRimossoNonNotificato() {
        lezione.attach(calendario);
        lezione.decrementaPostiDisponibili();
        assertEquals(1, calendario.getObserverState());
        
        lezione.detach(calendario);
        lezione.decrementaPostiDisponibili();
        assertEquals(1, calendario.getObserverState());
    }
    
    @Test
    @DisplayName("Observer - lista observers inizialmente vuota")
    void testObserversInizialmenteVuota() {
        assertNotNull(lezione.getObservers());
        assertTrue(lezione.getObservers().isEmpty());
    }
}