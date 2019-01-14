package by.mycompany.beautysalon.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import static by.mycompany.beautysalon.connection.ConnectionManager.getSession;

public class BaseDaoImpl<T, PK extends Serializable> implements BaseDao<T, PK>  {

    @Autowired
    private SessionFactory sessionFactory;

    private Class<T> type;

    public BaseDaoImpl() {
        ParameterizedType type = (ParameterizedType) getClass().getGenericSuperclass();
        this.type = (Class<T>) type.getActualTypeArguments()[0];
    }

    @Override
    public void save(T entity) {
        sessionFactory.getCurrentSession().saveOrUpdate(entity);
    }

    @Override
    public void update(T entity) {
        sessionFactory.getCurrentSession().update(entity);
    }

    @Override
    public T find(PK id) {
        return sessionFactory.getCurrentSession().get(type,id);
    }

    @Override
    public void delete(T entity) {
        sessionFactory.getCurrentSession().delete(entity);
    }

    @Override
    public List<T> findAll() {
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<T> criteria = cb.createQuery(type);
        criteria.select(criteria.from(type));
        List<T> result = session.createQuery(criteria).list();
        return result;
    }
}
