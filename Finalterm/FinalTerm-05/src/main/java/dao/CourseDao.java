package dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import model.Course;
import model.Department;
import util.JPAUtil;

import javax.swing.text.html.parser.Entity;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CourseDao {
    public List<Course> findCoursesByCreditsBetween(int minCredits, int maxCredits){

        try (EntityManager em = JPAUtil.getEntityManager()) {
            String jpql =
                    """
                    SELECT c
                    FROM Course c
                    WHERE c.credit BETWEEN :minCredits AND :maxCredits
                    """;

            TypedQuery<Course> query = em.createQuery(jpql, Course.class);
            query.setParameter("minCredits", minCredits);
            query.setParameter("maxCredits", maxCredits);

            return query.getResultList();
        }
    }

//    2. Tìm TƯƠNG ĐỐI khóa học (`Course`) thuộc khoa có tên chứa `deptName`
    public List<Course> findCourseByDepartmentNameContaining(String deptName){

        try (EntityManager em = JPAUtil.getEntityManager()) {
            String jpql =
                    """
                    SELECT c
                    FROM Course c
                    WHERE c.department.name LIKE CONCAT("%", :deptName, "%")
                   """;
//            """
//                    SELECT c
//                    FROM Course c
//                    JOIN c.department d
//                    WHERE d.name LIKE CONCAT("%", :deptName, "%")
//                   """;

            TypedQuery<Course> query = em.createQuery(jpql, Course.class);
            query.setParameter("deptName", deptName);

            return query.getResultList();
        }
    }

//    7. Thống kê số sinh viên theo khóa học
    public Map<Course, Long> countStudentsByCourse(){
        try (EntityManager em = JPAUtil.getEntityManager()){
            String jpql = """
            select c, count (distinct s)
            from Course c
            join c.studentGrades sg
            join sg.student s
            GROUP BY c
            order by count (s) desc 
            
            """;
            TypedQuery<Object[]> query = em.createQuery(jpql, Object[].class);

            return query.getResultList().stream().collect(Collectors.toMap(
                    objects -> (Course) objects[0],
                            objects -> (Long) objects[1],
                    (o1, o2) -> o1,
                    LinkedHashMap::new
            ));
        }
    }

}
