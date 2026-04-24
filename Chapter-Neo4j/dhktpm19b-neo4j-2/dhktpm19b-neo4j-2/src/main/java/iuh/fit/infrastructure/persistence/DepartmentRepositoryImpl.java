package iuh.fit.infrastructure.persistence;

import iuh.fit.core.entity.Department;
import iuh.fit.infrastructure.db.Neo4jConnManager;
import iuh.fit.infrastructure.mapper.GenericDataMapper;
import iuh.fit.infrastructure.mapper.impl.JacksonDataMapper;
import org.neo4j.driver.Record;
import org.neo4j.driver.Result;
import org.neo4j.driver.Session;
import org.neo4j.driver.types.Node;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class DepartmentRepositoryImpl implements iuh.fit.core.repository.DepartmentRepository {
    private Neo4jConnManager connManager;
    private GenericDataMapper mapper;

    public DepartmentRepositoryImpl(Neo4jConnManager connManager, GenericDataMapper mapper) {
        this.connManager = connManager;
        this.mapper = mapper;
    }

    @Override
    public Map<Department, Long> getNumOfStudentsByEachDeparment(){

        String query = "MATCH p=(s:Student)-[r:ENROLLED_IN]->(c:Course)-[r2:BELONGS_TO]->(d:Department) RETURN d, count(s) as numOfStudents order by numOfStudents " ;
        try(Session session = connManager.openSession()) {
            return session.executeRead(tx -> {
                Result result = tx.run(query);
                return result.list()
                        .stream()
                        .map(record -> {
                            Department department = mapper.toObject(record.get("d").asNode().asMap(), Department.class);
                            long count = record.get("numOfStudents", 0);
                            return Map.entry(department, count);
                        }).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (v1, v2) -> v1, LinkedHashMap::new));
            });
        }
    }
    @Override
    public Map<Department, Long> getNumOfStudentsByEachDeparment2(){

        Map<Department, Long> temp = new LinkedHashMap<>();
//        Map<Department, Long> temp = new HashMap<>();
        String query = "MATCH p=(s:Student)-[r:ENROLLED_IN]->(c:Course)-[r2:BELONGS_TO]->(d:Department) RETURN d, count(s) as numOfStudents  order by numOfStudents ";
        try(Session session = connManager.openSession()) {
            return session.executeRead(tx -> {
                Result result = tx.run(query);
                for (Record record : result.list()) {
                    Node node = record.get("d").asNode();
                    Map<String, Object> map = node.asMap();
                    Department department = mapper.toObject(map, Department.class);

                    long numOfStudents = record.get("numOfStudents").asLong();
                    temp.put(department, numOfStudents);
                }

                return temp;
            });
        }
    }

    @Override
    public List<Department> getHighestStudentsByDepartments(){
        String query = "MATCH p=(s:Student)-[r:ENROLLED_IN]->(c:Course)-[r2:BELONGS_TO]->(d:Department) \n" +
                "WITH d, count(s) as numOfStudents  \n" +
                "WITH collect({d:d, n:numOfStudents}) as temp, max(numOfStudents) as max\n" +
                "UNWIND temp as t\n" +
                "WITH t WHERE t.n = max\n" +
                "RETURN t.d as dept" ;

        try(Session session = connManager.openSession()) {
            return session.executeRead(tx -> {
                return tx.run(query)
                        .list(record -> mapper.toObject(record.get("dept").asNode().asMap(), Department.class));
            });
        }

    }

//  CREATE FULLTEXT INDEX departmnet_fulltext FOR (n:Department) ON EACH [n.name, n.dean]
    @Override
    public List<Department> getDepartmentsByKeyword(String keyword){//"Industrial Engineering Rubio"
        String query = "CALL db.index.fulltext.queryNodes('departmnet_fulltext', $keyword) YIELD node, score\n" +
                "RETURN node as dept, score";

        try(Session session = connManager.openSession()) {
            return session.executeRead(tx -> {
                return tx.run(query, Map.of("keyword", keyword))
                        .list(record -> mapper.toObject(record.get("dept").asNode().asMap(), Department.class));
            });
        }
    }


    public static void main(String[] args) {
        String uri = "neo4j://127.0.0.1:7687";
        String username = "neo4j";
        String password = "12345678";
        String dbName = "lan12345678";
        Neo4jConnManager conn = new Neo4jConnManager(uri,username, password, dbName);
        GenericDataMapper dataMapper = new JacksonDataMapper();

        DepartmentRepositoryImpl departmentRepository = new DepartmentRepositoryImpl(conn, dataMapper);
        departmentRepository.getDepartmentsByKeyword("Industrial Engineering Rubio")
                .forEach(d -> System.out.println(d));

//        departmentRepository.getHighestStudentsByDepartments()
//                .forEach(d -> System.out.println(d));

//        departmentRepository.getNumOfStudentsByEachDeparment()
//                .forEach((dept, count) -> {
//                    System.out.println(dept + ", number of students: " + count);
//                });
//
//

    }
}
