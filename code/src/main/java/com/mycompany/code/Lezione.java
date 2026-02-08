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
public class Lezione extends Subject {
    
    private String giorno;
    private double orainizio;
    private int postidisponibili;
    private int maxposti;
    private String sala;
    private double durata;
    private StatoLezione statolezione;
    private Date data;
    private String istruttore;
    private String IDLezione;
    
    public Lezione(String giorno, double orainizio, int postidisponibili, int maxposti,
                   String sala, double durata, StatoLezione statolezione, Date data,
                   String istruttore, String IDLezione) {
        super();
        this.giorno = giorno;
        this.orainizio = orainizio;
        this.postidisponibili = postidisponibili;
        this.maxposti = maxposti;
        this.sala = sala;
        this.durata = durata;
        this.statolezione = statolezione;
        this.data = data;
        this.istruttore = istruttore;
        this.IDLezione = IDLezione;
    }
    
    public Lezione getLezione() {
        return this;
    }
    
    public void decrementaPostiDisponibili() {
        if (this.postidisponibili > 0) {
            this.postidisponibili--;
            System.out.println("Posti disponibili aggiornati: " + this.postidisponibili + "/" + this.maxposti);
            notifyObserver();
        } else {
            System.out.println("Nessun posto disponibile per la lezione " + this.IDLezione);
        }
    }

    public void incrementaPostiDisponibili(Lezione l) {
        l.postidisponibili++;
        System.out.println("Posti disponibili aggiornati: " + l.postidisponibili + "/" + l.maxposti);
        l.notifyObserver();
    }
    
    public Lezione getLezioneSpecifica(String IDLezione) {
        if (this.IDLezione.equals(IDLezione)) {
            return this;
        }
        return null;
    }
    
    public Lezione getByGiorno(String giorno) {
        if (this.giorno.equalsIgnoreCase(giorno)) {
            return this;
        }
        return null;
    }
    
    public Lezione getByCorso(String corso) {
        return this;
    }
    
    public Lezione getByIstruttore(String istruttore) {
        if (this.istruttore.equalsIgnoreCase(istruttore)) {
            return this;
        }
        return null;
    }
    
    public void setStatoLezione(StatoLezione statolezione) {
        this.statolezione = statolezione;
    }
    
    public String getGiorno() { return giorno; }
    public double getOrainizio() { return orainizio; }
    public int getPostidisponibili() { return postidisponibili; }
    public int getMaxposti() { return maxposti; }
    public String getSala() { return sala; }
    public double getDurata() { return durata; }
    public StatoLezione getStatolezione() { return statolezione; }
    public Date getData() { return data; }
    public String getIstruttore() { return istruttore; }
    public String getIDLezione() { return IDLezione; }
}