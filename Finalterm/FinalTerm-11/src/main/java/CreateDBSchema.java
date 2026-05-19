import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;

public class CreateDBSchema {
    public static void main(String[] args) {
        EntityManager em = Persistence.createEntityManagerFactory("mariadb-pu").createEntityManager();
        em.getMetamodel().getEntities();
    }
}
