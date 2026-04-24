package iuh.fit.core.repository;

import iuh.fit.core.entity.Student;

import java.util.List;

public interface StudentRepository {
    boolean createStudent(Student student);
    boolean updateStudent(Student student);
    boolean deleteStudent(String studentId);
    Student findStudentById(String studentId);
    List<Student> getStudents(int page, int size);
    public List<String> getStudentNamesByCourse(String courseId);
}
