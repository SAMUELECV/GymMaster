/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.code;

import com.mycompany.code.Lezione;
import com.mycompany.code.StatoPrenotazione;
import java.util.Date;

/**
 *
 * @author carlo
 */
public class Prenotazione {
    private String oraprenotazione;
    private Date dataprenotazione;
    private StatoPrenotazione statoprenotazione;
    private int idprenotazione;
    private Lezione lezione;
    private String CFIscritto;
    
    public Prenotazione(Date dataprenotazione, String oraprenotazione, 
                        StatoPrenotazione statoprenotazione, int idprenotazione) {
        this.dataprenotazione = dataprenotazione;
        this.oraprenotazione = oraprenotazione;
        this.statoprenotazione = statoprenotazione;
        this.idprenotazione = idprenotazione;
    }
    
    public void getPrenotazione(String CF) {
        System.out.println("Recupero prenotazione per CF: " + CF);
    }
    
    public void setStatoPrenotazione(Prenotazione p, StatoPrenotazione statoprenotazione) {
        p.statoprenotazione = statoprenotazione;
        System.out.println("Stato prenotazione: " + statoprenotazione);
    }
    
    public Prenotazione getPrenotazioneSpecifica(String idprenotazione) {
        if (String.valueOf(this.idprenotazione).equals(idprenotazione)) {
            return this;
        }
        return null;
    }

    public String getOraprenotazione() { return oraprenotazione; }
    public Date getDataprenotazione() { return dataprenotazione; }
    public StatoPrenotazione getStatoprenotazione() { return statoprenotazione; }
    public int getIdprenotazione() { return idprenotazione; }
    public Lezione getLezione() { return lezione; }
    public String getCFIscritto() { return CFIscritto; }

    public void setLezione(Lezione lezione) { this.lezione = lezione; }
    public void setCFIscritto(String CFIscritto) { this.CFIscritto = CFIscritto; }
    public void setOraprenotazione(String oraprenotazione) { this.oraprenotazione = oraprenotazione; }
    public void setDataprenotazione(Date dataprenotazione) { this.dataprenotazione = dataprenotazione; }
}