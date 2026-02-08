/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.code;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author carlo
 */
public class Corso {
    private String nomecorso;
    private String descrizione;
    private String istruttorepredefinito;
    private List<Lezione> lezioni;
    
    public Corso(String nomecorso, String descrizione, String istruttorepredefinito) {
        this.nomecorso = nomecorso;
        this.descrizione = descrizione;
        this.istruttorepredefinito = istruttorepredefinito;
        this.lezioni = new ArrayList<>();
    }
    
    public Corso getCorso() {
        return this;
    }
    
    public void addLezione(Lezione lezione) {
        this.lezioni.add(lezione);
    }
    
    public String getNomecorso() { return nomecorso; }
    public String getDescrizione() { return descrizione; }
    public String getIstruttorepredefinito() { return istruttorepredefinito; }
    public List<Lezione> getLezioni() { return lezioni; }
    
    public void setNomecorso(String nomecorso) { this.nomecorso = nomecorso; }
    public void setDescrizione(String descrizione) { this.descrizione = descrizione; }
    public void setIstruttorepredefinito(String istruttorepredefinito) { this.istruttorepredefinito = istruttorepredefinito; }
}