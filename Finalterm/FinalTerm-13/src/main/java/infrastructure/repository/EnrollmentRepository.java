package infrastructure.repository;

import core.entity.Enrollment;

import java.sql.Date;

public interface EnrollmentRepository {
    //    Thêm mới một đăng ký khóa học
    boolean addEnrollment(Enrollment enrollment);

    //Viết phương thức cập nhật điểm số (score) cho một đăng ký khi biết studentId, courseId và enrollDate. Không cho phép cập nhật các thuộc tính khác của đăng ký.
    boolean updateScore(String studentId, String courseId, Date enrollDate, double newScore);
}
