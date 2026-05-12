package repository;

import model.Department;

import java.util.List;
import java.util.Map;

public interface DepartmentRepository {
    //    4. Thống kê số khóa học theo khoa
    Map<Department, Long> countCourseByDepartment();

    //    8. Tìm phòng ban (`Department`) có ngân sách lớn nhất
    List<Department> findDepartmentWithMaxBudget();
}
