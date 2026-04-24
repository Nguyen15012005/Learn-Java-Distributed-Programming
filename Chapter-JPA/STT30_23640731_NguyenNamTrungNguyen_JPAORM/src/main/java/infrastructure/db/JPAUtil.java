package infrastructure.db;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class JPAUtil {

    private static final String PERSISTENCE_UNIT = "mariadb-pu";
//    private static final String PERSISTENCE_UNIT = "mssql-pu";

    private static EntityManagerFactory emf;

    public static EntityManagerFactory getEntityManagerFactory() {
        if (emf == null) {
            synchronized (JPAUtil.class) {
                if (emf == null) {
                    try {
                        emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT);
                    } catch (Exception e) {
                        System.err.println("Lỗi khởi tạo EMF: " + e.getMessage());
                        throw new RuntimeException(e);
                    }
                }
            }
        }
        return emf;
    }

    public static EntityManager getEntityManager() {
        return getEntityManagerFactory().createEntityManager();
    }

    public static void close() {
        if (emf != null && emf.isOpen()) {
            emf.close();
        }
    }
}