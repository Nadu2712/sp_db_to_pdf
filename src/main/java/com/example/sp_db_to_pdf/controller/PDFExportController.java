package com.example.sp_db_to_pdf.controller;

import com.example.sp_db_to_pdf.model.Employee;
import com.example.sp_db_to_pdf.repository.EmployeeRepository;
import com.example.sp_db_to_pdf.service.DatabasePDFService;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.ByteArrayInputStream;
import java.util.List;

@Controller
public class PDFExportController {

    final
    EmployeeRepository employeeRepository;

    public PDFExportController(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @GetMapping(value = "/openpdf/employees", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<InputStreamResource> employeeReport() {
        List<Employee> employees = (List<Employee>) employeeRepository.findAll();

        ByteArrayInputStream bis = DatabasePDFService.employeePDFReport(employees);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=employees.pdf");

        return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(bis));
    }
}
