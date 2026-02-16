/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.code;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Calendar;
import java.text.SimpleDateFormat;

/**
 *
 * @author carlo
 */
public class GymMaster {

    private Map<Iscritto, String> iscritti;
    private Map<Abbonamento, Integer> abbonamenti;
    private Map<Prenotazione, Integer> prenotazioni;
    private Map<Corso, String> corsi;
    private Map<Lezione, String> lezioni;
    private Sala sala;
    private GestorePalestra gestorePalestra;
    private TipoAbbonamento tipoAbbonamentoSelezionato;
    private Iscritto iscrittoCorrente;
    private Corso corsoCorrente;
    private Lezione lezioneCorrente;
    private Prenotazione prenotazioneCorrente;
    private int contatoreidAbbonamento;
    private int contatoreidPrenotazione;
    private String opzione;
    private AbbonamentoFactory abbonamentoFactory;
    private CalendarioLezioni calendarioLezioni;
    
    private SimpleDateFormat sdfOutput = new SimpleDateFormat("EEEE dd MMMM yyyy HH:mm:ss", java.util.Locale.ITALIAN);
    
    public GymMaster() {
        this.iscritti = new HashMap<>();
        this.abbonamenti = new HashMap<>();
        this.prenotazioni = new HashMap<>();
        this.corsi = new HashMap<>();
        this.lezioni = new HashMap<>();
        this.sala = null;
        
        this.gestorePalestra = null;
        this.tipoAbbonamentoSelezionato = null;
        this.iscrittoCorrente = null;
        this.corsoCorrente = null;
        this.lezioneCorrente = null;
        this.prenotazioneCorrente = null;
        this.contatoreidAbbonamento = 1;
        this.contatoreidPrenotazione = 1;
        this.opzione = "";
        this.abbonamentoFactory = null;
        this.calendarioLezioni = new CalendarioLezioni();
    }
    
    private Iscritto findIscrittoByCF(String CF) {
        for (Map.Entry<Iscritto, String> entry : iscritti.entrySet()) {
            if (entry.getValue().equals(CF)) {
                return entry.getKey();
            }
        }
        return null;
    }
    
    private Abbonamento findAbbonamentoById(int id) {
        for (Map.Entry<Abbonamento, Integer> entry : abbonamenti.entrySet()) {
            if (entry.getValue().equals(id)) {
                return entry.getKey();
            }
        }
        return null;
    }
    
    private Abbonamento findAbbonamentoByCF(String CF) {
        for (Abbonamento a : abbonamenti.keySet()) {
            if (a.getCFIscritto() != null && a.getCFIscritto().equals(CF)) {
                return a;
            }
        }
        return null;
    }
    
    private Prenotazione findPrenotazioneById(int id) {
        for (Map.Entry<Prenotazione, Integer> entry : prenotazioni.entrySet()) {
            if (entry.getValue().equals(id)) {
                return entry.getKey();
            }
        }
        return null;
    }
    
    private Corso findCorsoByNome(String nome) {
        for (Map.Entry<Corso, String> entry : corsi.entrySet()) {
            if (entry.getValue().equals(nome)) {
                return entry.getKey();
            }
        }
        return null;
    }
    
    private Lezione findLezioneById(String id) {
        for (Map.Entry<Lezione, String> entry : lezioni.entrySet()) {
            if (entry.getValue().equals(id)) {
                return entry.getKey();
            }
        }
        return null;
    }
    
    private Lezione findLezioneByDataOra(Date data, double orainizio) {
        for (Lezione l : lezioni.keySet()) {
            if (l.getOrainizio() == orainizio) {
                return l;
            }
        }
        return null;
    }

