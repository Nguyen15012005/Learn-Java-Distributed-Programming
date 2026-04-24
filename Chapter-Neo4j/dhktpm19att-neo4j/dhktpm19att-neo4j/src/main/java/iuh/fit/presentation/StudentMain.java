package iuh.fit.presentation;

import iuh.fit.core.dto.StudentDTO;
import iuh.fit.core.repository.StudentRepository;
import iuh.fit.core.service.StudentService;
import iuh.fit.core.service.impl.StudentServiceImpl;
import iuh.fit.infrastructure.db.Neo4jConnManager;
import iuh.fit.infrastructure.mapper.GenericDataMapper;
import iuh.fit.infrastructure.mapper.JacksonDataMapper;
import iuh.fit.infrastructure.persistence.StudentRepositoryImpl;

public class StudentMain {
    public static void main(String[] args) {

        String uri = "neo4j://127.0.0.1:7687";
        String username = "neo4j";
        String password = "12345678";
        String dbName = "binh24556541";
        Neo4jConnManager conn = new Neo4jConnManager(uri, username, password, dbName);

        GenericDataMapper dataMapper = new JacksonDataMapper();

        StudentRepository studentRepository = new StudentRepositoryImpl(conn, dataMapper);
        StudentService studentService = new StudentServiceImpl(studentRepository, dataMapper);

        StudentDTO studentDTO = studentService.findStudentById("13");
        System.out.println(studentDTO);
    }
}
