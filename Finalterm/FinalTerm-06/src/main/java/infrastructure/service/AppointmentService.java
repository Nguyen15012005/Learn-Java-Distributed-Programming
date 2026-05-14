package infrastructure.service;

import core.dto.AppointmentDTO;
import core.dto.DoctorWorkloadDTO;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface AppointmentService extends Remote {
    boolean addAppointment(AppointmentDTO appointmentDTO) throws RemoteException;

    List<AppointmentDTO> getAppointmentDetails() throws RemoteException;

    List<DoctorWorkloadDTO> getDoctorWorkload() throws RemoteException;
}
