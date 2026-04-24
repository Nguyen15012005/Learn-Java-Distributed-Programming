package iuh.fit.db;

import org.neo4j.ogm.config.Configuration;
import org.neo4j.ogm.session.Session;
import org.neo4j.ogm.session.SessionFactory;

public class Neo4jSessionFactory {

    private static final String DB_NAME = "nguyen23640731";
    private static SessionFactory sessionFactory;

    static {
        Configuration configuration = new Configuration.Builder()
                .uri("neo4j://127.0.0.1:7687")
                .credentials("neo4j", "11092020")
                .database(DB_NAME)
                .build();

        sessionFactory = new SessionFactory(configuration, "iuh.fit.entity");
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static Session openSession() {
        return sessionFactory.openSession();
    }

    public static void close() {
        sessionFactory.close();
    }
}