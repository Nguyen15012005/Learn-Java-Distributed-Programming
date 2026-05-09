package dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import model.Department;
import model.Staff;
import util.EntityManagerUtil;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class StaffDao {

//    1.Tìm Staff có tên giống với keyword nhất
    public static List<Staff> findStaffByNameKeyword(String keyword){
        try(EntityManager em = EntityManagerUtil.getEntityManager()){
            String jqpl =
                    """
                        SELECT s
                        FROM Staff s
                        WHERE s.name LIKE CONCAT("%", :keyword, "%") 
                    """;
            TypedQuery<Staff> query = em.createQuery(jqpl, Staff.class);
            query.setParameter("keyword", keyword);
            return query.getResultList();
        }
    }

//    3. Tìm Staff có tuổi nằm trong từ và đến
    public static List<Staff> findStaffByAgeBetween(int minAge, int maxAge){
        try(EntityManager em = EntityManagerUtil.getEntityManager()){
            String jqpl =
                    """
                        SELECT s
                        FROM Staff s
                        WHERE s.age BETWEEN :minAge AND :maxAge 
                    """;

//            c1: WHERE s.age >= :minAge and s.age <= :maxAge
            TypedQuery<Staff> query = em.createQuery(jqpl, Staff.class);
            query.setParameter("minAge", minAge);
            query.setParameter("maxAge", maxAge);

            return query.getResultList();
        }
    }

//    6. Tìm số nhân viên theo phòng ban
    public static Map<Department, Long> countStaffByDepartment(){
        try(EntityManager em = EntityManagerUtil.getEntityManager()){
//            String jqpl =
//                    """
//                        SELECT d, SIZE(d.staffs)
//                        FROM Department d
//                    """;
            String jqpl =
                    """
                        SELECT d, COUNT(s)
                        FROM Department d
                        JOIN d.staffs s
                        GROUP BY d.name
                    """;
            TypedQuery<Object[]> query = em.createQuery(jqpl, Object[].class);
            Map<Department, Long> res = query
                    .getResultList()
                    .stream()
                    .collect(Collectors.toMap(
                            objects -> (Department) objects[0],
                            objects -> (Long) objects[1]
                    ));

            return res;
        }
    }

//    7. Tìm Staff không có proj
    public static List<Staff> findStaffWithoutProject(){
        try(EntityManager em = EntityManagerUtil.getEntityManager()){
            String jqpl =
                    """
                        SELECT s
                        FROM Staff s
                        WHERE SIZE(s.projects) = 0 
                    """;
            TypedQuery<Staff> query = em.createQuery(jqpl, Staff.class);
            return query.getResultList();
        }
    }

//    8. Tìm Staff theo phoneNumber
    public static List<Staff> findStaffByPhone(String phone){
        try(EntityManager em = EntityManagerUtil.getEntityManager()){
            String jqpl =
                    """
                        SELECT s
                        FROM Staff s
                        JOIN s.phoneNumbers phoneNumber
                        WHERE phoneNumber LIKE :phone
                    """;
            TypedQuery<Staff> query = em.createQuery(jqpl, Staff.class);
            query.setParameter("phone", phone);
            return query.getResultList();
        }
    }

//    10. Staff tham gia project có ngân sách lớn nhất
    public static List<Staff> findStaffInProjectWithMaxBudget(){
        try(EntityManager em = EntityManagerUtil.getEntityManager()){
            String jqpl =
                    """
                        SELECT s 
                        FROM Staff s
                        JOIN s.projects p
                        WHERE p.budget >= (
                            SELECT MAX(p2.budget)
                            FROM Project p2
                        )
                    """;
            TypedQuery<Staff> query = em.createQuery(jqpl, Staff.class);
            return query.getResultList();
        }
    }

//    12. Staff không tham gia bất kỳ project nào có ngân sách < xxx
    public static List<Staff> findStaffNotJoinLowBudgetProject(double budget){
        try(EntityManager em = EntityManagerUtil.getEntityManager()){
            String jqpl =
                    """
                        SELECT s
                        FROM Staff s
                        WHERE NOT EXISTS (
                            SELECT p
                            FROM s.projects p
                            WHERE p.budget < :budget
                        ) AND SIZE(s.projects) > 0
                    """;
            TypedQuery<Staff> query = em.createQuery(jqpl, Staff.class);
            query.setParameter("budget",budget);
            return query.getResultList();
        }
    }

}
