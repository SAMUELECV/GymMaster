/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.code;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.text.SimpleDateFormat;

/**
 *
 * @author carlo
 */
public class GymMaster {

    private Map<Iscritto, String> iscritti;
    private Map<Abbonamento, Integer> abbonamenti;
    
    private GestorePalestra gestorePalestra;
    private TipoAbbonamento tipoAbbonamentoSelezionato;
    private Iscritto iscrittoCorrente;
    private int contatoreidAbbonamento;
    private int contatoreidPrenotazione;
    private String opzione;
    private AbbonamentoFactory abbonamentoFactory;
    
    private SimpleDateFormat sdfOutput = new SimpleDateFormat("EEEE dd MMMM yyyy HH:mm:ss", java.util.Locale.ITALIAN);
    
    public GymMaster() {
        this.iscritti = new HashMap<>();
        this.abbonamenti = new HashMap<>();   
        this.gestorePalestra = null;
        this.tipoAbbonamentoSelezionato = null;
        this.iscrittoCorrente = null;
        this.contatoreidAbbonamento = 1;
        this.contatoreidPrenotazione = 1;
        this.opzione = "";
        this.abbonamentoFactory = null;
    }
    
    private Iscritto findIscrittoByCF(String CF) {
        for (Map.Entry<Iscritto, String> entry : iscritti.entrySet()) {
            if (entry.getValue().equals(CF)) {
                return entry.getKey();
            }
        }
        return null;
    }
    
    private Abbonamento findAbbonamentoById(int id) {
        for (Map.Entry<Abbonamento, Integer> entry : abbonamenti.entrySet()) {
            if (entry.getValue().equals(id)) {
                return entry.getKey();
            }
        }
        return null;
    }
    
    private Abbonamento findAbbonamentoByCF(String CF) {
        for (Abbonamento a : abbonamenti.keySet()) {
            if (a.getCFIscritto() != null && a.getCFIscritto().equals(CF)) {
                return a;
            }
        }
        return null;
    }
    
    public Iscritto registraNuovoIscritto(String nomeiscritto, String cognome, Date datadinascita,
                                         String CF, String telefono, String email, String indirizzo) {
        if (findIscrittoByCF(CF) != null) {
            System.out.println("Iscritto gia' presente!");
            return findIscrittoByCF(CF);
        }
        
        Iscritto i = new Iscritto(nomeiscritto, cognome, datadinascita, CF, telefono, email, indirizzo);
        iscritti.put(i, CF);
        System.out.println("Iscritto aggiunto: " + cognome + " " + nomeiscritto);
        return i;
    }
    
    public int generaCodiceTessera() {
        Random random = new Random();
        int codiceTessera = 10000 + random.nextInt(90000);
        System.out.println("Codice tessera generato: " + codiceTessera);
        return codiceTessera;
    }
    
    public void confermaInserimento(String nomeiscritto, String cognome, Date datadinascita,
                                    String CF, String telefono, String email, 
                                    String indirizzo, int codicetessera) {
        Iscritto i = findIscrittoByCF(CF);
        if (i != null) {
            i.setCodicetessera(codicetessera);
            System.out.println("Registrazione completata con successo!");
        }
    }
    
    public Iscritto attivaAbbonamento(String CF) {
        Iscritto i = findIscrittoByCF(CF);
        if (i != null) {
            Abbonamento abbonamentoCorrente = i.getAbbonamentoCorrente();
            if (abbonamentoCorrente != null) {
                if (abbonamentoCorrente.getStatoabbonamento() == StatoAbbonamento.ATTIVO) {
                    if (abbonamentoCorrente.controlloScadenza(new Date())) {
                        System.out.println("Impossibile attivare un nuovo abbonamento: l'iscritto " + i.getNomeiscritto() + " " + i.getCognome() + " possiede gia' un abbonamento attivo!");
                        System.out.println("ID Abbonamento attivo: " + abbonamentoCorrente.getIdabbonamento());
                        System.out.println("Scadenza: " + sdfOutput.format(abbonamentoCorrente.getDatafine()));
                        return null;
                    }
                }
            }

            this.iscrittoCorrente = i;
            System.out.println("Iscritto trovato: " + i.getNomeiscritto() + " " + i.getCognome());
            return i;
        }
        System.out.println("Iscritto non trovato!");
        return null;
    }
    
