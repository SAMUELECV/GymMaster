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
public class Abbonamento {
    private int idabbonamento;
    private Date datainizio;
    private Date datafine;
    private TipoAbbonamento tipo;
    private StatoAbbonamento statoabbonamento;
    private String CFIscritto;
    
    public Abbonamento(int idabbonamento, Date datainizio, Date datafine, 
                       TipoAbbonamento tipo, StatoAbbonamento statoabbonamento) {
        this.idabbonamento = idabbonamento;
        this.datainizio = datainizio;
        this.datafine = datafine;
        this.tipo = tipo;
        this.statoabbonamento = statoabbonamento;
    }

    public boolean controlloScadenza(Date dataodierna) {
        if (dataodierna.after(datafine)) {
            this.statoabbonamento = StatoAbbonamento.SCADUTO;
            System.out.println("Abbonamento SCADUTO");
            return false;
        }
        System.out.println("Abbonamento valido fino a: " + datafine);
        return true;
    }

    public Abbonamento getAbbonamento(String iCF) {
        if (this.CFIscritto != null && this.CFIscritto.equals(iCF)) {
            return this;
        }
        return null;
    }
    
    public void getAbbonamentiInScadenza(Date datalimite) {
        if (this.datafine != null && !this.datafine.after(datalimite) && 
            this.statoabbonamento == StatoAbbonamento.ATTIVO) {
            System.out.println("Abbonamento ID: " + idabbonamento + " in scadenza il " + datafine);
        }
    }
    
    public boolean isInScadenza(Date datalimite) {
        return this.datafine != null && !this.datafine.after(datalimite) && 
               this.statoabbonamento == StatoAbbonamento.ATTIVO;
    }
    
    public void setStatoAbbonamento(StatoAbbonamento statoAbbonamento) {
        this.statoabbonamento = statoAbbonamento;
    }
    
    public void add(Abbonamento a) {
        System.out.println("Abbonamento aggiunto ID: " + a.getIdabbonamento());
    }
    
    public int getIdabbonamento() { return idabbonamento; }
    public Date getDatainizio() { return datainizio; }
    public Date getDatafine() { return datafine; }
    public TipoAbbonamento getTipo() { return tipo; }
    public StatoAbbonamento getStatoabbonamento() { return statoabbonamento; }
    public String getCFIscritto() { return CFIscritto; }
    
    public void setIdabbonamento(int idabbonamento) { this.idabbonamento = idabbonamento; }
    public void setDatainizio(Date datainizio) { this.datainizio = datainizio; }
    public void setDatafine(Date datafine) { this.datafine = datafine; }
    public void setTipo(TipoAbbonamento tipo) { this.tipo = tipo; }
    public void setCFIscritto(String CFIscritto) { this.CFIscritto = CFIscritto; }
}