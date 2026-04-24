package iuh.fit.core.service;

import iuh.fit.core.dto.StudentDTO;
import iuh.fit.core.entity.Student;

public interface StudentService {
    boolean createStudent(StudentDTO studentDTO);
    StudentDTO findStudentById(String studentId);
}
