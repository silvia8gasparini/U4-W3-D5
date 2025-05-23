package dao;

import entities.Prestito;
import jakarta.persistence.EntityManager;

import java.util.List;

public class PrestitoDao {
    private EntityManager em;

    public PrestitoDao(EntityManager em) {
        this.em = em;
    }

    public Prestito findById(Long id) {
        return em.find(Prestito.class, id);
    }

    public void save(Prestito prestito) {
        em.persist(prestito);
    }

    public List<Prestito> findNonRestituiti() {
        return em.createQuery("select p from Prestito p where p.dataRestituzioneEffettiva is null", Prestito.class)
                .getResultList();
    }

    public List<Prestito> findByNumeroTessera(String numeroTessera) {
        return em.createQuery("select p from Prestito p where p.utente.numeroTessera = :tessera", Prestito.class)
                .setParameter("tessera", numeroTessera)
                .getResultList();
    }

    public void restituisciPrestito(Long idPrestito) {
        Prestito prestito = em.find(Prestito.class, idPrestito);
        if (prestito != null && prestito.getDataRestituzioneEffettiva() == null) {
            prestito.setDataRestituzioneEffettiva(java.time.LocalDate.now());
        }
    }
}
