package ems.employeemanagementsystem.service;

import ems.employeemanagementsystem.entity.Account;
import ems.employeemanagementsystem.entity.Employee;
import ems.employeemanagementsystem.exception.AccountNotFoundException;
import ems.employeemanagementsystem.exception.EmployeeNotFoundException;
import ems.employeemanagementsystem.exception.UnauthorizedException;
import ems.employeemanagementsystem.repository.AccountRepository;
import ems.employeemanagementsystem.repository.EmployeeRepository;
import ems.employeemanagementsystem.role.Role;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final AccountRepository accountRepository;

    public List<Employee> getAllEmployees(Long accountId){

        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new AccountNotFoundException("Account with id " + accountId + " not found."));

        if (account.getRole() != Role.ROLE_ADMIN) {
            throw new UnauthorizedException("Access denied. Admin role required.");
        }
        return employeeRepository.findAll();
    }


    public Employee addEmployee(Employee employee, Long accountId){

        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new AccountNotFoundException("Account with id " + accountId + " not found." ));

        employee.setAccount(account);
        account.getEmployees().add(employee);
        return employeeRepository.save(employee);
    }

    public Employee getEmployee(Long id){

        return employeeRepository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException("Employee with id " + id + " not found."));
    }

    public Employee deleteEmployee(Long id){

        Employee employee = employeeRepository.findById(id)
                .orElseThrow( () -> new EmployeeNotFoundException("Employee with id " + id + " does not exists."));

        employeeRepository.deleteById(id);

        return employee;
    }

    public Employee updateEmployee(Employee employee){

        Employee updatedEmp = employeeRepository.findById(employee.getEmployeeId())
                .orElseThrow(
                        () -> new EmployeeNotFoundException("Employee with id "
                                + employee.getEmployeeId() + " not found"));

                updatedEmp.setFirstName(employee.getFirstName());
                updatedEmp.setLastName(employee.getLastName());
                updatedEmp.setHireDate(employee.getHireDate());
                updatedEmp.setAnnualSalary(employee.getAnnualSalary());

               return employeeRepository.save(updatedEmp);
    }

}
