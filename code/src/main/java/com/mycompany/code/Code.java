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
    private static GestorePalestra gestorePalestra; // NUOVO: riferimento diretto per UC9
    private static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    private static SimpleDateFormat sdfOutput = new SimpleDateFormat("EEEE dd MMMM yyyy HH:mm:ss", java.util.Locale.ITALIAN);

    public static void main(String[] args) {
        try {
            System.setOut(new PrintStream(System.out, true, "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            System.out.println("Encoding UTF-8 non supportato");
        }
        
        gymMaster = new GymMaster();
        
        gestorePalestra = new GestorePalestra("Samuele", "Cavallaro");
        gymMaster.setGestorePalestra(gestorePalestra);
        
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
            System.out.println("1. Gestisci Tipi Abbonamento");
            System.out.println("0. Torna al menu principale");
            System.out.print("Inserisci il numero corrispondente all'azione desiderata: ");

            try {
                scelta = scanner.nextInt();

                switch (scelta) {
                    case 1:
                        gestisciTipiAbbonamento(scanner);
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
    
    private static void gestisciAddettoSegreteria(Scanner scanner) {
        int scelta;
        do {
            System.out.println("1. Gestisci Iscritti");
            System.out.println("2. Gestisci Abbonamenti");
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
            System.out.println("  ABBONAMENTO ATTIVATO CON SUCCESSO!");
            System.out.println("  ID: " + abbonamento.getIdabbonamento());
            System.out.println("  Tipo: " + tipo.getNomeabbonamento());
            System.out.println("  Factory utilizzata: " + gymMaster.getAbbonamentoFactory().getClass().getSimpleName());
            System.out.println("  Data inizio: " + sdfOutput.format(abbonamento.getDatainizio()));
            System.out.println("  Data fine: " + sdfOutput.format(abbonamento.getDatafine()));
            System.out.println("  Stato: " + abbonamento.getStatoabbonamento());
        }
    }
    
    public static String formatDate(Date date) {
        return sdfOutput.format(date);
    }
}