    public Iscritto registraNuovoIscritto(String nomeiscritto, String cognome, Date datadinascita,
                                         String CF, String telefono, String email, String indirizzo) {
        if (findIscrittoByCF(CF) != null) {
            System.out.println("Iscritto gia' presente!");
            return findIscrittoByCF(CF);
        }
        
        Iscritto i = new Iscritto(nomeiscritto, cognome, datadinascita, CF, telefono, email, indirizzo);
        iscritti.put(i, CF);
        System.out.println("Iscritto aggiunto: " + cognome + " " + nomeiscritto);
        return i;
    }
    
    public int generaCodiceTessera() {
        Random random = new Random();
        int codiceTessera = 10000 + random.nextInt(90000);
        System.out.println("Codice tessera generato: " + codiceTessera);
        return codiceTessera;
    }
    
    public void confermaInserimento(String nomeiscritto, String cognome, Date datadinascita,
                                    String CF, String telefono, String email, 
                                    String indirizzo, int codicetessera) {
        Iscritto i = findIscrittoByCF(CF);
        if (i != null) {
            i.setCodicetessera(codicetessera);
            System.out.println("Registrazione completata con successo!");
        }
    }

    public Iscritto attivaAbbonamento(String CF) {
        Iscritto i = findIscrittoByCF(CF);
        if (i != null) {
            Abbonamento abbonamentoCorrente = i.getAbbonamentoCorrente();
            if (abbonamentoCorrente != null) {
                if (abbonamentoCorrente.getStatoabbonamento() == StatoAbbonamento.ATTIVO) {
                    if (abbonamentoCorrente.controlloScadenza(new Date())) {
                        System.out.println("Impossibile attivare un nuovo abbonamento: l'iscritto " + i.getNomeiscritto() + " " + i.getCognome() + " possiede gia' un abbonamento attivo!");
                        System.out.println("ID Abbonamento attivo: " + abbonamentoCorrente.getIdabbonamento());
                        System.out.println("Scadenza: " + sdfOutput.format(abbonamentoCorrente.getDatafine()));
                        return null;
                    }
                }
            }
            this.iscrittoCorrente = i;
            System.out.println("Iscritto trovato: " + i.getNomeiscritto() + " " + i.getCognome());
            return i;
        }
        System.out.println("Iscritto non trovato!");
        return null;
    }

    public TipoAbbonamento creaNuovoTipoAbbonamento(String nomeabbonamento, String descrizione,
                                                     Date durata, double prezzo, boolean lezione) {
        if (gestorePalestra == null) {
            System.out.println("Nessun gestore palestra configurato!");
            return null;
        }
        return gestorePalestra.creaNuovoTipoAbbonamento(nomeabbonamento, descrizione, durata, prezzo, lezione);
    }

    public void modificaTipoAbbonamento(String nuovonomeabbonamento, String nuovadescrizione,
                                        Date nuovadurata, double nuovoprezzo, boolean nuovalezionedigruppo) {
        if (gestorePalestra == null) {
            System.out.println("Nessun gestore palestra configurato!");
            return;
        }
        gestorePalestra.modificaTipoAbbonamento(nuovonomeabbonamento, nuovadescrizione, nuovadurata, nuovoprezzo, nuovalezionedigruppo);
    } 

    public int mostraTipologieAbbonamento() {
        if (gestorePalestra == null) {
            System.out.println("Nessun gestore palestra configurato!");
            return 0;
        }
        return gestorePalestra.mostraTipologieAbbonamento();
    }
    
    public boolean esisteTipoAbbonamento(String nome) {
        if (gestorePalestra == null) return false;
        return gestorePalestra.esisteTipoAbbonamento(nome);
    }
    
