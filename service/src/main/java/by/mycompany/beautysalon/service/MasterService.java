package by.mycompany.beautysalon.service;

import by.mycompany.beautysalon.dto.MasterDto;
import by.mycompany.beautysalon.entity.Master;

import java.util.List;
import java.util.Set;

public interface MasterService extends BaseService<Master, Integer> {

    public Master getMasterByLastName(String lastName);

    public void saveMasterDto(MasterDto masterDto);

    List<MasterDto> getMasterDtos();

    MasterDto doMasterDto(int theId);

    Master doMaster(MasterDto masterDto, Set<String> updatedServices);

    void deleteMasterDto(MasterDto masterDto);

    Master findMasterById(Integer id);

    List<MasterDto> findAllByNameContaining(String name);
}
