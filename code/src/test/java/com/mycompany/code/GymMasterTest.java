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
public class GymMasterTest {
    
    private GymMaster gymMaster;
    private Date durataMensile;
    private Date durataTrimestrle;
    private Date durataAnnuale;
    private String CF1 = "BNCLCU95C15H501Z";
    
    @BeforeEach
    void setUp() {
        gymMaster = new GymMaster();
        
        GestorePalestra gestore = new GestorePalestra("Giuseppe", "Verdi");
        gymMaster.setGestorePalestra(gestore);
        
        Sala sala = new Sala(1);
        gymMaster.setSala(sala);
        
        Calendar cal = Calendar.getInstance();
        cal.set(1970, 0, 31);
        durataMensile = cal.getTime();
        
        cal.set(1970, 0, 90);
        durataTrimestrle = cal.getTime();
        
        cal.set(1970, 0, 365);
        durataAnnuale = cal.getTime();
    }
    
    private Iscritto creaIscrittoStandard() {
        Calendar cal = Calendar.getInstance();
        cal.set(1995, 2, 15);
        Date dataNascita = cal.getTime();
        
        return gymMaster.registraNuovoIscritto("Luca", "Bianchi", dataNascita, 
                                                CF1, "3331234567", "luca@email.it", "Via Roma 123");
    }
    
    @Test
    @DisplayName("UC1 - Test registrazione nuovo iscritto")
    void testRegistraNuovoIscritto() {
        Iscritto iscritto = creaIscrittoStandard();
        
        assertNotNull(iscritto);
        assertEquals("Luca", iscritto.getNomeiscritto());
        assertEquals(1, gymMaster.getIscritti().size());
    }
    
    @Test
    @DisplayName("UC1 - Test registrazione iscritto duplicato")
    void testRegistraIscrittoDuplicato() {
        creaIscrittoStandard();
        
        Calendar cal = Calendar.getInstance();
        cal.set(1995, 2, 15);
        Date dataNascita = cal.getTime();
        
        gymMaster.registraNuovoIscritto("Luca", "Bianchi", dataNascita, 
                                        CF1, "3331234567", "luca@email.it", "Via Roma 123");
        
        assertEquals(1, gymMaster.getIscritti().size());
    }
    
    @Test
    @DisplayName("UC1 - Test genera codice tessera")
    void testGeneraCodiceTessera() {
        int codice = gymMaster.generaCodiceTessera();
        assertTrue(codice >= 10000 && codice < 100000);
    }
    
    @Test
    @DisplayName("UC2 - Test attiva abbonamento iscritto esistente")
    void testAttivaAbbonamentoIscrittoEsistente() {
        creaIscrittoStandard();
        
        Iscritto iscritto = gymMaster.attivaAbbonamento(CF1);
        
        assertNotNull(iscritto);
        assertEquals("Luca", iscritto.getNomeiscritto());
    }
    
    @Test
    @DisplayName("UC2 - Test attiva abbonamento iscritto inesistente")
    void testAttivaAbbonamentoIscrittoInesistente() {
        Iscritto iscritto = gymMaster.attivaAbbonamento("CFINESISTENTE");
        assertNull(iscritto);
    }
    
    @Test
    @DisplayName("UC2 - Test conferma attivazione con MensileFactory")
    void testConfermaAttivazioneMensile() {
        creaIscrittoStandard();
        gymMaster.creaNuovoTipoAbbonamento("MENSILE", "Abbonamento mensile", durataMensile, 29.99, true);
        gymMaster.attivaAbbonamento(CF1);
        gymMaster.selezionaTipologieAbbonamento("MENSILE");
        
        assertNotNull(gymMaster.getAbbonamentoFactory());
        assertTrue(gymMaster.getAbbonamentoFactory() instanceof MensileFactory);
        
        Abbonamento abbonamento = gymMaster.confermaAttivazione(CF1);
        
        assertNotNull(abbonamento);
        assertEquals(StatoAbbonamento.ATTIVO, abbonamento.getStatoabbonamento());
        assertEquals(1, gymMaster.getAbbonamenti().size());
        
        long diffMillis = abbonamento.getDatafine().getTime() - abbonamento.getDatainizio().getTime();
        long diffGiorni = diffMillis / (1000 * 60 * 60 * 24);
        assertTrue(diffGiorni >= 29 && diffGiorni <= 30);
    }
    
