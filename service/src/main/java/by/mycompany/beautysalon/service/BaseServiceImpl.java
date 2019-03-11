package by.mycompany.beautysalon.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public class BaseServiceImpl<T, PK extends Serializable> implements BaseService<T,PK> {

    @Autowired
    JpaRepository<T, Integer> repository;

    @Override
    public void save(T entity) {
        repository.save(entity);
    }

    @Override
    public Optional<T> find(PK id) {
        return (Optional<T>) repository.findById((Integer) id);
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
