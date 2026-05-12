package repository;

import model.OnsiteCourse;

import java.util.List;

public interface OnlineSiteRepository {
    //   9. Tìm khóa học tại chỗ (`OnsiteCourse`) có ngày học (`days`) chứa `dayKeyword`
    List<OnsiteCourse> findOnsiteCourseByDaysContaining(String dayKeyword);
}