    @Test
    @DisplayName("UC2 - Test conferma attivazione con TrimestraleFactory")
    void testConfermaAttivazioneTrimestrle() {
        creaIscrittoStandard();
        gymMaster.creaNuovoTipoAbbonamento("TRIMESTRALE", "Abbonamento trimestrale", durataTrimestrle, 69.99, true);
        gymMaster.attivaAbbonamento(CF1);
        gymMaster.selezionaTipologieAbbonamento("TRIMESTRALE");
        
        assertNotNull(gymMaster.getAbbonamentoFactory());
        assertTrue(gymMaster.getAbbonamentoFactory() instanceof TrimestraleFactory);
        
        Abbonamento abbonamento = gymMaster.confermaAttivazione(CF1);
        
        assertNotNull(abbonamento);
        assertEquals(StatoAbbonamento.ATTIVO, abbonamento.getStatoabbonamento());
        
        long diffMillis = abbonamento.getDatafine().getTime() - abbonamento.getDatainizio().getTime();
        long diffGiorni = diffMillis / (1000 * 60 * 60 * 24);
        assertTrue(diffGiorni >= 89 && diffGiorni <= 90);
    }
    
    @Test
    @DisplayName("UC2 - Test conferma attivazione con AnnualeFactory")
    void testConfermaAttivazioneAnnuale() {
        creaIscrittoStandard();
        gymMaster.creaNuovoTipoAbbonamento("ANNUALE", "Abbonamento annuale", durataAnnuale, 199.99, true);
        gymMaster.attivaAbbonamento(CF1);
        gymMaster.selezionaTipologieAbbonamento("ANNUALE");
        
        assertNotNull(gymMaster.getAbbonamentoFactory());
        assertTrue(gymMaster.getAbbonamentoFactory() instanceof AnnualeFactory);
        
        Abbonamento abbonamento = gymMaster.confermaAttivazione(CF1);
        
        assertNotNull(abbonamento);
        assertEquals(StatoAbbonamento.ATTIVO, abbonamento.getStatoabbonamento());
        
        long diffMillis = abbonamento.getDatafine().getTime() - abbonamento.getDatainizio().getTime();
        long diffGiorni = diffMillis / (1000 * 60 * 60 * 24);
        assertTrue(diffGiorni >= 364 && diffGiorni <= 365);
    }
    
    @Test
    @DisplayName("UC2 - Test conferma attivazione senza factory (tipologia non riconosciuta)")
    void testConfermaAttivazioneSenzaFactory() {
        creaIscrittoStandard();
        gymMaster.creaNuovoTipoAbbonamento("Premium", "Completo", durataMensile, 49.99, true);
        gymMaster.attivaAbbonamento(CF1);
        gymMaster.selezionaTipologieAbbonamento("Premium");
        
        assertNull(gymMaster.getAbbonamentoFactory());
        
        Abbonamento abbonamento = gymMaster.confermaAttivazione(CF1);
        assertNull(abbonamento);
    }
    
    @Test
    @DisplayName("UC2 - Test conferma attivazione senza tipologia selezionata")
    void testConfermaAttivazioneSenzaTipologia() {
        creaIscrittoStandard();
        gymMaster.attivaAbbonamento(CF1);
        
        Abbonamento abbonamento = gymMaster.confermaAttivazione(CF1);
        assertNull(abbonamento);
    }
    
    @Test
    @DisplayName("UC2 - Test conferma attivazione senza iscritto")
    void testConfermaAttivazioneSenzaIscritto() {
        gymMaster.creaNuovoTipoAbbonamento("MENSILE", "Test", durataMensile, 29.99, true);
        gymMaster.selezionaTipologieAbbonamento("MENSILE");
        
        Abbonamento abbonamento = gymMaster.confermaAttivazione(CF1);
        assertNull(abbonamento);
    }
    
