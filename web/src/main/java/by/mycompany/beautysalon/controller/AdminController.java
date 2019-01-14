package by.mycompany.beautysalon.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/admin")
public class AdminController {

    @GetMapping("/startpage")
    public String showStartPage() {
        return "startpage";
    }


}
