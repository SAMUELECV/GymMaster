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
public class Iscritto {
    private String nomeiscritto;
    private String cognome;
    private Date datadinascita;
    private String CF;
    private String telefono;
    private String email;
    private String indirizzo;
    private int codicetessera;
    private Abbonamento abbonamentoCorrente;
    
    public Iscritto(String nomeiscritto, String cognome, Date datadinascita, 
                    String CF, String telefono, String email, String indirizzo) {
        this.nomeiscritto = nomeiscritto;
        this.cognome = cognome;
        this.datadinascita = datadinascita;
        this.CF = CF;
        this.telefono = telefono;
        this.email = email;
        this.indirizzo = indirizzo;
        this.codicetessera = 0;
        this.abbonamentoCorrente = null;
    }
    
    public void add() {
        System.out.println("Iscritto aggiunto: " + cognome + " " + nomeiscritto);
    }
    
    public void setAbbonamentoCorrente(Abbonamento abbonamento) {
        this.abbonamentoCorrente = abbonamento;
    }
    
    public void getIscritto(String CF) {
        System.out.println("Recuperato iscritto CF: " + CF);
    }
    
    public String getNomeiscritto() { return nomeiscritto; }
    public String getCognome() { return cognome; }
    public Date getDatadinascita() { return datadinascita; }
    public String getCF() { return CF; }
    public String getTelefono() { return telefono; }
    public String getEmail() { return email; }
    public String getIndirizzo() { return indirizzo; }
    public int getCodicetessera() { return codicetessera; }
    public Abbonamento getAbbonamentoCorrente() { return abbonamentoCorrente; }
    
    public void setNomeiscritto(String nomeiscritto) { this.nomeiscritto = nomeiscritto; }
    public void setCognome(String cognome) { this.cognome = cognome; }
    public void setDatadinascita(Date datadinascita) { this.datadinascita = datadinascita; }
    public void setCF(String CF) { this.CF = CF; }
    public void setTelefono(String telefono) { this.telefono = telefono; }
    public void setEmail(String email) { this.email = email; }
    public void setIndirizzo(String indirizzo) { this.indirizzo = indirizzo; }
    public void setCodicetessera(int codicetessera) { this.codicetessera = codicetessera; }
}