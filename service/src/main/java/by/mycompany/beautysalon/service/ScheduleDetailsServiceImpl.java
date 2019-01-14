package by.mycompany.beautysalon.service;

import by.mycompany.beautysalon.entity.ScheduleDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class ScheduleDetailsServiceImpl extends BaseServiceImpl<ScheduleDetails, Integer> implements ScheduleDetailsService {
}
