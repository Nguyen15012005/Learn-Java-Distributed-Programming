

-- Chèn Khoa (Department)
INSERT INTO Departments (DepartmentID, Name, Budget, StartDate, Administrator) VALUES
('DEPT_CS', 'Computer Science', 350000.00, '2026-01-01', 'INS_001'),
('DEPT_MATH', 'Mathematics', 120000.00, '2026-01-01', 'INS_002'),
('DEPT_PHYS', 'Physics', 200000.00, '2026-01-01', 'INS_003'),
('DEPT_ART', 'Fine Arts', 50000.00, '2026-03-16', 'INS_001'),
('DEPT_HIS', 'History', 40000.00, '2026-03-16', 'INS_002');

-- Chèn Giảng viên (Instructor)
INSERT INTO People (PersonID, FirstName, LastName, HireDate, Discriminator) VALUES
('INS_001', 'Nguyen', 'Van Hung', '2020-05-10', 'INSTRUCTOR'),
('INS_002', 'Tran', 'Thi Bich', '2018-03-15', 'INSTRUCTOR'),
('INS_003', 'Le', 'Hoang Anh', '2021-11-20', 'INSTRUCTOR');

-- Chèn Sinh viên (Student)
INSERT INTO People (PersonID, FirstName, LastName, EnrollmentDate, Discriminator) VALUES
('STU_001', 'Pham', 'Minh', '2025-09-01', 'STUDENT'),
('STU_002', 'Do', 'An', '2025-09-01', 'STUDENT'),
('STU_003', 'Hoang', 'Oanh', '2024-09-01', 'STUDENT'),
('STU_004', 'Tran', 'Lan', '2025-09-01', 'STUDENT');

-- Chèn Phân công văn phòng (OfficeAssignment)
INSERT INTO OfficeAssignments (InstructorID, Location, Timestamp) VALUES
('INS_001', 'Room 101 - Building A', '2026-03-16'),
('INS_002', 'Room 205 - Building B', '2026-03-16'),
('INS_003', 'Room 303 - Building A', '2026-03-16');

-- Chèn Khóa học (Course)
INSERT INTO Courses (CourseID, Title, Credits, DepartmentID) VALUES
('CRS_JAVA', 'Java Programming', 4, 'DEPT_CS'),
('CRS_DSA', 'Data Structures', 4, 'DEPT_CS'),
('CRS_CALC', 'Calculus I', 3, 'DEPT_MATH'),
('CRS_PHY', 'Quantum Physics', 4, 'DEPT_PHYS'),
('CRS_PAINT', 'Oil Painting', 3, 'DEPT_ART'),
('CRS_SCULP', 'Sculpture', 3, 'DEPT_ART');

INSERT INTO Courses (CourseID, Title, Credits, DepartmentID) VALUES
('CRS_ADV_JAVA', 'Advanced Java Programming', 4, 'DEPT_CS'),
('CRS_ALGO', 'Design and Analysis of Algorithms', 4, 'DEPT_CS');

-- Chèn Khóa học trực tuyến (OnlineCourse) và Khóa học tại chỗ (OnsiteCourse)
INSERT INTO OnlineCourses (CourseID, URL) VALUES
('CRS_JAVA', 'https://elearning.university.edu/java'),
('CRS_ADV_JAVA', 'https://elearning.university.edu/adv_java'),
('CRS_ALGO', 'https://elearning.university.edu/algo');

INSERT INTO OnsiteCourses (CourseID, Location, Days, Time) VALUES
('CRS_DSA', 'Lab 01', 'MWF', '08:00:00'),
('CRS_CALC', 'Hall 02', 'TTH', '10:00:00'),
('CRS_PHY', 'Hall 01', 'TTH', '15:00:00');

-- Chèn Mối quan hệ giữa Khóa học và Giảng viên (CoursesInstructors)
INSERT INTO CoursesInstructors (CourseID, instructorId) VALUES
('CRS_JAVA', 'INS_001'),
('CRS_DSA', 'INS_001'),
('CRS_CALC', 'INS_002'),
('CRS_PHY', 'INS_003'),
('CRS_PAINT', 'INS_003');

-- Chèn Mối quan hệ giữa Sinh viên và Khóa học (Enrollments)
INSERT INTO Enrollments (studentId, courseId, semester, grade) VALUES
('STU_001', 'CRS_JAVA', 'HK1_2026', 8.5),
('STU_001', 'CRS_CALC', 'HK1_2026', 7.5),
('STU_001', 'CRS_PHY', 'HK2_2026', 10.0),
('STU_001', 'CRS_DSA',  'HK1_2026', 9.0),
('STU_002', 'CRS_JAVA', 'HK1_2026', 7.0),
('STU_002', 'CRS_DSA', 'HK2_2026', 6.5),
('STU_002', 'CRS_CALC', 'HK2_2026', 8.0),
('STU_003', 'CRS_CALC', 'HK2_2026', 8.0),
('STU_003', 'CRS_JAVA', 'HK1_2026', 9.0),
('STU_003', 'CRS_ADV_JAVA', 'HK2_2026', 8.5),
('STU_004', 'CRS_JAVA', 'HK1_2026', 5.5),
('STU_004', 'CRS_PHY', 'HK1_2026', 6.0);

-- Chèn Mối quan hệ giữa Khóa học và Yêu cầu tiên quyết (CoursesPrerequisites)
INSERT INTO CoursesPrerequisites (courseId, prerequisiteId) VALUES
('CRS_ADV_JAVA', 'CRS_JAVA'), -- Để học Java nâng cao, phải học Java trước
('CRS_ALGO', 'CRS_DSA'),      -- Để học Thuật toán, phải học Cấu trúc dữ liệu trước
('CRS_DSA', 'CRS_JAVA');      -- Để học Cấu trúc dữ liệu, phải học Java trước

-- Truy vấn dữ liệu để kiểm tra
select * from Departments;
select * from People;
select * from OfficeAssignments;
select * from Courses;
select * from OnlineCourses;
select * from OnsiteCourses;
select * from CoursesInstructors;
select * from Enrollments;
select * from CoursesPrerequisites;

