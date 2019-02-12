package by.mycompany.beautysalon.dao;

import by.mycompany.beautysalon.entity.Client;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ClientDaoImpl extends BaseDaoImpl<Client, Integer> implements ClientDao  {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Client> getClientsByLastName(String lastName) {
        Session session = sessionFactory.getCurrentSession();
        Query<Client> query = session.createQuery("from Client C where C.lastName = :name");
        query.setParameter("name", lastName);
        List<Client> client = query.getResultList();
        return client;
    }

    @Override
    public Client getClientByPhone(String phone) {
        Session session = sessionFactory.getCurrentSession();
        Query<Client> query = session.createQuery("from Client C where C.phone = :phone");
        query.setParameter("phone", phone);
        List<Client> client = query.list();
        if (client.size() == 0) {return null;}
        else {
        return client.get(0); }
    }
}
