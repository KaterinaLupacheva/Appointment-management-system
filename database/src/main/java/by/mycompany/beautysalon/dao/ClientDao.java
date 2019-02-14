package by.mycompany.beautysalon.dao;

import by.mycompany.beautysalon.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClientDao extends JpaRepository<Client, Integer> {

    List<Client> findClientsByLastName(String lastName);
//    public List<Client> getClientsByLastName(String lastName);

    Client findClientByPhone(String phone);
//    public Client getClientByPhone(String phone);
}
