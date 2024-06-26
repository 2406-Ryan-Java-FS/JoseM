package ems.employeemanagementsystem.controller;

import ems.employeemanagementsystem.entity.Employee;
import ems.employeemanagementsystem.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    @RequestMapping
    public ResponseEntity<List<Employee>> getAllEmployees(){

        return ResponseEntity.ok(employeeService.getAllEmployees());
    }

    @PostMapping("/add")
    public ResponseEntity<Employee> addEmployee(@RequestBody Employee employee){
        return ResponseEntity.ok(employeeService.addEmployee(employee));
    }

}
