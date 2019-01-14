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
public class ServiceDaoImpl extends BaseDaoImpl<Service, Integer> implements ServiceDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Service getServiceByTitle(String title) {
        Session session = sessionFactory.getCurrentSession();
        Query<Service> query = session.createQuery("from Service where title = :title", Service.class);
        query.setParameter("title", title);
        Service service = query.uniqueResult();
        return service;
    }

    @Override
    public void saveServiceDto(ServiceDto serviceDto) {
        Session session = sessionFactory.getCurrentSession();
        Service service = new Service();
        service.setTitle(serviceDto.getTitle());
        service.setDuration(serviceDto.getDuration());
        session.save(service);
    }

    @Override
    public void updateServiceDto(ServiceDto serviceDto) {
        Session session = sessionFactory.getCurrentSession();
        Service service = session.get(Service.class, serviceDto.getId());
        service.setTitle(serviceDto.getTitle());
        service.setDuration(serviceDto.getDuration());
        session.update(service);
    }
}
