package by.mycompany.beautysalon.service;

import by.mycompany.beautysalon.dao.MasterDao;
import by.mycompany.beautysalon.dao.ScheduleAddDao;
import by.mycompany.beautysalon.dao.ScheduleDao;
import by.mycompany.beautysalon.dto.ScheduleDto;
import by.mycompany.beautysalon.entity.Master;
import by.mycompany.beautysalon.entity.Schedule;
import by.mycompany.beautysalon.entity.ScheduleDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class ScheduleServiceImpl extends BaseServiceImpl<Schedule, Integer> implements ScheduleService {

    @Autowired
    private ScheduleDao scheduleDao;

    @Autowired
    private ScheduleAddDao scheduleAddDao;

    @Autowired
    private MasterDao masterDao;

    @Override
    @Transactional
    public List<Schedule> getScheduleByMasterId(int masterId) {
        return scheduleDao.findScheduleByMasterId(masterId);
    }

    @Override
    @Transactional
    public void add(Schedule schedule) {
        scheduleAddDao.add(schedule);
    }

    @Override
    @Transactional
    public void doSchedule(ScheduleDto scheduleDto, int masterId) {
        Schedule schedule = new Schedule();
        schedule.setDay(scheduleDto.getDay());
        schedule.setStartTime(scheduleDto.getStartTime());
        schedule.setEnd_time(scheduleDto.getEnd_time());
        scheduleDao.save(schedule);
        Optional<Master> master= masterDao.findById(masterId);
        master.get().addSchedule(schedule);
    }

    @Override
    public List<Schedule> getSchedulesByDay(LocalDate day) {
        return scheduleDao.findScheduleByDayEquals(day);
    }

    @Override
    @Transactional
    public Schedule getScheduleByMasterIdAndDay(int masterId, LocalDate day) {
        return scheduleDao.getScheduleByMasterIdAndDay(masterId, day);
    }

}
