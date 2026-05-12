package repository;

import model.Course;

import java.util.List;
import java.util.Map;

public interface CourseRepository {
    List<Course> findCoursesByCreditsBetween(int minCredits, int maxCredits);

    //    2. Tìm TƯƠNG ĐỐI khóa học (`Course`) thuộc khoa có tên chứa `deptName`
    List<Course> findCourseByDepartmentNameContaining(String deptName);

    //    7. Thống kê số sinh viên theo khóa học
    Map<Course, Long> countStudentsByCourse();
}
