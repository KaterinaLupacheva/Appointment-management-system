package by.mycompany.beautysalon.service;

import by.mycompany.beautysalon.dto.ScheduleDto;
import by.mycompany.beautysalon.entity.Schedule;

import java.time.LocalDate;
import java.util.List;

public interface ScheduleService extends BaseService<Schedule, Integer> {

    public List<Schedule> getScheduleByMasterId(int masterId);

    void add (Schedule schedule);

    void doSchedule(ScheduleDto scheduleDto, int masterId);

    List<Schedule> getSchedulesByDay(LocalDate day);

    Schedule getScheduleByMasterIdAndDay(int masterId, LocalDate day);
}
