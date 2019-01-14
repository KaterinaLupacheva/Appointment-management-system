package by.mycompany.beautysalon.controller;

import by.mycompany.beautysalon.dto.MasterDto;
import by.mycompany.beautysalon.dto.ScheduleDto;
import by.mycompany.beautysalon.entity.Master;
import by.mycompany.beautysalon.entity.Schedule;
import by.mycompany.beautysalon.entity.Service;
import by.mycompany.beautysalon.service.MasterService;
import by.mycompany.beautysalon.service.ScheduleService;
import by.mycompany.beautysalon.service.ServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/admin")
public class AdminMasterController {

    @Autowired
    private MasterService masterService;

    @Autowired
    private ServiceService serviceService;

    @Autowired
    private ScheduleService scheduleService;

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
    @GetMapping("/master_menu")
    public String showMasterMenu(Model model) {
        List<MasterDto> allMasterDto = masterService.getMasterDtos();
        model.addAttribute("masters", allMasterDto);
        return "master_menu";
    }

    @GetMapping("/showFormForAddMaster")
    public String showFormForAddMaster(Model model) {
        model.addAttribute("masterDto", new MasterDto());
        return "master-form";
    }

    @PostMapping("/saveMaster")
    public String saveMaster(MasterDto masterDto) {
        masterService.saveMasterDto(masterDto);
        return "redirect:/admin/master_menu";
    }

    @GetMapping("/showFormForUpdateMaster")
    public String showFormForUpdateMaster(@RequestParam("masterId") int theId, Model model) {
        MasterDto masterDto= masterService.doMasterDto(theId);
        model.addAttribute("masterDto", masterDto);
        return "master-form-update";
    }

    @PostMapping("/updateMaster")
    public String updateMaster(@ModelAttribute("masterDto") MasterDto masterDto,
                               @RequestParam("services") Set<String> updatedServices) {
        Master master = masterService.doMaster(masterDto, updatedServices);
        masterService.save(master);
        return "redirect:/admin/master_menu";
    }

    @GetMapping("/deleteMaster")
    public String deleteMaster(@RequestParam("masterId") int theId) {
        Master master = masterService.find(theId);
        masterService.delete(master);
        return "redirect:/admin/master_menu";
    }

    @GetMapping("/scheduleMaster")
    public String scheduleMaster(@RequestParam("masterId") int theId, Model model) {
        Master master = masterService.find(theId);
        model.addAttribute("masterId", master.getId());
        model.addAttribute("masterName", master.getLastName());
        model.addAttribute("scheduleDto", new ScheduleDto());
        return "schedule-master-form";
    }

    @PostMapping("/addSchedule")
    public String addSchedule(ScheduleDto scheduleDto,
                              @RequestParam("masterId") int theId){
        scheduleService.doSchedule(scheduleDto, theId);
        return "redirect:/admin/master_menu";
    }
}
