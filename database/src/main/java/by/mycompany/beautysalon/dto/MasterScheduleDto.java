package by.mycompany.beautysalon.dto;

import java.time.LocalTime;
import java.util.Map;

public class MasterScheduleDto {

    private int masterId;

    private String masterName;

    private Map<Integer, LocalTime> availableSlots;

    public MasterScheduleDto() {
    }

    public int getMasterId() {
        return masterId;
    }

    public void setMasterId(int masterId) {
        this.masterId = masterId;
    }

    public String getMasterName() {
        return masterName;
    }

    public void setMasterName(String masterName) {
        this.masterName = masterName;
    }

    public Map<Integer, LocalTime> getAvailableSlots() {
        return availableSlots;
    }

    public void setAvailableSlots(Map<Integer, LocalTime> availableSlots) {
        this.availableSlots = availableSlots;
    }

    @Override
    public String toString() {
        return "MasterScheduleDto{" +
                "masterId=" + masterId +
                ", masterName='" + masterName + '\'' +
                ", availableSlots=" + availableSlots +
                '}';
    }
}