    @Test
    @DisplayName("UC2 - Test abbonamento gia' attivo")
    void testAbbonamentoGiaAttivo() {
        creaIscrittoStandard();
        gymMaster.creaNuovoTipoAbbonamento("MENSILE", "Test", durataMensile, 29.99, true);
        gymMaster.attivaAbbonamento(CF1);
        gymMaster.selezionaTipologieAbbonamento("MENSILE");
        gymMaster.confermaAttivazione(CF1);
        
        Iscritto iscritto = gymMaster.attivaAbbonamento(CF1);
        assertNull(iscritto);
    }
    
    @Test
    @DisplayName("UC2 - Test getter/setter AbbonamentoFactory")
    void testGetSetAbbonamentoFactory() {
        assertNull(gymMaster.getAbbonamentoFactory());
        
        AbbonamentoFactory factory = new MensileFactory();
        gymMaster.setAbbonamentoFactory(factory);
        
        assertNotNull(gymMaster.getAbbonamentoFactory());
        assertTrue(gymMaster.getAbbonamentoFactory() instanceof MensileFactory);
    }
    
    @Test
    @DisplayName("UC2 - Test salvaAbbonamento")
    void testSalvaAbbonamento() {
        Abbonamento a = new Abbonamento(99, new Date(), new Date(), null, StatoAbbonamento.ATTIVO);
        gymMaster.salvaAbbonamento(a);
        
        assertEquals(1, gymMaster.getAbbonamenti().size());
    }
    
    @Test
    @DisplayName("UC9 - Test crea nuovo tipo abbonamento tramite GymMaster")
    void testCreaNuovoTipoAbbonamento() {
        TipoAbbonamento tipo = gymMaster.creaNuovoTipoAbbonamento("MENSILE", "Completo", durataMensile, 49.99, true);
        
        assertNotNull(tipo);
        assertEquals("MENSILE", tipo.getNomeabbonamento());
    }
    
    @Test
    @DisplayName("UC9 - Test modifica tipo abbonamento tramite GymMaster con aggiornaDati")
    void testModificaTipoAbbonamento() {
        gymMaster.creaNuovoTipoAbbonamento("MENSILE", "Base", durataMensile, 29.99, false);
        
        Calendar cal = Calendar.getInstance();
        cal.set(1970, 0, 90);
        Date nuovaDurata = cal.getTime();
        
        gymMaster.modificaTipoAbbonamento("MENSILE", "Completo", nuovaDurata, 49.99, true);
        
        TipoAbbonamento ta = gymMaster.getGestorePalestra().getTipoAbbonamento("MENSILE");
        assertNotNull(ta);
        assertEquals("Completo", ta.getDescrizione());
        assertEquals(49.99, ta.getPrezzo());
        assertTrue(ta.isLezione());
    }
    
    @Test
    @DisplayName("UC9 - Test modifica tipo abbonamento inesistente")
    void testModificaTipoAbbonamentoInesistente() {
        assertDoesNotThrow(() -> gymMaster.modificaTipoAbbonamento("INESISTENTE", "Desc", durataMensile, 10.0, false));
    }
    
    @Test
    @DisplayName("UC9 - Test esiste tipo abbonamento")
    void testEsisteTipoAbbonamento() {
        gymMaster.creaNuovoTipoAbbonamento("MENSILE", "Completo", durataMensile, 49.99, true);
        
        assertTrue(gymMaster.esisteTipoAbbonamento("MENSILE"));
        assertFalse(gymMaster.esisteTipoAbbonamento("Inesistente"));
    }
    
    @Test
    @DisplayName("UC9 - Test mostra tipologie abbonamento")
    void testMostraTipologieAbbonamento() {
        assertEquals(0, gymMaster.mostraTipologieAbbonamento());
        
        gymMaster.creaNuovoTipoAbbonamento("MENSILE", "Test", durataMensile, 29.99, true);
        assertEquals(1, gymMaster.mostraTipologieAbbonamento());
    }
    
