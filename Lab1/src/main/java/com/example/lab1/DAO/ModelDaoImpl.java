package com.example.lab1.DAO;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

import com.example.lab1.models.Model;

@Stateless
public class ModelDaoImpl implements ModelDao {


    @PersistenceContext(unitName = "default")
    private EntityManager em;

    @Override
    public Model get(Integer id) {
        return em.find(Model.class, id);
    }

    @Override
    public List<Model> getAll() {
        TypedQuery<Model> getAllQuery = em.createQuery("select distinct c from Model c left join fetch c.manager", Model.class);
        List<Model> result = getAllQuery.getResultList();
        return result;
    }

    @Override
    public void save(Model model) {
        em.persist(model);
    }

    @Override
    public void update(Model model) {
        em.merge(model);
    }

    @Override
    public void delete(Model model) {
        em.remove(em.contains(model) ? model : em.merge(model));
    }
}
