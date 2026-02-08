/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.code;

/**
 *
 * @author carlo
 */
public class CalendarioLezioni implements Observer {
    
    private int observerState;
    
    public CalendarioLezioni() {
        this.observerState = 0;
    }
    
    @Override
    public void update() {
        this.observerState++;
        System.out.println("[Observer] CalendarioLezioni: notifica ricevuta - aggiornamento #" + observerState);
        refreshView();
    }

    public void refreshView() {
        System.out.println("[Observer] CalendarioLezioni: vista del calendario aggiornata automaticamente.");
    }

    public int getObserverState() {
        return observerState;
    }
}
