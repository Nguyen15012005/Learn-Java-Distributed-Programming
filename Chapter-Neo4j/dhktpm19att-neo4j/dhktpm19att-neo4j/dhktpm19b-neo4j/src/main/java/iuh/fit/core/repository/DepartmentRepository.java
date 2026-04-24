package iuh.fit.core.repository;

import iuh.fit.core.entity.Department;

import java.util.List;

public interface DepartmentRepository {
    List<Department> getDepartmentsByFulltext(String keyword);
}
