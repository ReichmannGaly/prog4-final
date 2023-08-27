package school.hei.prog4.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import school.hei.prog4.entity.PhoneEntity;

import java.util.Optional;

public interface PhoneRepository extends JpaRepository<PhoneEntity, String> {
    Optional<PhoneEntity> findByPhoneNumber(String phoneNumber);
}
