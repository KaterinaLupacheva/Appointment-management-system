package by.mycompany.beautysalon.service;

import java.io.Serializable;
import java.util.List;

public interface BaseService<T, PK extends Serializable> {

    void save (T entity);

    void update (T entity);

    T find (PK id);

    void delete(T entity);

    List<T> findAll();
}
