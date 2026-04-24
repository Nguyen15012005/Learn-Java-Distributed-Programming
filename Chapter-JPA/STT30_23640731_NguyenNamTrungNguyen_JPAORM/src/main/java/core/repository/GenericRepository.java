package core.repository;

import java.io.Serializable;
import java.util.List;

public interface GenericRepository <T, ID extends Serializable> {
    T create(T entity);
    T update(T entity);
    Boolean delete(ID id);
    T findById(ID id);
    List<T> findAll();
}
