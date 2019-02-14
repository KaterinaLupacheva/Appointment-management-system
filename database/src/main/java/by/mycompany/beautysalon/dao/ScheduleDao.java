package by.mycompany.beautysalon.dao;

import by.mycompany.beautysalon.entity.Availability;
import by.mycompany.beautysalon.entity.Schedule;
import by.mycompany.beautysalon.entity.ScheduleDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Repository
public interface ScheduleDao extends JpaRepository<Schedule, Integer> {

    List<Schedule> findScheduleByMasterId(int masterId);

//    public List<Schedule> getScheduleByMasterId(int masterId);

    List<Schedule> findScheduleByDayEquals(LocalDate day);

//    List<Schedule> getSchedulesByDay(LocalDate day);

    @Query("from Schedule where master_id = :id and day = :day")
    Schedule getScheduleByMasterIdAndDay(@Param("id") int masterId,
                                         @Param("day") LocalDate day);
}