    public TipoAbbonamento creaNuovoTipoAbbonamento(String nomeabbonamento, String descrizione,
                                                     Date durata, double prezzo, boolean lezione) {
        if (gestorePalestra == null) {
            System.out.println("Nessun gestore palestra configurato!");
            return null;
        }
        return gestorePalestra.creaNuovoTipoAbbonamento(nomeabbonamento, descrizione, durata, prezzo, lezione);
    }
    
    public void modificaTipoAbbonamento(String nuovonomeabbonamento, String nuovadescrizione,
                                        Date nuovadurata, double nuovoprezzo, boolean nuovalezionedigruppo) {
        if (gestorePalestra == null) {
            System.out.println("Nessun gestore palestra configurato!");
            return;
        }
        gestorePalestra.modificaTipoAbbonamento(nuovonomeabbonamento, nuovadescrizione, nuovadurata, nuovoprezzo, nuovalezionedigruppo);
    } 

    public int mostraTipologieAbbonamento() {
        if (gestorePalestra == null) {
            System.out.println("Nessun gestore palestra configurato!");
            return 0;
        }
        return gestorePalestra.mostraTipologieAbbonamento();
    }
    
    public boolean esisteTipoAbbonamento(String nome) {
        if (gestorePalestra == null) return false;
        return gestorePalestra.esisteTipoAbbonamento(nome);
    }
    
    public TipoAbbonamento selezionaTipologieAbbonamento(String nomeabbonamento) {
        if (gestorePalestra == null) {
            System.out.println("Nessun gestore palestra configurato!");
            return null;
        }
        
        TipoAbbonamento ta = gestorePalestra.selezionaTipologieAbbonamento(nomeabbonamento);
        if (ta != null) {
            this.tipoAbbonamentoSelezionato = ta;
            
            switch (nomeabbonamento.toUpperCase()) {
                case "MENSILE":
                    this.abbonamentoFactory = new MensileFactory();
                    break;
                case "TRIMESTRALE":
                    this.abbonamentoFactory = new TrimestraleFactory();
                    break;
                case "ANNUALE":
                    this.abbonamentoFactory = new AnnualeFactory();
                    break;
                default:
                    System.out.println("Tipologia non riconosciuta per la factory!");
                    this.abbonamentoFactory = null;
                    return ta;
            }
            
            System.out.println("Factory: " + abbonamentoFactory.getClass().getSimpleName());
            return ta;
        }
        return null;
    }
    
    public Abbonamento confermaAttivazione(String CF) {
        if (tipoAbbonamentoSelezionato == null) {
            System.out.println("Nessuna tipologia abbonamento selezionata!");
            return null;
        }
        
        if (abbonamentoFactory == null) {
            System.out.println("Nessuna factory selezionata!");
            return null;
        }
        
        if (iscrittoCorrente == null) {
            System.out.println("Nessun iscritto selezionato!");
            return null;
        }
        
        Date datainizio = new Date();
        
        Abbonamento a = abbonamentoFactory.creaAbbonamento(iscrittoCorrente, datainizio);
        a.setTipo(tipoAbbonamentoSelezionato);
        abbonamenti.put(a, a.getIdabbonamento());
        iscrittoCorrente.setAbbonamentoCorrente(a);
        
        System.out.println("Abbonamento attivato con successo! ID: " + a.getIdabbonamento());
        return a;
    }
    
    public void salvaAbbonamento(Abbonamento a) {
        abbonamenti.put(a, a.getIdabbonamento());
        System.out.println("Abbonamento salvato - ID: " + a.getIdabbonamento());
    }
    
    public void setGestorePalestra(GestorePalestra gestore) { this.gestorePalestra = gestore; }
    public GestorePalestra getGestorePalestra() { return gestorePalestra; }
    public Map<Iscritto, String> getIscritti() { return iscritti; }
    public Map<Abbonamento, Integer> getAbbonamenti() { return abbonamenti; }
    public AbbonamentoFactory getAbbonamentoFactory() { return abbonamentoFactory; }
    public void setAbbonamentoFactory(AbbonamentoFactory factory) { this.abbonamentoFactory = factory; }
}