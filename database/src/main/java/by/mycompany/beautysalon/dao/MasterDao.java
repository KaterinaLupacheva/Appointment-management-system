package by.mycompany.beautysalon.dao;

import by.mycompany.beautysalon.dto.MasterDto;
import by.mycompany.beautysalon.entity.Master;

import java.util.List;

public interface MasterDao extends BaseDao<Master, Integer> {

    public Master getMasterByLastName(String lastName);
}
