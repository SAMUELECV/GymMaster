/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.code;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;
import java.util.Calendar;

/**
 *
 * @author carlo
 */
public class AddettoSegreteriaTest {
    
    private AddettoSegreteria addetto;
    
    @BeforeEach
    void setUp() {
        addetto = new AddettoSegreteria("Mario", "Rossi");
    }
    
    @Test
    @DisplayName("Test creazione AddettoSegreteria")
    void testCreazioneAddettoSegreteria() {
        assertNotNull(addetto);
        assertEquals("Mario", addetto.getNomesegreteria());
        assertEquals("Rossi", addetto.getCognome());
    }
    
    @Test
    @DisplayName("Test iscritto corrente iniziale null")
    void testIscrittoCorrenteIniziale() {
        assertNull(addetto.getIscrittoCorrente());
    }
    
    @Test
    @DisplayName("Test setIscrittoCorrente")
    void testSetIscrittoCorrente() {
        Calendar cal = Calendar.getInstance();
        cal.set(1995, 2, 15);
        Date dataNascita = cal.getTime();
        
        Iscritto iscritto = new Iscritto("Luca", "Bianchi", dataNascita, 
                                         "BNCLCU95C15H501Z", "3331234567", 
                                         "luca@email.it", "Via Roma 123");
        
        addetto.setIscrittoCorrente(iscritto);
        
        assertNotNull(addetto.getIscrittoCorrente());
        assertEquals(iscritto, addetto.getIscrittoCorrente());
    }
    
    @Test
    @DisplayName("Test setters")
    void testSetters() {
        addetto.setNomesegreteria("Anna");
        addetto.setCognome("Verdi");
        
        assertEquals("Anna", addetto.getNomesegreteria());
        assertEquals("Verdi", addetto.getCognome());
    }
}