    @Test
    @DisplayName("UC9 - Test seleziona tipo abbonamento MENSILE con factory")
    void testSelezionaTipoAbbonamentoMensileConFactory() {
        gymMaster.creaNuovoTipoAbbonamento("MENSILE", "Completo", durataMensile, 49.99, true);
        
        TipoAbbonamento tipo = gymMaster.selezionaTipologieAbbonamento("MENSILE");
        
        assertNotNull(tipo);
        assertEquals("MENSILE", tipo.getNomeabbonamento());
        assertNotNull(gymMaster.getAbbonamentoFactory());
        assertTrue(gymMaster.getAbbonamentoFactory() instanceof MensileFactory);
    }
    
    @Test
    @DisplayName("UC9 - Test seleziona tipo abbonamento TRIMESTRALE con factory")
    void testSelezionaTipoAbbonamentoTrimestraleConFactory() {
        gymMaster.creaNuovoTipoAbbonamento("TRIMESTRALE", "3 mesi", durataTrimestrle, 69.99, true);
        
        TipoAbbonamento tipo = gymMaster.selezionaTipologieAbbonamento("TRIMESTRALE");
        
        assertNotNull(tipo);
        assertTrue(gymMaster.getAbbonamentoFactory() instanceof TrimestraleFactory);
    }
    
    @Test
    @DisplayName("UC9 - Test seleziona tipo abbonamento ANNUALE con factory")
    void testSelezionaTipoAbbonamentoAnnualeConFactory() {
        gymMaster.creaNuovoTipoAbbonamento("ANNUALE", "1 anno", durataAnnuale, 199.99, true);
        
        TipoAbbonamento tipo = gymMaster.selezionaTipologieAbbonamento("ANNUALE");
        
        assertNotNull(tipo);
        assertTrue(gymMaster.getAbbonamentoFactory() instanceof AnnualeFactory);
    }
    
    @Test
    @DisplayName("UC9 - Test seleziona tipo abbonamento inesistente")
    void testSelezionaTipoAbbonamentoInesistente() {
        TipoAbbonamento tipo = gymMaster.selezionaTipologieAbbonamento("Inesistente");
        assertNull(tipo);
    }
    
    @Test
    @DisplayName("UC7 - Test inserisci nuovo corso")
    void testInserisciNuovoCorso() {
        Corso corso = gymMaster.inserisciNuovoCorso("Yoga", "Corso yoga", "Marco Zen");
        
        assertNotNull(corso);
        assertEquals("Yoga", corso.getNomecorso());
        assertEquals(1, gymMaster.getCorsi().size());
    }
    
    @Test
    @DisplayName("UC7 - Test inserimento lezioni")
    void testInserimentoLezioni() {
        gymMaster.inserisciNuovoCorso("Yoga", "Corso yoga", "Marco Zen");
        
        Lezione lezione = gymMaster.inserimentoLezioni("Lunedi", 10.0, 15, 20, 1.5, 
                                                       StatoLezione.NON_INIZIATA, new Date(), "Marco Zen", "YOG001");
        
        assertNotNull(lezione);
        assertEquals("YOG001", lezione.getIDLezione());
        assertEquals(StatoLezione.NON_INIZIATA, lezione.getStatolezione());
        assertEquals(1, gymMaster.getLezioni().size());
    }
    
    @Test
    @DisplayName("UC7 - Test inserimento lezione registra CalendarioLezioni come Observer")
    void testInserimentoLezioneRegistraObserver() {
        gymMaster.inserisciNuovoCorso("Yoga", "Corso yoga", "Marco Zen");
        
        Lezione lezione = gymMaster.inserimentoLezioni("Lunedi", 10.0, 15, 20, 1.5, 
                                                       StatoLezione.NON_INIZIATA, new Date(), "Marco Zen", "YOG001");
        
        assertNotNull(lezione);
        assertEquals(1, lezione.getObservers().size());
        assertTrue(lezione.getObservers().contains(gymMaster.getCalendarioLezioni()));
    }
    
