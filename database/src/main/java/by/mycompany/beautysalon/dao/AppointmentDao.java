package by.mycompany.beautysalon.dao;

import by.mycompany.beautysalon.entity.Appointment;

import java.time.LocalDate;
import java.util.List;

public interface AppointmentDao extends BaseDao<Appointment, Integer> {

    public List<Appointment> getAppointmentsForDay(LocalDate date);
}
