package school.hei.prog4.controller;

import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import school.hei.prog4.controller.model.CreateEmployeeModel;
import school.hei.prog4.controller.model.UpdateEmployeeModel;
import school.hei.prog4.service.CompanyConfService;
import school.hei.prog4.service.EmployeeService;

import java.io.IOException;
import java.time.LocalDate;

@Controller
@AllArgsConstructor
public class EmployeeController {
    private final EmployeeService service;
    private final CompanyConfService companyConfService;

    @GetMapping(value = "/")
    public String index(Model model) {
        return service.getEmployees(model);
    }

    @GetMapping(value = "/addEmployee")
    public String createEmployee(Model model) {
        model.addAttribute("newEmployee", new CreateEmployeeModel());
        model.addAttribute("company", companyConfService.getCompanyConf());
        model.addAttribute("logo", "data:image/png;base64," +
                Base64.encodeBase64String(companyConfService.getCompanyConf().getLogo()));
        return "addEmployee";
    }

    @PostMapping("/addEmployee")
    public String createEmployee(
            @ModelAttribute("newEmployee") CreateEmployeeModel employee,
            @RequestParam("image") MultipartFile image,
            @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate birthDate) throws IOException {

        return service.addEmployee(employee, image, birthDate);
    }

    @PostMapping("/employees/{id}")
    public String updateEmployee(
            @ModelAttribute("updateEmployee") UpdateEmployeeModel employee,
            @RequestParam("updateImage") MultipartFile updateImage,
            @RequestParam("birthDate") LocalDate birthDate,
            @RequestParam("IDCardIssueDate") LocalDate IDCardIssueDate,
            @RequestParam("hireDate") LocalDate hireDate,
            @RequestParam(value = "resignationDate", required = false) LocalDate resignationDate,
            @PathVariable("id") String id) throws IOException{
        employee.setId(id);
        employee.setBirthDate(birthDate);
        employee.setIDCardIssueDate(IDCardIssueDate);
        employee.setHireDate(hireDate);
        employee.setResignationDate(resignationDate);

        return service.saveEmployee(employee, updateImage);
    }


    @GetMapping("/employees/{id}")
    public String getEmployeeById(Model model,@PathVariable String id){
        return service.getEmployeeById(model, id);
    }

    @GetMapping("/employees/sort")
    public String getSortedEmployees(
            @RequestParam("sortOption") String sortOption,
            @RequestParam(value = "sortDirection",required = false) String sortDirection,
            @RequestParam(value = "startDate", required = false) LocalDate startDate,
            @RequestParam(value = "endDate", required = false) LocalDate endDate,
            Model model ){
        return service.getSortedEmployees(sortOption, sortDirection, model, startDate, endDate);
    }

    @GetMapping("/employees/download-csv")
    public String exportToCSV(HttpServletResponse response,
                              @RequestParam("sortOption") String sortOption,
                              @RequestParam(value = "sortDirection",required = false) String sortDirection,
                              @RequestParam(value = "startDate", required = false) LocalDate startDate,
                              @RequestParam(value = "endDate", required = false) LocalDate endDate,
                              Model model ) throws IOException{

        return service.exportToCSV(response, sortOption, sortDirection, model, startDate, endDate);
    }

    @GetMapping("/employees/search")
    public String getEmployeesByNames(
            @RequestParam("sortOption") String sortOption,
            @RequestParam("searchInput") String searchInput,
            Model model){
        return service.getEmployeesByNamesContaining(sortOption, searchInput, model);
    }
}
