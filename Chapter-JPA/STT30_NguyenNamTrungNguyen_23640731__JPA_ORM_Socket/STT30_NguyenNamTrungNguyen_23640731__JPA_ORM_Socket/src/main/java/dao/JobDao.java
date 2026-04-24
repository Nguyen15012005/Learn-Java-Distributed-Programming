package dao;

import common.AbstractGenericDAO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import model.Job;
import util.JPAUtil;

import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author TrungNguyen
 * @created 4/12/2026
 * @description
 */
public class JobDao extends AbstractGenericDAO<Job, Long> {

//    Thống kê tổng số lượng đơn ứng tuyển mà công ty nào đó đã nhận được cho tất cả các
//    vị trí công việc mà họ đã đăng tuyển, sắp xếp kết quả theo số lượng đơn ứng tuyển giảm
//    dần.
    public JobDao() {
        super(Job.class);
    }

    public Map<Job, Long> countPerJobByCompany(String companyName){
        try{
            EntityManager em = JPAUtil.getEntityManager();
            String jpql =
                    """
                    
                            SELECT j, COUNT(a) AS total
                              FROM Application a
                              JOIN Job j ON a.job.id = j.id
                              JOIN Company c ON j.company.id = c.id
                              WHERE c.name = :companyName
                              GROUP BY j
                              ORDER BY total DESC 
                    """;
            TypedQuery<Object[]> query = em.createQuery(jpql, Object[].class);
            query.setParameter("companyName", companyName);
            Map<Job, Long> res = query
                    .getResultList()
                    .stream()
                    .collect(Collectors.toMap(
                            objects -> (Job) objects[0],
                            objects -> (Long) objects[1]
                    ));

            return res;
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
