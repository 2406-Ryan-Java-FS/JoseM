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
 * @author josemarin
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
    public Employee(String firstName, String lastName, LocalDate hireDate, double annualSalary){
        this.firstName = firstName;
        this.lastName = lastName;
        this.hireDate = hireDate;
        this.annualSalary = annualSalary;
    }
}
