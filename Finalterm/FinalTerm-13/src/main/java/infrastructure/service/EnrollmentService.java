package infrastructure.service;

import core.dto.EnrollmentDTO;
import core.entity.Enrollment;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.Date;

public interface EnrollmentService extends Remote {
    //    Thêm mới một đăng ký khóa học
    boolean addEnrollment(EnrollmentDTO enrollmentDTO) throws RemoteException;

    //Viết phương thức cập nhật điểm số (score) cho một đăng ký khi biết studentId, courseId và enrollDate. Không cho phép cập nhật các thuộc tính khác của đăng ký.
    boolean updateScore(String studentId, String courseId, Date enrollDate, double newScore) throws RemoteException;
}
