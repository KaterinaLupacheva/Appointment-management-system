package by.mycompany.beautysalon.service;

import by.mycompany.beautysalon.dao.MasterDao;
import by.mycompany.beautysalon.dao.ServiceDao;
import by.mycompany.beautysalon.dto.ServiceDto;
import by.mycompany.beautysalon.entity.Master;
import by.mycompany.beautysalon.entity.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

@org.springframework.stereotype.Service
@Transactional(readOnly = true)
public class ServiceServiceImpl extends BaseServiceImpl<Service, Integer> implements ServiceService {

    @Autowired
    private ServiceDao serviceDao;

    @Autowired
    private MasterDao masterDao;

    @Override
    @Transactional
    public Service getServiceByTitle(String title) {
        return serviceDao.getServiceByTitle(title);
    }

    @Override
    @Transactional
    public void saveServiceDto(ServiceDto serviceDto) {
        serviceDao.saveServiceDto(serviceDto);
    }

    @Override
    @Transactional
    public void addMaster(int serviceId, int masterId) {
        Service service = serviceDao.find(serviceId);
        Master master = masterDao.find(masterId);
        service.addMaster(master);
        serviceDao.update(service);
    }

    @Override
    @Transactional
    public void updateServiceDto(ServiceDto serviceDto) {
        serviceDao.updateServiceDto(serviceDto);
    }
}
