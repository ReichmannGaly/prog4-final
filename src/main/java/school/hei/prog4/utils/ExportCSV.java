package school.hei.prog4.utils;

import com.opencsv.CSVWriter;
import jakarta.servlet.http.HttpServletResponse;
import school.hei.prog4.controller.model.EmployeeModel;
import school.hei.prog4.entity.EmployeeEntity;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class ExportCSV {
    public static void export(List<EmployeeModel> employees, HttpServletResponse response) throws IOException {
        response.setContentType("text/csv");
        response.setHeader("Content-Disposition", "attachment; filename=\"employees.csv\"");

        PrintWriter writer = response.getWriter();
        CSVWriter csvWriter = new CSVWriter(writer);

        String[] header = {"id", "ref", "firstName", "lastName", "birthDate", "gender",
                "personalEmail", "professionalEmail", "phoneNumbers", "address",
                "IDCardNumber", "IDCardIssuePlace", "IDCardIssueDate", "childrenNumber",
                "socioProfessionalCategory", "position", "hireDate", "resignationDate",
                "CNAPSNumber"};

        csvWriter.writeNext(header);

        for (EmployeeModel employee : employees) {
            String[] data = {
                    employee.getId(),
                    employee.getRef(),
                    employee.getFirstName(),
                    employee.getLastName(),
                    employee.getGender().toString(),
                    employee.getPersonalEmail(),
                    employee.getProfessionalEmail(),
                    employee.getPhoneNumbers(),
                    employee.getAddress(),
                    employee.getIDCardNumber(),
                    employee.getIDCardIssuePlace(),
                    employee.getIDCardIssueDate().toString(),
                    employee.getChildrenNumber().toString(),
                    employee.getSocioProfessionalCategory().toString(),
                    employee.getPosition(),
                    employee.getHireDate().toString(),
                    Optional.ofNullable(employee.getResignationDate())
                            .map(LocalDate::toString)
                            .orElse(null),
                    employee.getCNAPSNumber()
            };
            csvWriter.writeNext(data);
        }

        csvWriter.close();
    }
}
