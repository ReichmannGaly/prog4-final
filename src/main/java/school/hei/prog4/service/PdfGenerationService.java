package school.hei.prog4.service;

import lombok.AllArgsConstructor;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.xhtmlrenderer.pdf.ITextRenderer;
import school.hei.prog4.entity.EmployeeEntity;
import school.hei.prog4.entity.configuration.CompanyConf;
import school.hei.prog4.entity.configuration.CompanyPhone;
import school.hei.prog4.repository.CompanyPhoneRepository;
import school.hei.prog4.repository.EmployeeRepository;

import java.io.ByteArrayOutputStream;
import java.time.Period;

import static java.time.LocalDate.now;

@Service
@AllArgsConstructor
public class PdfGenerationService {
    private TemplateEngine templateEngine;
    private final EmployeeRepository employeeRepository;
    private final CompanyConfService companyConfService;
    private final CompanyPhoneRepository companyPhoneRepository;

    public byte[] generatePdf(String id) throws Exception {
        EmployeeEntity employee = employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee with id " + id + "not found"));
        CompanyConf conf = companyConfService.getCompanyConf();
        CompanyPhone companyPhone = companyPhoneRepository.findByCompanyConfId(conf.getId());
        String returnedCompanyPhone = "N/A";

        if(companyPhone != null){
            returnedCompanyPhone = companyPhone.getPhone().getPhoneNumber();
        }

        int age = Period.between(employee.getBirthDate(), now()).getYears();

        Context context = new Context();
        context.setVariable("employee", employee);
        context.setVariable("image", "data:image/png;base64," +
                Base64.encodeBase64String(employee.getImage()));
        context.setVariable("age", age);
        context.setVariable("conf", conf);
        context.setVariable("logo", "data:image/png;base64," +
                Base64.encodeBase64String(conf.getLogo()));
        context.setVariable("companyPhone", returnedCompanyPhone);

        String processedHtml = templateEngine.process("ficheEmployee", context);

        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
            ITextRenderer renderer = new ITextRenderer();
            renderer.setDocumentFromString(processedHtml);
            renderer.layout();
            renderer.createPDF(outputStream);
            return outputStream.toByteArray();
        }
    }
}
