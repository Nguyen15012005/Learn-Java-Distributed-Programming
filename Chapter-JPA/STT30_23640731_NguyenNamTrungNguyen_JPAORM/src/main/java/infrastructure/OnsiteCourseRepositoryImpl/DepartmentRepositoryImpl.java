package infrastructure.persistence;

import core.entity.Department;
import infrastructure.OnsiteCourseRepositoryImpl.AbstractGenericRepositoryImpl;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class DepartmentRepositoryImpl
        extends AbstractGenericRepositoryImpl<Department, String> {

    public DepartmentRepositoryImpl() {
        super(Department.class);
    }

    // 🔥 1. Đếm số môn theo khoa
    public Map<Department, Long> getNumOfCoursesByDepartments() {

        String query =
                "select d, count(c) " +
                        "from Department d " +
                        "left join d.courses c " +
                        "group by d";

        return doInTransaction(em -> {
            List<Object[]> list = em.createQuery(query, Object[].class)
                    .getResultList();

            return list.stream()
                    .map(arr -> Map.entry((Department) arr[0], (Long) arr[1]))
                    .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        });
    }

    // 🔥 2. Đếm số sinh viên theo khoa
    public Map<Department, Long> getNumberOfStudentsByDepartment(){

        String query =
                "select d, count(s) " +
                        "from Department d " +
                        "join d.courses c " +
                        "join c.enrollments e " +
                        "join e.student s " +
                        "group by d " +   // ⚠️ phải có space
                        "order by count(s) desc";

        return doInTransaction(em -> {
            List<Object[]> list = em.createQuery(query, Object[].class)
                    .getResultList();

            return list.stream()
                    .map(arr -> Map.entry((Department) arr[0], (Long) arr[1]))
                    .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        });
    }

    // ================= MAIN TEST =================
    public static void main(String[] args) {

        DepartmentRepositoryImpl repo = new DepartmentRepositoryImpl();

        // 🔥 Test 1
        System.out.println("=== SỐ MÔN THEO KHOA ===");
        repo.getNumOfCoursesByDepartments()
                .forEach((k, v) ->
                        System.out.println(k.getId() + " - " + k.getName() + " : " + v)
                );

        // 🔥 Test 2
        System.out.println("\n=== SỐ SINH VIÊN THEO KHOA ===");
        repo.getNumberOfStudentsByDepartment()
                .forEach((k, v) ->
                        System.out.println(k.getId() + " - " + k.getName() + " : " + v)
                );
    }
}