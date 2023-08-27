package school.hei.prog4.controller.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;


@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CreateEmployeeModel {
    private String firstName;
    private String lastName;
    private LocalDate birthDate;
    private String gender;
    private String personalEmail;
    private String professionalEmail;
    private String phoneNumber;
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
    private MultipartFile image;
}
