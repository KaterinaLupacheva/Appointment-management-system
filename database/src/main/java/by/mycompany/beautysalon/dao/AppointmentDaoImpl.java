package by.mycompany.beautysalon.dao;

import by.mycompany.beautysalon.entity.Appointment;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public class AppointmentDaoImpl extends BaseDaoImpl<Appointment, Integer> implements AppointmentDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Appointment> getAppointmentsForDay(LocalDate date) {
        Session session = sessionFactory.getCurrentSession();
        Query<Appointment> query = session.createQuery("from Appointment where day = :date");
        query.setParameter("date", date);
        List<Appointment> appointments = query.getResultList();
        return appointments;
    }
}
