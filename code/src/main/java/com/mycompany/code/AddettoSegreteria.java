/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.code;

/**
 *
 * @author carlo
 */
public class AddettoSegreteria {
    private String nomesegreteria;
    private String cognome;
    private Iscritto iscrittoCorrente;
    
    public AddettoSegreteria(String nomesegreteria, String cognome) {
        this.nomesegreteria = nomesegreteria;
        this.cognome = cognome;
        this.iscrittoCorrente = null;
    }
    
    public String getNomesegreteria() { 
        return nomesegreteria; 
    }
    
    public String getCognome() { 
        return cognome; 
    }
    
    public Iscritto getIscrittoCorrente() { 
        return iscrittoCorrente; 
    }
    
    public void setNomesegreteria(String nomesegreteria) { 
        this.nomesegreteria = nomesegreteria; 
    }
    
    public void setCognome(String cognome) { 
        this.cognome = cognome; 
    }
    
    public void setIscrittoCorrente(Iscritto iscrittoCorrente) { 
        this.iscrittoCorrente = iscrittoCorrente; 
    }
}