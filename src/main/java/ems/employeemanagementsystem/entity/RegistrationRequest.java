package ems.employeemanagementsystem.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * This class represents a registration request.
 * It includes the necessary details for registering a new account such as email and password.
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RegistrationRequest {

    private String email;

    private String password;
}
