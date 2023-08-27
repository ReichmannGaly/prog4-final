package school.hei.prog4.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import school.hei.prog4.entity.configuration.CompanyConf;

@Repository
public interface CompanyConfRepository extends JpaRepository<CompanyConf, String> {
}
