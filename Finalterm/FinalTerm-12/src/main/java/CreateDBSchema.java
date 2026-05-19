import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;

public class CreateDBSchema {
    public static void main(String[] args) {
        EntityManager entityManager = Persistence.createEntityManagerFactory("mariadb-pu").createEntityManager();
        entityManager.getMetamodel().getEntities();
    }
}
