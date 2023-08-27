package school.hei.prog4.controller.mapper;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import school.hei.prog4.controller.model.CreateEmployeeModel;
import school.hei.prog4.controller.model.EmployeeModel;
import school.hei.prog4.controller.model.UpdateEmployeeModel;
import school.hei.prog4.entity.EmployeeEntity;
import org.apache.tomcat.util.codec.binary.Base64;
import school.hei.prog4.repository.EmployeeRepository;

import java.io.IOException;
import java.util.List;

@Component
@AllArgsConstructor
public class EmployeeMapper {
    private final EmployeeRepository employeeRepository;
    private final PhoneMapper phoneMapper;
    public EmployeeEntity toEntity(CreateEmployeeModel model) throws IOException {
        List<EmployeeEntity> employees = employeeRepository.findAll();
        return EmployeeEntity.builder()
                .firstName(model.getFirstName())
                .lastName(model.getLastName())
                .birthDate(model.getBirthDate())
                .gender(EmployeeEntity.Gender.valueOf(model.getGender()))
                .personalEmail(model.getPersonalEmail())
                .professionalEmail(model.getProfessionalEmail())
                .address(model.getAddress())
                .IDCardNumber(model.getIDCardNumber())
                .IDCardIssueDate(model.getIDCardIssueDate())
                .IDCardIssuePlace(model.getIDCardIssuePlace())
                .childrenNumber(model.getChildrenNumber())
                .socioProfessionalCategory(EmployeeEntity
                        .SocioProfessionalCategory
                        .valueOf(model.getSocioProfessionalCategory()))
                .position(model.getPosition())
                .hireDate(model.getHireDate())
                .resignationDate(model.getResignationDate())
                .CNAPSNumber(model.getCNAPSNumber())
                .ref("EMP-" + String.format("%03d", employees.size() + 1))
                .image(model.getImage().getBytes())
                .build();
    }

    public EmployeeEntity toEntity(UpdateEmployeeModel model, MultipartFile updateImage) throws IOException{
        byte[] image;

        if (updateImage.isEmpty()){
            image = employeeRepository.findById(model.getId()).get().getImage();
        }else {
            image = updateImage.getBytes();
        }

        return EmployeeEntity.builder()
                .id(model.getId())
                .firstName(model.getFirstName())
                .lastName(model.getLastName())
                .birthDate(model.getBirthDate())
                .gender(EmployeeEntity.Gender.valueOf(model.getGender()))
                .personalEmail(model.getPersonalEmail())
                .professionalEmail(model.getProfessionalEmail())
                .address(model.getAddress())
                .IDCardNumber(model.getIDCardNumber())
                .IDCardIssueDate(model.getIDCardIssueDate())
                .IDCardIssuePlace(model.getIDCardIssuePlace())
                .childrenNumber(model.getChildrenNumber())
                .socioProfessionalCategory(EmployeeEntity
                        .SocioProfessionalCategory
                        .valueOf(model.getSocioProfessionalCategory()))
                .position(model.getPosition())
                .hireDate(model.getHireDate())
                .resignationDate(model.getResignationDate())
                .CNAPSNumber(model.getCNAPSNumber())
                .ref(model.getRef())
                .image(image)
                .build();
    }

    public EmployeeModel toModel(EmployeeEntity entity) {
        return EmployeeModel.builder()
                .id(entity.getId())
                .firstName(entity.getFirstName())
                .lastName(entity.getLastName())
                .birthDate(entity.getBirthDate())
                .gender(entity.getGender().toString())
                .personalEmail(entity.getPersonalEmail())
                .professionalEmail(entity.getProfessionalEmail())
                .codePays(phoneMapper.toCode(entity).toString())
                .phoneNumbers(phoneMapper.toStringPhone(entity))
                .address(entity.getAddress())
                .IDCardNumber(entity.getIDCardNumber())
                .IDCardIssueDate(entity.getIDCardIssueDate())
                .IDCardIssuePlace(entity.getIDCardIssuePlace())
                .childrenNumber(entity.getChildrenNumber())
                .socioProfessionalCategory(entity.getSocioProfessionalCategory().toString())
                .position(entity.getPosition())
                .hireDate(entity.getHireDate())
                .resignationDate(entity.getResignationDate())
                .CNAPSNumber(entity.getCNAPSNumber())
                .ref(entity.getRef())
                .image("data:image/png;base64," + Base64.encodeBase64String(entity.getImage()))
                .build();
    }

    public UpdateEmployeeModel toUpdateModel(EmployeeEntity entity) {
        return UpdateEmployeeModel.builder()
                .id(entity.getId())
                .firstName(entity.getFirstName())
                .lastName(entity.getLastName())
                .birthDate(entity.getBirthDate())
                .gender(entity.getGender().toString())
                .personalEmail(entity.getPersonalEmail())
                .professionalEmail(entity.getProfessionalEmail())
                .codePays(phoneMapper.toCode(entity).toString())
                .phoneNumbers(phoneMapper.toUpdateStringPhone(entity))
                .address(entity.getAddress())
                .IDCardNumber(entity.getIDCardNumber())
                .IDCardIssueDate(entity.getIDCardIssueDate())
                .IDCardIssuePlace(entity.getIDCardIssuePlace())
                .childrenNumber(entity.getChildrenNumber())
                .socioProfessionalCategory(entity.getSocioProfessionalCategory().toString())
                .position(entity.getPosition())
                .hireDate(entity.getHireDate())
                .resignationDate(entity.getResignationDate())
                .CNAPSNumber(entity.getCNAPSNumber())
                .ref(entity.getRef())
                .image("data:image/png;base64," + Base64.encodeBase64String(entity.getImage()))
                .build();
    }
}
