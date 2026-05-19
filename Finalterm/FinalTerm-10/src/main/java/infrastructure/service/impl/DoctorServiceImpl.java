package infrastructure.service.impl;

import core.dto.DoctorWorkloadDTO;
import infrastructure.repository.DoctorRepository;
import infrastructure.repository.impl.DoctorRepositoryImpl;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

public class DoctorServiceImpl {
    private DoctorRepository doctorRepository ;

    public DoctorServiceImpl() {
        doctorRepository = new DoctorRepositoryImpl();
    }

    public List<DoctorWorkloadDTO> getDoctorWorkload() {
        return doctorRepository.getDoctorWorkload()
                .stream()
                .map(objects -> {
                    java.sql.Date date = (java.sql.Date) objects[2];

                    return new DoctorWorkloadDTO(
                            (String) objects[0],
                            (String) objects[1],
                            date.toLocalDate(),
                            (Long) objects[3]
                    );
                })
                .toList();
    }

}
