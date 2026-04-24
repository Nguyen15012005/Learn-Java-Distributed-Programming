package iuh.fit.core.service.impl;

import iuh.fit.core.dto.StudentDTO;
import iuh.fit.core.entity.Student;
import iuh.fit.core.repository.StudentRepository;
import iuh.fit.core.service.StudentService;
import iuh.fit.infrastructure.mapper.GenericDataMapper;

public class StudentServiceImpl implements StudentService {

    private StudentRepository studentRepository;
    private GenericDataMapper mapper;

    public StudentServiceImpl(StudentRepository studentRepository, GenericDataMapper genericDataMapper) {
        this.studentRepository = studentRepository;
        this.mapper = genericDataMapper;
    }

    @Override
    public boolean createStudent(StudentDTO studentDTO) {
        //1. Business rules validation
        if(studentDTO == null ||  studentDTO.getId() == null ||  studentDTO.getId().isBlank())
            throw  new IllegalArgumentException("Id must be not null or empty");

        if(studentDTO.getGpa() < 0.0 ||  studentDTO.getGpa() > 4.0)
            throw  new IllegalArgumentException("GPA must be between 0.0 and 4.0");

        //2. Convert from DTO to entity
        Student student = mapper.toObject(mapper.toMap(studentDTO), Student.class);

        //3. Call repository
        return studentRepository.createStudent(student);
    }

    @Override
    public StudentDTO findStudentById(String studentId) {
        //1. Business rules validation
        if(studentId == null ||  studentId.isBlank())
            throw  new IllegalArgumentException("Id must be not null or empty");
        //2. Call repository
        Student student = studentRepository.findStudentById(studentId);

        //3. Convert from entity to DTO
        return mapper.toObject(mapper.toMap(student), StudentDTO.class);
    }
}
