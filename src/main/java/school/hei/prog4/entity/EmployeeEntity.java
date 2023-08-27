package school.hei.prog4.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "employee")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EmployeeEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    private String firstName;

    private String lastName;

    private LocalDate birthDate;

    @Column(name = "gender")
    @Enumerated(EnumType.STRING)
    private Gender gender;

    private String personalEmail;

    private String professionalEmail;

    private String address;

    @Column(name = "id_card_number")
    private String IDCardNumber;

    @Column(name = "id_card_issue_place")
    private String IDCardIssuePlace;

    @Column(name = "id_card_issue_date")
    private LocalDate IDCardIssueDate;

    private Integer childrenNumber;

    @Column(name = "socio_professional_category")
    @Enumerated(EnumType.STRING)
    private SocioProfessionalCategory socioProfessionalCategory;

    private String position;

    private LocalDate hireDate;

    private LocalDate resignationDate;

    @Column(name = "cnaps_number")
    private String CNAPSNumber;

    private String ref;

    private long salary;

    private byte[] image;

    public enum Gender {
        H,F
    }

    public enum SocioProfessionalCategory{
        M1, M2, OS1, OS2, OS3, OP1, CADRE1, CADRE2
    }
}