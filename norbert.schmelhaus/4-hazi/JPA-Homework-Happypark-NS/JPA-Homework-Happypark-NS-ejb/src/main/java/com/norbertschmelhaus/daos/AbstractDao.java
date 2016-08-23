package com.norbertschmelhaus.daos;

import java.io.Serializable;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author norbeee sch.norbeee@gmail.com
 * @param <T>
 */
public abstract class AbstractDao<T extends Serializable> implements Dao<T> {

    @PersistenceContext(unitName = "Happypark_PU")
    protected EntityManager em;
    protected Class<T> clazz;

    public AbstractDao(Class<T> clazz) {
        this.clazz = clazz;
    }

    @Override
    public T create(T entiry) {
        em.persist(entiry);
        return entiry;
    }

    @Override
    public T read(Long id) {
        return em.find(clazz, id);
    }

    @Override
    public T update(T entity) {
        return em.merge(entity);
    }

    @Override
    public T delete(T entity) {
        em.remove(em.contains(entity) ? entity : em.merge(entity));
        return entity;
    }

}
