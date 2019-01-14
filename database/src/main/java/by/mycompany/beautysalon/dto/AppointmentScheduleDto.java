package by.mycompany.beautysalon.dto;

import java.time.LocalTime;

public class AppointmentScheduleDto {

    private LocalTime slot;

    private String slotAppointment;

    private int slotId;

    private String clientName;

    private String clientPhone;

    private int appointmentId;

    public AppointmentScheduleDto() {
    }

    public LocalTime getSlot() {
        return slot;
    }

    public void setSlot(LocalTime slot) {
        this.slot = slot;
    }

    public String getSlotAppointment() {
        return slotAppointment;
    }

    public void setSlotAppointment(String slotAppointment) {
        this.slotAppointment = slotAppointment;
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

    public int getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(int appointmentId) {
        this.appointmentId = appointmentId;
    }

    public int getSlotId() {
        return slotId;
    }

    public void setSlotId(int slotId) {
        this.slotId = slotId;
    }
}
