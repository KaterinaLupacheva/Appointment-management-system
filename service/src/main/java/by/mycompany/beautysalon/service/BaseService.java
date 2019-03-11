package by.mycompany.beautysalon.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.io.Serializable;
import java.util.List;
import java.util.Optional;

@Service
public interface BaseService<T, PK extends Serializable> {

    void save(T entity);

    Optional<T> find(PK id);

    void delete(T entity);

    List<T> findAll();

}
