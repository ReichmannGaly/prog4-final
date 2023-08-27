package school.hei.prog4.service;

import lombok.AllArgsConstructor;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;
import school.hei.prog4.entity.PhoneEntity;
import school.hei.prog4.entity.configuration.CompanyConf;
import school.hei.prog4.entity.configuration.CompanyPhone;
import school.hei.prog4.repository.CompanyConfRepository;
import school.hei.prog4.repository.CompanyPhoneRepository;
import school.hei.prog4.repository.PhoneRepository;
import school.hei.prog4.utils.PhoneUtils;

import java.io.IOException;

@Service
@AllArgsConstructor
public class CompanyConfService {
    private final CompanyConfRepository companyConfRepository;
    private final PhoneRepository phoneRepository;
    private final CompanyPhoneRepository companyPhoneRepository;

    public CompanyConf getCompanyConf() {
        return companyConfRepository.findAll().get(0);
    }

    public String getCompanyConfWithPhoneNumbers(Model model) {
        CompanyConf conf = getCompanyConf();
        CompanyPhone companyPhone = companyPhoneRepository.findByCompanyConfId(conf.getId());

        if (companyPhone != null && companyPhone.getPhone() != null) {
            String phoneNumber = companyPhone.getPhone().getPhoneNumber();
            String mappedPhoneNumber = "0" + phoneNumber.substring(phoneNumber.length() - 9);

            if (companyPhone.getPhone().getCodePays() != null) {
                model.addAttribute("codePays", companyPhone.getPhone().getCodePays().toString());
            }
            model.addAttribute("phoneNumber", mappedPhoneNumber);
        }

        model.addAttribute("company", conf);
        model.addAttribute("logo", "data:image/png;base64," + Base64.encodeBase64String(conf.getLogo()));
        return "updateCompanyConf";
    }

    public CompanyConf saveCompanyConf(
            CompanyConf companyConf,
            MultipartFile newLogo,
            String codePays,
            String phoneNumber) throws IOException {
        companyConf.setId(getCompanyConf().getId());
        CompanyPhone existing = companyPhoneRepository.findByCompanyConfId(companyConf.getId());
        if (existing == null) {
            PhoneEntity phone = PhoneEntity.builder()
                    .codePays(PhoneEntity.Code.valueOf(codePays))
                    .phoneNumber(phoneNumber)
                    .build();

            PhoneEntity saved = phoneRepository.save(PhoneUtils.addStateCode(phone));

            CompanyPhone toSave = CompanyPhone.builder()
                    .phone(saved)
                    .companyConf(companyConf)
                    .build();

            companyPhoneRepository.save(toSave);
        } else {
            PhoneEntity phone = existing.getPhone();

            phone.setCodePays(PhoneEntity.Code.valueOf(codePays));
            phone.setPhoneNumber(phoneNumber);

            phoneRepository.save(PhoneUtils.addStateCode(phone));
        }

        if (!newLogo.isEmpty()) {
            companyConf.setLogo(newLogo.getBytes());
        } else {
            companyConf.setLogo(getCompanyConf().getLogo());
        }

        return companyConfRepository.save(companyConf);
    }
}
