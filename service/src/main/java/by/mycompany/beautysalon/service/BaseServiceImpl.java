package by.mycompany.beautysalon.service;

import by.mycompany.beautysalon.dao.BaseDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

public class BaseServiceImpl<T, PK extends Serializable> implements BaseService<T,PK> {

    @Autowired
    private BaseDao<T, PK> baseDao;

    @Override
    @Transactional
    public void save(T entity) {
         baseDao.save(entity);
    }

    @Override
    @Transactional(propagation= Propagation.REQUIRED, readOnly=false)
    public void update(T entity) {
        baseDao.update(entity);
    }

    @Override
    @Transactional
    public T find(PK id) {
        return (T) baseDao.find(id);
    }

    @Override
    @Transactional
    public void delete(T entity) {
        baseDao.delete(entity);

    }

    @Override
    @Transactional
    public List<T> findAll() {
        return baseDao.findAll();
    }
}
