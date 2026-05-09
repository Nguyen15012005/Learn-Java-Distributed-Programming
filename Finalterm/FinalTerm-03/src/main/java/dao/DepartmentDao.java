package dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import model.Department;
import model.Staff;
import util.EntityManagerUtil;

import java.util.List;

public class DepartmentDao {

//    5. Tìm phòng ban có hơn 3 Staff
    public List<Department> findDepartmentHasMoreThan3Staffs(){
        try (EntityManager em = EntityManagerUtil.getEntityManager()){
            String jpql =
                    """
                        SELECT d
                        FROM Department d
                        WHERE SIZE(d.staffs) > 3 
                    """;
            TypedQuery<Department> query = em.createQuery(jpql, Department.class);
            return query.getResultList();
        }
    }

//    11. Department có số staff >= số staff của 1 phòng ban bất kỳ có ID
    public List<Department> findDepartmentHasMoreStaffThanDepartment(String deptId){
        try (EntityManager em = EntityManagerUtil.getEntityManager()){
            String jpql =
                    """
                        SELECT d
                        FROM Department d
                        WHERE SIZE(d.staffs) > (
                            SELECT SIZE(d2.staffs)
                            FROM Department d2
                            where d2.id like :deptId
                        ) 
                    """;
            TypedQuery<Department> query = em.createQuery(jpql, Department.class);
            query.setParameter("deptId", deptId);
            return query.getResultList();
        }
    }

//    13. Department có tuổi trung bình staff > xxx
    public List<Department> findDepartmentWithAvgAgeGreaterThan(int age){
        try(EntityManager em = EntityManagerUtil.getEntityManager()){
            String jqpl =
                    """
                        SELECT d
                        FROM Department d
                        JOIN d.staffs s
                        GROUP BY d
                        HAVING AVG(s.age) > :age
                        
                    """;
            TypedQuery<Department> query = em.createQuery(jqpl, Department.class);
            query.setParameter("age",age);
            return query.getResultList();
        }
    }
}
