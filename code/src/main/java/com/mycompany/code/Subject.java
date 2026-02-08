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
public class Subject {
    
    private List<Observer> observers;
    
    public Subject() {
        this.observers = new ArrayList<>();
    }
    
    public void attach(Observer o) {
        if (o != null && !observers.contains(o)) {
            observers.add(o);
            System.out.println("Observer aggiunto: " + o.getClass().getSimpleName());
        }
    }

    public void detach(Observer o) {
        if (o != null) {
            observers.remove(o);
            System.out.println("Observer rimosso: " + o.getClass().getSimpleName());
        }
    }
    
    public void notifyObserver() {
        for (Observer o : observers) {
            o.update();
        }
    }

    public List<Observer> getObservers() {
        return observers;
    }
}
