package repository.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import model.Department;
import util.JPAUtil;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class DepartmentRepositoryImpl implements repository.DepartmentRepository {

    //    4. Thống kê số khóa học theo khoa
    @Override
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

//    8. Tìm phòng ban (`Department`) có ngân sách lớn nhất
@Override
public List<Department> findDepartmentWithMaxBudget(){
        try (EntityManager em = JPAUtil.getEntityManager()) {
            String jpql =
                    """
                    select d
                    from Department d
                    where d.budget = (
                        select max (d2.budget)
                        from Department d2
                    )
                    """;
//                                  """ cái này sẽ sai khi trường hợp có nhiều max
//                               select d
//                               from Department d
//                               order by d.budget desc
//                               limit 1
//                               """;
            TypedQuery<Department> query = em.createQuery(jpql, Department.class);
            return query.getResultList();
        }
    }
}
