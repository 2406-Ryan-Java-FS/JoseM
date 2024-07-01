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


/**
 * Service class for managing employee related operations.
 */
@AllArgsConstructor
@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final AccountRepository accountRepository;

    /**
     * Retrieves all employees, but only if the requesting account has admin privileges.
     *
     * @param accountId The ID of the account making the request.
     * @return A list of all employees.
     * @throws AccountNotFoundException if the account with the given ID is not found.
     * @throws UnauthorizedException if the account does not have admin privileges.
     */
    public List<Employee> getAllEmployees(Long accountId){

        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new AccountNotFoundException("Account with id " + accountId + " not found."));

        if (account.getRole() != Role.ROLE_ADMIN) {
            throw new UnauthorizedException("Access denied. Admin role required.");
        }
        return employeeRepository.findAll();
    }


    /**
     * Adds a new employee to the specified account.
     *
     * @param employee The employee to be added.
     * @param accountId The ID of the account to which the employee will be added.
     * @return The added Employee.
     * @throws AccountNotFoundException if the account with the given ID is not found.
     */
    public Employee addEmployee(Employee employee, Long accountId){

        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new AccountNotFoundException("Account with id " + accountId + " not found." ));

        employee.setAccount(account);
        account.getEmployees().add(employee);
        return employeeRepository.save(employee);
    }

    /**
     * Retrieves an employee by ID.
     *
     * @param id The ID of the employee to retrieve.
     * @return The found Employee.
     * @throws EmployeeNotFoundException if the employee with the given ID is not found.
     */
    public Employee getEmployee(Long id){

        return employeeRepository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException("Employee with id " + id + " not found."));
    }

    /**
     * Deletes an employee by ID.
     *
     * @param id The ID of the employee to delete.
     * @return The deleted Employee.
     * @throws EmployeeNotFoundException if the employee with the given ID is not found.
     */
    public Employee deleteEmployee(Long id){

        Employee employee = employeeRepository.findById(id)
                .orElseThrow( () -> new EmployeeNotFoundException("Employee with id " + id + " does not exists."));

        employeeRepository.deleteById(id);

        return employee;
    }

    /**
     * Updates an existing employee's information.
     *
     * @param employee The employee with updated information.
     * @return The updated Employee.
     * @throws EmployeeNotFoundException if the employee with the given ID is not found.
     */
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
