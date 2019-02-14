package by.mycompany.beautysalon.service;

import by.mycompany.beautysalon.dao.BaseDao;
import by.mycompany.beautysalon.dao.ClientDao;
import by.mycompany.beautysalon.entity.Appointment;
import by.mycompany.beautysalon.entity.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class ClientServiceImpl extends BaseServiceImpl<Client, Integer> implements ClientService {

    @Autowired
    private ClientDao clientDao;

    @Override
    @Transactional
    public List<Client> getClientsByLastName(String lastName) {
        return clientDao.findClientsByLastName(lastName);
    }

    @Override
    @Transactional
    public Client getClientByPhone(String phone) {
        return clientDao.findClientByPhone(phone);
    }
}
