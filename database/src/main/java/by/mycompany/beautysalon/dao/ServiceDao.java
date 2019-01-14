package by.mycompany.beautysalon.dao;

import by.mycompany.beautysalon.dto.ServiceDto;
import by.mycompany.beautysalon.entity.Service;

public interface ServiceDao extends BaseDao<Service, Integer> {

    public Service getServiceByTitle(String title);

    void saveServiceDto(ServiceDto serviceDto);

    void updateServiceDto(ServiceDto serviceDto);
}
