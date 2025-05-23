package entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;

    @Entity
    @Inheritance(strategy = InheritanceType.JOINED)
    public abstract class ElementoCatalogo {

        @Id
        private String isbn;
        private String titolo;
        private int annoPubblicazione;
        private int numeroPagine;

        public ElementoCatalogo() {
        }

        public ElementoCatalogo(String isbn, String titolo, int annoPubblicazione, int numeroPagine) {
            this.isbn = isbn;
            this.titolo = titolo;
            this.annoPubblicazione = annoPubblicazione;
            this.numeroPagine = numeroPagine;
        }

        public String getIsbn() {
            return isbn;
        }

        public String getTitolo() {
            return titolo;
        }

        public int getAnnoPubblicazione() {
            return annoPubblicazione;
        }

        public int getNumeroPagine() {
            return numeroPagine;
        }

        public void setIsbn(String isbn) {
            this.isbn = isbn;
        }

        public void setTitolo(String titolo) {
            this.titolo = titolo;
        }

        public void setAnnoPubblicazione(int annoPubblicazione) {
            this.annoPubblicazione = annoPubblicazione;
        }

        public void setNumeroPagine(int numeroPagine) {
            this.numeroPagine = numeroPagine;
        }

        @Override
        public String toString() {
            return "ElementoCatalogo{" +
                    "isbn='" + isbn + '\'' +
                    ", titolo='" + titolo + '\'' +
                    ", annoPubblicazione=" + annoPubblicazione +
                    ", numeroPagine=" + numeroPagine +
                    '}';
        }
    }

