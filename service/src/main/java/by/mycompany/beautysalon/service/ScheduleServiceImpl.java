package by.mycompany.beautysalon.service;

import by.mycompany.beautysalon.dao.MasterDao;
import by.mycompany.beautysalon.dao.ScheduleDao;
import by.mycompany.beautysalon.dto.ScheduleDto;
import by.mycompany.beautysalon.entity.Master;
import by.mycompany.beautysalon.entity.Schedule;
import by.mycompany.beautysalon.entity.ScheduleDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class ScheduleServiceImpl extends BaseServiceImpl<Schedule, Integer> implements ScheduleService {

    @Autowired
    private ScheduleDao scheduleDao;

    @Autowired
    private MasterDao masterDao;

    @Override
    @Transactional
    public List<Schedule> getScheduleByMasterId(int masterId) {
        return scheduleDao.getScheduleByMasterId(masterId);
    }

    @Override
    @Transactional
    public void add(Schedule schedule) {
        scheduleDao.add(schedule);
    }

    @Override
    @Transactional
    public void doSchedule(ScheduleDto scheduleDto, int masterId) {
        Schedule schedule = new Schedule();
        schedule.setDay(scheduleDto.getDay());
        schedule.setStartTime(scheduleDto.getStartTime());
        schedule.setEnd_time(scheduleDto.getEnd_time());
        scheduleDao.add(schedule);
        Master master= masterDao.find(masterId);
        master.addSchedule(schedule);
    }

    @Override
    public List<Schedule> getSchedulesByDay(LocalDate day) {
        return scheduleDao.getSchedulesByDay(day);
    }

    @Override
    @Transactional
    public Schedule getScheduleByMasterIdAndDay(int masterId, LocalDate day) {
        return scheduleDao.getScheduleByMasterIdAndDay(masterId, day);
    }

}
