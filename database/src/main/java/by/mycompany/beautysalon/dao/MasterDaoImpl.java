package by.mycompany.beautysalon.dao;

import by.mycompany.beautysalon.dto.MasterDto;
import by.mycompany.beautysalon.entity.Master;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public class MasterDaoImpl extends BaseDaoImpl<Master, Integer> implements MasterDao  {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Master getMasterByLastName(String lastName) {
        Session session = sessionFactory.getCurrentSession();
        Query<Master> query = session.createQuery("from Master where lastName = :lastName");
        query.setParameter("lastName", lastName);
        Master master = query.uniqueResult();
        return master;
    }

}
