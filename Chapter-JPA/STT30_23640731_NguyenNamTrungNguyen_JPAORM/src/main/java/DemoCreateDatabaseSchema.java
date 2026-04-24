import core.entity.*;
import infrastructure.db.JPAUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;

public class DemoCreateDatabaseSchema {
    public static void main(String[] args) {

        EntityTransaction tx = null;
        try(EntityManager em = JPAUtil.getEntityManager()){
            tx = em.getTransaction();
            tx.begin();


//            Person person1 = Student.builder().id("STU_987").firstName("John").lastName("Smith").enrollmentDate(LocalDateTime.now()).build();
//            Person person2 = Instructor.builder().id("INS_456").firstName("Anna").lastName("Nguyen").hireDate(LocalDateTime.now()).build();
//
////            em.persist(person1);
////            em.persist(person2);
//
//            Course course1 = OnlineCourse.builder().id("CS101").title("Computer basic").credits(3).url("http://learnings/cs101").build();
//            Course course2 = OnsiteCourse.builder().id("Java1").title("Java basic").credits(3).days("Monday").location("V10.1").time(LocalDateTime.now()).build();
//
////            em.persist(course1);
////            em.persist(course2);
////            em.merge(course1);
////            em.merge(course2);
//
//            Instructor instructor = em.find(Instructor.class, "INS_456");
//            OfficeAssignment officeAssignment = OfficeAssignment.builder().instructor((Instructor) person2).timestamp(LocalDateTime.now()).location("H2.1").build();
////            em.persist(officeAssignment);
//
////            instructor.setOfficeAssignment(null);
//
//            instructor.setCourses(new HashSet<>(List.of(course1, course2)));
////            em.merge(instructor);
//
//            course2.setPrerequisites(new HashSet<>(List.of(course1)));
//            em.merge(course2);
//
//            Student student = em.find(Student.class, "STU_987");
//            Enrollment enrollment1 = Enrollment.builder().student(student).course(course1).semester("Spring 2025").grade(8.5).build();
//            Enrollment enrollment2 = Enrollment.builder().student(student).course(course2).semester("Spring 2026").build();
////            em.persist(enrollment1);
////            em.persist(enrollment2);
//
//            Enrollment.EnrollmentId enrollmentId = Enrollment.EnrollmentId.builder()
//                    .student("STU_987")
//                    .course("CS101")
//                    .semester("Spring 2025")
//                    .build();
//            Enrollment enrollment = em.find(Enrollment.class, enrollmentId);
//
//            System.out.println(enrollment);
//
//
//


            tx.commit();
        } catch (RuntimeException e) {
            if (tx != null || tx.isActive()) {
                tx.rollback();
            }
            throw new RuntimeException(e);
        }

    }
}

//EntityTransaction tx = null;
//        try(EntityManager em = JPAUtil.getEntityManager()){
//tx = em.getTransaction();
//            tx.begin();
////CRUD operations
//            tx.commit();
//        } catch (RuntimeException e) {
//        if (tx != null || tx.isActive()) {
//        tx.rollback();
//            }
//                    throw new RuntimeException(e);
//        }
