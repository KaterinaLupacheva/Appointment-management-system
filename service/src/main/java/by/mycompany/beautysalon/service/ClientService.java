package by.mycompany.beautysalon.service;

import by.mycompany.beautysalon.entity.Client;

import java.util.List;

public interface ClientService extends BaseService<Client, Integer> {

    public List<Client> getClientsByLastName(String lastName);

    Client getClientByPhone(String phone);
}
