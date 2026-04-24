package infrastructure.OnsiteCourseRepositoryImpl;

import core.repository.GenericRepository;
import infrastructure.db.JPAUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.io.Serializable;
import java.util.function.Consumer;
import java.util.function.Function;

public abstract class AbstractGenericRepositoryImpl <T, ID extends Serializable> implements GenericRepository<T, ID> {

    protected Class <T> entityClass;

    public AbstractGenericRepositoryImpl(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    protected <R> R doInTransaction(Function<EntityManager, R> function){
        EntityTransaction tx = null;
        try(EntityManager em = JPAUtil.getEntityManager()){
            tx = em.getTransaction();
            tx.begin();
            R result = function.apply(em);
            tx.commit();
            return result;
        }
        catch (Exception e){
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }
            System.err.println("Lỗi khi tạo student: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    protected <R> R doInTransaction1(Consumer<EntityManager> consumer){
        EntityTransaction tx = null;
        try(EntityManager em = JPAUtil.getEntityManager()){
            tx = em.getTransaction();
            tx.begin();
            consumer.accept(em);
            tx.commit();
        }
        catch (Exception e){
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }
            System.err.println("Lỗi khi tạo student: " + e.getMessage());
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public T create(T entity) {

        return doInTransaction(em -> {
            em.persist(entity);
            return entity;
        });
    }

    @Override
    public T update(T entity) {
        return doInTransaction(em -> {
            T updatedEntity = em.merge(entity);
            return updatedEntity;
        });

    }

    @Override
    public Boolean delete(ID id) {
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
    public T findById(ID id) {
        return doInTransaction(em -> em.find(entityClass, id));

    }

    @Override
    public java.util.List<T> findAll() {
    String query = "select e from " + entityClass.getSimpleName() + " e";
        return doInTransaction(em -> em.createQuery(query,entityClass).getResultList());
    }
}
