package by.mycompany.beautysalon.dao;

import by.mycompany.beautysalon.entity.Client;

import java.util.List;

public interface ClientDao extends BaseDao<Client, Integer> {

    public List<Client> getClientsByLastName(String lastName);

    public Client getClientByPhone(String phone);
}
