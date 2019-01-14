package by.mycompany.beautysalon.tests;

import by.mycompany.beautysalon.configuration.ApplicationConfiguration;
import by.mycompany.beautysalon.entity.Master;
import by.mycompany.beautysalon.entity.Service;
import by.mycompany.beautysalon.service.MasterService;
import by.mycompany.beautysalon.service.ServiceService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.transaction.annotation.Transactional;


public class ServiceAndMaster {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(ApplicationConfiguration.class);

        ServiceService serviceService = context.getBean(ServiceService.class);
        MasterService masterService = context.getBean(MasterService.class);

        serviceService.addMaster(2,2);

    }
}