    @Test
    @DisplayName("UC8 - Test seleziona lezione specifica")
    void testSelezionaLezioneSpecifica() {
        gymMaster.inserisciNuovoCorso("Yoga", "Corso yoga", "Marco Zen");
        gymMaster.inserimentoLezioni("Lunedi", 10.0, 15, 20, 1.5, 
                                    StatoLezione.NON_INIZIATA, new Date(), "Marco Zen", "YOG001");
        
        Lezione lezione = gymMaster.selezionaLezioneSpecifica("YOG001");
        
        assertNotNull(lezione);
        assertEquals("YOG001", lezione.getIDLezione());
    }
    
    @Test
    @DisplayName("UC8 - Test seleziona lezione inesistente")
    void testSelezionaLezioneInesistente() {
        Lezione lezione = gymMaster.selezionaLezioneSpecifica("INESISTENTE");
        assertNull(lezione);
    }
    
    @Test
    @DisplayName("UC8 - Test get/set opzione")
    void testGetSetOpzione() {
        assertEquals("", gymMaster.getOpzione());
        
        gymMaster.setOpzione("Lunedi");
        assertEquals("Lunedi", gymMaster.getOpzione());
    }
    
    @Test
    @DisplayName("UC3 - Test prenotazione completa con Observer")
    void testPrenotazioneCompletaConObserver() {
        creaIscrittoStandard();
        gymMaster.creaNuovoTipoAbbonamento("MENSILE", "Completo", durataMensile, 49.99, true);
        gymMaster.attivaAbbonamento(CF1);
        gymMaster.selezionaTipologieAbbonamento("MENSILE");
        gymMaster.confermaAttivazione(CF1);
        
        gymMaster.inserisciNuovoCorso("Yoga", "Corso yoga", "Marco Zen");
        Date dataLezione = new Date();
        Lezione lezione = gymMaster.inserimentoLezioni("Lunedi", 10.0, 15, 20, 1.5, 
                                    StatoLezione.NON_INIZIATA, dataLezione, "Marco Zen", "YOG001");
        
        CalendarioLezioni cal = gymMaster.getCalendarioLezioni();
        assertEquals(0, cal.getObserverState());
        
        gymMaster.visualizzaLezioniECorsi(CF1);
        gymMaster.selezionaCorso("Yoga");
        Prenotazione prenotazione = gymMaster.prenotaLezione(dataLezione, 10.0);
        
        assertNotNull(prenotazione);
        assertEquals(StatoPrenotazione.EFFETTUATA, prenotazione.getStatoprenotazione());
        assertEquals(1, gymMaster.getPrenotazioni().size());
        assertEquals(1, cal.getObserverState());
        assertEquals(14, lezione.getPostidisponibili());
    }
    
    @Test
    @DisplayName("UC3 - Test prenotazioni multiple notificano Observer")
    void testPrenotazioniMultipleNotificanoObserver() {
        creaIscrittoStandard();
        gymMaster.creaNuovoTipoAbbonamento("MENSILE", "Completo", durataMensile, 49.99, true);
        gymMaster.attivaAbbonamento(CF1);
        gymMaster.selezionaTipologieAbbonamento("MENSILE");
        gymMaster.confermaAttivazione(CF1);
        
        gymMaster.inserisciNuovoCorso("Yoga", "Corso yoga", "Marco Zen");
        Date dataLezione = new Date();
        gymMaster.inserimentoLezioni("Lunedi", 10.0, 15, 20, 1.5, 
                                    StatoLezione.NON_INIZIATA, dataLezione, "Marco Zen", "YOG001");
        
        CalendarioLezioni cal = gymMaster.getCalendarioLezioni();
        
        gymMaster.visualizzaLezioniECorsi(CF1);
        gymMaster.selezionaCorso("Yoga");
        gymMaster.prenotaLezione(dataLezione, 10.0);
        assertEquals(1, cal.getObserverState());
        
        gymMaster.visualizzaLezioniECorsi(CF1);
        gymMaster.selezionaCorso("Yoga");
        gymMaster.prenotaLezione(dataLezione, 10.0);
        assertEquals(2, cal.getObserverState());
    }
    
