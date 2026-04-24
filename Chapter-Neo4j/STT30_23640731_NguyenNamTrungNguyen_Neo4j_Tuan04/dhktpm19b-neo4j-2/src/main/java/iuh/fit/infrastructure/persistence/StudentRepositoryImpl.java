package iuh.fit.infrastructure.persistence;

import iuh.fit.core.entity.Student;
import iuh.fit.core.repository.StudentRepository;
import iuh.fit.infrastructure.db.Neo4jConnManager;
import iuh.fit.infrastructure.mapper.GenericDataMapper;
import iuh.fit.infrastructure.mapper.impl.JacksonDataMapper;
import org.neo4j.driver.Record;
import org.neo4j.driver.Result;
import org.neo4j.driver.Session;
import org.neo4j.driver.types.Node;

import java.util.List;
import java.util.Map;

public class StudentRepositoryImpl implements StudentRepository {

    private Neo4jConnManager connManager;
    private GenericDataMapper  mapper;

    public StudentRepositoryImpl(Neo4jConnManager connManager, GenericDataMapper mapper) {
        this.connManager = connManager;
        this.mapper = mapper;
    }



    @Override
    public boolean updateStudent(Student student) {
        return false;
    }

    @Override
    public boolean deleteStudent(String studentId) {
        return false;
    }



    @Override
    public List<Student> getStudents(int page, int size) {
        String cypher = "MATCH (n:Student) RETURN n SKIP $skip LIMIT $limit";
        int skip = (page - 1) * size;
        Map<String, Object> paras = Map.of("skip", skip, "limit", size);

        try(Session session = connManager.openSession()){
            return session.executeRead(tx -> {
                Result result = tx.run(cypher, paras);
                List<Record> list = result.list();
                return list.stream()
                        .map(record -> record.get("n").asNode())
                        .map(node -> mapper.toObject(node.asMap(), Student.class))
                        .toList();
            });
        }

    }

    @Override
    public Student findStudentById(String studentId) {
        String cypher = "MATCH (n:Student{student_id:$student_id}) RETURN n ;";
        Map<String, Object> params = Map.of("student_id", studentId);

        try(Session session = connManager.openSession()) {
            return session.executeRead(tx -> {
                Result result = tx.run(cypher, params);
                if(result.hasNext()) {
                    Node node = result.single().get("n").asNode();
                    Map<String, Object> map = node.asMap();
                    return mapper.toObject(map, Student.class);
                }
                return null;
            });
        }
    }

    @Override
    public boolean createStudent(Student student) {
        String cypher = "CREATE (s:Student{\n" +
                "  student_id:$student_id,\n" +
                "  name: $name,\n" +
                "  gpa:$gpa\n" +
                "});";

        Map<String, Object> params = mapper.toMap(student);

        try(Session session = connManager.openSession()) {
            return session.executeWrite(tx -> {
                Result result = tx.run(cypher, params);
                return result.consume().counters().nodesCreated() > 0;
            });
        }
    }

//    MATCH p=(s:Student)-[r:ENROLLED_IN]->(c:Course{course_id:'CS101'}) RETURN s.name as name LIMIT 25

    public List<String> getStudentNamesByCourse(String courseId){
        String query = "MATCH p=(s:Student)-[r:ENROLLED_IN]->(c:Course{course_id:$course_id}) RETURN s.name as name";
        Map<String, Object> paras = Map.of("course_id", courseId);
        try(Session session = connManager.openSession()){
            return session.executeRead(tx -> {
                return tx.run(query, paras)
                        .list()
                        .stream()
                        .map(record -> record.get("name").asString())
                        .toList();
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
        StudentRepository studentRepository = new StudentRepositoryImpl(conn, dataMapper);

        studentRepository.getStudentNamesByCourse("CS101")
                .forEach(name -> System.out.println(name));

//        studentRepository.getStudents(2, 3)
//                .forEach(s -> System.out.println(s));

//        Student student = studentRepository.findStudentById("13");
//        System.out.println(student);
//
//        Student st = Student.builder()
//                .id("23999991")
//                .name("John Smith")
//                .gpa(3.5)
//                .build();
//
//        boolean result = studentRepository.createStudent(st);
//        System.out.println(result);
    }
}
