package com.management.system.employee.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.BlockingDeque;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.management.system.employee.exception.ResourceNotFoundException;
import com.management.system.employee.model.Employee;
import com.management.system.employee.repository.EmployeeRepository;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
//@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("api/v1")
public class EmployeeController {

    @Autowired
    private EmployeeRepository employeeRepository;

    @GetMapping("/employees")
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @PostMapping("/employees")
    public Employee getAllEmployees(@RequestBody Employee employee) {
        return employeeRepository.save(employee);
    }

    @GetMapping("/employee/{id}")
    public Employee getEmployeeById(@PathVariable Long id) {
        Employee employee = employeeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employee Not found with id: "+ id));
        return employee;
    }

    @PutMapping("/employee/{id}")
    public Employee updateEmployeeById(@PathVariable Long id, @RequestBody Employee employeeDetails) {
        Employee employee = employeeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employee Not found with id: "+ id));

        employee.setFirstName(employeeDetails.getFirstName());
        employee.setLastName(employeeDetails.getLastName());
        employee.setEmailId(employeeDetails.getEmailId());
        Employee updatedEmployee = employeeRepository.save(employee);
        return updatedEmployee;
    }

    @DeleteMapping("employee/{id}")
    public Map<Long, Boolean> deleteEmployeeById(@PathVariable Long id) {
        Employee employee = employeeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employee Not found with id: "+ id));
        employeeRepository.deleteById(id);

        Map<Long, Boolean> deletedList = new HashMap<>();
        deletedList.put(id, Boolean.TRUE);

        return deletedList;
    }
}