    public TipoAbbonamento selezionaTipologieAbbonamento(String nomeabbonamento) {
        if (gestorePalestra == null) {
            System.out.println("Nessun gestore palestra configurato!");
            return null;
        }
        
        TipoAbbonamento ta = gestorePalestra.selezionaTipologieAbbonamento(nomeabbonamento);
        if (ta != null) {
            this.tipoAbbonamentoSelezionato = ta;
            
            switch (nomeabbonamento.toUpperCase()) {
                case "MENSILE":
                    this.abbonamentoFactory = new MensileFactory();
                    break;
                case "TRIMESTRALE":
                    this.abbonamentoFactory = new TrimestraleFactory();
                    break;
                case "ANNUALE":
                    this.abbonamentoFactory = new AnnualeFactory();
                    break;
                default:
                    System.out.println("Tipologia non riconosciuta per la factory!");
                    this.abbonamentoFactory = null;
                    return ta;
            }
            
            System.out.println("Factory: " + abbonamentoFactory.getClass().getSimpleName());
            return ta;
        }
        return null;
    }
    
    public Abbonamento confermaAttivazione(String CF) {
        if (tipoAbbonamentoSelezionato == null) {
            System.out.println("Nessuna tipologia abbonamento selezionata!");
            return null;
        }
        
        if (abbonamentoFactory == null) {
            System.out.println("Nessuna factory selezionata!");
            return null;
        }
        
        if (iscrittoCorrente == null) {
            System.out.println("Nessun iscritto selezionato!");
            return null;
        }
        
        Date datainizio = new Date();
        
        Abbonamento a = abbonamentoFactory.creaAbbonamento(iscrittoCorrente, datainizio);
        a.setTipo(tipoAbbonamentoSelezionato);
        abbonamenti.put(a, a.getIdabbonamento());
        iscrittoCorrente.setAbbonamentoCorrente(a);
        
        System.out.println("Abbonamento attivato con successo! ID: " + a.getIdabbonamento());
        return a;
    }
    
    public void salvaAbbonamento(Abbonamento a) {
        abbonamenti.put(a, a.getIdabbonamento());
        System.out.println("Abbonamento salvato - ID: " + a.getIdabbonamento());
    }
    
    public Lezione visualizzaLezioniECorsi(String CF) {
        Iscritto i = findIscrittoByCF(CF);
        if (i == null) {
            System.out.println("Iscritto non trovato!");
            return null;
        }
        this.iscrittoCorrente = i;
        
        Abbonamento a = i.getAbbonamentoCorrente();
        if (a == null) {
            System.out.println("Nessun abbonamento attivo!");
            return null;
        }
        
        if (!a.controlloScadenza(new Date())) {
            System.out.println("Abbonamento scaduto!");
            return null;
        }
        
        boolean lezionedigruppo = a.getTipo().verificaTipoAbbonamento();
        
        if (lezionedigruppo) {
            System.out.println("\n=== CORSI DISPONIBILI ===");
            for (Corso c : corsi.keySet()) {
                System.out.println("- " + c.getNomecorso() + " | Istruttore: " + c.getIstruttorepredefinito());
            }
            
            System.out.println("\n=== LEZIONI DISPONIBILI ===");
            for (Lezione l : lezioni.keySet()) {
                System.out.println("- " + l.getIDLezione() + " | " + l.getGiorno() + " | Ora: " + l.getOrainizio() + " | Posti: " + l.getPostidisponibili());
            }
            
            return lezioni.keySet().stream().findFirst().orElse(null);
        }
        System.out.println("Il tuo abbonamento non include lezioni di gruppo!");
        return null;
    }
    
    public Lezione selezionaCorso(String nomecorso) {
        Corso c = findCorsoByNome(nomecorso);
        if (c == null) {
            System.out.println("Corso non trovato!");
            return null;
        }
        this.corsoCorrente = c;
        
        System.out.println("\n--- Lezioni del corso " + nomecorso + " ---");
        for (Lezione l : c.getLezioni()) {
            System.out.println("- " + l.getIDLezione() + " | " + l.getGiorno() + " | Ora: " + l.getOrainizio());
        }
        return c.getLezioni().isEmpty() ? null : c.getLezioni().get(0);
    }
    
