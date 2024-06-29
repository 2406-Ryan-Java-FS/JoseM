package ems.employeemanagementsystem.entity;

import ems.employeemanagementsystem.role.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private String password;
    private Role role;
    @OneToMany(mappedBy = "account")
    private List<Employee> employees;

    public Account(String email, String password, List<Employee> employees){
        this.email = email;
        this.password = password;
        this.employees = employees;
    }


}
