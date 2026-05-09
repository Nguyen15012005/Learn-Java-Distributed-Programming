import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;

/**
 * @author TrungNguyen
 * @created 4/22/2026
 * @description
 */

public class Main {
    public static void main(String[] args) {
        EntityManager em = Persistence
                .createEntityManagerFactory("mariadb")
                .createEntityManager();

        em.getMetamodel().getEntities();
    }
}
