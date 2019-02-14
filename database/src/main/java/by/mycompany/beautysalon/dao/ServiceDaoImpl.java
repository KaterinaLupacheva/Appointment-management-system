package by.mycompany.beautysalon.dao;

import by.mycompany.beautysalon.dto.ServiceDto;
import by.mycompany.beautysalon.entity.Master;
import by.mycompany.beautysalon.entity.Service;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public abstract class ServiceDaoImpl implements ServiceDao {

//    public void saveServiceDto(ServiceDto serviceDto) {
//        Service service = new Service();
//        service.setTitle(serviceDto.getTitle());
//        service.setDuration(serviceDto.getDuration());
//        save(service);
//    }

//    @Override
//    public void updateServiceDto(ServiceDto serviceDto) {
//        Service service = session.get(Service.class, serviceDto.getId());
//        service.setTitle(serviceDto.getTitle());
//        service.setDuration(serviceDto.getDuration());
//        update(service);
//    }
}
