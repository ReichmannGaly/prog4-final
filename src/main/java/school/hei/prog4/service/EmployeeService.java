package school.hei.prog4.service;

import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;
import school.hei.prog4.controller.mapper.EmployeeMapper;
import school.hei.prog4.controller.model.CreateEmployeeModel;
import school.hei.prog4.controller.model.EmployeeModel;
import school.hei.prog4.controller.model.UpdateEmployeeModel;
import school.hei.prog4.entity.EmployeeEntity;
import school.hei.prog4.entity.EmployeePhoneEntity;
import school.hei.prog4.entity.PhoneEntity;
import school.hei.prog4.repository.EmployeePhoneRepository;
import school.hei.prog4.repository.EmployeeRepository;
import school.hei.prog4.repository.PhoneRepository;
import school.hei.prog4.utils.ExportCSV;
import school.hei.prog4.utils.PhoneUtils;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class EmployeeService {
    private final EmployeePhoneService employeePhoneService;
    private final EmployeeRepository employeeRepository;
    private final EmployeeMapper employeeMapper;
    private final PhoneService phoneService;
    private final EmployeePhoneRepository employeePhoneRepository;
    private final PhoneRepository phoneRepository;
    private final CompanyConfService companyConfService;

    public String getEmployees(Model model){
        List<EmployeeModel> employees = employeeRepository.findAll()
                .stream()
                .map(employeeMapper::toModel)
                .toList();

        model.addAttribute("employees", employees);
        model.addAttribute("newEmployee", new EmployeeEntity());
        model.addAttribute("company", companyConfService.getCompanyConf());
        model.addAttribute("logo", "data:image/png;base64," +
                Base64.encodeBase64String(companyConfService.getCompanyConf().getLogo()));
        return "index";
    }

    public String addEmployee(
            CreateEmployeeModel employee,
            MultipartFile image,
            LocalDate birthDate) throws IOException {
        employee.setBirthDate(birthDate);
        employee.setImage(image);

        PhoneEntity phone = PhoneEntity.builder()
                .codePays(PhoneEntity.Code.valueOf(employee.getCodePays()))
                .phoneNumber(employee.getPhoneNumber())
                .build();

        PhoneEntity phoneEntity = phoneService.saveOne(phone);

        EmployeeEntity domain = employeeMapper.toEntity(employee);
        EmployeeEntity saved = employeeRepository.save(domain);

        EmployeePhoneEntity employeePhone = EmployeePhoneEntity.builder()
                        .phone(phoneEntity)
                        .employee(saved)
                        .build();

        employeePhoneService.addEmployeePhone(employeePhone);
        return "redirect:/";
    }

    public String saveEmployee(
            UpdateEmployeeModel employee,
            MultipartFile updateImage) throws IOException{
        EmployeePhoneEntity employeePhone = employeePhoneRepository
                .findEmployeePhoneEntityByEmployeeId(employee.getId());

        PhoneEntity phone = employeePhone.getPhone();
        phone.setCodePays(PhoneEntity.Code.valueOf(employee.getCodePays()));
        phone.setPhoneNumber(employee.getPhoneNumbers());

        phoneRepository.save(PhoneUtils.addStateCode(phone));

        EmployeeEntity domain = employeeMapper.toEntity(employee, updateImage);
        employeeRepository.save(domain);

        return "redirect:/";
    }

    public String getEmployeeById(Model model, String id){
        Optional<EmployeeEntity> employee = employeeRepository.findById(id);
        if (employee.isPresent()) {
            UpdateEmployeeModel response = employeeMapper.toUpdateModel(employee.get());
            model.addAttribute("company", companyConfService.getCompanyConf());
            model.addAttribute("logo", "data:image/png;base64," +
                    Base64.encodeBase64String(companyConfService.getCompanyConf().getLogo()));
            model.addAttribute("employee", response);
        }
        return "employeeDetails";
    }

    private List<EmployeeModel> sortEmployees(String sortOption, String sortDirection, LocalDate startDate, LocalDate endDate) {
        List<EmployeeEntity> employees;

        switch (sortOption) {
            case "lastName" -> {
                if (sortDirection.equals("asc")) {
                    employees = employeeRepository.findAllOrderByLastNameAsc();
                } else {
                    employees = employeeRepository.findAllOrderByLastNameDesc();
                }
            }
            case "firstName" -> {
                if (sortDirection.equals("asc")) {
                    employees = employeeRepository.findAllOrderByFirstNameAsc();
                } else {
                    employees = employeeRepository.findAllOrderByFirstNameDesc();
                }
            }
            case "fonction" -> {
                if (sortDirection.equals("asc")) {
                    employees = employeeRepository.findAlOrderByPositionAsc();
                } else {
                    employees = employeeRepository.findAlOrderByPositionDesc();
                }
            }
            case "gender" -> {
                if (sortDirection.equals("H")){
                    employees = employeeRepository.findAllByGender("H");
                } else {
                    employees = employeeRepository.findAllByGender("F");
                }
            }
            case "hireDate" -> {
                employees = employeeRepository.findAllByHireDateBetween(startDate, endDate);
            }

            case "resignationDate" -> {
                employees = employeeRepository.findAllByResignationDateBetween(startDate, endDate);
            }
            default -> employees = employeeRepository.findAll();
        }

        return employees.stream().map(employeeMapper::toModel).toList();
    }

    public String getSortedEmployees(String sortOption, String sortDirection, Model model, LocalDate startDate, LocalDate endDate){
        List<EmployeeModel> employees = sortEmployees(sortOption, sortDirection, startDate, endDate);

        model.addAttribute("company", companyConfService.getCompanyConf());
        model.addAttribute("logo", "data:image/png;base64," +
                Base64.encodeBase64String(companyConfService.getCompanyConf().getLogo()));
        model.addAttribute("employees", employees);
        return "index";
    }

    public String exportToCSV(
            HttpServletResponse response,
            String sortOption,
            String sortDirection,
            Model model,
            LocalDate startDate,
            LocalDate endDate) throws IOException {
        List<EmployeeModel> employees = sortEmployees(sortOption, sortDirection, startDate, endDate);

        ExportCSV.export(employees, response);

        model.addAttribute("company", companyConfService.getCompanyConf());
        model.addAttribute("logo", "data:image/png;base64," +
                Base64.encodeBase64String(companyConfService.getCompanyConf().getLogo()));
        model.addAttribute("employees", employees);
        return "index";
    }

    public String getEmployeesByNamesContaining(String sortOption, String searchInput, Model model) {
        List<EmployeeEntity> employees;

        switch (sortOption) {
            case "lastName" -> employees = employeeRepository.findAllByLastNameContainingIgnoreCase(searchInput);
            case "firstName" -> employees = employeeRepository.findAllByFirstNameContainingIgnoreCase(searchInput);
            default -> employees = employeeRepository.findAll();
        }
        List<EmployeeModel> models = employees
                .stream()
                .map(employeeMapper::toModel)
                .toList();

        model.addAttribute("company", companyConfService.getCompanyConf());
        model.addAttribute("logo", "data:image/png;base64," +
                Base64.encodeBase64String(companyConfService.getCompanyConf().getLogo()));
        model.addAttribute("employees", models);
        return "index";
    }
}