    public Prenotazione prenotaLezione(Date data, double orainizio) {
        Lezione l = findLezioneByDataOra(data, orainizio);
        
        if (l == null) {
            System.out.println("Lezione non trovata!");
            return null;
        }
        
        if (l.getPostidisponibili() <= 0) {
            System.out.println("Nessun posto disponibile!");
            return null;
        }
        
        if (l.getStatolezione() == StatoLezione.TERMINATA) {
            System.out.println("Lezione gia' terminata!");
            return null;
        }
        
        this.lezioneCorrente = l;
        
        Prenotazione p = new Prenotazione(data, String.valueOf(orainizio), 
                                          StatoPrenotazione.EFFETTUATA, contatoreidPrenotazione);
        
        p.setStatoPrenotazione(p, StatoPrenotazione.EFFETTUATA);
        p.setLezione(l);
        p.setCFIscritto(iscrittoCorrente.getCF());
        
        prenotazioni.put(p, contatoreidPrenotazione);
        contatoreidPrenotazione++;
        
        l.decrementaPostiDisponibili();
        
        return p;
    }
    
    public boolean visualizzaPrenotazioni(String CF) {
        Iscritto i = findIscrittoByCF(CF);
        if (i == null) {
            System.out.println("Iscritto non trovato!");
            return false;
        }
        this.iscrittoCorrente = i;
        
        System.out.println("\n=== PRENOTAZIONI DI " + i.getNomeiscritto() + " " + i.getCognome() + " ===");
        boolean trovate = false;
        for (Prenotazione p : prenotazioni.keySet()) {
            if (p.getCFIscritto() != null && p.getCFIscritto().equals(CF)) {
                trovate = true;
                Lezione l = p.getLezione();
                if (l != null) {
                    System.out.println("- ID: " + p.getIdprenotazione() + " | Ora: " + p.getOraprenotazione() + " | Stato: " + p.getStatoprenotazione());
                }
            }
        }
        if (!trovate) {
            System.out.println("Nessuna prenotazione trovata.");
            return false;
        }
        return true;
    }
    
    public boolean selezionaPrenotazione(int idprenotazione, String oraprenotazione) {
        Prenotazione p = findPrenotazioneById(idprenotazione);
        if (p == null) {
            System.out.println("Prenotazione non trovata!");
            return false;
        }

        if (p.getStatoprenotazione() == StatoPrenotazione.ANNULLATA) {
            System.out.println("Questa prenotazione e' gia' stata annullata! Non puoi selezionarla.");
            return false;
        }

        this.prenotazioneCorrente = p;

        Lezione l = p.getLezione();
        if (l != null) {
            this.lezioneCorrente = l.getLezione();
        }

        System.out.println("Prenotazione selezionata - ID: " + p.getIdprenotazione() + " | Stato: " + p.getStatoprenotazione());
        return true;
    }
    
    public void confermaCancellazione(int idprenotazione) {
        Prenotazione p = findPrenotazioneById(idprenotazione);
        if (p == null) {
            System.out.println("Prenotazione non trovata!");
            return;
        }
        
        Calendar cal = Calendar.getInstance();
        double orattuale = cal.get(Calendar.HOUR_OF_DAY) + (cal.get(Calendar.MINUTE) / 60.0);
        double oraPrenotazione = Double.parseDouble(p.getOraprenotazione());
        
        if (orattuale < oraPrenotazione - 2) {
            p.setStatoPrenotazione(p, StatoPrenotazione.ANNULLATA);
            
            Lezione l = p.getLezione();
            if (l != null) {
                l.incrementaPostiDisponibili(l);
            }
            System.out.println("Prenotazione annullata con successo!");
        } else {
            System.out.println("Impossibile cancellare: mancano meno di 2 ore alla lezione!");
        }
    }
    
    public Corso inserisciNuovoCorso(String nomecorso, String descrizione, String istruttorepredefinito) {
        Corso c = new Corso(nomecorso, descrizione, istruttorepredefinito);
        corsi.put(c, nomecorso);
        this.corsoCorrente = c;
        System.out.println("Corso creato: " + nomecorso);
        return c;
    }
    
