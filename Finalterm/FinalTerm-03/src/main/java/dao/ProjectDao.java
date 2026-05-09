package dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import model.Project;
import util.EntityManagerUtil;

import java.util.List;

public class ProjectDao {

//    2. Tìm Project lớn hơn Budget truyền vào
    public List<Project> findProjectByMinBudget(double minBudget){
        try (EntityManager em = EntityManagerUtil.getEntityManager()){
            String jpql =
                    """
                        SELECT p
                        FROM Project p
                        WHERE p.budget > :minBudget
                    """;
            TypedQuery<Project> query = em.createQuery(jpql, Project.class);
            query.setParameter("minBudget", minBudget);
            return query.getResultList();
        }
    }

//    4. Tìm Project mà có Staff
    public List<Project> findProjectHasStaffs(){
        try (EntityManager em = EntityManagerUtil.getEntityManager()){
            String jpql =
                    """
                        SELECT p
                        FROM Project p
                        WHERE size(p.staffs) = 0 
                    """;
            TypedQuery<Project> query = em.createQuery(jpql, Project.class);
            return query.getResultList();
        }
    }

//    9. Project có số nhân viên > trung bình số nhân viên tất cả project
    public List<Project> findProjectsHasMoreStaffThanAverage(){
        try(EntityManager em = EntityManagerUtil.getEntityManager()){
            String jqpl =
                    """
                       SELECT p
                         FROM Project p
                         WHERE SIZE(p.staffs) > (
                           SELECT AVG(SIZE(p2.staffs))
                           FROM Project p2
                         )
                    """;
            TypedQuery<Project> query = em.createQuery(jqpl, Project.class);
            return query.getResultList();
        }
    }

}
