package school.hei.prog4.controller.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;

@Getter
@Setter
@Builder
public class EmployeeModel {
    private String id;
    private String firstName;
    private String lastName;
    private LocalDate birthDate;
    private String gender;
    private String personalEmail;
    private String professionalEmail;
    private String phoneNumbers;
    private String codePays;
    private String address;
    private String IDCardNumber;
    private String IDCardIssuePlace;
    private LocalDate IDCardIssueDate;
    private Integer childrenNumber;
    private String socioProfessionalCategory;
    private String position;
    private LocalDate hireDate;
    private LocalDate resignationDate;
    private String CNAPSNumber;
    private String ref;
    private String image;
    private Long salary;
    private MultipartFile updateImage;
}
