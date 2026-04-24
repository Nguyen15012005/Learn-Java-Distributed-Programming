package infrastructure.persistence;

import core.entity.Student;
import infrastructure.OnsiteCourseRepositoryImpl.AbstractGenericRepositoryImpl;

public class StudentRepositoryImpl
        extends AbstractGenericRepositoryImpl<Student, String> {

    public StudentRepositoryImpl() {
        super(Student.class);
    }

    public static void main(String[] args) {
        StudentRepositoryImpl repo = new StudentRepositoryImpl();

        String id = "STU_001";

        Student student = repo.findById(id);

        if (student == null) {
            System.out.println("Không tìm thấy sinh viên!");
            return;
        }

        System.out.println("Trước update: " + student);

        // 🔹 2. Update
        student.setLastName("Nguyen");
        repo.update(student);

        // 🔹 3. Kiểm tra lại
        Student updated = repo.findById(id);
        System.out.println("Sau update: " + updated);
    }
}