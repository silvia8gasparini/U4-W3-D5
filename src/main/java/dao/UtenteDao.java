package dao;

import entities.Utente;
import jakarta.persistence.EntityManager;

public class UtenteDao {
    private EntityManager em;

    public UtenteDao(EntityManager em) {
        this.em = em;
    }

    public void save(Utente utente) {
        em.persist(utente);
    }

    public Utente findByNumeroTessera(String numeroTessera) {
        return em.createQuery("select u from Utente u where u.numeroTessera = :t", Utente.class)
                .setParameter("t", numeroTessera)
                .getSingleResult();
    }
}
