package repository;

import model.Student;

import java.time.LocalDateTime;
import java.util.List;

public interface StudentRepository {
    //    5. Tìm sinh viên (`Student`) có ngày nhập học nằm giữa `startDate` và `endDate`
    List<Student> findStudentByEnrollmentDateBetween(LocalDateTime startDate, LocalDateTime endDate);
}
