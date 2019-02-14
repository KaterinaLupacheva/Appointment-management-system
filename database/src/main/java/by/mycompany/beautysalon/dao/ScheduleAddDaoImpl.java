package by.mycompany.beautysalon.dao;

import by.mycompany.beautysalon.entity.Availability;
import by.mycompany.beautysalon.entity.Schedule;
import by.mycompany.beautysalon.entity.ScheduleDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.time.LocalTime;

@Repository
public class ScheduleAddDaoImpl implements ScheduleAddDao {

    @Value("${schedule.interval}")
    private Integer interval;

    @Autowired
    private ScheduleDao scheduleDao;

    @Autowired
    private ScheduleDetailsDao scheduleDetailsDao;

    @Override
    public void add(Schedule schedule) {
        LocalTime tempTime = schedule.getStartTime();
        while (tempTime.isBefore(schedule.getEnd_time())) {
            ScheduleDetails scheduleDetails = new ScheduleDetails();
            scheduleDetails.setSlot(tempTime);
            scheduleDetails.setAvailable(Availability.YES);
            schedule.addScheduleDetails(scheduleDetails);
            scheduleDetailsDao.save(scheduleDetails);
            tempTime = tempTime.plusMinutes(interval);
        }
        scheduleDao.save(schedule);
    }
}
