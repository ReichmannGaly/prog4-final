package school.hei.prog4.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import school.hei.prog4.entity.configuration.CompanyPhone;

@Repository
public interface CompanyPhoneRepository extends JpaRepository<CompanyPhone, String> {
    CompanyPhone findByCompanyConfId(String companyConfId);
}
