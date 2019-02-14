package by.mycompany.beautysalon.dao;

import by.mycompany.beautysalon.dto.MasterDto;
import by.mycompany.beautysalon.entity.Master;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MasterDao extends JpaRepository<Master, Integer> {

    Master findMasterByLastName(String lastName);
//    public Master getMasterByLastName(String lastName);
}
