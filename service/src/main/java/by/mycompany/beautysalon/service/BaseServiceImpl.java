package by.mycompany.beautysalon.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;
import java.util.List;

public class BaseServiceImpl<T, PK extends Serializable> implements BaseService<T,PK> {

    @Autowired
    JpaRepository<T, Integer> repository;

    @Override
    public void save(T entity) {
        repository.save(entity);
    }

    @Override
    public T find(PK id) {
        return (T) repository.findById((Integer) id);
    }

    @Override
    public void delete(T entity) {
        repository.delete(entity);
    }

    @Override
    public List<T> findAll() {
        return repository.findAll();
    }
}
