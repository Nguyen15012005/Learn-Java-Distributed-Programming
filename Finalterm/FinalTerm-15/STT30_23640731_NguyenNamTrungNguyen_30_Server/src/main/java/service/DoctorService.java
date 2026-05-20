package service;

import dto.DoctorWorkloadDTO;
import repository.DoctorRepository;

import java.sql.Date;
import java.util.List;

public class DoctorService {

    private final DoctorRepository doctorRepository = new DoctorRepository();
    public List<DoctorWorkloadDTO> getDoctorWorkload(){
        return doctorRepository.getDoctorWorkload().stream().map(
                objects -> new DoctorWorkloadDTO(
                        (String) objects[0],
                        (String) objects[1],
                        (Date) objects[2],
                        (Long) objects[3]
                )
        ).toList();
    }
}
