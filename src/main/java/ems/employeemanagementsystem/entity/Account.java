package ems.employeemanagementsystem.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import ems.employeemanagementsystem.role.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
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
    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Employee> employees = new ArrayList<>();

    public Account(String email, String password, Role role, List<Employee> employees){
        this.email = email;
        this.password = password;
        this.role = role;
        this.employees = employees != null ? employees : new ArrayList<>();;
    }

}
