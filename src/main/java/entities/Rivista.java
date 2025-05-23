package entities;

import enumeration.Periodicita;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

@Entity
public class Rivista extends ElementoCatalogo {

@Enumerated(EnumType.STRING)
    private Periodicita periodicita;

    public Rivista() {
        super();
    }

    public Rivista(String isbn, String titolo, int annoPubblicazione, int numeroPagine, Periodicita periodicita) {
        super(isbn, titolo, annoPubblicazione, numeroPagine);
        this.periodicita = periodicita;
    }

    public Periodicita getPeriodicita() {
        return periodicita;
    }

    public void setPeriodicita(Periodicita periodicita) {
        this.periodicita = periodicita;
    }

    @Override
    public String toString() {
        return "Rivista{" +
                "periodicita=" + periodicita +
                '}';
    }
}
