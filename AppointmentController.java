package by.mycompany.beautysalon.controller;

import by.mycompany.beautysalon.dto.AppointmentDto;
import by.mycompany.beautysalon.dto.MasterScheduleDto;
import by.mycompany.beautysalon.entity.*;
import by.mycompany.beautysalon.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

@Controller
@SessionAttributes("appointmentDto")
public class AppointmentController {

    @Value("${schedule.interval}")
    private Integer interval;

    @Autowired
    private ScheduleService scheduleService;

    @Autowired
    private ServiceService serviceService;

    @Autowired
    private ScheduleDetailsService scheduleDetailsService;

    @Autowired
    private ClientService clientService;

    @Autowired
    private AppointmentService appointmentService;

    @Autowired
    private EmailService emailService;

    @ModelAttribute("scheduleDate")
    public Set<LocalDate> getAllWorkingDays() {
        List<Schedule> scheduleList = scheduleService.findAll();
        Set<LocalDate> workingDays = new TreeSet<>();
        for(Schedule schedule : scheduleList) {
            workingDays.add(schedule.getDay());
        }
        return workingDays;
    }

    @ModelAttribute("allServices")
    public Set<String> getAllServices() {
        List<Service> services = serviceService.findAll();
        Set<String> titles = new HashSet<>();
        titles.add(" ");
        for(Service tempService : services) {
            titles.add(tempService.getTitle());
        }
        return titles;
    }

    @ModelAttribute("masterAppointmentList")
    private List<MasterScheduleDto> setMasterScheduleList(ArrayList<Schedule> list, AppointmentDto appointmentDto) {
        List<MasterScheduleDto> masterScheduleList = new ArrayList<>();
        for (Schedule tempSchedule : list) {
            List<Service> services = tempSchedule.getMaster().getServices();
            MasterScheduleDto masterScheduleDto = new MasterScheduleDto();
            for (Service tempService : services) {
                if (tempService.getTitle().equals(appointmentDto.getService())) {
                    masterScheduleDto.setMasterId(tempSchedule.getMaster().getId());
                    masterScheduleDto.setMasterName(tempSchedule.getMaster().getLastName());
                    masterScheduleDto.setAvailableSlots(availableSlots(tempSchedule, tempService));
                    masterScheduleList.add(masterScheduleDto);
                }
            }
        }
        return masterScheduleList;
    }

    @GetMapping("/appointment")
    public String submitAppointment(Model model) {
        model.addAttribute("appointmentDto", new AppointmentDto());
        return "appointment";
    }


    @PostMapping("/chooseMaster")
    @Transactional
    public String chooseMaster(@ModelAttribute("appointmentDto") AppointmentDto appointmentDto, Model model) {
        ArrayList<Schedule> list = (ArrayList<Schedule>) scheduleService.getSchedulesByDay(appointmentDto.getDate());
        List<MasterScheduleDto> masterScheduleList = setMasterScheduleList(list, appointmentDto);
        for(MasterScheduleDto msd : masterScheduleList) {
            Map<Integer, LocalTime> availableSlots = msd.getAvailableSlots();
            Map<Integer, LocalTime> sortedMap = new TreeMap<>(availableSlots);
            msd.setAvailableSlots(sortedMap);
        }
        appointmentDto.setMasterScheduleDtos(masterScheduleList);
        model.addAttribute("masterAppointmentList", masterScheduleList);
        return "master-appointment";
    }

    private Map<Integer, LocalTime> availableSlots(Schedule schedule, Service service) {
        Map<Integer, LocalTime> result = new HashMap<>();
        List<ScheduleDetails> scheduleDetails = schedule.getAllScheduleDetails();
        int duration = service.getDuration();
        int numOfSlots = duration / interval;
        Map<Integer, LocalTime> tempResult = new HashMap<>();
        for(ScheduleDetails scd : scheduleDetails) {
            if (scd.getAvailable().equals(Availability.YES)) {
                tempResult.put(scd.getId(), scd.getSlot());
            } else {
                tempResult.clear();
            }

            if (tempResult.size() == numOfSlots) {
                Set<Integer> keySet = tempResult.keySet();
                Integer min = Collections.min(keySet);
                LocalTime localTime = tempResult.get(min);
                result.put(min, localTime);
                tempResult.remove(min);
            }
        }
        return result;
    }

    @PostMapping("/submitAppointment")
    public String submitAppointment(@ModelAttribute("appointmentDto") AppointmentDto appointmentDto,
                                    SessionStatus status) {
        ScheduleDetails scheduleDetails = scheduleDetailsService.find(appointmentDto.getReservedSlot());
        Service service = serviceService.getServiceByTitle(appointmentDto.getService());
        int duration = service.getDuration();
        int numOfSlots = duration / interval;
        int startId = appointmentDto.getReservedSlot();
        for(int i = startId; i<(startId+numOfSlots); i++) {
           ScheduleDetails scd = scheduleDetailsService.find(i);
            scd.setAvailable(Availability.NO);
            scheduleDetailsService.update(scd);
        }
        Client client = clientService.getClientByPhone(appointmentDto.getClientPhone());
        if (client == null) {
            Client client1 = new Client();
            client1.setLastName(appointmentDto.getClientName());
            client1.setPhone(appointmentDto.getClientPhone());
            clientService.save(client1);
            client = client1;
        }

        Appointment appointment = new Appointment();
        appointment.setDay(appointmentDto.getDate());
        appointment.setStartTime(scheduleDetails.getSlot());
        appointment.setClient(client);
        appointment.setService(service);
        Schedule schedule = scheduleDetails.getSchedule();
        appointment.setMaster(schedule.getMaster());
        appointment.setComment(appointmentDto.getComment());
        appointmentService.save(appointment);

        //notification to admin
        emailService.sendSimpleMessage("katerinalupacheva@gmail.com", "New Appointment!",
                "New appointment: " + "\n\t\tDate: " + appointment.getDay()
                        + "\n\t\tService: " + appointmentDto.getService()
                        + "\n\t\tTime: " + appointment.getStartTime()
                        + "\n\t\tMaster: " + appointment.getMaster().getLastName()
                        + "\nClient: " + "\n\t\tName: " + client.getLastName()
                        + "\n\t\tPhone: " + client.getPhone());

        status.setComplete();
        return "appointmentSuccess";
    }

}
