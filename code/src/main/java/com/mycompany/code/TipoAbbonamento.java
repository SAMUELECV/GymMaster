/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.code;

import java.util.Date;

/**
 *
 * @author carlo
 */
public class TipoAbbonamento {
    private double prezzo;
    private boolean lezione;
    private String descrizione;
    private String nomeabbonamento;
    private Date durata;
    
    public TipoAbbonamento(String nomeabbonamento, String descrizione, 
                          Date durata, double prezzo, boolean lezione) {
        this.nomeabbonamento = nomeabbonamento;
        this.descrizione = descrizione;
        this.durata = durata;
        this.prezzo = prezzo;
        this.lezione = lezione;
    }
    
    public void aggiornaDati(String nuovonome, String nuovadescrizione, 
                             Date nuovadurata, double nuovoprezzo, boolean nuovalezionedigruppo) {
        this.nomeabbonamento = nuovonome;
        this.descrizione = nuovadescrizione;
        this.durata = nuovadurata;
        this.prezzo = nuovoprezzo;
        this.lezione = nuovalezionedigruppo;
        System.out.println("Tipo abbonamento aggiornato: " + this.nomeabbonamento);
    }
    
    public void setNome(String nuovonomeabbonamento) { this.nomeabbonamento = nuovonomeabbonamento; }
    public void setLezioneDiGruppo(boolean nuovalezionedigruppo) { this.lezione = nuovalezionedigruppo; }
    public void setDescrizione(String nuovadescrizione) { this.descrizione = nuovadescrizione; }
    public void setPrezzo(double nuovoprezzo) { this.prezzo = nuovoprezzo; }
    public void setDurata(Date nuovadurata) { this.durata = nuovadurata; }
    
    public boolean verificaTipoAbbonamento() {
        return this.lezione;
    }
    
    public double getPrezzo() { return prezzo; }
    public boolean isLezione() { return lezione; }
    public String getDescrizione() { return descrizione; }
    public String getNomeabbonamento() { return nomeabbonamento; }
    public Date getDurata() { return durata; }
}