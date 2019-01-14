package by.mycompany.beautysalon.controller;

import by.mycompany.beautysalon.dto.AppointmentDto;
import by.mycompany.beautysalon.dto.AppointmentScheduleDto;
import by.mycompany.beautysalon.dto.CurrentScheduleDto;
import by.mycompany.beautysalon.entity.*;
import by.mycompany.beautysalon.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Controller
@RequestMapping("/admin")
public class AdminScheduleController {

    @Value("${schedule.interval}")
    private Integer interval;

    @Autowired
    private ScheduleService scheduleService;

    @Autowired
    private MasterService masterService;

    @Autowired
    private AppointmentService appointmentService;

    @Autowired
    private ScheduleDetailsService scheduleDetailsService;

    @Autowired
    private ServiceService serviceService;

    @Autowired
    private ClientService clientService;

    @ModelAttribute("scheduleDate")
    public Set<LocalDate> getAllWorkingDays() {
        List<Schedule> scheduleList = scheduleService.findAll();
        Set<LocalDate> workingDays = new TreeSet<>();
        for (Schedule schedule : scheduleList) {
            workingDays.add(schedule.getDay());
        }
        return workingDays;
    }

    @ModelAttribute("allMasters")
    public List<String> getAllMastersNames() {
        List<Master> masters = masterService.findAll();
        List<String> masterNames = new ArrayList<>();
        for (Master tempMaster : masters) {
            masterNames.add(tempMaster.getLastName());
        }
        Collections.sort(masterNames);
        return masterNames;
    }

//    @ModelAttribute("times")
//    public List<LocalTime> setTimeSlots() {
//        List<LocalTime> timeSlots = new ArrayList<>();
//        LocalTime tempTime = LocalTime.of(9, 00);
//        timeSlots.add(tempTime);
//        while (tempTime.isBefore(LocalTime.of(21, 00))) {
//            tempTime = tempTime.plusMinutes(30);
//            timeSlots.add(tempTime);
//        }
//        Collections.sort(timeSlots);
//        return timeSlots;
//    }

    @GetMapping("/appointment_menu")
    public String showAppointmentMenu(Model model) {
        model.addAttribute("currentScheduleDto", new CurrentScheduleDto());
        return "appointment_menu";
    }

    @PostMapping("/showSchedule")
    @Transactional
    public String showSchedule(@ModelAttribute("currentScheduleDto") CurrentScheduleDto currentScheduleDto, Model model) {
        List<Schedule> list = scheduleService.getSchedulesByDay(currentScheduleDto.getCurrentDate());
        Map<Schedule, List<AppointmentScheduleDto>> scheduleListMap = getScheduleListMap(list);
        model.addAttribute("appointmentList", scheduleListMap);
        model.addAttribute("currentScheduleDto", currentScheduleDto);
        return "currentSchedule";
    }

    @GetMapping("/showSchedule")
    @Transactional
    public String showScheduleGet(@ModelAttribute("currentScheduleDto") CurrentScheduleDto currentScheduleDto,
                                  Model model, @RequestParam(value = "date") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date) {
        List<Schedule> list = scheduleService.getSchedulesByDay(date);
        Map<Schedule, List<AppointmentScheduleDto>> scheduleListMap = getScheduleListMap(list);
        model.addAttribute("appointmentList", scheduleListMap);
        model.addAttribute("currentScheduleDto", currentScheduleDto);
        model.addAttribute("date", date);
        return "currentSchedule";
    }

    @GetMapping("/showSchedule1")
    @Transactional
    public String showScheduleGet1(@ModelAttribute("currentScheduleDto") CurrentScheduleDto currentScheduleDto,
                                  Model model, @RequestParam(value = "date") @DateTimeFormat(pattern = "dd.MM.yy") LocalDate date1) {
        List<Schedule> list = scheduleService.getSchedulesByDay(date1);
        Map<Schedule, List<AppointmentScheduleDto>> scheduleListMap = getScheduleListMap(list);
        model.addAttribute("appointmentList", scheduleListMap);
        model.addAttribute("currentScheduleDto", currentScheduleDto);
        model.addAttribute("date", date1);
        return "currentSchedule";
    }


