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
public class Sala {
    private int ID;
    private boolean disponibilita;
    
    public Sala(int ID) {
        this.ID = ID;
        this.disponibilita = true;
    }
    
    public boolean isDisponibile(Date data, double orainizio, double durata, String sala) {
        return this.disponibilita;
    }
    
    public int getID() {
        return ID;
    }
    
    public void setID(int ID) {
        this.ID = ID;
    }
    
    public boolean isDisponibilita() {
        return disponibilita;
    }
    
    public void setDisponibilita(boolean disponibilita) {
        this.disponibilita = disponibilita;
    }
}