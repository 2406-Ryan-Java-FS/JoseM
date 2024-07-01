package ems.employeemanagementsystem.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * This class represents an employee entity.
 * Each employee is associated with an account.
 * The employee entity includes details such as first name, last name, hire date, and annual salary.
 *
 * @autor josemarin
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long employeeId;
    private String firstName;
    private String lastName;
    private LocalDate hireDate;
    private double annualSalary;

    @ManyToOne
    @JoinColumn(name = "account_id", nullable = false)
    @JsonBackReference
    private Account account;

    /**
     * Constructs a new Employee with the specified first name, last name, hire date, and annual salary.
     * This constructor does not set the associated account.
     *
     * @param firstName The first name of the employee.
     * @param lastName The last name of the employee.
     * @param hireDate The hire date of the employee.
     * @param annualSalary The annual salary of the employee.
     */
    public Employee(String firstName, String lastName, LocalDate hireDate, double annualSalary){
        this.firstName = firstName;
        this.lastName = lastName;
        this.hireDate = hireDate;
        this.annualSalary = annualSalary;
    }
}
