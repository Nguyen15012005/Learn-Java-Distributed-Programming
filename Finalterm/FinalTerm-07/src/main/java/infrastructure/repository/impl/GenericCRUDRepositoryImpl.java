package infrastructure.repository.impl;

import infrastructure.config.JPAUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.swing.text.html.parser.Entity;
import java.io.Serializable;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

@NoArgsConstructor
@AllArgsConstructor
@Data
public abstract class GenericCRUDRepositoryImpl <T, ID extends Serializable> implements infrastructure.repository.GenericCRUDRepository<T, ID> {

    protected Class<T> entityClass;

//    Dùng khi method cần return về dữ liệu
    protected <R> R doInTransaction(Function<EntityManager, R> function){
        EntityTransaction tx = null;
        try (EntityManager em = JPAUtil.getEntityManager()){
            tx = em.getTransaction();
            tx.begin();
            R result = function.apply(em);
            tx.commit();
            return result;
        } catch (Exception e) {
            if (tx != null && tx.isActive()){
                tx.rollback();
            }
            System.out.println("Lỗi Khi tạo" + e.getMessage());
            return null;
        }
    }

//    Dùng khi method không cần return về dữ liệu
    protected <R> R doInTransaction1(Consumer<EntityManager> consumer){
        EntityTransaction tx = null;
        try (EntityManager em = JPAUtil.getEntityManager()){
            tx = em.getTransaction();
            tx.begin();
            consumer.accept(em);
            tx.commit();
        } catch (Exception e) {
            if (tx != null && tx.isActive()){
                tx.rollback();
            }
            System.out.println("Lỗi Khi tạo" + e.getMessage());
        }
        return null;
    }

    @Override
    public T create(T entity){
        return doInTransaction(em -> {
            em.persist(entity);
            return entity;
        });
    }

    @Override
    public T update(T entity){
        return doInTransaction(em -> {
            T updateEntity = em.merge(entity);
            return updateEntity;
        });
    }

    @Override
    public boolean delete(ID id){
        return doInTransaction(em -> {
            T entity = em.find(entityClass, id);
            if (entity != null){
                em.remove(entity);
                return true;
            }
            return false;
        });
    }

    @Override
    public T findById(ID id){
        return doInTransaction(em -> em.find(entityClass, id));
    }

    @Override
    public List<T> loadAll(){
        String query = "select e from " + entityClass.getSimpleName() + " e";
        return doInTransaction(em -> em.createQuery(query,entityClass).getResultList());
    }

}
