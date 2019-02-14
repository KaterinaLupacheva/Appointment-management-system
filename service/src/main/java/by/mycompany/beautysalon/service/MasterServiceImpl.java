package by.mycompany.beautysalon.service;

import by.mycompany.beautysalon.dao.MasterDao;
import by.mycompany.beautysalon.dao.ServiceDao;
import by.mycompany.beautysalon.dto.MasterDto;
import by.mycompany.beautysalon.entity.Master;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional(readOnly = true)
public class MasterServiceImpl extends BaseServiceImpl<Master, Integer> implements MasterService {

    @Autowired
    private MasterDao masterDao;

    @Autowired
    private ServiceDao serviceDao;

    @Override
    @Transactional
    public Master getMasterByLastName(String lastName) {
        return masterDao.findMasterByLastName(lastName);
    }

    @Override
    @Transactional
    public void saveMasterDto(MasterDto masterDto) {
        Master master = new Master();
        master.setFirstName(masterDto.getFirstName());
        master.setLastName(masterDto.getLastName());
        Set<String> serviceTitles = masterDto.getServices();
        for (String title: serviceTitles) {
            by.mycompany.beautysalon.entity.Service service = serviceDao.findByTitle(title);
            master.addService(service);
        }
        masterDao.save(master);
    }

    @Override
    @Transactional
    public List<MasterDto> getMasterDtos() {
        List<Master> allMasters = masterDao.findAll();
        List<MasterDto> allMasterDto = new ArrayList<>();
        for(Master tempMaster : allMasters) {
            MasterDto masterDto = new MasterDto();
            masterDto.setId(tempMaster.getId());
            masterDto.setFirstName(tempMaster.getFirstName());
            masterDto.setLastName(tempMaster.getLastName());
            Set<String> serviceTitle = new HashSet<>();
            List<by.mycompany.beautysalon.entity.Service> tempMasterServices = tempMaster.getServices();
            for (by.mycompany.beautysalon.entity.Service tempService : tempMasterServices) {
                String title = tempService.getTitle();
                serviceTitle.add(title);
                masterDto.setServices(serviceTitle);
            }
            allMasterDto.add(masterDto);
        }
        return allMasterDto;
    }

    @Override
    @Transactional
    public MasterDto doMasterDto(int theId) {
        MasterDto masterDto = new MasterDto();
        Optional<Master> master = masterDao.findById(theId);
        masterDto.setId(master.get().getId());
        masterDto.setFirstName(master.get().getFirstName());
        masterDto.setLastName(master.get().getLastName());
        Set<String> titles = new HashSet<>();
        List<by.mycompany.beautysalon.entity.Service> services = master.get().getServices();
        for(by.mycompany.beautysalon.entity.Service s : services) {
            titles.add(s.getTitle());
        }
        return masterDto;
    }

    @Override
    @Transactional
    public Master doMaster(MasterDto masterDto, Set<String> updatedServices) {
        Optional<Master> master = masterDao.findById(masterDto.getId());
        master.get().setFirstName(masterDto.getFirstName());
        master.get().setLastName(masterDto.getLastName());
        master.get().getServices().clear();
        for(String s : updatedServices) {
            by.mycompany.beautysalon.entity.Service serviceByTitle = serviceDao.findByTitle(s);
            master.get().addService(serviceByTitle);
        }
        return master.get();
    }
}
