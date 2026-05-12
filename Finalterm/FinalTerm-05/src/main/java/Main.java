import dao.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;
import util.JPAUtil;

import java.time.LocalDateTime;

/**
 * @author TrungNguyen
 * @created 4/22/2026
 * @description
 */

public class Main {
    public static void main(String[] args) {
        EntityManager em = Persistence
                .createEntityManagerFactory("mariadb")
                .createEntityManager();

        em.getMetamodel().getEntities();

        CourseDao courseDao = new CourseDao();
//        courseDao.findCoursesByCreditsBetween(2, 3).forEach(System.out::println);


//        courseDao.findCourseByDepartmentNameContaining("Math").forEach(System.out::println);


        OnlineCourseDao onlineCourseDao = new OnlineCourseDao();
//        onlineCourseDao.findOnlineCourseByUrlContainingAndCreditsGreaterThanEqual("Composition", 2).forEach(System.out::println);

        DepartmentDao departmentDao = new DepartmentDao();
//        departmentDao.countCourseByDepartment().forEach((k,v) -> System.out.println(k.getName() + ":" + v));

        StudentDao studentDao = new StudentDao();
//        studentDao.findStudentByEnrollmentDateBetween(LocalDateTime.of(2001, 01, 01,00, 00), LocalDateTime.of(2022, 01, 01,00, 00)).forEach(System.out::println);

        InstructorDao instructorDao = new InstructorDao();
//        instructorDao.findInstructorByOfficeAssignmentTimestampBefore(LocalDateTime.of(2027, 01,01,00, 00)).forEach(System.out::println);

//        courseDao.countStudentsByCourse().forEach((k,v)-> System.out.println(k.getTitle()  + ":" + v));
//        departmentDao.findDepartmentWithMaxBudget().forEach(System.out::println);

//        OnlineSiteDao onlineSiteDao = new OnlineSiteDao();
//        onlineSiteDao.findOnsiteCourseByDaysContaining("MW").forEach(System.out::println);


        JPAUtil.close();


    }
}
