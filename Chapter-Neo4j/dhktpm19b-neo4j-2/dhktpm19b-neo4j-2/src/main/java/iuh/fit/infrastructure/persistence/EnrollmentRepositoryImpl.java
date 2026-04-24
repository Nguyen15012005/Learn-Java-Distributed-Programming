package iuh.fit.infrastructure.persistence;

import iuh.fit.core.entity.Course;
import iuh.fit.core.entity.Enrollment;
import iuh.fit.core.entity.Student;
import iuh.fit.infrastructure.db.Neo4jConnManager;
import iuh.fit.infrastructure.mapper.GenericDataMapper;
import iuh.fit.infrastructure.mapper.impl.JacksonDataMapper;
import org.neo4j.driver.Result;
import org.neo4j.driver.Session;

import java.util.Map;

public class EnrollmentRepositoryImpl implements iuh.fit.core.repository.EnrollmentRepository {

    private Neo4jConnManager connManager;
    private GenericDataMapper mapper;

    public EnrollmentRepositoryImpl(Neo4jConnManager connManager, GenericDataMapper mapper) {
        this.connManager = connManager;
        this.mapper = mapper;
    }

    @Override
    public boolean createEnrollment(Enrollment enrollment){
        String query = "MATCH (c:Course{course_id:$course_id})\n" +
                "MATCH (s:Student{student_id:$student_id})\n" +
                "MERGE (s) - [:ENROLLED_IN {semester:$semester, grade:$grade}] -> (c);";

        Map<String, Object> paras = Map.of(
                "course_id", enrollment.getCourse().getId(),
                "student_id", enrollment.getStudent().getId(),
                "semester", enrollment.getSemester(),
                "grade", enrollment.getGrade()
        );

        try(Session session = connManager.openSession()){
            return session.executeWrite(tx -> {
                Result result = tx.run(query, paras);
                return result.consume().counters().relationshipsCreated() > 0;
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

        EnrollmentRepositoryImpl enrollmentRepository = new EnrollmentRepositoryImpl(conn, dataMapper);


        Student student = Student.builder().id("13").build();
        Course course = Course.builder().id("IE101").build();
        Enrollment enrollment = Enrollment.builder()
                .student(student)
                .course(course)
                .semester("Spring 2026")
                .build();

        boolean result = enrollmentRepository.createEnrollment(enrollment);
        System.out.println(result);

    }

}