    @Test
    @DisplayName("UC4 - Test visualizza prenotazioni iscritto inesistente")
    void testVisualizzaPrenotazioniIscrittoInesistente() {
        boolean risultato = gymMaster.visualizzaPrenotazioni("CFINESISTENTE");
        assertFalse(risultato);
    }
    
    @Test
    @DisplayName("UC4 - Test seleziona prenotazione inesistente")
    void testSelezionaPrenotazioneInesistente() {
        boolean risultato = gymMaster.selezionaPrenotazione(999, "10.0");
        assertFalse(risultato);
    }
    
    @Test
    @DisplayName("UC5 - Test registra accesso CF inesistente")
    void testRegistraAccessoCFInesistente() {
        assertDoesNotThrow(() -> gymMaster.registraAccesso("CFINESISTENTE"));
    }
    
    @Test
    @DisplayName("UC6 - Test calcola data limite")
    void testCalcolaDataLimite() {
        Date dataLimite = gymMaster.calcolaDataLimite(30);
        
        assertNotNull(dataLimite);
        
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_MONTH, 30);
        
        long diffInMillis = Math.abs(dataLimite.getTime() - cal.getTime().getTime());
        long diffInMinutes = diffInMillis / (1000 * 60);
        assertTrue(diffInMinutes < 5);
    }
    
    @Test
    @DisplayName("Observer - CalendarioLezioni inizializzato nel costruttore")
    void testCalendarioLezioniInizializzato() {
        assertNotNull(gymMaster.getCalendarioLezioni());
        assertEquals(0, gymMaster.getCalendarioLezioni().getObserverState());
    }
    
    @Test
    @DisplayName("Observer - getter/setter CalendarioLezioni")
    void testGetSetCalendarioLezioni() {
        CalendarioLezioni nuovoCal = new CalendarioLezioni();
        gymMaster.setCalendarioLezioni(nuovoCal);
        
        assertEquals(nuovoCal, gymMaster.getCalendarioLezioni());
    }
    
    @Test
    @DisplayName("Observer - tutte le lezioni create hanno lo stesso CalendarioLezioni come Observer")
    void testTutteLezioniStessoObserver() {
        gymMaster.inserisciNuovoCorso("Yoga", "Corso yoga", "Marco Zen");
        
        Lezione l1 = gymMaster.inserimentoLezioni("Lunedi", 10.0, 15, 20, 1.5, 
                                                   StatoLezione.NON_INIZIATA, new Date(), "Marco Zen", "YOG001");
        Lezione l2 = gymMaster.inserimentoLezioni("Martedi", 11.0, 10, 15, 1.0, 
                                                   StatoLezione.NON_INIZIATA, new Date(), "Marco Zen", "YOG002");
        
        CalendarioLezioni cal = gymMaster.getCalendarioLezioni();
        
        assertTrue(l1.getObservers().contains(cal));
        assertTrue(l2.getObservers().contains(cal));
        
        l1.decrementaPostiDisponibili();
        assertEquals(1, cal.getObserverState());
        
        l2.decrementaPostiDisponibili();
        assertEquals(2, cal.getObserverState());
    }
    
    @Test
    @DisplayName("Test getters mappe")
    void testGettersMappe() {
        assertNotNull(gymMaster.getIscritti());
        assertNotNull(gymMaster.getAbbonamenti());
        assertNotNull(gymMaster.getCorsi());
        assertNotNull(gymMaster.getLezioni());
        assertNotNull(gymMaster.getPrenotazioni());
    }
    
    @Test
    @DisplayName("Test sala")
    void testSala() {
        assertNotNull(gymMaster.getSala());
        assertEquals(1, gymMaster.getSala().getID());
    }
    
    @Test
    @DisplayName("Test gestore palestra")
    void testGestorePalestra() {
        assertNotNull(gymMaster.getGestorePalestra());
        assertEquals("Giuseppe", gymMaster.getGestorePalestra().getNomegestore());
    }
}