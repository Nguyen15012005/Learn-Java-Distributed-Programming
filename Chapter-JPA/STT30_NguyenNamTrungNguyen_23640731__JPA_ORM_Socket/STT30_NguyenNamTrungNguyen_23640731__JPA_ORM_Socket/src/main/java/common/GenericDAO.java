package common;

/**
 * @author TrungNguyen
 * @created 4/12/2026
 * @description
 */
import java.util.List;

public interface GenericDAO<T, String> {

    T create(T t);

    T update(T t);

    boolean delete(String id);

    T findById(String id);

    List<T> loadAll();
}