package by.mycompany.beautysalon.dao;

import by.mycompany.beautysalon.dto.ServiceDto;
import by.mycompany.beautysalon.entity.Service;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ServiceDao extends JpaRepository<Service, Integer> {

    Service findByTitle(String title);
    List<Service> findByTitleContaining(String title);

//    public Service getServiceByTitle(String title);

//    void saveServiceDto(ServiceDto serviceDto);

//    void updateServiceDto(ServiceDto serviceDto);
}
