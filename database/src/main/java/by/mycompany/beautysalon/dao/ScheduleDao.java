package by.mycompany.beautysalon.dao;

import by.mycompany.beautysalon.entity.Schedule;

import java.time.LocalDate;
import java.util.List;

public interface ScheduleDao extends BaseDao<Schedule, Integer> {

    public List<Schedule> getScheduleByMasterId(int masterId);

    void add (Schedule schedule);

    List<Schedule> getSchedulesByDay(LocalDate day);

    Schedule getScheduleByMasterIdAndDay(int masterId, LocalDate day);
}
