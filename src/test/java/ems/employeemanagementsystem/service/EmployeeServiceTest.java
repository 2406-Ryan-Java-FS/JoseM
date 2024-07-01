package ems.employeemanagementsystem.service;

import ems.employeemanagementsystem.entity.Account;
import ems.employeemanagementsystem.entity.Employee;
import ems.employeemanagementsystem.repository.AccountRepository;
import ems.employeemanagementsystem.repository.EmployeeRepository;
import ems.employeemanagementsystem.role.Role;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.jdbc.SqlConfig;

import java.time.LocalDate;

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
}
