package infrastructure.repository;

import java.util.List;

public interface CourseRepository {
    List<Object[]> getOpenCoursesByGenre(String genreName);
}
