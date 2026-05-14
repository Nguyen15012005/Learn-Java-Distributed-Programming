package infrastructure.service.impl;

import core.domain.Status;
import core.dto.AppointmentDTO;
import core.dto.DoctorWorkloadDTO;
import infrastructure.mapper.Mapper;
import infrastructure.persistence_repository.AppointmentRepository;
import infrastructure.persistence_repository.impl.AppointmentRepositoryImpl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class AppointmentServiceImpl extends UnicastRemoteObject implements infrastructure.service.AppointmentService {

    private final AppointmentRepository appointmentRepository;

    public AppointmentServiceImpl() throws RemoteException {
        appointmentRepository = new AppointmentRepositoryImpl();
    }

    @Override
    public boolean addAppointment(AppointmentDTO appointmentDTO) throws RemoteException {
        return appointmentRepository.addAppointment(Mapper.toAppointment(appointmentDTO));
    }

    @Override
    public List<AppointmentDTO> getAppointmentDetails() throws RemoteException {
        return appointmentRepository.getAppointmentDetails()
                .stream()
                .map(obj -> new AppointmentDTO(
                        (String) obj[0],
                        (String) obj[1],
                        (String) obj[2],
                        (String) obj[3],
                        (LocalDateTime) obj[4],
                        (Status) obj[5]
                ))
                .toList();
    }

    @Override
    public List<DoctorWorkloadDTO> getDoctorWorkload() throws RemoteException {
        return appointmentRepository.getDoctorWorkload()
                .stream()
                .map(obj -> new DoctorWorkloadDTO(
                        (String) obj[0],
                        (String) obj[1],
                        obj[2].toString(),
                        (Long) obj[3]
                ))
                .toList();
    }
}