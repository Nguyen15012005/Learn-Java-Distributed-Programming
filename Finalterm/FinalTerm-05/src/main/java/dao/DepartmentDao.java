package dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import model.Department;
import util.JPAUtil;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class DepartmentDao {

    //    4. Thống kê số khóa học theo khoa
    public Map<Department, Long> countCourseByDepartment(){
        try (EntityManager em = JPAUtil.getEntityManager()) {
            String jpql =
                    """
                    select d, count(c)
                    from Department d
                    join d.courses c
                    GROUP BY d
                    ORDER BY count (c) desc
                    """;

            TypedQuery<Object[]> query = em.createQuery(jpql, Object[].class);

            return query.getResultList().stream().collect(Collectors.toMap(
                    obj -> (Department) obj[0],
                    obj -> (Long) obj[1],
                    (o1, o2) -> o1,
                    LinkedHashMap::new
            ));
        }
    }
}
