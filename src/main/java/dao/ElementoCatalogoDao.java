package dao;

import entities.ElementoCatalogo;
import entities.Libro;
import jakarta.persistence.EntityManager;

import java.util.List;

public class ElementoCatalogoDao {
    private final EntityManager em;

    public ElementoCatalogoDao(EntityManager em) {
        this.em = em;
    }

    public void save(ElementoCatalogo elemento) {
        em.persist(elemento);
    }

    public ElementoCatalogo findByIsbn(String isbn) {
        return em.find(ElementoCatalogo.class, isbn);
    }

    public List<ElementoCatalogo> findByAnno(int anno) {
        return em.createQuery("select e from ElementoCatalogo e where e.annoPubblicazione = :anno", ElementoCatalogo.class)
                .setParameter("anno", anno)
                .getResultList();
    }

    public List<ElementoCatalogo> findByTitoloLike(String titolo) {
        return em.createQuery("select e from ElementoCatalogo e where lower(e.titolo) like lower(:titolo)", ElementoCatalogo.class)
                .setParameter("titolo", "%" + titolo + "%")
                .getResultList();
    }

    public List<Libro> findLibriByAutore(String autore) {
        return em.createQuery("select l from Libro l where l.autore = :autore", Libro.class)
                .setParameter("autore", autore)
                .getResultList();
    }

    public void deleteByIsbn(String isbn) {
        ElementoCatalogo e = findByIsbn(isbn);
        if (e != null) em.remove(e);
    }
}
