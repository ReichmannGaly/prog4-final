package school.hei.prog4.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import school.hei.prog4.entity.EmployeePhoneEntity;

public interface EmployeePhoneRepository extends JpaRepository<EmployeePhoneEntity, String> {
    EmployeePhoneEntity findEmployeePhoneEntityByEmployeeId(String employeeId);
}
