package iuh.fit.infrastructure.persistence;

import iuh.fit.core.entity.Department;
import iuh.fit.infrastructure.db.Neo4jConnManager;
import iuh.fit.infrastructure.mapper.GenericDataMapper;
import iuh.fit.infrastructure.mapper.impl.JacksonDataMapper;
import org.neo4j.driver.Session;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class DepartmentRepositoryImpl {
    private Neo4jConnManager connManager;
    private GenericDataMapper mapper;

    public DepartmentRepositoryImpl(Neo4jConnManager connManager, GenericDataMapper mapper) {
        this.connManager = connManager;
        this.mapper = mapper;
    }

    public Map<Department, Long> getNumOfStudentsByEachDeparment(){
        String query = "MATCH p=(s:Student)-[r:ENROLLED_IN]->(c:Course)-[r1:BELONGS_TO]->(d:Department) RETURN d, count(s) as n ";
        try(Session session = connManager.openSession()){
            return session.executeRead(tx -> {
                return tx.run(query)
                        .list()
                        .stream()
                        .map(record -> {
                            Department department = mapper.toObject(record.get("d").asNode().asMap(), Department.class);
                            Long n = record.get("n").asLong();
                            return Map.entry(department, n);
                        }).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

            });
        }
    }

//    CREATE FULLTEXT INDEX department_textsearch FOR (n:Department) ON EACH [n.name, n.dean, n.building]
    public List<Department> serachByFulltext(String keyword){
        String query = "CALL db.index.fulltext.queryNodes('department_textsearch', $keyword) YIELD node, score\n" +
                "RETURN node as dept, score";
        try(Session session = connManager.openSession()){
            return session.executeRead(tx -> {
                return tx.run(query, Map.of("keyword", keyword))
                        .list(record -> mapper.toObject(record.get("dept").asNode().asMap(), Department.class));
            });
        }
    }

    public List<Department> getMaxNumOfStudentsByEachDeparment (){
        String query = "MATCH p=(s:Student)-[r:ENROLLED_IN]->(c:Course)-[r1:BELONGS_TO]->(d:Department) \n" +
                "WITH d, count(s) as n \n" +
                "WITH collect({d: d, n:n}) as temp,  max(n) as max\n" +
                "UNWIND temp as t\n" +
                "WITH t, max WHERE t.n = max\n" +
                "RETURN t.d as dept, max";

        try(Session session = connManager.openSession()){
            return session.executeRead(tx -> {
                return tx.run(query)
                        .list(record -> mapper.toObject(record.get("dept").asNode().asMap(), Department.class));
            });
        }

    }

    public static void main(String[] args) {
        String uri = "neo4j://127.0.0.1:7687";
        String username = "neo4j";
        String password = "12345678";
        String dbName = "lan3266655";
        Neo4jConnManager conn = new Neo4jConnManager(uri,username, password, dbName);
        GenericDataMapper dataMapper = new JacksonDataMapper();

        DepartmentRepositoryImpl departmentRepository = new DepartmentRepositoryImpl(conn, dataMapper);

        departmentRepository.getMaxNumOfStudentsByEachDeparment()
                .forEach(d -> System.out.println(d));

//        departmentRepository.serachByFulltext("Industrial Engineering Rubio")
//                .forEach(d -> System.out.println(d));
//        departmentRepository.getNumOfStudentsByEachDeparment()
//                .entrySet()
//                .forEach(entry -> {
//                    System.out.println(entry.getKey());
//                    System.out.println(entry.getValue());
//                    System.out.println("===");
//                });
    }
}
