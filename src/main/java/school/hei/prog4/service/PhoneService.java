package school.hei.prog4.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import school.hei.prog4.entity.PhoneEntity;
import school.hei.prog4.repository.PhoneRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class PhoneService {
    private final PhoneRepository phoneRepository;

    public PhoneEntity saveOne(PhoneEntity phone){
        return phoneRepository.save(phone);
    }
}
