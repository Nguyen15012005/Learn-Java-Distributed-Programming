package repository;

import model.OnlineCourse;

import java.util.List;

public interface OnlineCourseRepository {
    //    3. Tìm khóa học trực tuyến (`OnlineCourse`) có URL chứa `urlKeyword` và tín chỉ ≥ `minCredits`
    List<OnlineCourse> findOnlineCourseByUrlContainingAndCreditsGreaterThanEqual(String urlKeyword, int minCredits);
}
