package iuh.fit.infrastructure.persistence;

import iuh.fit.core.entity.Student;
import iuh.fit.core.repository.StudentRepository;
import iuh.fit.infrastructure.db.Neo4jConnManager;
import iuh.fit.infrastructure.mapper.GenericDataMapper;
import iuh.fit.infrastructure.mapper.impl.JacksonDataMapper;
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
        String query = "MATCH (n:Student) RETURN n SKIP $skip LIMIT $limit";
        int skip = (page - 1) * size;
        try(Session session = connManager.openSession()) {
            return session.executeRead(tx -> {
                Result result = tx.run(query, Map.of("skip", skip, "limit", size));
                return result.list(record -> {
                   return mapper.toObject(record.get("n").asNode().asMap(), Student.class);
                });
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

    /**
     * List the names of students enrolled in the CS101 course
     * @param courseId
     * @return
     */
    public List<String> getStudentNamesByCourse(String courseId){
//        List<String> temp = new ArrayList<>();
        String query = "MATCH p=(s:Student)-[r:ENROLLED_IN]->(c:Course{course_id:$course_id}) RETURN s.name as studentName";
        try(Session session = connManager.openSession()){
            return session.executeRead(tx -> {
                Result result = tx.run(query, Map.of("course_id", courseId));

                return result.list()
                        .stream()
                        .map(record -> record.get("studentName").asString())
                        .toList();

//                for(Record record: result.list()){
//                    String studentName = record.get("studentName").asString();
//                    temp.add(studentName);
//                }

//                return temp;
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
        StudentRepository studentRepository = new StudentRepositoryImpl(conn, dataMapper);

        studentRepository.getStudents(2, 3)
                .forEach(st -> System.out.println(st));

//        studentRepository.getStudentNamesByCourse("CS101")
//                .forEach(st -> System.out.println(st));


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
