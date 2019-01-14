package by.mycompany.beautysalon.service;

import by.mycompany.beautysalon.entity.Appointment;

import java.time.LocalDate;
import java.util.List;

public interface AppointmentService extends BaseService<Appointment,Integer> {

    public List<Appointment> getAppointmentsForDay(LocalDate date);
}
