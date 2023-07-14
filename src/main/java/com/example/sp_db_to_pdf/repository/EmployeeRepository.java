package com.example.sp_db_to_pdf.repository;

import com.example.sp_db_to_pdf.model.Employee;
import org.springframework.data.repository.CrudRepository;

public interface EmployeeRepository
        extends CrudRepository<Employee, Long> {
}
