/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.code;


import java.util.Date;
import java.util.Calendar;
/**
 *
 * @author carlo
 */
public abstract class AbbonamentoFactory {
    
    public abstract Abbonamento creaAbbonamento(Iscritto i, Date datainizio);
    
    protected Date calcolaDataFine(Date datainizio, int giorniDurata) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(datainizio);
        cal.add(Calendar.DAY_OF_MONTH, giorniDurata);
        return cal.getTime();
    }
}