    private Map<Schedule, List<AppointmentScheduleDto>> getScheduleListMap(List<Schedule> list) {
        Map<Schedule, List<AppointmentScheduleDto>> scheduleListMap = new HashMap<>();
        for (Schedule scd : list) {
            List<AppointmentScheduleDto> appointmentScheduleDtoList = new ArrayList<>();
            List<Appointment> appointments = scd.getMaster().getAppointments();
            List<ScheduleDetails> allScheduleDetails = scd.getAllScheduleDetails();
            for (ScheduleDetails scdDetails : allScheduleDetails) {
                AppointmentScheduleDto appointmentScheduleDto = new AppointmentScheduleDto();
                appointmentScheduleDto.setSlot(scdDetails.getSlot());
                appointmentScheduleDto.setSlotId(scdDetails.getId());
                if (scdDetails.getAvailable().equals(Availability.YES)) {
                    appointmentScheduleDto.setSlotAppointment("Free");
                } else {
                    for (Appointment tempAppointment : appointments) {
                        if (tempAppointment.getStartTime().equals(scdDetails.getSlot())) {
                            appointmentScheduleDto.setSlotAppointment(tempAppointment.getService().getTitle());
                            appointmentScheduleDto.setClientName(tempAppointment.getClient().getLastName());
                            appointmentScheduleDto.setClientPhone(tempAppointment.getClient().getPhone());
                            appointmentScheduleDto.setAppointmentId(tempAppointment.getId());
                        }
                    }
                }
                if (appointmentScheduleDto.getSlotAppointment() != null) {
                    appointmentScheduleDtoList.add(appointmentScheduleDto);
                }
            }
            scheduleListMap.put(scd, appointmentScheduleDtoList);
        }
        return scheduleListMap;
    }

    @PostMapping("/showMasterSchedule")
    @Transactional
    public String showMasterSchedule(@ModelAttribute("currentScheduleDto") CurrentScheduleDto currentScheduleDto,
                                     Model model) {
        Master masterByLastName = masterService.getMasterByLastName(currentScheduleDto.getMasterName());
        List<Schedule> scheduleList = scheduleService.getScheduleByMasterId(masterByLastName.getId());
        Map<Schedule, List<AppointmentScheduleDto>> scheduleListMap = getScheduleListMap(scheduleList);
        model.addAttribute("appointmentList", scheduleListMap);
        model.addAttribute("currentScheduleDto", currentScheduleDto);
        return "masterSchedule";
    }

    @GetMapping("/showMasterSchedule")
    @Transactional
    public String showMasterSchedule(@ModelAttribute("currentScheduleDto") CurrentScheduleDto currentScheduleDto,
                                     @RequestParam("master") String masterName,
                                     Model model) {
        Master masterByLastName = masterService.getMasterByLastName(masterName);
        List<Schedule> scheduleList = scheduleService.getScheduleByMasterId(masterByLastName.getId());
        Map<Schedule, List<AppointmentScheduleDto>> scheduleListMap = getScheduleListMap(scheduleList);
        model.addAttribute("appointmentList", scheduleListMap);
        model.addAttribute("currentScheduleDto", currentScheduleDto);
        model.addAttribute("master", masterName);
        return "masterSchedule";
    }

//    private List<CurrentScheduleDto> getCurrentScheduleDtos(List<Schedule> scheduleList, List<CurrentScheduleDto> currentSchedule, List<List<CurrentScheduleDto>> allSchedules) {
//        List<ScheduleDetails> scheduleDetails;
//        for (Schedule theSchedule : scheduleList) {
//            scheduleDetails = theSchedule.getAllScheduleDetails();
//            currentSchedule = new ArrayList<>();
//            for (ScheduleDetails scd : scheduleDetails) {
//                CurrentScheduleDto scheduleDto = new CurrentScheduleDto();
//                scheduleDto.setSlot(scd.getSlot());
//                scheduleDto.setAvailable(scd.getAvailable());
//                currentSchedule.add(scheduleDto);
//            }
//            allSchedules.add(currentSchedule);
//        }
//        return currentSchedule;
//    }

    @GetMapping("/showAppointment")
    public String showAppointment(@RequestParam("appointmentId") int id,
                                  @RequestParam(value = "masterName", required = false) String masterName,
                                  Model model) {
        Appointment appointment = appointmentService.find(id);
        AppointmentDto appointmentDto = makeAppointmentDto(appointment);
        model.addAttribute("appointmentDto", appointmentDto);
        if(masterName != null) {
            model.addAttribute("masterName", masterName);
        }
        return "view-appointment";
    }

    private AppointmentDto makeAppointmentDto(Appointment appointment) {
        AppointmentDto appointmentDto = new AppointmentDto();
        appointmentDto.setId(appointment.getId());
        appointmentDto.setDate(appointment.getDay());
        appointmentDto.setStartTime(appointment.getStartTime());
        appointmentDto.setService(appointment.getService().getTitle());
        appointmentDto.setClientName(appointment.getClient().getLastName());
        appointmentDto.setClientPhone(appointment.getClient().getPhone());
        appointmentDto.setComment(appointment.getComment());
        return appointmentDto;
    }

//    @GetMapping("/editAppointment")
//    public String editAppointment(@RequestParam("appointmentId") int id, Model model) {
//        Appointment appointment = appointmentService.find(id);
//        AppointmentDto appointmentDto = makeAppointmentDto(appointment);
//        //delete appointment, add new (autofill existing fields)
//
//        model.addAttribute("appointmentDto", appointmentDto);
//        return "appointment-form";
//    }

