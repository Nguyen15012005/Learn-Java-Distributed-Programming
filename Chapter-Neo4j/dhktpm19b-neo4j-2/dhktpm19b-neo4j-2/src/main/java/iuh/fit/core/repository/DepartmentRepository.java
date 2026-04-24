package iuh.fit.core.repository;

import iuh.fit.core.entity.Department;

import java.util.List;
import java.util.Map;

public interface DepartmentRepository {
    Map<Department, Long> getNumOfStudentsByEachDeparment();

    Map<Department, Long> getNumOfStudentsByEachDeparment2();

    List<Department> getHighestStudentsByDepartments();

    //  CREATE FULLTEXT INDEX departmnet_fulltext FOR (n:Department) ON EACH [n.name, n.dean]
    List<Department> getDepartmentsByKeyword(String keyword);
}
