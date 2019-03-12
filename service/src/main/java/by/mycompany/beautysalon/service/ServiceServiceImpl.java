package by.mycompany.beautysalon.service;

import by.mycompany.beautysalon.dao.MasterDao;
import by.mycompany.beautysalon.dao.ServiceDao;
import by.mycompany.beautysalon.dto.ServiceDto;
import by.mycompany.beautysalon.entity.Master;
import by.mycompany.beautysalon.entity.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

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
        return serviceDao.findByTitle(title);
    }

    @Override
    public List<Service> findByTitleContaining(String title) {
        return serviceDao.findByTitleContaining(title);
    }

//    @Override
//    @Transactional
//    public void saveServiceDto(ServiceDto serviceDto) {
//        serviceDao.saveServiceDto(serviceDto);
//    }

    @Override
    @Transactional
    public void addMaster(int serviceId, int masterId) {
        Optional<Service> service = serviceDao.findById(serviceId);
        Optional<Master> master = masterDao.findById(masterId);
        service.get().addMaster(master.get());
        serviceDao.save(service.get());
    }

//    @Override
//    @Transactional
//    public void updateServiceDto(ServiceDto serviceDto) {
//        serviceDao.updateServiceDto(serviceDto);
//    }
}
