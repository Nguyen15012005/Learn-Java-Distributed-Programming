package iuh.fit.presentation;


import iuh.fit.core.entity.Student;
import iuh.fit.core.repository.StudentRepository;
import iuh.fit.infrastructure.db.Neo4jConnManager;
import iuh.fit.infrastructure.mapper.GenericDataMapper;
import iuh.fit.infrastructure.mapper.JacksonDataMapper;
import org.neo4j.driver.Result;
import org.neo4j.driver.Session;
import org.neo4j.driver.TransactionContext;
import org.neo4j.driver.types.Node;

import java.util.List;
import java.util.Map;

public class StudentRepositoryImpl implements StudentRepository {

    private Neo4jConnManager connManager;
    private GenericDataMapper mapper;

    public StudentRepositoryImpl(Neo4jConnManager connManager, GenericDataMapper mapper) {
        this.connManager = connManager;
        this.mapper = mapper;
    }

    @Override
    public boolean createStudent(Student student) {

        String cypher = "CREATE (s:Student{student_id: $student_id, name: $name, gpa: $gpa})";
        Map<String, Object> paras = mapper.toMap(student);

        try (Session session = connManager.getSession()) {
            return session.executeWrite(tx -> {
                Result result = tx.run(cypher, paras);
                return result.consume().counters().nodesCreated() > 0;
            });
        }
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
    public Student findStudentById(String studentId) {

        String cypher = "MATCH (n:Student{student_id:$student_id}) RETURN n ";
        Map<String, Object> params = Map.of("student_id", studentId);

        try (Session session = connManager.getSession()) {

            return session.executeRead((TransactionContext tx) -> {

                Result result = tx.run(cypher, params);

                if (result.hasNext()) {
                    Node node = result.single().get("n").asNode();
                    Map<String, Object> map = node.asMap();

                    return mapper.toObject(map, Student.class);
                }

                return null;
            });
        }
    }

    @Override
    public List<Student> getStudents(int page, int size) {
        return List.of();
    }

    public static void main(String[] args) {
        String url = "neo4j://127.0.0.1:7687";
        String username = "neo4j";
        String password = "11092020";
        String dbName = "nguyen23640731";
        Neo4jConnManager conn = new Neo4jConnManager(url, username, password, dbName);

        GenericDataMapper dataMapper = new JacksonDataMapper();

        StudentRepository studentRepository = new StudentRepositoryImpl(conn, dataMapper);
        Student student = studentRepository.findStudentById("13");
        System.out.println(student);

        Student st = Student.builder()
                .id("23777771")
                .name("Le Lan")
                .gpa(3.4)
                .build();

        boolean result = studentRepository.createStudent(st);
        System.out.println(result);

    }
}