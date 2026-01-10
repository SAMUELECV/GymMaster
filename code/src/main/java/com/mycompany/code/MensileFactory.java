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
public class MensileFactory extends AbbonamentoFactory {
    
    private static int contatoreId = 1;
    
    @Override
    public Abbonamento creaAbbonamento(Iscritto i, Date datainizio) {
        Date datafine = calcolaDataFine(datainizio, 30); // 30 giorni
        
        Abbonamento a = new Abbonamento(contatoreId, datainizio, datafine,
                                        null, StatoAbbonamento.ATTIVO);
        a.setCFIscritto(i.getCF());
        contatoreId++;
        
        System.out.println("Abbonamento MENSILE creato - ID: " + a.getIdabbonamento());
        System.out.println("Inizio: " + datainizio + " | Fine: " + datafine);
        return a;
    }
}
