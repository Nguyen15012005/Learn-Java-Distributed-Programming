package mapper;

import dto.CourseDTO;
import model.Course;

public class CourseMapper {

    public static CourseDTO toDTO(Course course){
        return new CourseDTO(
                course.getId(),
                course.getTitle(),
                course.getCredit(),
                course.getDepartment().getName()
        );
    }
}
