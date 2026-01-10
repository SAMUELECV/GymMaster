/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.code;

/**
 *
 * @author carlo
 */
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class GestorePalestra {
    
    private String nomegestore;
    private String cognome;
    private Map<TipoAbbonamento, String> tipoAbbonamento;
    
    public GestorePalestra(String nomegestore, String cognome) {
        this.nomegestore = nomegestore;
        this.cognome = cognome;
        this.tipoAbbonamento = new HashMap<>();
    }
    
    public TipoAbbonamento creaNuovoTipoAbbonamento(String nomeabbonamento, String descrizione,
                                                     Date durata, double prezzo, boolean lezione) {
        TipoAbbonamento ta = new TipoAbbonamento(nomeabbonamento, descrizione, durata, prezzo, lezione);
        tipoAbbonamento.put(ta, nomeabbonamento);
        System.out.println("Tipo abbonamento creato con successo: " + nomeabbonamento);
        return ta;
    }
    
    public void modificaTipoAbbonamento(String nuovonomeabbonamento, String nuovadescrizione,
                                        Date nuovadurata, double nuovoprezzo, boolean nuovalezionedigruppo) {
        TipoAbbonamento ta = getTipoAbbonamento(nuovonomeabbonamento);
        if (ta != null) {
            ta.aggiornaDati(nuovonomeabbonamento, nuovadescrizione, nuovadurata, nuovoprezzo, nuovalezionedigruppo);
            System.out.println("Tipo abbonamento modificato con successo!");
        }
    }
    
    public TipoAbbonamento getTipoAbbonamento(String nomeabbonamento) {
        for (Map.Entry<TipoAbbonamento, String> entry : tipoAbbonamento.entrySet()) {
            if (entry.getValue().equals(nomeabbonamento)) {
                return entry.getKey();
            }
        }
        return null;
    }
    
    public int getTipiAbbonamento() {
        return tipoAbbonamento.size();
    }
    
    public int mostraTipologieAbbonamento() {
        System.out.println("\n--- Tipologie abbonamento disponibili ---");
        if (tipoAbbonamento.isEmpty()) {
            System.out.println("Nessuna tipologia disponibile.");
            return 0;
        }
        for (Map.Entry<TipoAbbonamento, String> entry : tipoAbbonamento.entrySet()) {
            TipoAbbonamento ta = entry.getKey();
            System.out.println("- " + ta.getNomeabbonamento() + " | " + ta.getPrezzo() 
                + " euro | Lezioni: " + (ta.isLezione() ? "Si" : "No"));
        }
        return tipoAbbonamento.size();
    }
    
    public TipoAbbonamento selezionaTipologieAbbonamento(String nomeabbonamento) {
        TipoAbbonamento ta = getTipoAbbonamento(nomeabbonamento);
        if (ta != null) {
            System.out.println("Tipologia selezionata: " + nomeabbonamento);
            return ta;
        }
        System.out.println("Tipo abbonamento non trovato!");
        return null;
    }
    
    public boolean esisteTipoAbbonamento(String nome) {
        return getTipoAbbonamento(nome) != null;
    }
    
    public Map<TipoAbbonamento, String> getTipoAbbonamentoMap() {
        return tipoAbbonamento;
    }
    
    // Getters
    public String getNomegestore() { return nomegestore; }
    public String getCognome() { return cognome; }
}