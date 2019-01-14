package by.mycompany.beautysalon.service;

import by.mycompany.beautysalon.dao.AppointmentDao;
import by.mycompany.beautysalon.entity.Appointment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class AppointmentServiceImpl extends BaseServiceImpl<Appointment, Integer> implements AppointmentService {

    @Autowired
    private AppointmentDao appointmentDao;

    @Override
    @Transactional
    public List<Appointment> getAppointmentsForDay(LocalDate date) {
        return appointmentDao.getAppointmentsForDay(date);
    }
}
