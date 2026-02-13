/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.code;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;
/**
 *
 * @author carlo
 */
public class SubjectTest {
    
    private Subject subject;
    private CalendarioLezioni observer1;
    private CalendarioLezioni observer2;
    
    @BeforeEach
    void setUp() {
        subject = new Subject();
        observer1 = new CalendarioLezioni();
        observer2 = new CalendarioLezioni();
    }
    
    @Test
    @DisplayName("Test attach - aggiunta di un Observer")
    void testAttach() {
        subject.attach(observer1);
        
        assertEquals(1, subject.getObservers().size());
        assertTrue(subject.getObservers().contains(observer1));
    }
    
    @Test
    @DisplayName("Test attach - aggiunta di più Observer")
    void testAttachMultipli() {
        subject.attach(observer1);
        subject.attach(observer2);
        
        assertEquals(2, subject.getObservers().size());
    }
    
    @Test
    @DisplayName("Test attach - non aggiunge duplicati")
    void testAttachDuplicato() {
        subject.attach(observer1);
        subject.attach(observer1);
        
        assertEquals(1, subject.getObservers().size());
    }
    
    @Test
    @DisplayName("Test attach - ignora null")
    void testAttachNull() {
        subject.attach(null);
        
        assertEquals(0, subject.getObservers().size());
    }
    
    @Test
    @DisplayName("Test detach - rimozione di un Observer")
    void testDetach() {
        subject.attach(observer1);
        subject.attach(observer2);
        
        subject.detach(observer1);
        
        assertEquals(1, subject.getObservers().size());
        assertFalse(subject.getObservers().contains(observer1));
        assertTrue(subject.getObservers().contains(observer2));
    }
    
    @Test
    @DisplayName("Test detach - rimozione di Observer non presente")
    void testDetachNonPresente() {
        subject.attach(observer1);
        
        subject.detach(observer2);
        
        assertEquals(1, subject.getObservers().size());
    }
    
    @Test
    @DisplayName("Test detach null - non genera eccezioni")
    void testDetachNull() {
        assertDoesNotThrow(() -> subject.detach(null));
    }
    
    @Test
    @DisplayName("Test notifyObserver - notifica tutti gli observer registrati")
    void testNotifyObserver() {
        subject.attach(observer1);
        subject.attach(observer2);
        
        assertEquals(0, observer1.getObserverState());
        assertEquals(0, observer2.getObserverState());
        
        subject.notifyObserver();
        
        assertEquals(1, observer1.getObserverState());
        assertEquals(1, observer2.getObserverState());
    }
    
    @Test
    @DisplayName("Test notifyObserver - notifiche multiple incrementano lo stato")
    void testNotifyObserverMultiplo() {
        subject.attach(observer1);
        
        subject.notifyObserver();
        subject.notifyObserver();
        subject.notifyObserver();
        
        assertEquals(3, observer1.getObserverState());
    }
    
    @Test
    @DisplayName("Test notifyObserver - senza observer non genera eccezioni")
    void testNotifyObserverSenzaObserver() {
        assertDoesNotThrow(() -> subject.notifyObserver());
    }
    
    @Test
    @DisplayName("Test getObservers - lista inizialmente vuota")
    void testGetObserversVuota() {
        assertNotNull(subject.getObservers());
        assertTrue(subject.getObservers().isEmpty());
    }
}