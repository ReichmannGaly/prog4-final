package school.hei.prog4.controller.mapper;

import org.springframework.stereotype.Component;
import school.hei.prog4.entity.EmployeeEntity;
import school.hei.prog4.entity.EmployeePhoneEntity;
import school.hei.prog4.entity.PhoneEntity;

import java.util.ArrayList;
import java.util.List;

@Component
public class EmployeePhoneMapper {
    public List<EmployeePhoneEntity> toEmployeePhoneEntity(List<PhoneEntity> phoneEntities, EmployeeEntity employeeEntity){
        List<EmployeePhoneEntity> employeePhoneEntities = new ArrayList<>();

        for (PhoneEntity phone: phoneEntities) {
            EmployeePhoneEntity employeePhone = EmployeePhoneEntity.builder()
                    .employee(employeeEntity)
                    .phone(phone)
                    .build();
            employeePhoneEntities.add(employeePhone);
        }
        return employeePhoneEntities;
    }
}
