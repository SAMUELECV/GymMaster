/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.code;

import java.util.Date;
import java.util.Calendar;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;

/**
 *
 * @author carlo
 */
public class Code {
    
    private static GymMaster gymMaster;
    private static GestorePalestra gestorePalestra;
    private static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    private static SimpleDateFormat sdfOutput = new SimpleDateFormat("EEEE dd MMMM yyyy HH:mm:ss", java.util.Locale.ITALIAN);

    public static void main(String[] args) {
        try {
            System.setOut(new PrintStream(System.out, true, "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            System.out.println("Encoding UTF-8 non supportato");
        }
        
        gymMaster = new GymMaster();

        gestorePalestra = new GestorePalestra("Giuseppe", "Verdi");
        gymMaster.setGestorePalestra(gestorePalestra);
        
        Sala sala = new Sala(1);
        gymMaster.setSala(sala);
        
        Scanner scanner = new Scanner(System.in, "UTF-8");

        int scelta;
        do {
            System.out.println("1. Gestore Palestra");
            System.out.println("2. Addetto Segreteria");
            System.out.println("0. Esci");
            System.out.print("Inserisci il numero corrispondente all'azione desiderata: ");

            try {
                scelta = scanner.nextInt();

                switch (scelta) {
                    case 1:
                        gestisciGestorePalestra(scanner);
                        break;
                    case 2:
                        gestisciAddettoSegreteria(scanner);
                        break;
                    case 0:
                        System.out.println("Uscita dal programma.");
                        break;
                    default:
                        System.out.println("Scelta non valida. Riprova.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Input non valido. Inserisci un numero intero.");
                scanner.next();
                scelta = -1;
            } catch (Exception e) {
                System.out.println("Si e' verificato un errore durante l'esecuzione: " + e.getMessage());
                scelta = -1;
            }

        } while (scelta != 0);

        scanner.close();
    }
    
    private static void gestisciGestorePalestra(Scanner scanner) {
        int scelta;
        do {
            System.out.println("1. Inserisci nuovo corso");
            System.out.println("2. Inserisci lezione");
            System.out.println("3. Gestisci Tipi Abbonamento");
            System.out.println("4. Visualizza Calendario Lezioni");
            System.out.println("0. Torna al menu principale");
            System.out.print("Inserisci il numero corrispondente all'azione desiderata: ");

            try {
                scelta = scanner.nextInt();

                switch (scelta) {
                    case 1:
                        inserisciNuovoCorso(scanner);
                        break;
                    case 2:
                        inserimentoLezioni(scanner);
                        break;
                    case 3:
                        gestisciTipiAbbonamento(scanner);
                        break;
                    case 4:
                        visualizzaCalendarioLezioni(scanner);
                        break;
                    case 0:
                        System.out.println("Uscita dal menu Gestore Palestra.");
                        break;
                    default:
                        System.out.println("Scelta non valida. Riprova.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Input non valido. Inserisci un numero intero.");
                scanner.next();
                scelta = -1;
            } catch (Exception e) {
                System.out.println("Si e' verificato un errore durante l'esecuzione: " + e.getMessage());
                scelta = -1;
            }

        } while (scelta != 0);
    }

    private static void inserisciNuovoCorso(Scanner scanner) {
        scanner.nextLine();
        System.out.print("Inserisci il nome del corso: ");
        String nomecorso = scanner.nextLine();
        System.out.print("Inserisci la descrizione del corso: ");
        String descrizione = scanner.nextLine();
        System.out.print("Inserisci il nome dell'istruttore predefinito: ");
        String istruttore = scanner.nextLine();

        Corso corso = gymMaster.inserisciNuovoCorso(nomecorso, descrizione, istruttore);
        if (corso != null) {
            System.out.println("Corso inserito con successo.");
        }
    }

    private static void inserimentoLezioni(Scanner scanner) {
        scanner.nextLine();
        System.out.print("Inserisci il giorno della lezione (es. Lunedi): ");
        String giorno = scanner.nextLine();
        System.out.print("Inserisci l'orario di inizio (es. 10.0): ");
        double orainizio = scanner.nextDouble();
        System.out.print("Inserisci i posti disponibili: ");
        int postidisponibili = scanner.nextInt();
        System.out.print("Inserisci il numero massimo di posti: ");
        int maxposti = scanner.nextInt();
        System.out.print("Inserisci la durata in ore (es. 1.5): ");
        double durata = scanner.nextDouble();
        scanner.nextLine();
        System.out.print("Inserisci il nome dell'istruttore: ");
        String istruttore = scanner.nextLine();
        System.out.print("Inserisci l'ID della lezione (es. YOG001): ");
        String idLezione = scanner.nextLine();

        Lezione lezione = gymMaster.inserimentoLezioni(giorno, orainizio, postidisponibili, maxposti,
                                                       durata, StatoLezione.NON_INIZIATA, 
                                                       new Date(), istruttore, idLezione);
        if (lezione != null) {
            System.out.println("Lezione inserita con successo.");
        }
    }
    
    private static void gestisciTipiAbbonamento(Scanner scanner) {
        int scelta;
        do {
            System.out.println("1. Crea nuovo tipo abbonamento");
            System.out.println("2. Modifica tipo abbonamento");
            System.out.println("3. Visualizza tipi abbonamento");
            System.out.println("0. Torna indietro");
            System.out.print("Inserisci il numero corrispondente all'azione desiderata: ");

            try {
                scelta = scanner.nextInt();

                switch (scelta) {
                    case 1:
                        creaNuovoTipoAbbonamento(scanner);
                        break;
                    case 2:
                        modificaTipoAbbonamento(scanner);
                        break;
                    case 3:
                        gestorePalestra.mostraTipologieAbbonamento();
                        break;
                    case 0:
                        System.out.println("Uscita dal menu Gestione Tipi Abbonamento.");
                        break;
                    default:
                        System.out.println("Scelta non valida. Riprova.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Input non valido. Inserisci un numero intero.");
                scanner.next();
                scelta = -1;
            } catch (Exception e) {
                System.out.println("Si e' verificato un errore durante l'esecuzione: " + e.getMessage());
                scelta = -1;
            }

        } while (scelta != 0);
    }

    private static void creaNuovoTipoAbbonamento(Scanner scanner) {
        scanner.nextLine();
        System.out.print("Inserisci il nome dell'abbonamento (MENSILE / TRIMESTRALE / ANNUALE): ");
        String nome = scanner.nextLine();
        System.out.print("Inserisci la descrizione: ");
        String descrizione = scanner.nextLine();
        System.out.print("Inserisci la durata in giorni: ");
        int durataGiorni = scanner.nextInt();
        System.out.print("Inserisci il prezzo: ");
        double prezzo = scanner.nextDouble();
        System.out.print("Include lezioni di gruppo? (true/false): ");
        boolean lezioni = scanner.nextBoolean();

        Calendar cal = Calendar.getInstance();
        cal.set(1970, 0, durataGiorni);
        Date durata = cal.getTime();

        gestorePalestra.creaNuovoTipoAbbonamento(nome, descrizione, durata, prezzo, lezioni);
    }

        private static void modificaTipoAbbonamento(Scanner scanner) {
        scanner.nextLine();
        System.out.print("Inserisci il nome dell'abbonamento da modificare: ");
        String nome = scanner.nextLine();
        
        if (!gymMaster.esisteTipoAbbonamento(nome)) {
            System.out.println("Tipo abbonamento non trovato!");
            return;
        }
        
        System.out.print("Inserisci la nuova descrizione: ");
        String descrizione = scanner.nextLine();
        System.out.print("Inserisci la nuova durata in giorni: ");
        int durataGiorni = scanner.nextInt();
        System.out.print("Inserisci il nuovo prezzo: ");
        double prezzo = scanner.nextDouble();
        System.out.print("Include lezioni di gruppo? (true/false): ");
        boolean lezioni = scanner.nextBoolean();

        Calendar cal = Calendar.getInstance();
        cal.set(1970, 0, durataGiorni);
        Date durata = cal.getTime();
        
        gymMaster.modificaTipoAbbonamento(nome, descrizione, durata, prezzo, lezioni);
    }
    
    private static void visualizzaCalendarioLezioni(Scanner scanner) {
        System.out.println("Come vuoi visualizzare il calendario?");
        System.out.println("1 - Filtra per GIORNO");
        System.out.println("2 - Filtra per CORSO");
        System.out.println("3 - Filtra per ISTRUTTORE");
        System.out.print("Inserisci la tua scelta (1/2/3): ");

        int scelta = scanner.nextInt();
        scanner.nextLine();

        String valore = "";

        switch (scelta) {
            case 1:
                System.out.print("Inserisci il giorno (es. Lunedi, Martedi, ...): ");
                valore = scanner.nextLine();
                break;
            case 2:
                System.out.print("Inserisci il nome del corso (es. Yoga, Spinning, ...): ");
                valore = scanner.nextLine();
                break;
            case 3:
                System.out.print("Inserisci il nome dell'istruttore (es. Marco Zen, ...): ");
                valore = scanner.nextLine();
                break;
            default:
                System.out.println("Scelta non valida!");
                return;
        }

        gymMaster.visualizzaCalendarioLezioni(scelta, valore);

        System.out.print("\nVuoi selezionare una lezione specifica? (S/N): ");
        String risposta = scanner.nextLine();
        if (risposta.equalsIgnoreCase("S")) {
            System.out.print("Inserisci l'ID della lezione (es. YOG001): ");
            String idLezione = scanner.nextLine();
            gymMaster.selezionaLezioneSpecifica(idLezione);
        }
    }
    
    private static void gestisciAddettoSegreteria(Scanner scanner) {
        int scelta;
        do {
            System.out.println("1. Gestisci Iscritti");
            System.out.println("2. Gestisci Abbonamenti");
            System.out.println("3. Gestisci Prenotazioni");
            System.out.println("0. Torna al menu principale");
            System.out.print("Inserisci il numero corrispondente all'azione desiderata: ");

            try {
                scelta = scanner.nextInt();

                switch (scelta) {
                    case 1:
                        gestisciIscritti(scanner);
                        break;
                    case 2:
                        gestisciAbbonamenti(scanner);
                        break;
                    case 3:
                        gestisciPrenotazioni(scanner);
                        break;
                    case 0:
                        System.out.println("Uscita dal menu Addetto Segreteria.");
                        break;
                    default:
                        System.out.println("Scelta non valida. Riprova.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Input non valido. Inserisci un numero intero.");
                scanner.next();
                scelta = -1;
            } catch (Exception e) {
                System.out.println("Si e' verificato un errore durante l'esecuzione: " + e.getMessage());
                scelta = -1;
            }

        } while (scelta != 0);
    }
    
    private static void gestisciIscritti(Scanner scanner) {
        int scelta;
        do {
            System.out.println("1. Registra nuovo iscritto");
            System.out.println("0. Torna indietro");
            System.out.print("Inserisci il numero corrispondente all'azione desiderata: ");

            try {
                scelta = scanner.nextInt();

                switch (scelta) {
                    case 1:
                        registraNuovoIscritto(scanner);
                        break;
                    case 0:
                        System.out.println("Uscita dal menu Gestione Iscritti.");
                        break;
                    default:
                        System.out.println("Scelta non valida. Riprova.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Input non valido. Inserisci un numero intero.");
                scanner.next();
                scelta = -1;
            } catch (Exception e) {
                System.out.println("Si e' verificato un errore durante l'esecuzione: " + e.getMessage());
                scelta = -1;
            }

        } while (scelta != 0);
    }

    private static void registraNuovoIscritto(Scanner scanner) {
        try {
            scanner.nextLine();
            System.out.print("Inserisci il nome dell'iscritto: ");
            String nome = scanner.nextLine();
            System.out.print("Inserisci il cognome dell'iscritto: ");
            String cognome = scanner.nextLine();
            System.out.print("Inserisci la data di nascita (dd/MM/yyyy): ");
            Date dataNascita = sdf.parse(scanner.nextLine());
            System.out.print("Inserisci il codice fiscale (CF): ");
            String CF = scanner.nextLine();
            System.out.print("Inserisci il numero di telefono: ");
            String telefono = scanner.nextLine();
            System.out.print("Inserisci l'email: ");
            String email = scanner.nextLine();
            System.out.print("Inserisci l'indirizzo: ");
            String indirizzo = scanner.nextLine();

            Iscritto iscritto = gymMaster.registraNuovoIscritto(nome, cognome, dataNascita, CF, telefono, email, indirizzo);
            
            if (iscritto != null) {
                int codiceTessera = gymMaster.generaCodiceTessera();
                gymMaster.confermaInserimento(nome, cognome, dataNascita, CF, telefono, email, indirizzo, codiceTessera);
            }
        } catch (ParseException e) {
            System.out.println("Formato data non valido. Utilizza il formato dd/MM/yyyy.");
        }
    }
    
    private static void gestisciAbbonamenti(Scanner scanner) {
        int scelta;
        do {
            System.out.println("1. Attiva abbonamento");
            System.out.println("0. Torna indietro");
            System.out.print("Inserisci il numero corrispondente all'azione desiderata: ");

            try {
                scelta = scanner.nextInt();

                switch (scelta) {
                    case 1:
                        attivaAbbonamento(scanner);
                        break;
                    case 0:
                        System.out.println("Uscita dal menu Gestione Abbonamenti.");
                        break;
                    default:
                        System.out.println("Scelta non valida. Riprova.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Input non valido. Inserisci un numero intero.");
                scanner.next();
                scelta = -1;
            } catch (Exception e) {
                System.out.println("Si e' verificato un errore durante l'esecuzione: " + e.getMessage());
                scelta = -1;
            }

        } while (scelta != 0);
    }

    private static void attivaAbbonamento(Scanner scanner) {
        scanner.nextLine();
        System.out.print("Inserisci il codice fiscale (CF) dell'iscritto: ");
        String CF = scanner.nextLine();

        Iscritto iscritto = gymMaster.attivaAbbonamento(CF);
        if (iscritto == null) {
            return;
        }

        int numTipi = gymMaster.mostraTipologieAbbonamento();
        if (numTipi == 0) {
            System.out.println("Nessun tipo di abbonamento disponibile. Il Gestore Palestra deve crearne uno prima.");
            return;
        }

        System.out.println("\nTipologie valide per il Factory Method: MENSILE, TRIMESTRALE, ANNUALE");
        System.out.print("Inserisci il nome della tipologia di abbonamento da selezionare: ");
        String nomeAbbonamento = scanner.nextLine();

        TipoAbbonamento tipo = gymMaster.selezionaTipologieAbbonamento(nomeAbbonamento);
        if (tipo == null) {
            return;
        }
        
        if (gymMaster.getAbbonamentoFactory() == null) {
            System.out.println("Errore: la tipologia '" + nomeAbbonamento 
                + "' non corrisponde a nessuna Factory (MENSILE/TRIMESTRALE/ANNUALE).");
            System.out.println("L'abbonamento non puo' essere creato con il Factory Method.");
            return;
        }

        Abbonamento abbonamento = gymMaster.confermaAttivazione(CF);
        if (abbonamento != null) {
            System.out.println("  ABBONAMENTO ATTIVATO CON SUCCESSO!");;
            System.out.println("  ID: " + abbonamento.getIdabbonamento());
            System.out.println("  Tipo: " + tipo.getNomeabbonamento());
            System.out.println("  Factory utilizzata: " + gymMaster.getAbbonamentoFactory().getClass().getSimpleName());
            System.out.println("  Data inizio: " + sdfOutput.format(abbonamento.getDatainizio()));
            System.out.println("  Data fine: " + sdfOutput.format(abbonamento.getDatafine()));
            System.out.println("  Stato: " + abbonamento.getStatoabbonamento());
        }
    }
    
    private static void gestisciPrenotazioni(Scanner scanner) {
        int scelta;
        do {
            System.out.println("1. Prenota lezione");
            System.out.println("2. Cancella prenotazione");
            System.out.println("0. Torna indietro");
            System.out.print("Inserisci il numero corrispondente all'azione desiderata: ");

            try {
                scelta = scanner.nextInt();

                switch (scelta) {
                    case 1:
                        prenotaLezione(scanner);
                        break;
                    case 2:
                        cancellaPrenotazione(scanner);
                        break;
                    case 0:
                        System.out.println("Uscita dal menu Gestione Prenotazioni.");
                        break;
                    default:
                        System.out.println("Scelta non valida. Riprova.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Input non valido. Inserisci un numero intero.");
                scanner.next();
                scelta = -1;
            } catch (Exception e) {
                System.out.println("Si e' verificato un errore durante l'esecuzione: " + e.getMessage());
                scelta = -1;
            }

        } while (scelta != 0);
    }

    private static void prenotaLezione(Scanner scanner) {
        try {
            scanner.nextLine();
            System.out.print("Inserisci il codice fiscale (CF) dell'iscritto: ");
            String CF = scanner.nextLine();

            Lezione lezione = gymMaster.visualizzaLezioniECorsi(CF);
            if (lezione == null) {
                return;
            }

            Lezione lezioneSelezionata = null;
            while (lezioneSelezionata == null) {
                System.out.print("Inserisci il nome del corso: ");
                String nomecorso = scanner.nextLine();
                lezioneSelezionata = gymMaster.selezionaCorso(nomecorso);
                
                if (lezioneSelezionata == null) {
                    System.out.print("Vuoi riprovare? (S/N): ");
                    String risposta = scanner.nextLine();
                    if (!risposta.equalsIgnoreCase("S")) {
                        return;
                    }
                }
            }

            System.out.print("Inserisci la data della lezione (dd/MM/yyyy): ");
            Date data = sdf.parse(scanner.nextLine());
            System.out.print("Inserisci l'ora di inizio (es. 10.0): ");
            double ora = scanner.nextDouble();

            Prenotazione prenotazione = gymMaster.prenotaLezione(data, ora);
            if (prenotazione != null) {
                System.out.println("Prenotazione effettuata con successo. ID: " + prenotazione.getIdprenotazione());
            }
        } catch (ParseException e) {
            System.out.println("Formato data non valido. Utilizza il formato dd/MM/yyyy.");
        }
    }

    private static void cancellaPrenotazione(Scanner scanner) {
        scanner.nextLine();
        System.out.print("Inserisci il codice fiscale (CF) dell'iscritto: ");
        String CF = scanner.nextLine();

        boolean haPrenotazioni = gymMaster.visualizzaPrenotazioni(CF);
        
        if (!haPrenotazioni) {
            return;
        }

        System.out.print("Inserisci l'ID della prenotazione da cancellare: ");
        int idPrenotazione = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Inserisci l'ora della prenotazione: ");
        String ora = scanner.nextLine();

        boolean trovata = gymMaster.selezionaPrenotazione(idPrenotazione, ora);
        
        if (!trovata) {
            return;
        }

        System.out.print("Confermi la cancellazione? (S/N): ");
        String conferma = scanner.nextLine();
        if (conferma.equalsIgnoreCase("S")) {
            gymMaster.confermaCancellazione(idPrenotazione);
        } else {
            System.out.println("Cancellazione annullata.");
        }
    }
    
    public static String formatDate(Date date) {
        return sdfOutput.format(date);
    }
}