package school.hei.prog4.controller.mapper;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import school.hei.prog4.entity.EmployeeEntity;
import school.hei.prog4.entity.EmployeePhoneEntity;
import school.hei.prog4.entity.PhoneEntity;
import school.hei.prog4.repository.EmployeePhoneRepository;

@Component
@AllArgsConstructor
public class PhoneMapper {
    private final EmployeePhoneRepository employeePhoneRepository;

    public String toUpdateStringPhone(EmployeeEntity employee){
        EmployeePhoneEntity employeePhone = employeePhoneRepository
                .findEmployeePhoneEntityByEmployeeId(employee.getId());

        String phoneNumber = employeePhone.getPhone().getPhoneNumber();
        String output = "0";
        return output + phoneNumber.substring(phoneNumber.length() - 9);
    }

    public String toStringPhone(EmployeeEntity employee){
        EmployeePhoneEntity employeePhone = employeePhoneRepository
                .findEmployeePhoneEntityByEmployeeId(employee.getId());

        return employeePhone.getPhone().getPhoneNumber();
    }

    public PhoneEntity.Code toCode(EmployeeEntity employee){
        EmployeePhoneEntity employeePhone = employeePhoneRepository
                .findEmployeePhoneEntityByEmployeeId(employee.getId());

        return employeePhone.getPhone().getCodePays();
    }
}
