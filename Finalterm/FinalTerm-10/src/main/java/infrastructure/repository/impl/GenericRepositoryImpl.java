package infrastructure.repository.impl;

import infrastructure.db.JPAUtil;
import infrastructure.repository.GenericRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.util.function.Function;

public abstract class GenericRepositoryImpl<T, ID> implements GenericRepository {
    protected Class <T> entityClass;

    public GenericRepositoryImpl(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    protected GenericRepositoryImpl() {
    }

    protected <R> R doInTransaction(Function<EntityManager, R> function){
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
            if (tx != null && tx.isActive()) tx.rollback();
            throw new IllegalArgumentException(e.getMessage());
        }
        finally {
            em.close();
        }
    }

}
