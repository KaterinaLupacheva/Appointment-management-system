package by.mycompany.beautysalon.entity;

import javax.persistence.*;
import java.sql.Time;
import java.time.LocalTime;

@Entity
@Table(name = "schedule_details", schema = "beauty_salon")
public class ScheduleDetails implements Comparable<ScheduleDetails> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "slot")
    private LocalTime slot;

    @Enumerated(EnumType.STRING)
    @Column(name = "available")
    private Availability available;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "schedule_id")
    private Schedule schedule;

    public ScheduleDetails() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalTime getSlot() {
        return slot;
    }

    public void setSlot(LocalTime slot) {
        this.slot = slot;
    }

    public Availability getAvailable() {
        return available;
    }

    public void setAvailable(Availability available) {
        this.available = available;
    }

    public Schedule getSchedule() {
        return schedule;
    }

    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
    }

    @Override
    public String toString() {
        return "ScheduleDetails{" +
                "id=" + id +
                ", slot=" + slot +
                ", available=" + available +
                '}';
    }

    @Override
    public int compareTo(ScheduleDetails scheduleDetails) {
        if(id == scheduleDetails.id) {
            return 0;
        } else if(id > scheduleDetails.id) {
            return 1;
        } else {
            return -1;
        }
    }
}
