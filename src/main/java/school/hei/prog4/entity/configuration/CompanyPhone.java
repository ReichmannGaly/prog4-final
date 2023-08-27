package school.hei.prog4.entity.configuration;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import school.hei.prog4.entity.PhoneEntity;

@Getter
@Setter
@Entity
@Table(name = " company_phone")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CompanyPhone {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    @ManyToOne
    @JoinColumn(name = "company_id")
    private CompanyConf companyConf;

    @ManyToOne
    @JoinColumn(name = "phone_id")
    private PhoneEntity phone;
}
