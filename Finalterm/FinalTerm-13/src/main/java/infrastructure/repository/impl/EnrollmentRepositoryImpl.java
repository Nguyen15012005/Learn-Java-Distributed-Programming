package infrastructure.repository.impl;

import core.entity.Enrollment;
import core.entity.EnrollmentId;
import jakarta.persistence.EntityManager;
import infrastructure.util.JPAUtil;

import java.sql.Date;

public class EnrollmentRepositoryImpl implements infrastructure.repository.EnrollmentRepository {
//    Thêm mới một đăng ký khóa học
    @Override
    public boolean addEnrollment(Enrollment enrollment){
            try (EntityManager em = JPAUtil.getEntityManager()){
                em.getTransaction().begin();
                if (enrollment == null){
                    em.getTransaction().rollback();
                    return false;
                }
                em.persist(enrollment);
                em.getTransaction().commit();
                return true;
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }

    //Viết phương thức cập nhật điểm số (score) cho một đăng ký khi biết studentId, courseId và enrollDate. Không cho phép cập nhật các thuộc tính khác của đăng ký.
    @Override
    public boolean updateScore(String studentId, String courseId, Date enrollDate, double newScore){
            try (EntityManager em = JPAUtil.getEntityManager()){
                em.getTransaction().begin();

                EnrollmentId id = new EnrollmentId(studentId, courseId, enrollDate);
                Enrollment enrollment = em.find(Enrollment.class, id);
                if (enrollment == null){
                    em.getTransaction().rollback();
                    return false;
                }
                enrollment.setScore(newScore);
                em.merge(enrollment);
                em.getTransaction().commit();
                return true;
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }

//    public static void main(String[] args) {
//        EnrollmentId enrollmentId = new EnrollmentId();
//        enrollmentId.setEnrollDate(Date.valueOf("2026-05-19"));
//        Course course = new Course();
//        course.setId("C002");
//        Student student = new Student();
//        student.setId("S004");
//        Enrollment enrollment = new Enrollment(enrollmentId, 9.5, EnrollStatus.COMPLETED, course, student);
//        boolean result = EnrollmentRepositoryImpl.addEnrollment(enrollment);
//        System.out.println(result? "Them thanh cong" : "them that bai");
//
//        boolean res = EnrollmentRepositoryImpl.updateScore("S004", "C002", Date.valueOf("2026-05-19"), 9.9);
//        System.out.println(res ? "Cap nhap thanh cong" : "Cap nhap that bai");
//    }
}
