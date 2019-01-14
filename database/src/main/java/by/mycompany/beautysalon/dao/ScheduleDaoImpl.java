package by.mycompany.beautysalon.dao;

import by.mycompany.beautysalon.entity.Availability;
import by.mycompany.beautysalon.entity.Schedule;
import by.mycompany.beautysalon.entity.ScheduleDetails;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static by.mycompany.beautysalon.connection.ConnectionManager.getSession;

@Repository
public class ScheduleDaoImpl extends BaseDaoImpl<Schedule, Integer> implements ScheduleDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Value("${schedule.interval}")
    private Integer interval;

    @Autowired
    private ScheduleDetailsDao scheduleDetailsDao;

    @Override
    public List<Schedule> getScheduleByMasterId(int masterId) {
        Session session = sessionFactory.getCurrentSession();
        Query<Schedule> query = session.createQuery("from Schedule where master_id = :id");
        query.setParameter("id", masterId);
        List<Schedule> list = query.getResultList();
        return list;
    }

    @Override
    public void add(Schedule schedule) {
        LocalTime tempTime = schedule.getStartTime();
        while(tempTime.isBefore(schedule.getEnd_time())) {
            ScheduleDetails scheduleDetails = new ScheduleDetails();
            scheduleDetails.setSlot(tempTime);
            scheduleDetails.setAvailable(Availability.YES);
            schedule.addScheduleDetails(scheduleDetails);
            scheduleDetailsDao.save(scheduleDetails);
            tempTime = tempTime.plusMinutes(interval);

        }
        sessionFactory.getCurrentSession().save(schedule);
    }

    @Override
    public List<Schedule> getSchedulesByDay(LocalDate day) {
        Session session = sessionFactory.getCurrentSession();
        Query<Schedule> query = session.createQuery("from Schedule where day = :day");
        query.setParameter("day", day);
        List<Schedule> result = query.getResultList();
        for(Schedule tempSchedule : result) {
            tempSchedule.getAllScheduleDetails().size();
        }
        return result;
    }

    @Override
    public Schedule getScheduleByMasterIdAndDay(int masterId, LocalDate day) {
        Session session = sessionFactory.getCurrentSession();
        Query<Schedule> query = session.createQuery("from Schedule where master_id = :id and day = :day");
        query.setParameter("id", masterId);
        query.setParameter("day", day);
        Schedule schedule = query.uniqueResult();
        schedule.getAllScheduleDetails().size();
        return schedule;
    }
}
