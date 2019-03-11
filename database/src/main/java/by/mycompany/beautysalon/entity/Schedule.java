package by.mycompany.beautysalon.entity;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Entity
@Table(name = "schedule", schema = "beauty_salon")
public class Schedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "day")
    private LocalDate day;

    @Column(name = "start_time")
    private LocalTime startTime;

    @Column(name = "end_time")
    private LocalTime end_time;

    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "master_id")
    private Master master;

    @OneToMany(mappedBy = "schedule", cascade = CascadeType.MERGE)
    private List<ScheduleDetails> allScheduleDetails;

    public Schedule() {
    }

    public Schedule(LocalDate day, LocalTime startTime, LocalTime end_time) {
        this.day = day;
        this.startTime = startTime;
        this.end_time = end_time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getDay() {
        return day;
    }

    public void setDay(LocalDate day) {
        this.day = day;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEnd_time() {
        return end_time;
    }

    public void setEnd_time(LocalTime end_time) {
        this.end_time = end_time;
    }

    public Master getMaster() {
        return master;
    }

    public void setMaster(Master master) {
        this.master = master;
    }

    public List<ScheduleDetails> getAllScheduleDetails() {
        Collections.sort(allScheduleDetails);
        return allScheduleDetails;
    }

    public void setAllScheduleDetails(List<ScheduleDetails> allScheduleDetails) {
        this.allScheduleDetails = allScheduleDetails;
    }

    public void addScheduleDetails(ScheduleDetails scheduleDetails) {
        if (allScheduleDetails == null) {
            allScheduleDetails = new ArrayList<>();
        }

        allScheduleDetails.add(scheduleDetails);
        scheduleDetails.setSchedule(this);
    }

    @Override
    public String toString() {
        return "Schedule{" +
                "id=" + id +
                ", day=" + day +
                ", startTime=" + startTime +
                ", end_time=" + end_time +
                ", master=" + master +
                ", allScheduleDetails=" + allScheduleDetails +
                '}';
    }
}

