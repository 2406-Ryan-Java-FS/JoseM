package ems.employeemanagementsystem.service;

import ems.employeemanagementsystem.entity.Account;
import ems.employeemanagementsystem.entity.Employee;
import ems.employeemanagementsystem.exception.AccountNotFoundException;
import ems.employeemanagementsystem.exception.EmployeeNotFoundException;
import ems.employeemanagementsystem.exception.UnauthorizedException;
import ems.employeemanagementsystem.repository.AccountRepository;
import ems.employeemanagementsystem.repository.EmployeeRepository;
import ems.employeemanagementsystem.role.Role;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.jdbc.SqlConfig;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @Mock
    private AccountRepository accountRepository;

    @InjectMocks
    private EmployeeService employeeService;

    private Account userAccount;
    private Account adminAccount;
    private Employee employee;

    @BeforeEach
    void setUp() {
        userAccount = new Account("user@example.com", "password", Role.ROLE_USER, null);
        adminAccount = new Account("admin@example.com", "password", Role.ROLE_ADMIN, null);
        employee = new Employee();
        employee.setEmployeeId(1L);
        employee.setFirstName("John");
        employee.setLastName("Doe");
        employee.setHireDate(LocalDate.now());
        employee.setAnnualSalary(50000.0);
    }

    @Test
    void getAllEmployeesShouldThrowUnauthorizedExceptionWhenAccountIsNotAdmin() {
        Long accountId = 1L;

        when(accountRepository.findById(accountId)).thenReturn(Optional.of(userAccount));

        assertThrows(UnauthorizedException.class, () -> employeeService.getAllEmployees(accountId));
    }

    @Test
    void getAllEmployeesShouldReturnAllEmployeesWhenAccountIsAdmin() {
        Long accountId = 1L;

        when(accountRepository.findById(accountId)).thenReturn(Optional.of(adminAccount));
        when(employeeRepository.findAll()).thenReturn(List.of(employee));

        List<Employee> employees = employeeService.getAllEmployees(accountId);

        assertNotNull(employees);
        assertFalse(employees.isEmpty());
    }

    @Test
    void addEmployeeShouldThrowAccountNotFoundExceptionWhenAccountDoesNotExist() {
        Long accountId = 1L;

        when(accountRepository.findById(accountId)).thenReturn(Optional.empty());

        assertThrows(AccountNotFoundException.class, () -> employeeService.addEmployee(employee, accountId));
    }

    @Test
    void addEmployeeShouldSaveEmployeeWhenAccountExists() {
        Long accountId = 1L;

        when(accountRepository.findById(accountId)).thenReturn(Optional.of(userAccount));
        when(employeeRepository.save(employee)).thenReturn(employee);

        Employee savedEmployee = employeeService.addEmployee(employee, accountId);

        assertNotNull(savedEmployee);
        verify(employeeRepository).save(employee);
    }

    @Test
    void getEmployeeShouldThrowEmployeeNotFoundExceptionWhenEmployeeDoesNotExist() {
        Long employeeId = 1L;

        when(employeeRepository.findById(employeeId)).thenReturn(Optional.empty());

        assertThrows(EmployeeNotFoundException.class, () -> employeeService.getEmployee(employeeId));
    }

    @Test
    void getEmployeeShouldReturnEmployeeWhenEmployeeExists() {
        Long employeeId = 1L;

        when(employeeRepository.findById(employeeId)).thenReturn(Optional.of(employee));

        Employee foundEmployee = employeeService.getEmployee(employeeId);

        assertNotNull(foundEmployee);
    }
}
