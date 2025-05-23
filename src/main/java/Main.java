import entities.*;
import entities.Archivio;
import enumeration.Periodicita;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("postgres");
        EntityManager em = emf.createEntityManager();
        Archivio archivio = new Archivio(em);
        Scanner sc = new Scanner(System.in);

        boolean running = true;

        while (running) {
            System.out.println("MENU");
            System.out.println("1. Inserisci libro");
            System.out.println("2. Inserisci rivista");
            System.out.println("3. Inserisci utente");
            System.out.println("4. Registra prestito");
            System.out.println("5. Restituisci prestito");
            System.out.println("6. Ricerca per ISBN");
            System.out.println("7. Ricerca per anno");
            System.out.println("8. Ricerca per autore");
            System.out.println("9. Ricerca per titolo");
            System.out.println("10. Prestiti per tessera");
            System.out.println("11. Prestiti non restituiti");
            System.out.println("0. Esci");
            System.out.print("Scelta: ");

            int scelta = Integer.parseInt(sc.nextLine());

            try {
                em.getTransaction().begin();

                switch (scelta) {
                    case 1 -> {
                        System.out.print("ISBN: ");
                        String isbn = sc.nextLine();
                        System.out.print("Titolo: ");
                        String titolo = sc.nextLine();
                        System.out.print("Autore: ");
                        String autore = sc.nextLine();
                        System.out.print("Genere: ");
                        String genere = sc.nextLine();
                        System.out.print("Anno pubblicazione: ");
                        int anno = Integer.parseInt(sc.nextLine());
                        System.out.print("Numero pagine: ");
                        int pagine = Integer.parseInt(sc.nextLine());

                        Libro libro = new Libro(isbn, titolo, autore, genere, anno, pagine);
                        archivio.aggiungiElemento(libro);
                    }
                    case 2 -> {
                        System.out.print("ISBN: ");
                        String isbn = sc.nextLine();
                        System.out.print("Titolo: ");
                        String titolo = sc.nextLine();
                        System.out.print("Periodicità (SETTIMANALE/MENSILE/SEMESTRALE): ");
                        Periodicita periodicita = Periodicita.valueOf(sc.nextLine().toUpperCase());
                        System.out.print("Anno pubblicazione: ");
                        int anno = Integer.parseInt(sc.nextLine());
                        System.out.print("Numero pagine: ");
                        int pagine = Integer.parseInt(sc.nextLine());

                        Rivista rivista = new Rivista(isbn, titolo, anno, pagine, periodicita);
                        archivio.aggiungiElemento(rivista);
                    }
                    case 3 -> {
                        System.out.print("Nome: ");
                        String nome = sc.nextLine();
                        System.out.print("Cognome: ");
                        String cognome = sc.nextLine();
                        System.out.print("Data di nascita (YYYY-MM-DD): ");
                        LocalDate nascita = LocalDate.parse(sc.nextLine());
                        System.out.print("Numero tessera: ");
                        String tessera = sc.nextLine();

                        Utente utente = new Utente(nome, cognome, nascita, tessera);
                        archivio.registraUtente(utente);
                    }
                    case 4 -> {
                        System.out.print("Numero tessera utente: ");
                        String tessera = sc.nextLine();
                        Utente u = archivio.cercaUtentePerTessera(tessera);
                        System.out.print("ISBN elemento da prestare: ");
                        String isbn = sc.nextLine();
                        ElementoCatalogo ec = archivio.cercaPerIsbn(isbn);
                        System.out.print("Data inizio prestito (YYYY-MM-DD): ");
                        LocalDate inizio = LocalDate.parse(sc.nextLine());
                        archivio.effettuaPrestito(u, ec, inizio);
                    }
                    case 5 -> {
                        System.out.print("ID prestito da restituire: ");
                        Long id = Long.parseLong(sc.nextLine());
                        System.out.print("Data restituzione (YYYY-MM-DD): ");
                        LocalDate data = LocalDate.parse(sc.nextLine());
                        archivio.restituisciPrestito(id, data);
                    }
                    case 6 -> {
                        System.out.print("ISBN: ");
                        String isbn = sc.nextLine();
                        System.out.println(archivio.cercaPerIsbn(isbn));
                    }
                    case 7 -> {
                        System.out.print("Anno pubblicazione: ");
                        int anno = Integer.parseInt(sc.nextLine());
                        archivio.cercaPerAnno(anno).forEach(System.out::println);
                    }
                    case 8 -> {
                        System.out.print("Autore: ");
                        String autore = sc.nextLine();
                        archivio.cercaLibriPerAutore(autore).forEach(System.out::println);
                    }
                    case 9 -> {
                        System.out.print("Titolo/parola chiave: ");
                        String titolo = sc.nextLine();
                        archivio.cercaPerTitolo(titolo).forEach(System.out::println);
                    }
                    case 10 -> {
                        System.out.print("Numero tessera: ");
                        String tessera = sc.nextLine();
                        archivio.prestitiPerTessera(tessera).forEach(System.out::println);
                    }
                    case 11 -> archivio.prestitiNonRestituiti().forEach(System.out::println);
                    case 0 -> running = false;
                    default -> System.out.println("Scelta non valida.");
                }

                em.getTransaction().commit();

            } catch (Exception e) {
                em.getTransaction().rollback();
                System.out.println("Errore: " + e.getMessage());
            }
        }

        sc.close();
        em.close();
        emf.close();
        System.out.println("Chiusura programma.");
    }
}
