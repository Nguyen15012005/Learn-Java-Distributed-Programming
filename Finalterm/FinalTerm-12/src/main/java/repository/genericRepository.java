package repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.transaction.Transaction;
import util.JPAUtil;

import java.util.function.Function;

public abstract class genericRepository <T, String>{

    protected Class<T> entityClass;

    public genericRepository() {
        this.entityClass = entityClass;
    }

    public <R> R doInTransaction(Function<EntityManager, R> function){
        EntityManager em = null;
        EntityTransaction tx = null;

        try {
            em = JPAUtil.getEmEntityManager();
            tx = em.getTransaction();
            tx.begin();
            R result = function.apply(em);
            tx.commit();
            return result;
        } catch (Exception e) {
            if (tx != null && tx.isActive()) {
                tx.rollback();
                throw new IllegalArgumentException(e.getMessage());
            }
        }
        finally {
            em.close();
        }
        return null;
    }


}
