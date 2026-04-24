package common;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import util.JPAUtil;

import java.util.List;
import java.util.function.Function;

/**
 * @author TrungNguyen
 * @created 4/12/2026
 * @description
 */
public abstract class AbstractGenericDAO <T, String> implements GenericDAO<T, String>{
    protected Class<T> entityClass;

    public AbstractGenericDAO(Class<T> entityClass) {
        this.entityClass = entityClass;
    }
    protected <R> R doInTransaction(Function<EntityManager, R> function) {
        EntityManager em = null;
        EntityTransaction tx = null;

        try {
            em = JPAUtil.getEntityManager();
            tx = em.getTransaction();
            tx.begin();
            R result = function.apply(em);
            tx.commit();
            return result;
        } catch (Exception e) {
            if(tx != null && tx.isActive())
                tx.rollback();
            throw new RuntimeException(e);
        } finally {
            em.close();
        }
    }

    @Override
    public T create(T t) {
        return doInTransaction(em -> {
            em.persist(t);
            return t;
        });
    }

    @Override
    public T update(T t) {
        return doInTransaction(em -> em.merge(t));
    }

    @Override
    public boolean delete(String id) {
        return doInTransaction(em -> {
            T entity = em.find(entityClass, id);
            if (entity != null) {
                em.remove(entity);
                return true;
            }
            return false;
        });
    }

    @Override
    public T findById(String id) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return em.find(entityClass, id);
        } finally {
            em.close();
        }
    }


    @Override
    public List<T> loadAll() {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return em.createQuery(
                    "FROM " + entityClass.getSimpleName(), entityClass
            ).getResultList();
        } finally {
            em.close();
        }
    }

}
