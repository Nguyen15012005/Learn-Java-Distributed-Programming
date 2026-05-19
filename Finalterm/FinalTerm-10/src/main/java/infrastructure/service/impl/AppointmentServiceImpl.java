package infrastructure.service.impl;

import core.dto.AppointmentDTO;
import core.enums.Status;
import infrastructure.mapper.Mapper;
import infrastructure.repository.AppointmentRepository;
import infrastructure.repository.impl.AppointmentRepositoryImpl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.time.LocalDateTime;
import java.util.List;

public class AppointmentServiceImpl extends UnicastRemoteObject{

    private AppointmentRepository appointmentRepository ;

    public AppointmentServiceImpl() throws RemoteException {
       appointmentRepository = new AppointmentRepositoryImpl();
    }

    public boolean addAppointment(AppointmentDTO appointmentDTO)throws RemoteException {
        return appointmentRepository.addAppointment(Mapper.toEntity(appointmentDTO));
    }

    public List<AppointmentDTO> getAppointmentDetails() throws RemoteException {
        return appointmentRepository.getAppointmentDetails().stream().map(
                objects -> new AppointmentDTO(
                        (String) objects[0],
                        (String) objects[1],
                        (String) objects[2],
                        (String) objects[3],
                        (LocalDateTime) objects[4],
                        (Status) objects[5]
                )

        ).toList();
    }

}
