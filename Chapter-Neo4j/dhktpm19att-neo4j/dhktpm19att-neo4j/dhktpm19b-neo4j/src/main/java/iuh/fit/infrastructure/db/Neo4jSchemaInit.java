package iuh.fit.infrastructure.db;

import org.neo4j.driver.Result;
import org.neo4j.driver.Session;

public class Neo4jSchemaInit {
    private Neo4jConnManager connManager;

    public Neo4jSchemaInit(Neo4jConnManager connManager) {
        this.connManager = connManager;
    }

    public String createDepartmentFullTextIndex() {
        String cypher = "CREATE FULLTEXT INDEX department_fulltext IF NOT EXISTS" +
                " FOR (d:Department) ON EACH [d.name, d.dean] ";
        try(Session session = connManager.openSession()){
            Result result = session.run(cypher);
            if(result.consume().counters().indexesAdded() > 0)
                return "Index created successfully";
            return "Existed or failed to create index";
        }
    }

    public static void main(String[] args) {
        String uri = "neo4j://127.0.0.1:7687";
        String username = "neo4j";
        String password = "12345678";
        String dbName = "hoa23434311";
        Neo4jConnManager conn = new Neo4jConnManager(uri,username, password, dbName);
        Neo4jSchemaInit init = new Neo4jSchemaInit(conn);
        System.out.println(init.createDepartmentFullTextIndex());
    }
}
