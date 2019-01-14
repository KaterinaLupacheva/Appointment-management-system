package by.mycompany.beautysalon.controller;

import by.mycompany.beautysalon.dto.ServiceDto;
import by.mycompany.beautysalon.entity.Service;
import by.mycompany.beautysalon.service.ServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminServiceController {

    @Autowired
    private ServiceService serviceService;

    @GetMapping("/service_menu")
    public String showServiceMenu(Model model) {
        List<Service> services = serviceService.findAll();
        model.addAttribute("services", services);
        return "service_menu";
    }

    @GetMapping("/showFormForAddService")
    public String showFormForAddService(Model model) {
        model.addAttribute("serviceDto", new ServiceDto());
        return "service-form";
    }

    @PostMapping("/saveService")
    public String saveService(ServiceDto serviceDto) {
        serviceService.saveServiceDto(serviceDto);
        return "redirect:/admin/service_menu";
    }

    @PostMapping("/updateService")
    public String updateService(@ModelAttribute("service") Service service) {
        serviceService.update(service);
        return "redirect:/admin/service_menu";
    }

    @GetMapping("/delete")
    public String deleteService(@RequestParam("serviceId") int theId) {
        Service service = serviceService.find(theId);
        serviceService.delete(service);
        return "redirect:/admin/service_menu";
    }

    @GetMapping("/showFormForUpdateService")
    public String showFormForUpdateService(@RequestParam("serviceId") int theId, Model model) {
        Service service = serviceService.find(theId);
        model.addAttribute("service", service);
        return "service-form-update";
    }
}
