package school.hei.prog4.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import school.hei.prog4.entity.EmployeePhoneEntity;
import school.hei.prog4.repository.EmployeePhoneRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class EmployeePhoneService {
    private final EmployeePhoneRepository employeePhoneRepository;


    public void addEmployeePhone(EmployeePhoneEntity toSave){
        employeePhoneRepository.save(toSave);
    }
}
