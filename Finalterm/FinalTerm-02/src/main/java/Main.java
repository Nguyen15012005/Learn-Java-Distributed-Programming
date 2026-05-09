import dao.DepartmentDao;
import dao.ProjectDao;
import dao.StaffDao;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;
import util.EntityManagerUtil;

/**
 * Author : TrungNguyen
 * Date   : 4/7/2026
 * Description:
 */
public class Main {
    public static void main(String[] args) {
//        EntityManager em = Persistence
//                .createEntityManagerFactory("mariadb")
//                .createEntityManager();


//        StaffDao.findStaffByNameKeyword("Kuvalis").forEach(System.out::println);
//
//        ProjectDao.findProjectByMinBudget(40000).forEach(System.out::println);
//
//        StaffDao.findStaffByAgeBetween(40, 64).forEach(System.out::println);
//
//        ProjectDao.findProjectHasStaffs().forEach(System.out::println);
//        DepartmentDao.findDepartmentHasMoreThan3Staffs().forEach(System.out::println);
//
//        StaffDao.countStaffByDepartment().forEach((k,v) -> {
//            System.out.println(k.getName()+ " : " + v);
//        });
//
//        StaffDao.findStaffWithoutProject().forEach(System.out::println);

//            StaffDao.findStaffByPhone("(274) 262-3266").forEach(System.out::println);
//            ProjectDao.findProjectsHasMoreStaffThanAverage().forEach(System.out::println);
//        StaffDao.findStaffInProjectWithMaxBudget().forEach(System.out::println);
//
//        DepartmentDao.findDepartmentHasMoreStaffThanDepartment("D4").forEach(System.out::println);
//
//
        StaffDao.findStaffNotJoinLowBudgetProject(545).forEach(System.out::println);

//        DepartmentDao.findDepartmentWithAvgAgeGreaterThan(12).forEach(System.out::println);

        EntityManagerUtil.close();



    }
}
