package com.example.lab1.DAO;

import com.example.lab1.models.Manager;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;


@Stateless
public class ManagerDaoImpl implements ManagerDao {

    @PersistenceContext(unitName = "default")
    private EntityManager em;


    @Override
    public Manager get(Integer id) {
        return em.find(Manager.class, id);
    }

    @Override
    public List<Manager> getAll() {
        TypedQuery<Manager> getAllQuery = em.createQuery("select c from Manager c", Manager.class);
        List<Manager> result = getAllQuery.getResultList();
        return result;
    }

    @Override
    public void save(Manager manager) {
        em.persist(manager);
    }

    @Override
    public void update(Manager manager) {
        em.merge(manager);

    }

    @Override
    public void delete(Manager manager) {
        em.remove(em.contains(manager) ? manager : em.merge(manager));
    }
}
