package com.example.lab1.DAO;

import com.example.lab1.models.Dress;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

@Stateless
public class DressDaoImpl implements DressDao {
    @PersistenceContext(unitName = "default")
    private EntityManager em;


    @Override
    public Dress get(Integer id) {
        return em.find(Dress.class, id);
    }

    @Override
    public List<Dress> getAll() {
        TypedQuery<Dress> getAllQuery = em.createQuery("select c from Dress c", Dress.class);
        List<Dress> result = getAllQuery.getResultList();
        return result;
    }

    @Override
    public void save(Dress dress) {
        em.persist(dress);

    }

    @Override
    public void update(Dress dress) {
        em.merge(dress);

    }

    @Override
    public void delete(Dress dress) {
        em.remove(em.contains(dress) ? dress : em.merge(dress));
    }
}
