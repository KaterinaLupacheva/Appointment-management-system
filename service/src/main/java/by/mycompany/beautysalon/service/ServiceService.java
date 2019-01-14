package by.mycompany.beautysalon.service;

import by.mycompany.beautysalon.dto.ServiceDto;
import by.mycompany.beautysalon.entity.Service;

public interface ServiceService extends BaseService<Service, Integer> {

    public Service getServiceByTitle(String title);

    public void saveServiceDto(ServiceDto serviceDto);

    public void addMaster(int serviceId, int masterId);

    void updateServiceDto(ServiceDto serviceDto);
}
