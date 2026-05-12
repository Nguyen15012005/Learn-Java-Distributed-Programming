package repository;

import model.Instructor;

import java.time.LocalDateTime;
import java.util.List;

public interface InstructorRepository {
    //6. Tìm giảng viên (`Instructor`) có thời điểm phân công văn phòng trước `timestamp`
    List<Instructor> findInstructorByOfficeAssignmentTimestampBefore(LocalDateTime timestamp);
}
