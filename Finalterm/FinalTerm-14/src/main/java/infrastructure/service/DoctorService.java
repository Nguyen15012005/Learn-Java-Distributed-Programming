package infrastructure.service;

import core.dto.DoctorWorkloadDTO;
import infrastructure.repository.DoctorRepositoryImpl;
import infrastructure.util.JPAUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import javax.print.Doc;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public class DoctorService {

    private final DoctorRepositoryImpl doctorService;

    public DoctorService() {
        doctorService = new DoctorRepositoryImpl();
    }

    public List<DoctorWorkloadDTO> getDoctorWorkload(){
        return doctorService.getDoctorWorkload().stream().map(
                objects -> new DoctorWorkloadDTO(
                        (String) objects[0],
                        (String) objects[1],
                        (Date) objects[2],
                        (Long) objects[3]
                )
        ).toList();
    }
}
