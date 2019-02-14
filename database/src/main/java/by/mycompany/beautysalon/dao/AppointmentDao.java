package by.mycompany.beautysalon.dao;

import by.mycompany.beautysalon.entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface AppointmentDao extends JpaRepository<Appointment, Integer> {

    List<Appointment> getAppointmentsByDayEquals(LocalDate date);
//    public List<Appointment> getAppointmentsForDay(LocalDate date);
}
