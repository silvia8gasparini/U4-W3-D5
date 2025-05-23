package entities;

import jakarta.persistence.Entity;

@Entity
public class Libro extends ElementoCatalogo {

    private String autore;
    private String genere;

    public Libro() {
        super();
    }

    public Libro(String isbn, String titolo, String autore, String genere, int annoPubblicazione, int numeroPagine) {
        super(isbn, titolo, annoPubblicazione, numeroPagine);
        this.autore = autore;
        this.genere = genere;
    }

    public String getAutore() {
        return autore;
    }

    public String getGenere() {
        return genere;
    }

    public void setAutore(String autore) {
        this.autore = autore;
    }

    public void setGenere(String genere) {
        this.genere = genere;
    }

    @Override
    public String toString() {
        return "Libro{" +
                "autore='" + autore + '\'' +
                ", genere='" + genere + '\'' +
                '}';
    }
}
