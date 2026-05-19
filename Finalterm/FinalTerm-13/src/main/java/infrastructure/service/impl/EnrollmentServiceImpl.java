package infrastructure.service.impl;

import core.dto.EnrollmentDTO;
import core.entity.Course;
import core.entity.Enrollment;
import core.entity.EnrollmentId;
import core.entity.Student;
import infrastructure.repository.EnrollmentRepository;
import infrastructure.repository.impl.EnrollmentRepositoryImpl;
import infrastructure.util.JPAUtil;
import jakarta.persistence.EntityManager;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Date;

public class EnrollmentServiceImpl extends UnicastRemoteObject implements infrastructure.service.EnrollmentService {
    private final EnrollmentRepository enrollmentRepository = new EnrollmentRepositoryImpl();

    public EnrollmentServiceImpl() throws RemoteException {
    }

    //    Thêm mới một đăng ký khóa học
    @Override
    public boolean addEnrollment(EnrollmentDTO enrollmentDTO)throws RemoteException{
        EnrollmentId enrollmentId = new EnrollmentId(enrollmentDTO.getStudentId(), enrollmentDTO.getCourseId(), enrollmentDTO.getEnroll_date());
        Course course = new Course();
        course.setId(enrollmentDTO.getCourseId());

        Student student = new Student();
        student.setId(enrollmentDTO.getStudentId());
        Enrollment enrollment = new Enrollment(enrollmentId, enrollmentDTO.getScore(), enrollmentDTO.getStatus(), course, student);

        return enrollmentRepository.addEnrollment(enrollment);
    }

    //Viết phương thức cập nhật điểm số (score) cho một đăng ký khi biết studentId, courseId và enrollDate. Không cho phép cập nhật các thuộc tính khác của đăng ký.
    @Override
    public boolean updateScore(String studentId, String courseId, Date enrollDate, double newScore)throws RemoteException{
        return enrollmentRepository.updateScore(studentId,courseId,enrollDate,newScore);
    }

}