    @GetMapping("/deleteAppointment")
    public String deleteAppointment(@RequestParam("appointmentId") int id,
                                    @RequestParam(value = "masterName", required = false) String masterName,
                                    RedirectAttributes redirectAttributes) {
        Appointment appointment = appointmentService.find(id);
        int masterId = appointment.getMaster().getId();
        LocalDate day = appointment.getDay();
        Schedule schedule = scheduleService.getScheduleByMasterIdAndDay(masterId, day);
        List<ScheduleDetails> allScheduleDetails = schedule.getAllScheduleDetails();
        for (ScheduleDetails scd : allScheduleDetails) {
            if (scd.getSlot().equals(appointment.getStartTime())) {
                int duration = appointment.getService().getDuration();
                int numberOfSlots = duration / interval;
                int startId = scd.getId();
                for (int i = startId; i < (startId + numberOfSlots); i++) {
                    ScheduleDetails tempScd = scheduleDetailsService.find(i);
                    tempScd.setAvailable(Availability.YES);
                    scheduleDetailsService.update(tempScd);
                }
            }
        }
        appointmentService.delete(appointment);
        if (!masterName.isEmpty()) {
            redirectAttributes.addAttribute("master", masterName);
            return "redirect:/admin/showMasterSchedule";
        } else {
        redirectAttributes.addAttribute("date", day);
        return "redirect:/admin/showSchedule1";}
    }

    @GetMapping("/addAppointment")
    public String addAppointment(@RequestParam("date") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date,
                                 @RequestParam("slot") LocalTime startTime,
                                 @RequestParam("scheduleId") int id,
                                 @RequestParam("slotId") int slotId,
                                 @RequestParam(value = "check", required = false) Integer check,
                                 Model model) {
        AppointmentDto appointmentDto = new AppointmentDto();
        appointmentDto.setDate(date);
        appointmentDto.setStartTime(startTime);
        appointmentDto.setReservedSlot(slotId);
        Master master = scheduleService.find(id).getMaster();
        appointmentDto.setMasterId(master.getId());
        String masterName = master.getLastName();
        List<Service> services = master.getServices();
        Set<String> titles = new HashSet<>();
        for (Service tempService : services) {
            titles.add(tempService.getTitle());
        }
        model.addAttribute("services", titles);
        model.addAttribute("master", masterName);
        model.addAttribute("appointmentDto", appointmentDto);
        model.addAttribute("check", check);
        return "addAppointment";
    }

    @PostMapping("/saveAppointmentByAdmin")
    public String saveAppointment(@ModelAttribute("appointmentDto") AppointmentDto appointmentDto,
                                  Model model, RedirectAttributes redirectAttributes) {
        Service service = serviceService.getServiceByTitle(appointmentDto.getService());
        int duration = service.getDuration();
        int numOfSlots = duration / interval;
        int startId = appointmentDto.getReservedSlot();
        List<ScheduleDetails> tempResult = new ArrayList<>();
        for (int i = startId; i < (startId + numOfSlots); i++) {
            ScheduleDetails scd = scheduleDetailsService.find(i);
            if (scd.getAvailable().equals(Availability.YES)) {
                scd.setAvailable(Availability.NO);
                tempResult.add(scd);
            } else {
                tempResult.clear();
            }
        }
        if (tempResult.size() == numOfSlots) {
            for (ScheduleDetails tempScd : tempResult) {
                scheduleDetailsService.update(tempScd);
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
            appointment.setStartTime(appointmentDto.getStartTime());
            Master master = masterService.find(appointmentDto.getMasterId());
            appointment.setMaster(master);
            appointment.setService(service);
            appointment.setClient(client);
            appointment.setComment(appointmentDto.getComment());
            appointmentService.save(appointment);

            if(appointmentDto.getCheck() != null) {
                redirectAttributes.addAttribute("master", master.getLastName());
                return "redirect:/admin/showMasterSchedule";
            } else {
            redirectAttributes.addAttribute("date", appointmentDto.getDate());
            return "redirect:/admin/showSchedule1"; }
        } else {
            model.addAttribute("service", service);
            model.addAttribute("duration", duration);
            model.addAttribute("startTime", appointmentDto.getStartTime());
            return "errorAddAppointment";
        }
    }
}