    public Lezione inserimentoLezioni(String giorno, double orarioinizio, int postidisponibili,
                                      int maxposti, double durata, StatoLezione statolezione, 
                                      Date data, String istruttore, String IDLezione) {
        boolean disponibilita = true;
        if (sala != null) {
            disponibilita = sala.isDisponibile(data, orarioinizio, durata, "SalaUnica");
        }
        
        if (disponibilita) {
            Lezione l = new Lezione(giorno, orarioinizio, postidisponibili, maxposti,
                                    "SalaUnica", durata, statolezione, data, istruttore, IDLezione);
            
            l.setStatoLezione(StatoLezione.NON_INIZIATA);
            l.attach(calendarioLezioni);
            lezioni.put(l, IDLezione);
            
            if (corsoCorrente != null) {
                corsoCorrente.addLezione(l);
            }
            System.out.println("Lezione creata: " + IDLezione);
            return l;
        }
        System.out.println("Sala non disponibile!");
        return null;
    }
    
    public String getOpzione() { return this.opzione; }
    public void setOpzione(String opzione) { this.opzione = opzione; }
    
    public void visualizzaCalendarioLezioni(int scelta, String valore) {
        System.out.println("\n=== CALENDARIO LEZIONI ===");
        
        boolean trovate = false;
        
        switch (scelta) {
            case 1:
                System.out.println("Filtro per giorno: " + valore);
                for (Lezione l : lezioni.keySet()) {
                    if (l.getByGiorno(valore) != null) {
                        trovate = true;
                        System.out.println("- " + l.getIDLezione() + " | " + l.getGiorno() + " | Ora: " + l.getOrainizio() + " | Istruttore: " + l.getIstruttore() + " | Posti: " + l.getPostidisponibili());
                    }
                }
                break;
            case 2:
                System.out.println("Filtro per corso: " + valore);
                Corso c = findCorsoByNome(valore);
                if (c != null) {
                    for (Lezione l : c.getLezioni()) {
                        trovate = true;
                        System.out.println("- " + l.getIDLezione() + " | " + l.getGiorno() + " | Ora: " + l.getOrainizio() + " | Istruttore: " + l.getIstruttore() + " | Posti: " + l.getPostidisponibili());
                    }
                } else {
                    System.out.println("Corso non trovato!");
                }
                break;
            case 3:
                System.out.println("Filtro per istruttore: " + valore);
                for (Lezione l : lezioni.keySet()) {
                    if (l.getByIstruttore(valore) != null) {
                        trovate = true;
                        System.out.println("- " + l.getIDLezione() + " | " + l.getGiorno() + " | Ora: " + l.getOrainizio() + " | Istruttore: " + l.getIstruttore() + " | Posti: " + l.getPostidisponibili());
                    }
                }
                break;
            default:
                System.out.println("Opzione non valida!");
                return;
        }
        
        if (!trovate && scelta != 2) {
            System.out.println("Nessuna lezione trovata con i criteri specificati.");
        }
    }
    
    public Lezione selezionaLezioneSpecifica(String IDLezione) {
        Lezione l = findLezioneById(IDLezione);
        if (l != null) {
            l = l.getLezioneSpecifica(IDLezione);
            this.lezioneCorrente = l;
            System.out.println("\n--- Dettagli Lezione ---");
            System.out.println("ID: " + l.getIDLezione());
            System.out.println("Giorno: " + l.getGiorno());
            System.out.println("Ora inizio: " + l.getOrainizio());
            System.out.println("Durata: " + l.getDurata() + " ore");
            System.out.println("Istruttore: " + l.getIstruttore());
            System.out.println("Posti disponibili: " + l.getPostidisponibili() + "/" + l.getMaxposti());
            System.out.println("Stato: " + l.getStatolezione());
            return l;
        }
        System.out.println("Lezione non trovata!");
        return null;
    }
    
