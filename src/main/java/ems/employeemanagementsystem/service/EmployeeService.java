package ems.employeemanagementsystem.service;

import ems.employeemanagementsystem.entity.Employee;
import ems.employeemanagementsystem.repository.EmployeeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public List<Employee> getAllEmployees(){
        return employeeRepository.findAll();
    }

    public Employee addEmployee(Employee employee){

       return employeeRepository.save(employee);
    }

    public Employee getEmployee(Long id){

        return employeeRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("Employee with id " + id + " not found."));
    }

    public Employee deleteEmployee(Long id){

        Employee employee = employeeRepository.findById(id)
                .orElseThrow( () -> new IllegalStateException("Employee with id " + id + " does not exists."));

        employeeRepository.deleteById(id);

        return employee;
    }
}
