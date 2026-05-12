import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;
import repository.*;
import repository.impl.*;
import util.JPAUtil;

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

        CourseRepository courseDao = new CourseRepositoryImpl();



        OnlineCourseRepository onlineCourseDao = new OnlineCourseRepositoryImpl();
//        onlineCourseDao.findOnlineCourseByUrlContainingAndCreditsGreaterThanEqual("Composition", 2).forEach(System.out::println);

        DepartmentRepository departmentDao = new DepartmentRepositoryImpl();
//        departmentDao.countCourseByDepartment().forEach((k,v) -> System.out.println(k.getName() + ":" + v));

        StudentRepository studentDao = new StudentRepositoryImpl();
//        studentDao.findStudentByEnrollmentDateBetween(LocalDateTime.of(2001, 01, 01,00, 00), LocalDateTime.of(2022, 01, 01,00, 00)).forEach(System.out::println);

        InstructorRepository instructorDao = new InstructorRepositoryImpl();
//        instructorDao.findInstructorByOfficeAssignmentTimestampBefore(LocalDateTime.of(2027, 01,01,00, 00)).forEach(System.out::println);


//        departmentDao.findDepartmentWithMaxBudget().forEach(System.out::println);

        OnlineSiteRepository onlineSiteDao = new OnlineSiteRepositoryImpl();
        onlineSiteDao.findOnsiteCourseByDaysContaining("MW").forEach(System.out::println);


        JPAUtil.close();
    }
}
