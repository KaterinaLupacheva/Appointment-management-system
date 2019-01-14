package by.mycompany.beautysalon.dto;


import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;

public class AppointmentDto {

    private int id;

    private String clientName;

    private String clientPhone;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;

    private String service;

    private int reservedSlot;

    private List<MasterScheduleDto> masterScheduleDtos;

    private LocalTime startTime;

    private int masterId;

    private Integer check;

    private String comment;

    public AppointmentDto() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public int getReservedSlot() {
        return reservedSlot;
    }

    public void setReservedSlot(int reservedSlot) {
        this.reservedSlot = reservedSlot;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getClientPhone() {
        return clientPhone;
    }

    public void setClientPhone(String clientPhone) {
        this.clientPhone = clientPhone;
    }

    public List<MasterScheduleDto> getMasterScheduleDtos() {
        return masterScheduleDtos;
    }

    public void setMasterScheduleDtos(List<MasterScheduleDto> masterScheduleDtos) {
        this.masterScheduleDtos = masterScheduleDtos;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public int getMasterId() {
        return masterId;
    }

    public void setMasterId(int masterId) {
        this.masterId = masterId;
    }

    public Integer getCheck() {
        return check;
    }

    public void setCheck(Integer check) {
        this.check = check;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
