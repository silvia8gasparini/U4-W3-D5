package entities;

import dao.ElementoCatalogoDao;
import dao.PrestitoDao;
import dao.UtenteDao;
import jakarta.persistence.EntityManager;

import java.time.LocalDate;
import java.util.List;

public class Archivio {

    private final ElementoCatalogoDao catalogoDao;
    private final UtenteDao utenteDao;
    private final PrestitoDao prestitoDao;

    public Archivio(EntityManager em) {
        this.catalogoDao = new ElementoCatalogoDao(em);
        this.utenteDao = new UtenteDao(em);
        this.prestitoDao = new PrestitoDao(em);
    }

    public void aggiungiElemento(ElementoCatalogo e) {
        catalogoDao.save(e);
    }

    public void rimuoviElemento(String isbn) {
        catalogoDao.deleteByIsbn(isbn);
    }

    public ElementoCatalogo cercaPerIsbn(String isbn) {
        return catalogoDao.findByIsbn(isbn);
    }

    public List<ElementoCatalogo> cercaPerAnno(int anno) {
        return catalogoDao.findByAnno(anno);
    }

    public List<ElementoCatalogo> cercaPerTitolo(String titolo) {
        return catalogoDao.findByTitoloLike(titolo);
    }

    public List<Libro> cercaLibriPerAutore(String autore) {
        return catalogoDao.findLibriByAutore(autore);
    }

    public void registraUtente(Utente utente) {
        utenteDao.save(utente);
    }

    public Utente cercaUtentePerTessera(String numeroTessera) {
        return utenteDao.findByNumeroTessera(numeroTessera);
    }

    public void effettuaPrestito(Utente utente, ElementoCatalogo elemento, LocalDate dataInizio) {
        Prestito prestito = new Prestito(utente, elemento, dataInizio, dataInizio.plusDays(30),null);
        prestitoDao.save(prestito);
    }

    public void restituisciPrestito(Long idPrestito, LocalDate dataRestituzione) {
        Prestito prestito = prestitoDao.findById(idPrestito);
        if (prestito != null && prestito.getDataRestituzioneEffettiva() == null) {
            prestito.setDataRestituzioneEffettiva(dataRestituzione);
        }
    }

    public List<Prestito> prestitiNonRestituiti() {
        return prestitoDao.findNonRestituiti();
    }

    public List<Prestito> prestitiPerTessera(String numeroTessera) {
        return prestitoDao.findByNumeroTessera(numeroTessera);
    }
}
