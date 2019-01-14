package by.mycompany.beautysalon.dto;

import by.mycompany.beautysalon.entity.Availability;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;

public class CurrentScheduleDto {

    private LocalDate currentDate;

    private LocalTime slot;

    @Enumerated(EnumType.STRING)
    private Availability available;

    private String masterName;

    public CurrentScheduleDto() {
    }

    public LocalDate getCurrentDate() {
        return currentDate;
    }

    public void setCurrentDate(LocalDate currentDate) {
        this.currentDate = currentDate;
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

    public String getMasterName() {
        return masterName;
    }

    public void setMasterName(String masterName) {
        this.masterName = masterName;
    }

    @Override
    public String toString() {
        return "CurrentScheduleDto{" +
                "currentDate=" + currentDate +
                ", slot=" + slot +
                ", available=" + available +
                ", masterName='" + masterName + '\'' +
                '}';
    }
}
