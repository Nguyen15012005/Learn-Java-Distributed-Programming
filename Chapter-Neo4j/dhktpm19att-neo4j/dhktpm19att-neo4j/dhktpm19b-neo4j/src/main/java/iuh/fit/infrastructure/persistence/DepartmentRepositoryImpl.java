package iuh.fit.infrastructure.persistence;

import iuh.fit.core.entity.Department;
import iuh.fit.core.repository.DepartmentRepository;
import iuh.fit.infrastructure.db.Neo4jConnManager;
import iuh.fit.infrastructure.mapper.GenericDataMapper;
import iuh.fit.infrastructure.mapper.impl.JacksonDataMapper;

import java.util.List;
import java.util.Map;

public class DepartmentRepositoryImpl implements DepartmentRepository {
    private Neo4jConnManager connManager;
    private GenericDataMapper mapper;

    public DepartmentRepositoryImpl(Neo4jConnManager connManager, GenericDataMapper mapper) {
        this.connManager = connManager;
        this.mapper = mapper;
    }

    @Override
    public List<Department> getDepartmentsByFulltext(String keyword) {
        String cypher = "CALL db.index.fulltext.queryNodes('department_fulltext', $keyword) YIELD node, score WHERE score >= 0.35 RETURN node, score";
        try(var session = connManager.openSession()) {
            return session.executeRead(tx -> {
                var result = tx.run(cypher, Map.of("keyword", keyword));
                return result.list(r -> {
                    var node = r.get("node").asNode();
                    System.out.println(node.asMap());
                    System.out.println(r.get("score").asDouble());
                    return null;
                });
            });
        }
    }

    public static void main(String[] args) {
        String uri = "neo4j://127.0.0.1:7687";
        String username = "neo4j";
        String password = "12345678";
        String dbName = "hoa23434311";
        Neo4jConnManager conn = new Neo4jConnManager(uri,username, password, dbName);
        GenericDataMapper dataMapper = new JacksonDataMapper();
        DepartmentRepository departmentRepository = new DepartmentRepositoryImpl(conn, dataMapper);
        System.out.println(departmentRepository.getDepartmentsByFulltext("Industrial Engineering"));
    }
}