    public void registraAccesso(String CF) {
        System.out.println("\n=== REGISTRAZIONE ACCESSO ===");
        
        Iscritto i = findIscrittoByCF(CF);
        if (i == null) {
            System.out.println("Iscritto non trovato!");
            return;
        }
        this.iscrittoCorrente = i;
        System.out.println("Iscritto: " + i.getNomeiscritto() + " " + i.getCognome());
        
        Abbonamento a = findAbbonamentoByCF(i.getCF());
        if (a == null) {
            System.out.println("Nessun abbonamento trovato!");
            return;
        }
        
        System.out.println("Abbonamento ID: " + a.getIdabbonamento() + " | Stato: " + a.getStatoabbonamento());
        
        if (a.getStatoabbonamento() != StatoAbbonamento.SCADUTO && 
            a.getStatoabbonamento() != StatoAbbonamento.SOSPESO) {
            registraAbbonamento(i.getCodicetessera());
        } else {
            System.out.println("         ACCESSO NEGATO                  ");
            System.out.println("   Abbonamento: " + a.getStatoabbonamento());
        }
    }
    
    public void registraAbbonamento(int codicetessera) {
        System.out.println("         ACCESSO CONSENTITO              ");
        System.out.println("  Codice Tessera: " + codicetessera);
        System.out.println("  Data/Ora: " + sdfOutput.format(new Date()));
    }
    
    public void visualizzaAbbonamentiInScadenza(int numerogiorni) {
        System.out.println("\n=== ABBONAMENTI IN SCADENZA (entro " + numerogiorni + " giorni) ===");
        
        Date datalimite = calcolaDataLimite(numerogiorni);
        System.out.println("Data limite: " + sdfOutput.format(datalimite));
        
        boolean trovati = false;
        for (Abbonamento a : abbonamenti.keySet()) {
            a.getAbbonamentiInScadenza(datalimite);
            
            if (a.isInScadenza(datalimite)) {
                Iscritto i = findIscrittoByCF(a.getCFIscritto());
                if (i != null) {
                    trovati = true;
                    System.out.println(" ABBONAMENTO ID: " + a.getIdabbonamento());
                    System.out.println(" Scadenza: " + sdfOutput.format(a.getDatafine()));
                    System.out.println(" Iscritto: " + i.getNomeiscritto() + " " + i.getCognome());
                    System.out.println(" Email: " + i.getEmail());
                    System.out.println(" Telefono: " + i.getTelefono());
                }
            }
        }
        
        if (!trovati) {
            System.out.println("Nessun abbonamento in scadenza nei prossimi " + numerogiorni + " giorni.");
        }
    }
    
    public Date calcolaDataLimite(int numerogiorni) {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_MONTH, numerogiorni);
        return cal.getTime();
    }
    
    public void setGestorePalestra(GestorePalestra gestore) { this.gestorePalestra = gestore; }
    public GestorePalestra getGestorePalestra() { return gestorePalestra; }
    public Map<Iscritto, String> getIscritti() { return iscritti; }
    public Map<Abbonamento, Integer> getAbbonamenti() { return abbonamenti; }
    public Map<Prenotazione, Integer> getPrenotazioni() { return prenotazioni; }
    public Map<Corso, String> getCorsi() { return corsi; }
    public Map<Lezione, String> getLezioni() { return lezioni; }
    public Sala getSala() { return sala; }
    public void setSala(Sala sala) { this.sala = sala; }
    public AbbonamentoFactory getAbbonamentoFactory() { return abbonamentoFactory; }
    public void setAbbonamentoFactory(AbbonamentoFactory factory) { this.abbonamentoFactory = factory; }
    public CalendarioLezioni getCalendarioLezioni() { return calendarioLezioni; }
    public void setCalendarioLezioni(CalendarioLezioni calendarioLezioni) { this.calendarioLezioni = calendarioLezioni; }
}