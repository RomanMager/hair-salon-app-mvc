package by.bsuir.hairdressingsalon.hairsalonapp.service;

import by.bsuir.hairdressingsalon.hairsalonapp.entity.Customer;
import by.bsuir.hairdressingsalon.hairsalonapp.entity.Employee;
import by.bsuir.hairdressingsalon.hairsalonapp.entity.ProcedureAppointment;
import by.bsuir.hairdressingsalon.hairsalonapp.repository.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;

    @Autowired
    public AppointmentService(AppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }

    public List<ProcedureAppointment> getAllAppointments() {
        return appointmentRepository.findAll();
    }

    public List<ProcedureAppointment> getAppAppointments() {
        return appointmentRepository.findAll();
    }

    public List<ProcedureAppointment> getAppointmentsForCustomer(Customer customer) {
        return appointmentRepository.findProcedureAppointmentsBySignedUpCustomer(customer);
    }

    public void deleteAppointmentsForEmployee(Employee employee) {
        List<ProcedureAppointment> appointments = appointmentRepository
                .findProcedureAppointmentsByPerformingEmployee(employee);

        appointmentRepository.deleteAll(appointments);
    }

    public void save(ProcedureAppointment appointment) {
        appointmentRepository.save(appointment);
    }
}
