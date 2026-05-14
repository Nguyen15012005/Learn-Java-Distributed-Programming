package infrastructure.config;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class JPAUtil {
    public static final EntityManagerFactory emf;

    static {
        try {
            emf = Persistence.createEntityManagerFactory("mariadb-pu");
        }
        catch (Exception e){
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    public static EntityManager getEntityManager(){
        return emf.createEntityManager();
    }

    public void close(){
        if ((emf.isOpen())) emf.close();
    }
}
